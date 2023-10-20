package br.com.githubapp

import br.com.githubapp.data.UserRepository
import br.com.githubapp.data.local.IUserLocalDataSource
import br.com.githubapp.data.remote.user.IUserRemoteDataSource
import br.com.githubapp.ui.models.UserOnListUIModel
import br.com.githubapp.ui.models.UserUIModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class UserRepositoryTest {

    @Mock
    private lateinit var remoteDataSource: IUserRemoteDataSource

    @Mock
    private lateinit var localDataSource: IUserLocalDataSource

    private lateinit var userRepository: UserRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        userRepository = UserRepository(remoteDataSource, localDataSource)
    }

    @Test
    fun `getUserList should return flow of user list`() = runTest {
        // Arrange
        val userList = listOf(
            UserOnListUIModel(
                "",
                0,
                "",
                ""
            )
        )
        val flow = flow { emit(userList) }
        Mockito.`when`(remoteDataSource.getUserList()).thenReturn(flow)

        // Act
        val result = userRepository.getUserList().toList()

        // Assert
        assertEquals(1, result.size)
        assertEquals(userList, result[0])
        Mockito.verify(remoteDataSource).getUserList()
    }

    @Test
    fun `getUserDetail should return flow of user detail`() = runTest {
        // Arrange
        val username = "Filipe"
        val expectedUser = UserUIModel(
            "",
            "",
            "",
            "",
            "",
            "",
            0,
            0,
            false,
            1,
            "",
            username,
            "",
            0,
            false,
            "",
            "",
            "",
            ""
        )
        val flow = flow { emit(expectedUser) }
        Mockito.`when`(remoteDataSource.getUserDetail(username)).thenReturn(flow)

        // Act
        val result = userRepository.getUserDetail(username).toList()

        // Assert
        assertEquals(1, result.size)
        assertEquals(expectedUser.id, result[0].id)
        Mockito.verify(remoteDataSource).getUserDetail(username)
    }
}