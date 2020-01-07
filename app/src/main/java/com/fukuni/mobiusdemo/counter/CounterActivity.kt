package com.fukuni.mobiusdemo.counter

import android.widget.Toast
import com.fukuni.mobiusdemo.R
import com.fukuni.mobiusdemo.architecture.BaseActivity
import com.fukuni.mobiusdemo.architecture.ViewRenderer
import com.fukuni.mobiusdemo.architecture.threading.DefaultSchedulersProvider
import com.fukuni.mobiusdemo.counter.effecthandlers.CounterEffectHandler
import com.fukuni.mobiusdemo.counter.effecthandlers.CounterViewActions
import com.spotify.mobius.Next
import io.reactivex.ObservableTransformer
import kotlinx.android.synthetic.main.activity_counter.*

class CounterActivity: BaseActivity<CounterModel, CounterEvent, CounterEffect>(),
    CounterViewActions, CounterView
{

    override fun layoutRes(): Int {
        return R.layout.activity_counter
    }

    override fun setup() {
        incrementButton.setOnClickListener { eventSource.notifyEvent(IncrementalCounterEvent)}
        decrementButton.setOnClickListener { eventSource.notifyEvent(DecrementalCounterEvent)}
        multiplyBy5Button.setOnClickListener { eventSource.notifyEvent(MultiplyByEvent(5))}

    }

    override fun initialModel(): CounterModel =
        CounterModel.ZERO

    override fun updateFunction(model: CounterModel, event: CounterEvent): Next<CounterModel, CounterEffect> =
        CounterLogic.update(model, event)

    override fun viewRenderer(): ViewRenderer<CounterModel, *> =
        CounterViewRenderer(this)

    override fun effectHandler(): ObservableTransformer<CounterEffect, CounterEvent> =
        CounterEffectHandler.createEffectHandler(this, DefaultSchedulersProvider())

    override fun setCounter(value: Int) {
        counterTextView.text = value.toString()
    }

    override fun showMultipleOf3Toast() {
        Toast.makeText(this, "That's a multiple of 3", Toast.LENGTH_SHORT).show()

    }
}