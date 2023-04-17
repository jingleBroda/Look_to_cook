package com.jinglebroda.presentation.singleActivity.mvvm.utils.letterLanguage

object LetterRusAndEngLanguage {
    private val capsEngLetter = 'А'..'Z'
    private val capsRuLetter = 'А'..'Я'
    private val littleEngLetter = 'a'..'z'
    private val littleRuLetter = 'а'..'я'

    fun isEnglishLetter(ch: Char): Boolean = (ch in capsEngLetter) || (ch in littleEngLetter)
    fun isRussianLetter(ch: Char): Boolean = (ch in capsRuLetter) || (ch in littleRuLetter)
}