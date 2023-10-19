package br.com.githubapp.ui.personal

import android.content.Context
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import br.com.githubapp.R
import br.com.githubapp.domain.UserViewModel
import br.com.githubapp.hide
import br.com.githubapp.show
import br.com.githubapp.ui.models.UIState
import br.com.githubapp.ui.models.UserUIModel
import br.com.githubapp.ui.repository.list.RepositoryListFragment
import br.com.githubapp.ui.user.detail.UserDetailFragment
import retrofit2.HttpException
import java.net.UnknownHostException

class PersonalDetailFragment : UserDetailFragment() {


    override fun setupObservers() {
        userViewModel.user.observe(viewLifecycleOwner){
            when(it){
                is UIState.Error -> onError(it.cause)
                is UIState.Loading -> onLoading()
                is UIState.Success -> onSuccess(it.data)
            }
        }

        repositoriesViewModel.repositoriesList.observe(viewLifecycleOwner){
            when(it){
                is UIState.Error -> onReposError()
                is UIState.Loading -> {}
                is UIState.Success -> onReposSuccess(it.data)
            }
        }

    }

    override fun onSuccess(data: UserUIModel) {
        super.onSuccess(data)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(SAVED_USER, username)
            apply()
        }
    }

    override fun onError(cause: Throwable?) {
        when {
            cause is HttpException && cause.code() == UserViewModel.RESULT_NOT_FOUND_CODE -> binding.errorLayout.errorText.text = getString(R.string.feedback_no_user_found)
            cause is UnknownHostException -> binding.errorLayout.errorText.text = getString(R.string.feedback_no_internet_connection)
            else -> {
                binding.errorLayout.root.hide()
                binding.loadingLayout.hide()
                binding.emptyLayout.root.show()
            }
        }
    }

    override fun goToRepositoryList() {
        val bundle = bundleOf(
            RepositoryListFragment.USERNAME to username,
        )
        findNavController().navigate(R.id.action_personalDetailFragment_to_repositoryListFragment, bundle)
    }


    companion object {
        const val SAVED_USER = "SAVED_USER"
    }
}