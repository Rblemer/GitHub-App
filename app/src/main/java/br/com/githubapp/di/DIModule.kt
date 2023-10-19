package br.com.githubapp.di

import android.content.Context
import androidx.room.Room
import br.com.githubapp.data.local.AppDatabase
import br.com.githubapp.data.local.UserDao
import br.com.githubapp.data.GithubReposRepository
import br.com.githubapp.data.remote.GithubService
import br.com.githubapp.data.IGithubReposRepository
import br.com.githubapp.data.remote.github_repos.IRepositoryRemoteDataSource
import br.com.githubapp.data.remote.user.IUserRemoteDataSource
import br.com.githubapp.data.IUserRepository
import br.com.githubapp.data.remote.github_repos.RepositoriesRemoteRemoteDataSource
import br.com.githubapp.data.remote.RetrofitConfig
import br.com.githubapp.data.remote.user.UserRemoteDataSource
import br.com.githubapp.data.UserRepository
import br.com.githubapp.data.local.IUserLocalDataSource
import br.com.githubapp.data.local.UserLocalDataSource
import br.com.githubapp.domain.AddFavoriteUserUseCase
import br.com.githubapp.domain.GetFavoriteUsersListUseCase
import br.com.githubapp.domain.GetUserListUseCase
import br.com.githubapp.domain.GetUserRepositoriesUseCase
import br.com.githubapp.domain.IAddFavoriteUserUseCase
import br.com.githubapp.domain.IGetFavoriteUsersListUseCase
import br.com.githubapp.domain.IGetUserListUseCase
import br.com.githubapp.domain.IGetUserRepositoriesUseCase
import br.com.githubapp.domain.IRemoveFavoriteUserUseCase
import br.com.githubapp.domain.RemoveFavoriteUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DIModule {
    @Provides
    @Singleton
    fun provideGithubService() : GithubService {
        return RetrofitConfig.service
    }

    @Provides
    @Singleton
    fun provideUserRemoteDataSource(githubService: GithubService) : IUserRemoteDataSource {
        return UserRemoteDataSource(githubService)
    }

    @Provides
    @Singleton
    fun provideUserLocalDataSource(userDao: UserDao) : IUserLocalDataSource {
        return UserLocalDataSource(userDao)
    }

    @Provides
    @Singleton
    fun provideUserRepository(userRemoteDataSource: IUserRemoteDataSource, userLocalDataSource: IUserLocalDataSource) : IUserRepository {
        return UserRepository(userRemoteDataSource, userLocalDataSource)
    }

    @Provides
    @Singleton
    fun provideGetUserListUseCase(userRepository: IUserRepository) : IGetUserListUseCase{
        return GetUserListUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideGetFavoriteUsersListUseCase(userRepository: IUserRepository) : IGetFavoriteUsersListUseCase {
        return GetFavoriteUsersListUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideAddFavoriteUserUseCase(userRepository: IUserRepository) : IAddFavoriteUserUseCase{
        return AddFavoriteUserUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideRemoveFavoriteUserUseCase(userRepository: IUserRepository) : IRemoveFavoriteUserUseCase {
        return RemoveFavoriteUserUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideRepositoryRemoteDataSource(githubService: GithubService): IRepositoryRemoteDataSource {
        return RepositoriesRemoteRemoteDataSource(githubService)
    }

    @Provides
    @Singleton
    fun provideGithubReposRepository(reposRemoteDataSource: IRepositoryRemoteDataSource) : IGithubReposRepository {
        return GithubReposRepository(reposRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetUserRepositoriesUseCase(githubReposRepository: IGithubReposRepository) : IGetUserRepositoriesUseCase{
        return GetUserRepositoriesUseCase(githubReposRepository)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) : AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase) : UserDao {
        return appDatabase.userDao()
    }

}