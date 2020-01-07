package com.fukuni.mobiusdemo.counter

import com.fukuni.mobiusdemo.architecture.ViewRenderer

class CounterViewRenderer(view: CounterView): ViewRenderer<CounterModel, CounterView>(view) {

    override fun render(model: CounterModel) {
        view.setCounter(model.counter)
    }
}