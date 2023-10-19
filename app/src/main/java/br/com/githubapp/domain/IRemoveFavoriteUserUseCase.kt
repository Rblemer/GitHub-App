package br.com.githubapp.domain

import br.com.githubapp.ui.models.UserUIModel
import kotlinx.coroutines.flow.Flow

interface IRemoveFavoriteUserUseCase {
    suspend fun execute(userUIModel: UserUIModel) : Flow<Int>
}