package com.fukuni.mobiusdemo.counter.effecthandlers

import com.fukuni.mobiusdemo.architecture.threading.SchedulersProvider
import com.fukuni.mobiusdemo.counter.CounterEffect
import com.fukuni.mobiusdemo.counter.CounterEvent
import com.fukuni.mobiusdemo.counter.ShowMultipleOf3Toast
import com.spotify.mobius.rx2.RxMobius
import io.reactivex.ObservableTransformer

object CounterEffectHandler {

    fun createEffectHandler(
        viewActions: CounterViewActions,
        schedulersProvider: SchedulersProvider
    ): ObservableTransformer<CounterEffect, CounterEvent> {
        return RxMobius
            .subtypeEffectHandler<CounterEffect, CounterEvent>()
            .addAction(ShowMultipleOf3Toast::class.java, viewActions::showMultipleOf3Toast, schedulersProvider.ui)
            .build()
    }
}