package br.com.githubapp.domain

import br.com.githubapp.ui.models.UserUIModel
import kotlinx.coroutines.flow.Flow

interface IGetUserDetailUseCase {
    suspend fun execute(username: String) : Flow<UserUIModel>
}