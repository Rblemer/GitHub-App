package br.com.githubapp.data.remote.user

import br.com.githubapp.ui.models.UserOnListUIModel
import br.com.githubapp.ui.models.UserUIModel
import kotlinx.coroutines.flow.Flow

interface IUserRemoteDataSource {
    suspend fun getUserList() : Flow<List<UserOnListUIModel>>
    suspend fun getUserDetail(username: String) : Flow<UserUIModel>
}