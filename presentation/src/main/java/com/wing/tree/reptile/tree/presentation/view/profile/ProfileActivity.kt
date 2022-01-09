package com.wing.tree.reptile.tree.presentation.view.profile

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.wing.tree.reptile.tree.domain.model.Profile
import com.wing.tree.reptile.tree.presentation.Extra
import com.wing.tree.reptile.tree.presentation.R
import com.wing.tree.reptile.tree.presentation.TransitionName
import com.wing.tree.reptile.tree.presentation.databinding.ActivityProfileBinding
import com.wing.tree.reptile.tree.presentation.eventbus.OnBackPressedEventBus
import com.wing.tree.reptile.tree.presentation.parcelable.ParcelableProfile
import com.wing.tree.reptile.tree.presentation.view.base.BaseActivity
import com.wing.tree.reptile.tree.presentation.view.diary.WriteDiaryFragment
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber

class ProfileActivity : BaseActivity<ActivityProfileBinding>() {
    private val compositeDisposable = CompositeDisposable()

    override fun inflate(inflater: LayoutInflater): ActivityProfileBinding {
        return ActivityProfileBinding.inflate(inflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        subscribe()
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    private fun initData() {
        val parcelableProfile = intent?.getParcelableExtra<ParcelableProfile>(Extra.PARCELABLE_PROFILE)
            ?: run {
                finish()
                return@initData
            }

        bind(parcelableProfile)
    }

    private fun bind(parcelableProfile: ParcelableProfile) {
        with(viewBinding) {
            imageViewProfilePicture.transitionName = TransitionName.PROFILE_PHOTO
            textViewName.transitionName = TransitionName.NAME

            Glide.with(applicationContext)
                .load(parcelableProfile.profilePictureUri)
                .transform(CenterCrop())
                .into(imageViewProfilePicture)

            textViewName.text = parcelableProfile.name

            imageViewAddDiary.setOnClickListener {
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(
                        android.R.anim.fade_in,
                        android.R.anim.fade_out,
                        android.R.anim.fade_in,
                        android.R.anim.fade_out,
                    )
                    .setReorderingAllowed(true)
                    .replace(R.id.frame_layout_profile, WriteDiaryFragment.newInstance(parcelableProfile))
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    private fun subscribe() {
        compositeDisposable.add(
            OnBackPressedEventBus.subscribe().subscribe {
                onBackPressed()
            }
        )
    }
}