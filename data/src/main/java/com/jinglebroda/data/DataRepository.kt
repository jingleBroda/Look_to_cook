package com.jinglebroda.data

import com.jinglebroda.data.retrofit2.searchRecipe.SearchRecipeService
import com.jinglebroda.data.retrofit2.translateWord.TranslateWordService
import com.jinglebroda.domain.DomainRepository
import com.jinglebroda.domain.model.searchRecipeModel.Hits
import com.jinglebroda.domain.model.searchRecipeModel.advancedSearchGetParams.AdvancedSearchGetParams
import com.jinglebroda.domain.model.translateWordModel.TranslateWordResponse
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class DataRepository @Inject constructor(
   private val searchRecipeService: SearchRecipeService,
   private val translateWordService: TranslateWordService
):DomainRepository() {
    override suspend fun apiDefaultSearch(getParams: AdvancedSearchGetParams): Deferred<Hits> =
        searchRecipeService.searchRecipe(
            getParams.ingredientsString,
            getParams.caloriesRange,
            getParams.diet?.toTypedArray()
        )

    override suspend fun apiTranslateWord(
        word: String,
        langPair: String
    ): Deferred<TranslateWordResponse> =
        translateWordService.translate(
            word,
            langPair
        )
}