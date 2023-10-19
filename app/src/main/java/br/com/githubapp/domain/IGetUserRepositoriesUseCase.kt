package br.com.githubapp.domain

import br.com.githubapp.ui.models.RepositoryOnListUIModel
import kotlinx.coroutines.flow.Flow

interface IGetUserRepositoriesUseCase {
    suspend fun execute(username: String) : Flow<List<RepositoryOnListUIModel>>
}