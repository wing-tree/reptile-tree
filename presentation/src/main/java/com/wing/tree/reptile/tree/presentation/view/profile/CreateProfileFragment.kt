package com.wing.tree.reptile.tree.presentation.view.profile

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.wing.tree.reptile.tree.domain.model.Profile
import com.wing.tree.reptile.tree.eventbus.OnBackPressedEventBus
import com.wing.tree.reptile.tree.presentation.R
import com.wing.tree.reptile.tree.presentation.databinding.FragmentCreateProfileBinding
import com.wing.tree.reptile.tree.presentation.view.base.BaseFragment
import com.wing.tree.reptile.tree.presentation.viewmodel.profile.CreateProfileViewModel
import com.wing.tree.reptile.tree.presentation.writeFileToAppSpecificStorage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateProfileFragment : BaseFragment<FragmentCreateProfileBinding>() {
    private val viewModel by viewModels<CreateProfileViewModel>()

    private val startActivityForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
        if (activityResult.resultCode == RESULT_OK) {
            val data = activityResult.data?.data ?: return@registerForActivityResult

            when(data.scheme) {
                "content" -> {
                    writeFileToAppSpecificStorage(requireContext(), data)?.let {
                        viewModel.setProfilePictureFile(it)
                    }
                }
                "file" -> {

                }
            }
        }
    }

    private val requestPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            pickProfilePicture()
        }
    }

    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCreateProfileBinding {
        return FragmentCreateProfileBinding.inflate(inflater, container, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        observe()
    }

    private fun bind() {
        with(viewBinding) {
            toolbar.setNavigationOnClickListener {
                OnBackPressedEventBus.callOnBackPressed()
            }

            toolbar.setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.menu_complete -> {
                        if (checkRequiredValue()) {
                            insertProfile()
                        }

                        true
                    }
                    else -> false
                }
            }

            imageViewProfilePicture.setOnClickListener {
                when {
                    isPermissionGranted(READ_EXTERNAL_STORAGE) -> pickProfilePicture()
                    shouldShowRequestPermissionRationale(READ_EXTERNAL_STORAGE) -> requestPermission.launch(READ_EXTERNAL_STORAGE)
                    else -> requestPermission.launch(READ_EXTERNAL_STORAGE)
                }
            }
        }
    }

    private fun observe() {
        viewModel.profilePictureFile.observe(viewLifecycleOwner) {
            Glide.with(requireContext())
                .load(it.path)
                .transform(CenterCrop())
                .into(viewBinding.imageViewProfilePicture)
        }
    }

    private fun checkRequiredValue(): Boolean {
        with(viewBinding) {
            if (textInputLayoutName.editText?.text.isNullOrBlank()) {
                return false
            }
        }

        return true
    }

    private fun insertProfile() {
        with(viewBinding) {
            val name = textInputLayoutName.editText?.text.toString()

            viewModel.insertProfile(object : Profile() {
                override val name = name
                override val photo = viewModel.profilePictureFile.value?.absolutePath ?: ""
            })
        }
    }

    private fun isPermissionGranted(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_GRANTED
    }

    private fun pickProfilePicture() {
        val intent = Intent(
            Intent.ACTION_PICK,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        ).apply {
            type = "image/*"
        }

        startActivityForResult.launch(intent)
    }
}