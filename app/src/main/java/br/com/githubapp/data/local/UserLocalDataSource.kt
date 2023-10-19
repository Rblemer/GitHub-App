package br.com.githubapp.data.local

import br.com.githubapp.ui.models.UserOnListUIModel
import br.com.githubapp.ui.models.UserUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(
    private val userLocalDAO: UserDao
) : IUserLocalDataSource {

    override suspend fun getFavoritesUsersList(): Flow<List<UserOnListUIModel>> = flow {
        val listInUIModel = userLocalDAO.getAll().map {
            it.toUIModel()
        }
        emit(listInUIModel)
    }

    override suspend fun addFavoriteUser(user: UserUIModel): Flow<Long> = flow {
        val result = userLocalDAO.addUser(user.toLocalModel())
        emit(result)
    }

    override suspend fun removeFavoriteUser(user: UserUIModel): Flow<Int> = flow {
        val result = userLocalDAO.deleteUser(user.toLocalModel())
        emit(result)
    }

    override suspend fun isUserFavorited(id: Int): Flow<Boolean> = flow {
        val result = userLocalDAO.getUserById(id)
        emit(result != null)
    }


}