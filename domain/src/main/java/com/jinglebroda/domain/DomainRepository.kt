package com.jinglebroda.domain

import com.jinglebroda.domain.model.searchRecipeModel.Hits
import com.jinglebroda.domain.model.translateWordModel.TranslateWordResponse
import kotlinx.coroutines.Deferred

abstract class DomainRepository {
    abstract suspend fun apiSearch(nameRecipe:String):Deferred<Hits>//тут будут параметры поиска
    abstract suspend fun apiTranslateWord(
        word:String,
        langPair:String
    ):Deferred<TranslateWordResponse>
}