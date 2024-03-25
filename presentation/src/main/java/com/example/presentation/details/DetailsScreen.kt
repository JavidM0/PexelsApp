package com.example.presentation.details

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.presentation.R
import com.example.presentation.databinding.FragmentDetailsScreenBinding
import com.example.presentation.model.ImageItem
import com.example.presentation.utils.bind
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsScreen : Fragment(R.layout.fragment_details_screen) {

    private val binding by viewBinding(FragmentDetailsScreenBinding::bind)
    private val viewModel: DetailsScreenViewModel by viewModels()

    private val args: DetailsScreenArgs by navArgs()
    private var item: ImageItem? = null

    private var requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            registerStorageStateListener()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        bindViewModelInputs()
        bindViewModelOutputs()
        item?.let {
            viewModel.checkFavorite(it.id)
        }
    }

    private fun setUpView() {
        item = args.imageItem
        binding.author.text = item?.author
        Glide.with(this)
            .load(item?.imageUrl)
            .into(binding.image)
    }

    private fun bindViewModelInputs() = with(binding) {
        actionAddFavorites.setOnClickListener {
            viewModel.saveBookMarks(checkNotNull(item))
        }
        actionBack.setOnClickListener {
            findNavController().popBackStack()
        }

        actionDownload.setOnClickListener {
            registerStorageStateListener()
        }

    }

    private fun bindViewModelOutputs() = with(viewModel) {
        isFavorite.bind(viewLifecycleOwner) {
            val icon = if (it) {
                R.drawable.bookmark_button_active
            } else {
                R.drawable.bookmark_button_inactive
            }
            binding.actionAddFavorites.setImageResource(icon)
            binding.actionAddFavorites.isClickable = !it
        }

        isError.bind(viewLifecycleOwner) {
            showToast(it)
        }
    }

    private fun isPermissionGranted(): Boolean = checkSelfPermission(
        requireContext(),
        getStoragePermission()
    ) == PackageManager.PERMISSION_GRANTED

    private fun registerStorageStateListener() {
        if (isPermissionGranted()) {
            item?.let {
                viewModel.onDownloadClicked(it)
            }
        } else {
            requestPermissionLauncher.launch(getStoragePermission())
        }
    }

    private fun showToast(isError: Boolean) {
        val message = if (isError) {
            R.string.dowland_error
        } else {
            R.string.photo_dowlanded
        }
        Toast.makeText(requireContext(), getString(message), Toast.LENGTH_SHORT).show()
    }

    private fun getStoragePermission(): String =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
}
