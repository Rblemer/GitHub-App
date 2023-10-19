package br.com.githubapp.data.remote.github_repos

import br.com.githubapp.ui.models.RepositoryOnListUIModel
import kotlinx.coroutines.flow.Flow

interface IRepositoryRemoteDataSource {
    suspend fun getUserRepositoriesList(username: String) : Flow<List<RepositoryOnListUIModel>>
}