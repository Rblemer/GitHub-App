package br.com.githubapp.ui.user.list

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.githubapp.R
import br.com.githubapp.databinding.UserListItemBinding
import br.com.githubapp.loadCircleImage
import br.com.githubapp.ui.models.UserOnListUIModel

class UserListViewHolder(private val binding: UserListItemBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(user: UserOnListUIModel, onItemClicked: (String) -> Unit){
        binding.tvName.text = user.login
        binding.ivPhoto.loadCircleImage(user.avatar_url, ContextCompat.getDrawable(binding.root.context, R.drawable.ic_personal_user))
        binding.tvUserType.text = user.type
        binding.root.setOnClickListener {
            onItemClicked(user.login)
        }
    }

}