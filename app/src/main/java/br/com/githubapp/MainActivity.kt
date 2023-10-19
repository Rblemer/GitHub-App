package br.com.githubapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import br.com.githubapp.databinding.ActivityMainBinding
import br.com.githubapp.ui.personal.InsertPersonalUsernameFragment
import br.com.githubapp.ui.personal.PersonalDetailFragment
import br.com.githubapp.ui.user.FavoriteListFragment
import br.com.githubapp.ui.user.list.UsersListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = LayoutInflater.from(this)
        binding = ActivityMainBinding.inflate(inflater)
        setContentView(binding.root)

        setBottomNavigationListeners()
    }

    private fun setBottomNavigationListeners() {
        binding.bnvMainActivity.setOnItemSelectedListener {menuItem ->
            when(menuItem.itemId){
                R.id.item_users -> {
                    findNavController(binding.navHostFragment.id).navigate(R.id.go_to_usersListFragment)
                    true
                }
                R.id.item_favorite -> {
                    findNavController(binding.navHostFragment.id).navigate(R.id.go_to_favoriteUsersListFragment)
                    true
                }
                R.id.item_me ->{
                    findNavController(binding.navHostFragment.id).navigate(R.id.go_to_personalInfo)
                    true
                }
                else -> {
                    false
                }

            }
        }
    }

    override fun onBackPressed() {
        val navHostFragment: NavHostFragment? = supportFragmentManager.findFragmentById(binding.navHostFragment.id) as? NavHostFragment
        val fragment = navHostFragment!!.childFragmentManager.fragments[0]

        val navController = findNavController(binding.navHostFragment.id)

        when(fragment){
            is PersonalDetailFragment -> {
                navController.clearBackStack(R.id.personalDetailFragment)
                navController.navigate(R.id.go_to_usersListFragment)
                binding.bnvMainActivity.selectedItemId = R.id.item_users
            }
            is InsertPersonalUsernameFragment -> {
                navController.clearBackStack(R.id.insertPersonalUsernameFragment)
                navController.navigate(R.id.go_to_usersListFragment)
                binding.bnvMainActivity.selectedItemId = R.id.item_users
            }
            is FavoriteListFragment -> {
                navController.clearBackStack(R.id.favoriteListFragment)
                navController.navigate(R.id.go_to_usersListFragment)
                binding.bnvMainActivity.selectedItemId = R.id.item_users
            }
            is UsersListFragment -> {
                this.finish()
            }
            else -> {
                super.onBackPressed()
            }

        }
    }
}