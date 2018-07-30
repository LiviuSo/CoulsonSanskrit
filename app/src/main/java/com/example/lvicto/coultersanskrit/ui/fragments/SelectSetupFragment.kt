package com.example.lvicto.coultersanskrit.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lvicto.coultersanskrit.utils.Constants
import com.example.lvicto.coultersanskrit.utils.PreferenceHelper
import kotlinx.android.synthetic.main.fragment_setup.view.*

class SelectSetupFragment : SetupFragment() {

    companion object {
        fun create(): SelectSetupFragment {
            val instance = SelectSetupFragment()
            instance.step = Constants.SetupStep.SELECT
            instance.nextStep = Constants.SetupStep.INSTALL
            instance.ctaSetupLabel = ""
            instance.errorDlgMessage = ""
            instance.nextBtnLabel = "Next"
            return instance
        }
    }

    override fun canContinue(): Boolean {
        return true
    }

    override fun getOnClickSetupListener(): View.OnClickListener {
        return View.OnClickListener {
            // nothing
        }
    }

    private var keyboardName = ""
    private var packageName = ""

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        view.tvStep1.visibility = View.GONE
        view.tvStep2.visibility = View.GONE
        view.tvStep3.visibility = View.GONE
        view.setupCta.visibility = View.GONE
        // todo show picker or radio
        keyboardName = Constants.Keyboard.SANSKRIT_KEYS_NAME_2 // todo set form the radio selection
        packageName = Constants.Keyboard.SANSKRIT_KEYS_PACKAGE_PACKAGE_NAME_2 // todo set form the radio selection
        view.btnNext.setOnClickListener {
            SelectSetupFragment@super.moveToNext()
            PreferenceHelper.setKeyboardSelected(true)
            PreferenceHelper.setKeyboardName(keyboardName)
            PreferenceHelper.setKeyboardPackage(packageName)
        }
        return view
    }
}