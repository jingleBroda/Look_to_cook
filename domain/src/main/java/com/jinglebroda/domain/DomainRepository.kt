package com.jinglebroda.domain

import com.jinglebroda.domain.model.searchRecipeModel.Hits
import com.jinglebroda.domain.model.searchRecipeModel.advancedSearchGetParams.AdvancedSearchGetParams
import com.jinglebroda.domain.model.translateWordModel.TranslateWordResponse
import kotlinx.coroutines.Deferred

abstract class DomainRepository {
    abstract suspend fun apiDefaultSearch(
        getParams: AdvancedSearchGetParams
    ):Deferred<Hits>

    abstract suspend fun apiTranslateWord(
        word:String,
        langPair:String
    ):Deferred<TranslateWordResponse>
}