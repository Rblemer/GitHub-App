package br.com.githubapp.ui.user.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import br.com.githubapp.BaseFragment
import br.com.githubapp.R
import br.com.githubapp.databinding.FragmentUsersListBinding
import br.com.githubapp.domain.UserViewModel
import br.com.githubapp.hide
import br.com.githubapp.show
import br.com.githubapp.ui.GridLayoutItemDecoration
import br.com.githubapp.ui.models.UIState
import br.com.githubapp.ui.models.UserOnListUIModel
import br.com.githubapp.ui.user.detail.UserDetailFragment
import java.net.UnknownHostException

open class UsersListFragment : BaseFragment() {

    protected lateinit var binding: FragmentUsersListBinding
    protected val userViewModel: UserViewModel by viewModels()
    protected lateinit var userListAdapter: UserListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersListBinding.inflate(inflater)

        setupUserRecyclerView()
        binding.fabSearch.show()
        binding.fabSearch.setOnClickListener {
            findNavController().navigate(R.id.action_usersListFragment_to_searchUserDialogFragment)
        }

        loadData()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    open fun setupObservers() {
        userViewModel.usersList.observe(viewLifecycleOwner) {
            when (it) {
                is UIState.Loading -> onLoading()
                is UIState.Error -> onError(it.cause)
                is UIState.Success -> onSuccess(it.data)
            }
        }
    }

    open fun onSuccess(data: List<UserOnListUIModel>) {
        userListAdapter.setUserList(data)
        binding.errorLayout.root.hide()
        binding.loadingLayout.hide()
        binding.usersList.show()
    }

    open fun onLoading() {
        binding.errorLayout.root.hide()
        binding.loadingLayout.show()
        binding.usersList.hide()
    }

    open fun onError(cause: Throwable?) {
        if (cause is UnknownHostException){
            binding.errorLayout.errorText.text = getString(R.string.feedback_no_internet_connection)
        } else {
            binding.errorLayout.errorText.text = getString(R.string.feedback_generic_error)
        }
        binding.errorLayout.root.show()
        binding.errorLayout.root.setOnClickListener {
            loadData()
        }
        binding.loadingLayout.hide()
        binding.usersList.hide()
    }

    open fun loadData() {
        userViewModel.loadUserList()
    }

    private fun setupUserRecyclerView() {
        userListAdapter = UserListAdapter { username ->
            onUserListItemClicked(username)
        }
        val layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        val itemDecoration = GridLayoutItemDecoration(
            16,
            16,
            16,
            16,
        )

        binding.usersList.layoutManager = layoutManager
        binding.usersList.adapter = userListAdapter
        binding.usersList.addItemDecoration(itemDecoration)

    }

    open fun onUserListItemClicked(username: String) {
        val bundle = bundleOf(
            UserDetailFragment.USERNAME to username,
        )
        view?.findNavController()?.navigate(R.id.action_usersListFragment_to_userDetailFragment, bundle)
    }


}