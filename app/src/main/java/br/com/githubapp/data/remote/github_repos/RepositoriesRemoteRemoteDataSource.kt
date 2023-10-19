package br.com.githubapp.data.remote.github_repos

import br.com.githubapp.data.remote.GithubService
import br.com.githubapp.ui.models.RepositoryOnListUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoriesRemoteRemoteDataSource @Inject constructor(
    private val githubService: GithubService
) : IRepositoryRemoteDataSource {

    override suspend fun getUserRepositoriesList(username: String): Flow<List<RepositoryOnListUIModel>> = flow {
        val listInUIModel = githubService.getUserRepos(username).map { it.toUIModel() }
        emit(listInUIModel)
    }

}