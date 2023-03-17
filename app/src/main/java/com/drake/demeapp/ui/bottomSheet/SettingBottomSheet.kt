package com.drake.demeapp.ui.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.drake.demeapp.MainViewModel
import com.drake.demeapp.R
import com.drake.demeapp.databinding.BottomSheetSettingBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SettingBottomSheet : BottomSheetDialogFragment(), TimerAdapter.ITimerClick {
    private lateinit var binding: BottomSheetSettingBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = BottomSheetSettingBinding.inflate(inflater, container, false)
        bind()
        return binding.root
    }

    override fun onClickTime(minute: Int) {
        Toast.makeText(context, "Selected: $minute mins", Toast.LENGTH_SHORT).show()
        viewModel.setTime(minute.toLong())
        dismiss()
    }

    private fun bind() {
        binding.apply {
            val listTime = arrayListOf(1, 2, 3, 5)
            val adapter = TimerAdapter(listTime, this@SettingBottomSheet)

            rcTimer.layoutManager = LinearLayoutManager(context)
            rcTimer.setHasFixedSize(true)
            rcTimer.adapter = adapter

            // Detected action when user click bottom sheet
            root.setOnClickListener {
                if (viewModel.isReset.value == false) {
                    viewModel.isReset.postValue(true)
                }
            }

            viewModel.time.observe(viewLifecycleOwner) {
                it?.let { time ->
                    val min = time / (60 * 1000)
                    textTime.text = getString(R.string.text_time, min.toString())
                }
                editTextTime.setText((viewModel.getTime() / (60 * 1000)).toString())

                // Detected action when user click keyboard
                editTextTime.addTextChangedListener {
                    viewModel.isReset.postValue(true)
                }
                buttonSetTime.setOnClickListener {
                    val value = editTextTime.text.toString()
                    try {
                        viewModel.setTime(value.toLong())
                        dismiss()
                    } catch (e: Exception) {
                        Toast.makeText(requireContext(), "Some thing wrong", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    companion object {
        const val TAG = "BOTTOM_SHEET_SETTING"
    }
}
