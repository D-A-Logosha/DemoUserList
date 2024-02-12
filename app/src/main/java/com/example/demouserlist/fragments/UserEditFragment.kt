package com.example.demouserlist.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.demouserlist.data.User
import com.example.demouserlist.databinding.FragmentUserEditBinding
import com.example.demouserlist.navigator
import com.example.demouserlist.parcelable

class UserEditFragment : Fragment() {

    private lateinit var binding: FragmentUserEditBinding

    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentUserEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        user = savedInstanceState?.parcelable(KEY_USER) ?: arguments?.parcelable(ARG_USER)!!
        with(binding) {
            ietFirstName.setText(user.firstName)
            ietLastName.setText(user.lastName)
            ietPhoneNumber.setText(user.phoneNumber)
            ivUserImage.setImageResource(user.image)

            btnCancel.setOnClickListener {
                hideKeyboard(view)
                navigator().backFragmentUserList()
            }

            btnApply.setOnClickListener {
                hideKeyboard(view)
                navigator().setResultFragmentUserEdit(saveUser())
                navigator().backFragmentUserList()
            }

            ivUserImage.setOnClickListener {
                AvatarsDialogFragment.show(parentFragmentManager) //childFragmentManager
            }

            AvatarsDialogFragment.setupListener(parentFragmentManager, viewLifecycleOwner) {
                user = user.copy(image = it)
                ivUserImage.setImageResource(it)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_USER, saveUser())
    }

    private fun saveUser() = User(
        id = user.id,
        firstName = binding.ietFirstName.text.toString(),
        lastName = binding.ietLastName.text.toString(),
        phoneNumber = binding.ietPhoneNumber.text.toString(),
        image = user.image
    )

    private fun hideKeyboard(view: View) {
        getInputMethodManager(view).hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun getInputMethodManager(view: View): InputMethodManager {
        val context = view.context
        return context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }


    companion object {
        const val FRAGMENT_USER_EGIT_TAG = "FRAGMENT_USER_EGIT_TAG"

        @JvmStatic
        private val ARG_USER = "ARG_USER"

        @JvmStatic
        private val KEY_USER = "KEY_USER"

        @JvmStatic
        fun newInstance(user: User): UserEditFragment {
            return UserEditFragment().apply {
                arguments = bundleOf(ARG_USER to user)
            }
        }
    }
}