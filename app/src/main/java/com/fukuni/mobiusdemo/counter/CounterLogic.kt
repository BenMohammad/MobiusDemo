package com.fukuni.mobiusdemo.counter

import com.spotify.mobius.First
import com.spotify.mobius.Init
import com.spotify.mobius.Next
import com.spotify.mobius.Next.next

object CounterLogic {

    internal fun init(): Init<CounterModel, CounterEffect> =
        Init{ First.first(it)}

    fun update(model: CounterModel, event: CounterEvent): Next<CounterModel, CounterEffect> {
        val updatedModel = when (event) {
            IncrementalCounterEvent -> model.increment()
            DecrementalCounterEvent -> model.decrement()
            is MultiplyByEvent -> model.multiplyBy(event.multiplier)
        }

        return if (updatedModel.counter % 3 == 0) {
            next(updatedModel, setOf(ShowMultipleOf3Toast))
        } else {
            next(updatedModel)
        }

    }

}