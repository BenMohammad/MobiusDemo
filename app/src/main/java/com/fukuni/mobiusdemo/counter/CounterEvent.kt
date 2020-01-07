package com.fukuni.mobiusdemo.counter

sealed class CounterEvent

object IncrementalCounterEvent: CounterEvent()
object DecrementalCounterEvent: CounterEvent()
data class MultiplyByEvent(val multiplier: Int): CounterEvent()