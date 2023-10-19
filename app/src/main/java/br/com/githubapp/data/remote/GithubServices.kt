package br.com.githubapp.data.remote

import br.com.githubapp.data.remote.model.RepositoryOnListRemoteModel
import br.com.githubapp.data.remote.model.UserOnListRemoteModel
import br.com.githubapp.data.remote.model.UserRemoteModel
import retrofit2.http.GET
import retrofit2.http.Path


interface GithubService {
    @GET("users")
    suspend fun getUserList(): List<UserOnListRemoteModel>

    @GET("users/{user}")
    suspend fun getUserDetail(@Path("user") user: String): UserRemoteModel

    @GET("users/{user}/repos")
    suspend fun getUserRepos(@Path("user") user: String?): List<RepositoryOnListRemoteModel>
}