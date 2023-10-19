package br.com.githubapp.data

import br.com.githubapp.ui.models.RepositoryOnListUIModel
import kotlinx.coroutines.flow.Flow

interface IGithubReposRepository {
    suspend fun getUserRepositoriesList(username: String) : Flow<List<RepositoryOnListUIModel>>
}