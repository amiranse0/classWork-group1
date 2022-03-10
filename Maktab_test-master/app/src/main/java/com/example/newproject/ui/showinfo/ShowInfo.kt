package com.example.newproject.ui.showinfo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.newproject.R
import com.example.newproject.databinding.SelectUserFragmentBinding
import com.example.newproject.ui.uploadimage.UploadImageArgs
import com.example.newproject.ui.users.UserViewModel

class ShowInfo:Fragment(R.layout.select_user_fragment) {

    lateinit var binding: SelectUserFragmentBinding
    private val args by navArgs<ShowInfoArgs>()
    private val viewModel: ShowInfoViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SelectUserFragmentBinding.bind(view)

        viewModel.showDetails(args.id)

        viewModel.user.observe(viewLifecycleOwner, Observer{
            binding.tvName.text = it.firstName
            binding.tvFamily.text = it.lastName
            binding.tvNationalCode.text = it.nationalCode
        })





    }

}