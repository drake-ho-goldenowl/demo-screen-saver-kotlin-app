package com.drake.demeapp.ui.main

import androidx.fragment.app.activityViewModels
import com.drake.demeapp.MainViewModel
import com.drake.demeapp.core.BaseFragment
import com.drake.demeapp.databinding.FragmentMainBinding
import com.drake.demeapp.ui.bottomSheet.SettingBottomSheet

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    override val viewModel: MainViewModel by activityViewModels()

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun setUpViews() {
        val bottomSheet = SettingBottomSheet()
        binding.apply {
            buttonStartNow.setOnClickListener {
                viewModel.onClick()
            }

            buttonSetting.setOnClickListener {
                bottomSheet.show(parentFragmentManager, SettingBottomSheet.TAG)
            }

            viewModel.dismissBottomSheet.observe(viewLifecycleOwner) {
                if (it == true) {
                    if (bottomSheet.isVisible) {
                        bottomSheet.dismiss()
                    }
                    viewModel.dismissBottomSheet.postValue(false)
                }
            }
        }
    }
}