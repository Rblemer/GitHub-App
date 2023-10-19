package br.com.githubapp.data.remote.user

import br.com.githubapp.data.remote.GithubService
import br.com.githubapp.ui.models.UserOnListUIModel
import br.com.githubapp.ui.models.UserUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val githubService: GithubService
) : IUserRemoteDataSource {
    override suspend fun getUserList(): Flow<List<UserOnListUIModel>> = flow {
        val listInUIModel = githubService.getUserList().map {
            it.toUIModel()
        }
        emit(listInUIModel)
    }

    override suspend fun getUserDetail(username: String): Flow<UserUIModel> = flow {
        val userInUIModel = githubService.getUserDetail(username).toUIModel()
        emit(userInUIModel)
    }

}