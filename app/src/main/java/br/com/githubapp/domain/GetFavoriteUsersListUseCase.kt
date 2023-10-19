package br.com.githubapp.domain

import br.com.githubapp.data.IUserRepository
import br.com.githubapp.ui.models.UserOnListUIModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteUsersListUseCase @Inject constructor(
    private val userRepository: IUserRepository
): IGetFavoriteUsersListUseCase{

    override suspend fun execute(): Flow<List<UserOnListUIModel>> = userRepository.getFavoriteUsers()
}