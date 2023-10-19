package br.com.githubapp.ui.user

import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import br.com.githubapp.R
import br.com.githubapp.hide
import br.com.githubapp.show
import br.com.githubapp.ui.models.UIState
import br.com.githubapp.ui.models.UserOnListUIModel
import br.com.githubapp.ui.user.detail.UserDetailFragment
import br.com.githubapp.ui.user.list.UsersListFragment

class FavoriteListFragment() : UsersListFragment() {

    override fun setupObservers() {
        userViewModel.favoriteUsersList.observe(viewLifecycleOwner) {
            when (it) {
                is UIState.Loading -> onLoading()
                is UIState.Error -> onError(it.cause)
                is UIState.Success -> onSuccess(it.data)
            }
        }
    }

    override fun loadData() {
        userViewModel.loadFavoriteUserList()
    }

    override fun onUserListItemClicked(username: String) {
        val bundle = bundleOf(
            UserDetailFragment.USERNAME to username,
        )
        view?.findNavController()?.navigate(R.id.action_favoriteListFragment_to_userDetailFragment, bundle)
    }

    override fun onSuccess(data: List<UserOnListUIModel>) {
        if (data.isEmpty()){
            binding.emptyLayout.errorText.text = getString(R.string.feedback_empty_response_favorite)
            binding.emptyLayout.root.show()
            binding.usersList.hide()
            binding.loadingLayout.hide()
            binding.errorLayout.root.hide()

        } else {
            userListAdapter.setUserList(data)
            binding.errorLayout.root.hide()
            binding.emptyLayout.root.hide()
            binding.loadingLayout.hide()
            binding.usersList.show()
        }
    }

    override fun onLoading() {
        binding.errorLayout.root.hide()
        binding.emptyLayout.root.hide()
        binding.loadingLayout.show()
        binding.usersList.hide()
    }

    override fun onError(cause: Throwable?) {
        binding.errorLayout.errorText.text = getString(R.string.feedback_generic_error)

        binding.errorLayout.root.show()
        binding.emptyLayout.root.hide()
        binding.loadingLayout.hide()
        binding.usersList.hide()
    }


}