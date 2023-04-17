package com.jinglebroda.domain.usecase

import com.jinglebroda.domain.DomainRepository
import com.jinglebroda.domain.model.translateWordModel.TranslateWordResponse
import kotlinx.coroutines.Deferred

class TranslateWordUseCase(private val repository:DomainRepository) {
    suspend operator fun invoke(
        word:String,
        langPair:String
    ): Deferred<TranslateWordResponse> =
        repository.apiTranslateWord(word, langPair)
}