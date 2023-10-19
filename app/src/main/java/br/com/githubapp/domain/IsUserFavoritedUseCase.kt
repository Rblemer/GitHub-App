package br.com.githubapp.domain

import br.com.githubapp.data.IUserRepository
import javax.inject.Inject

class IsUserFavoritedUseCase @Inject constructor(
    private val userRepository: IUserRepository
): IIsUserFavoritedUseCase{
    override suspend fun execute(id: Int) = userRepository.isUserFavorited(id)
}