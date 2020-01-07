package com.fukuni.mobiusdemo.architecture

import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.spotify.mobius.*
import com.spotify.mobius.android.MobiusAndroid
import com.spotify.mobius.extras.Connectables
import com.spotify.mobius.functions.Consumer
import com.spotify.mobius.functions.Function
import com.spotify.mobius.rx2.RxMobius
import io.reactivex.ObservableTransformer

abstract class BaseActivity<M : Parcelable, E, F> : AppCompatActivity(), Connectable<M, E> {

    companion object{
        private const val KEY_MODEL = "model"

    }

    private lateinit var controller: MobiusLoop.Controller<M, E>

    private val loop by lazy(LazyThreadSafetyMode.NONE) {

        RxMobius.loop(
            { model: M, event: E -> updateFunction(model, event) },
            {effects -> effects.compose(effectHandler())}
        )
            .eventSource(eventSource)

    }

    protected val eventSource by lazy(LazyThreadSafetyMode.NONE) {
        DeferredEventSource<E>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes())
        controller = MobiusAndroid.controller(loop, resolveDefaultModel(savedInstanceState))
        controller.connect(Connectables.contramap(identity(), this))
        setup()
    }

    override fun onResume() {
        super.onResume()
        controller.start()
    }

    override fun onStop() {
        controller.stop()
        super.onStop()
    }

    override fun onDestroy() {
        controller.disconnect()
        super.onDestroy()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable(KEY_MODEL, controller.model)
    }


    override fun connect(output: Consumer<E>): Connection<M> {
        return object: Connection<M> {
            private val viewRenderer = viewRenderer()

            override fun accept(value: M) {
                viewRenderer.render(value)
            }

            override fun dispose() {
                //no-op
            }
        }
    }

    private fun identity(): Function<M, M> =
        Function { it }

    private fun resolveDefaultModel(savedInstanceState: Bundle?): M =
        savedInstanceState?.getParcelable(KEY_MODEL) ?: initialModel()

    @LayoutRes abstract fun layoutRes(): Int

    abstract fun setup()

    abstract fun initialModel(): M

    abstract fun updateFunction(
        model: M,
        event: E
    ): Next<M, F>

    abstract fun viewRenderer(): ViewRenderer<M, *>

    abstract fun effectHandler(): ObservableTransformer<F, E>
}

