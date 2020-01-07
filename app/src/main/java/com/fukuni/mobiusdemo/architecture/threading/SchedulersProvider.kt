package com.fukuni.mobiusdemo.architecture.threading

import io.reactivex.Scheduler

interface SchedulersProvider {

    val io: Scheduler
    val ui: Scheduler
    val computation: Scheduler
}