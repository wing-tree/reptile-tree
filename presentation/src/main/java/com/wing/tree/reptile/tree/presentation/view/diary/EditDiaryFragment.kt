package com.wing.tree.reptile.tree.presentation.view.diary

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import com.wing.tree.reptile.tree.presentation.R
import com.wing.tree.reptile.tree.presentation.adapter.diary.EditDiaryAdapter
import com.wing.tree.reptile.tree.presentation.constant.Key
import com.wing.tree.reptile.tree.presentation.databinding.FragmentWriteDiaryBinding
import com.wing.tree.reptile.tree.presentation.parcelable.ParcelableProfile
import com.wing.tree.reptile.tree.presentation.util.writeFileToAppSpecificStorage
import com.wing.tree.reptile.tree.presentation.view.base.BaseFragment
import com.wing.tree.reptile.tree.presentation.view.base.LinearLayoutManagerWrapper
import com.wing.tree.reptile.tree.presentation.viewmodel.diary.EditDiaryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditDiaryFragment : BaseFragment<FragmentWriteDiaryBinding>() {
    private val viewModel by viewModels<EditDiaryViewModel>()

    private val diaryAdapter by lazy {
        EditDiaryAdapter()
    }

    private val onBackPressedCallback by lazy {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                parentFragmentManager.popBackStackImmediate()
            }
        }
    }

    private val requestPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            pickPicture()
        }
    }

    private val startActivityForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
        if (activityResult.resultCode == Activity.RESULT_OK) {
            val data = activityResult.data?.data ?: return@registerForActivityResult

            when(data.scheme) {
                "content" -> {
                    writeFileToAppSpecificStorage(requireContext(), data)?.let {
                        diaryAdapter.addImage(it)
                    }
                }
                "file" -> {

                }
            }
        }
    }

    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWriteDiaryBinding {
        return FragmentWriteDiaryBinding.inflate(inflater, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        diaryAdapter.addText()
    }

    override fun onDetach() {
        super.onDetach()
        onBackPressedCallback.remove()
    }

    private fun bind() {
        val parcelableProfile = arguments?.getParcelable<ParcelableProfile>(Key.PARCELABLE_PROFILE)

        with(viewBinding) {
            parcelableProfile ?: run {
                parentFragmentManager.popBackStackImmediate()
                return@bind
            }

            materialToolbar.setNavigationOnClickListener {
                parentFragmentManager.popBackStackImmediate()
            }

            materialToolbar.setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.menu_complete -> {
                        viewModel.insertDiary(diaryAdapter.toDiary(parcelableProfile.id))

                        delayOnLifecycle(120L) {
                            parentFragmentManager.popBackStackImmediate()
                        }
                        true
                    }
                    else -> false
                }
            }

            recyclerView.apply {
                adapter = diaryAdapter
                layoutManager = LinearLayoutManagerWrapper(context)
                setHasFixedSize(true)
            }

            imageViewAddPicture.setOnClickListener {
                when {
                    isPermissionGranted(Manifest.permission.READ_EXTERNAL_STORAGE) -> pickPicture()
                    shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) -> requestPermission.launch(
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                    else -> requestPermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }
        }
    }

    private fun isPermissionGranted(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_GRANTED
    }

    private fun pickPicture() {
        val intent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        ).apply {
            type = "image/*"
        }

        startActivityForResult.launch(intent)
    }

    companion object {
        fun newInstance(parcelableProfile: ParcelableProfile): EditDiaryFragment {
            val bundle = Bundle().apply {
                putParcelable(Key.PARCELABLE_PROFILE, parcelableProfile)
            }

            return EditDiaryFragment().apply {
                arguments = bundle
            }
        }
    }
}