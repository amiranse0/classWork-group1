package com.example.newproject.ui.uploadimage

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.newproject.R
import com.example.newproject.databinding.FragmentUploadimageBinding

class UploadImage : Fragment(R.layout.fragment_uploadimage) {
    private val args by navArgs<UploadImageArgs>()
    lateinit var binding: FragmentUploadimageBinding
    private lateinit var imageByteArray: Uri
    private val viewmodel by viewModels<ViewModelUploadImage>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUploadimageBinding.bind(view)

        val getImageFromGallery = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                imageByteArray = it
                binding.imgProfile.setImageURI(it)
            }
        )

        binding.imgProfile.setOnClickListener {
            getImageFromGallery.launch("image/*")
        }
        binding.btnUpload.setOnClickListener {
            val change = context?.contentResolver?.openInputStream(imageByteArray)?.readBytes()
            viewmodel.uploadImage(args.userId, change!!)
            viewmodel.response.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            })
            viewmodel.error.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            })
        }


    }
}