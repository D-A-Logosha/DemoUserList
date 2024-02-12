package com.example.demouserlist

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.example.demouserlist.data.User

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {

    fun showFragmentUserEdit(user: User)
    fun setResultFragmentUserEdit(user: User)
    fun setResultListenerFragmentUserEdit(lifecycleOwner: LifecycleOwner,listener: (User) -> Unit)
    fun backFragmentUserList()

    companion object {
        const val KEY_EDIT_USER = "KEY_EDIT_USER"
        const val KEY_ARG_USER = "KEY_ARG_USER"
    }
}