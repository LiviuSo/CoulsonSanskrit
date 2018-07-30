package com.example.lvicto.coultersanskrit.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lvicto.coultersanskrit.utils.Constants
import com.example.lvicto.coultersanskrit.utils.PreferenceHelper

class DoneSetupFragment : SetupFragment(){

    companion object {
        fun create(): DoneSetupFragment {
            val instance = DoneSetupFragment()
            instance.step = Constants.SetupStep.DONE
            instance.nextStep = Constants.SetupStep.NONE
            instance.ctaSetupLabel = ""
            instance.errorDlgMessage = ""
            instance.nextBtnLabel = "Done"
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

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        btnSetup.visibility = View.GONE
        return view
    }
}