package com.fukuni.mobiusdemo.architecture.effecthandlers

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer

class   <E>: ObservableTransformer<Nothing, E> {

    override fun apply(upstream: Observable<Nothing>): ObservableSource<E> =
        Observable.empty<E>()

}