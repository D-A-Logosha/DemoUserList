package com.example.demouserlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.lifecycle.LifecycleOwner
import com.example.demouserlist.data.User
import com.example.demouserlist.databinding.ActivityMainBinding
import com.example.demouserlist.fragments.UserEditFragment

class MainActivity : AppCompatActivity(), Navigator {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun showFragmentUserEdit(user: User) {
        supportFragmentManager.commit {
            setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)
            replace(
                R.id.fragmentContainer,
                UserEditFragment.newInstance(user),
                UserEditFragment.FRAGMENT_USER_EGIT_TAG
            )
            addToBackStack(UserEditFragment.FRAGMENT_USER_EGIT_TAG)
        }
    }

    override fun setResultFragmentUserEdit(user: User) {
        supportFragmentManager.setFragmentResult(
            Navigator.KEY_EDIT_USER,
            bundleOf(Navigator.KEY_ARG_USER to user)
        )
    }

    override fun setResultListenerFragmentUserEdit(
        lifecycleOwner: LifecycleOwner,
        listener: (User) -> Unit,
    ) {
        supportFragmentManager.setFragmentResultListener(
            Navigator.KEY_EDIT_USER,
            lifecycleOwner
        ) { key, bundle ->
            listener.invoke(bundle.parcelable(Navigator.KEY_ARG_USER)!!)
            supportFragmentManager.clearFragmentResult(key)
        }
    }

    override fun backFragmentUserList() {
        supportFragmentManager.popBackStack()
    }
}