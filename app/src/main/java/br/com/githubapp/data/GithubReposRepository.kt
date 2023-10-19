package br.com.githubapp.data

import br.com.githubapp.data.remote.github_repos.IRepositoryRemoteDataSource
import br.com.githubapp.ui.models.RepositoryOnListUIModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubReposRepository @Inject constructor(
    private val remoteDataSource: IRepositoryRemoteDataSource
): IGithubReposRepository {

    override suspend fun getUserRepositoriesList(username: String): Flow<List<RepositoryOnListUIModel>> = remoteDataSource.getUserRepositoriesList(username)

}