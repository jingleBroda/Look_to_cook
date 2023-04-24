package com.jinglebroda.domain.usecase

import com.jinglebroda.domain.DomainRepository
import com.jinglebroda.domain.model.searchRecipeModel.Hits
import com.jinglebroda.domain.model.searchRecipeModel.advancedSearchGetParams.AdvancedSearchGetParams
import kotlinx.coroutines.Deferred

class ApiDefaultSearchUseCase(private val repository:DomainRepository) {
    suspend operator fun invoke(getParams: AdvancedSearchGetParams): Deferred<Hits> =
        repository.apiDefaultSearch(getParams)
}