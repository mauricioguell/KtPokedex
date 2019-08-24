package com.mguell.ktpokedex.presentation.backpack

interface BackpackView {
    fun filledBackpack()
    fun setLoadingBackpackBarVisibility(visibility: Int)
    fun emptyBackpack()
}