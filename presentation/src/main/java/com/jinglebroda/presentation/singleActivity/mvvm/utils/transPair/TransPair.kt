package com.jinglebroda.presentation.singleActivity.mvvm.utils.transPair

interface TransPair {
    val value:String

    class RuEnTransPair: TransPair {
        override val value: String = "ru|en"
    }
}