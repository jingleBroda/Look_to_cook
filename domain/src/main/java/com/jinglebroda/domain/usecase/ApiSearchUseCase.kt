package com.jinglebroda.domain.usecase

import com.jinglebroda.domain.DomainRepository
import com.jinglebroda.domain.model.searchRecipeModel.Hits
import kotlinx.coroutines.Deferred

class ApiSearchUseCase(private val repository:DomainRepository) {
    suspend operator fun invoke(nameRecipe:String): Deferred<Hits> = repository.apiSearch(nameRecipe) //тут будут параметры поиска
}