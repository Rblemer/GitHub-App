package br.com.githubapp.data.local

import br.com.githubapp.ui.models.UserOnListUIModel
import br.com.githubapp.ui.models.UserUIModel
import kotlinx.coroutines.flow.Flow

interface IUserLocalDataSource {
    suspend fun getFavoritesUsersList() : Flow<List<UserOnListUIModel>>

    suspend fun addFavoriteUser(user: UserUIModel) : Flow<Long>

    suspend fun removeFavoriteUser(user: UserUIModel) : Flow<Int>
    suspend fun isUserFavorited(id: Int) : Flow<Boolean>
}