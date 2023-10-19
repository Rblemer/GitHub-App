package br.com.githubapp.domain

import br.com.githubapp.data.IUserRepository
import br.com.githubapp.ui.models.UserUIModel
import javax.inject.Inject

class RemoveFavoriteUserUseCase @Inject constructor(
    private val userRepository: IUserRepository
): IRemoveFavoriteUserUseCase{
    override suspend fun execute(userUIModel: UserUIModel) = userRepository.removeFavoriteUser(userUIModel)
}