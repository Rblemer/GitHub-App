package br.com.githubapp.domain

import br.com.githubapp.ui.models.UserOnListUIModel
import kotlinx.coroutines.flow.Flow

interface IGetFavoriteUsersListUseCase {
    suspend fun execute() : Flow<List<UserOnListUIModel>>
}