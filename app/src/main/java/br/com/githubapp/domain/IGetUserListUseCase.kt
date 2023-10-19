package br.com.githubapp.domain

import br.com.githubapp.ui.models.UserOnListUIModel
import kotlinx.coroutines.flow.Flow

interface IGetUserListUseCase {
    suspend fun execute() : Flow<List<UserOnListUIModel>>
}