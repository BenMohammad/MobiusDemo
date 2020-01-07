package com.fukuni.mobiusdemo.budapest

import com.fukuni.mobiusdemo.architecture.ViewRenderer

class BudapestViewRenderer(view: BudapestView): ViewRenderer<BudapestModel, BudapestView>(view) {

    override fun render(model: BudapestModel) {
        if(model == BudapestModel.STRANGER) {
            view.greetStranger()
        } else {
            view.greet(model.name)
        }
    }
}