package com.example.lvicto.coultersanskrit.ui.fragments

import android.view.View
import com.example.lvicto.coultersanskrit.utils.Constants
import com.example.lvicto.coultersanskrit.utils.KeyboardHelper
import com.example.lvicto.coultersanskrit.utils.PreferenceHelper

class InstallSetupFragment : SetupFragment() {

    private lateinit var packageName: String

    companion object {
        fun create(): InstallSetupFragment {
            val instance = InstallSetupFragment()
            instance.step = Constants.SetupStep.INSTALL
            instance.nextStep = Constants.SetupStep.ENABLE
            instance.ctaSetupLabel = "Install" // todo make res
            instance.errorDlgMessage = "Keyboard not installed."
            instance.packageName = ""
            return instance
        }
    }

    override fun canContinue(): Boolean {
        if(packageName.isEmpty()) {
            packageName = PreferenceHelper.getKeyboardPackage()
        }
        return KeyboardHelper.softInputInstalled(packageName = packageName)
    }

    override fun getOnClickSetupListener() = View.OnClickListener {
        if(!canContinue()) {
            KeyboardHelper.sendToPlayStore(packageName = packageName)
        } else {
            btnSetup.isEnabled = false
        }
        btnSetupNext.isEnabled = true
    }
}