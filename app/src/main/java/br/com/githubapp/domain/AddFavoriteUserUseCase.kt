package br.com.githubapp.domain

import br.com.githubapp.data.IUserRepository
import br.com.githubapp.ui.models.UserUIModel
import javax.inject.Inject

class AddFavoriteUserUseCase @Inject constructor(
    private val userRepository: IUserRepository
): IAddFavoriteUserUseCase{
    override suspend fun execute(userUIModel: UserUIModel) = userRepository.addFavoriteUser(userUIModel)
}