package br.com.githubapp.domain

import br.com.githubapp.data.IUserRepository
import br.com.githubapp.ui.models.UserUIModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(
    private val userRepository: IUserRepository
): IGetUserDetailUseCase{

    override suspend fun execute(username: String): Flow<UserUIModel> = userRepository.getUserDetail(username)
}