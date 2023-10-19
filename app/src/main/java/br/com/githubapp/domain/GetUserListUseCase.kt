package br.com.githubapp.domain

import br.com.githubapp.data.IUserRepository
import br.com.githubapp.ui.models.UserOnListUIModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val userRepository: IUserRepository
): IGetUserListUseCase{

    override suspend fun execute(): Flow<List<UserOnListUIModel>> = userRepository.getUserList()
}