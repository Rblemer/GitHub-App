package br.com.githubapp.domain

import kotlinx.coroutines.flow.Flow

interface IIsUserFavoritedUseCase {
    suspend fun execute(id: Int) : Flow<Boolean>
}