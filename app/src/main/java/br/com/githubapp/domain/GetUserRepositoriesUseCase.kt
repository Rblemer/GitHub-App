package br.com.githubapp.domain

import br.com.githubapp.data.IGithubReposRepository
import javax.inject.Inject

class GetUserRepositoriesUseCase @Inject constructor(
    private val githubReposRepository: IGithubReposRepository
): IGetUserRepositoriesUseCase{

    override suspend fun execute(username: String) = githubReposRepository.getUserRepositoriesList(username)
}