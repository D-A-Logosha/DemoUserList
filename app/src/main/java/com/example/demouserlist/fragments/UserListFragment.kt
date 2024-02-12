package com.example.demouserlist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.demouserlist.data.User
import com.example.demouserlist.databinding.FragmentUserListBinding
import com.example.demouserlist.models.UserListViewModel
import com.example.demouserlist.navigator

class UserListFragment : Fragment() {

    private lateinit var binding: FragmentUserListBinding

    private val viewModel: UserListViewModel by lazy {
        ViewModelProvider(this)[UserListViewModel::class.java]
    }

    private val adapter: UsersRvAdapter by lazy {
        UsersRvAdapter(object : UserActionListener {
            override fun selectUser(user: User) {
                navigator().showFragmentUserEdit(user)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvUserList.adapter = adapter

        viewModel.users.observe(viewLifecycleOwner) {
            adapter.users = it
        }

        navigator().setResultListenerFragmentUserEdit(this) {
            viewModel.editUser(it)
        }
    }

    companion object {
        const val FRAGMENT_USER_LIST_TAG = "FRAGMENT_USER_LIST_TAG"
    }
}