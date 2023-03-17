package com.drake.demeapp.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.drake.demeapp.MainViewModel
import com.drake.demeapp.OyikaApp
import com.drake.demeapp.core.BaseFragment
import com.drake.demeapp.databinding.FragmentMainBinding
import com.drake.demeapp.ui.bottomSheet.SettingBottomSheet
import com.drake.demeapp.utils.ConnectivityManager

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    override val viewModel: MainViewModel by activityViewModels()
    lateinit var connectivityManager: ConnectivityManager
    override fun onStart() {
        super.onStart()
        connectivityManager.registerConnectionObserver(this)
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityManager = ConnectivityManager(OyikaApp.instance)
        connectivityManager.isNetworkAvailable.observe(this) {
            onConnectivityChanged(it)
        }
    }

    private var isConnect = false
    fun onConnectivityChanged(hasConnection: Boolean) {
        isConnect = hasConnection
    }

    override fun onDestroy() {
        super.onDestroy()
        connectivityManager.unregisterConnectionObserver(this)
    }

    override fun setUpViews() {
        val bottomSheet = SettingBottomSheet()
        binding.apply {
            buttonStartNow.setOnClickListener {
                if (isConnect == false) {
                    Toast.makeText(context, "No internet", Toast.LENGTH_LONG).show()
                } else {
                    viewModel.onClick()
                }
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
