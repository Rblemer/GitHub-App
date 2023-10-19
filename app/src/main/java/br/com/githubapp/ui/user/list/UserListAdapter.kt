package br.com.githubapp.ui.user.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.githubapp.databinding.UserListItemBinding
import br.com.githubapp.ui.models.UserOnListUIModel

class UserListAdapter(private val onItemClicked: (String) -> Unit): RecyclerView.Adapter<UserListViewHolder>() {

    private val userList = mutableListOf<UserOnListUIModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = UserListItemBinding.inflate(inflater)

        return UserListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val user = userList[position]

        holder.bind(user, onItemClicked)
    }

    override fun getItemCount() = userList.size

    fun setUserList(userList: List<UserOnListUIModel>){
        this.userList.addAll(userList)
        notifyDataSetChanged()
    }
}