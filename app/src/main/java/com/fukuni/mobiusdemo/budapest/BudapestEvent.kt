package com.fukuni.mobiusdemo.budapest

sealed class BudapestEvent
data class EnterNameEvent(val name: String): BudapestEvent()
object DeleteNameEvent : BudapestEvent()