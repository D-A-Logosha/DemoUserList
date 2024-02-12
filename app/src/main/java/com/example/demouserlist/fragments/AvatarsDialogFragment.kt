package com.example.demouserlist.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import com.example.demouserlist.R
import com.example.demouserlist.databinding.DialogImageBinding

class AvatarsDialogFragment : DialogFragment() {

    private lateinit var adapter: AvatarsRvAdapter

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBinding = DialogImageBinding.inflate(layoutInflater)
        val dialogBuilder = AlertDialog.Builder(requireContext())

        val dialog = dialogBuilder.setTitle(getString(R.string.select_avatar))
            .setView(dialogBinding.root)
            .create()

        adapter = AvatarsRvAdapter((object : AvatarActionListener {
            override fun selectAvatar(image: Int) {
                parentFragmentManager.setFragmentResult(
                    KEY_IMAGE_RESPONSE, bundleOf(
                        ARG_IMAGE to image
                    )
                )
                dismiss()
            }
        }))
        adapter.images = avatarsRepository
        dialogBinding.rvImagesList.adapter = adapter

        return dialog
    }

    companion object {
        @JvmStatic
        private val TAG = AvatarsDialogFragment::class.java.simpleName

        @JvmStatic
        private val KEY_IMAGE_RESPONSE = "KEY_IMAGE_RESPONSE"

        @JvmStatic
        private val ARG_IMAGE = "ARG_IMAGE"

        fun show(manager: FragmentManager) {
            if (manager.findFragmentByTag(TAG) == null) {
                AvatarsDialogFragment().show(manager, TAG)
            }
        }

        fun setupListener(
            manager: FragmentManager,
            lifecycleOwner: LifecycleOwner,
            listener: (Int) -> Unit,
        ) {
            manager.setFragmentResultListener(KEY_IMAGE_RESPONSE, lifecycleOwner) { key, result ->
                listener.invoke(result.getInt(ARG_IMAGE))
            }
        }
    }

    private val avatarsRepository = listOf(
        R.drawable.male_1, R.drawable.male_2, R.drawable.male_3, R.drawable.male_4,
        R.drawable.male_5, R.drawable.male_6, R.drawable.male_7, R.drawable.male_8,
        R.drawable.male_9, R.drawable.male_10, R.drawable.male_11, R.drawable.male_12,
        R.drawable.male_13, R.drawable.male_14, R.drawable.male_15, R.drawable.male_16,
        R.drawable.male_17, R.drawable.male_18, R.drawable.male_19, R.drawable.male_20,
        R.drawable.male_21, R.drawable.male_22, R.drawable.male_23, R.drawable.male_24,
        R.drawable.male_25, R.drawable.male_26, R.drawable.male_27, R.drawable.male_28,
        R.drawable.male_29, R.drawable.male_30, R.drawable.male_31, R.drawable.male_32,
        R.drawable.male_33, R.drawable.male_34, R.drawable.male_35, R.drawable.male_36,
        R.drawable.male_37, R.drawable.male_38, R.drawable.male_39, R.drawable.male_40,
        R.drawable.male_41, R.drawable.male_42, R.drawable.male_43, R.drawable.male_44,
        R.drawable.male_45, R.drawable.male_46, R.drawable.male_47, R.drawable.male_48,
        R.drawable.female_1, R.drawable.female_2, R.drawable.female_3, R.drawable.female_4,
        R.drawable.female_5, R.drawable.female_6, R.drawable.female_7, R.drawable.female_8,
        R.drawable.female_9, R.drawable.female_10, R.drawable.female_11, R.drawable.female_12,
        R.drawable.female_13, R.drawable.female_14, R.drawable.female_15, R.drawable.female_16,
        R.drawable.female_17, R.drawable.female_18, R.drawable.female_19, R.drawable.female_20,
        R.drawable.female_21, R.drawable.female_22, R.drawable.female_23, R.drawable.female_24,
        R.drawable.female_25, R.drawable.female_26, R.drawable.female_27, R.drawable.female_28,
        R.drawable.female_29, R.drawable.female_30, R.drawable.female_31, R.drawable.female_32,
        R.drawable.female_33, R.drawable.female_34, R.drawable.female_35, R.drawable.female_36,
        R.drawable.female_37, R.drawable.female_38, R.drawable.female_39, R.drawable.female_40,
        R.drawable.female_41, R.drawable.female_42, R.drawable.female_43, R.drawable.female_44,
        R.drawable.female_45, R.drawable.female_46, R.drawable.female_47, R.drawable.female_48
    ).toMutableList().shuffled()
}