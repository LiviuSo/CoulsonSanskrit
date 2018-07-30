package com.example.lvicto.coultersanskrit.ui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.lvicto.coultersanskrit.R
import com.example.lvicto.coultersanskrit.ui.fragments.*
import com.example.lvicto.coultersanskrit.utils.Constants
import com.example.lvicto.coultersanskrit.utils.KeyboardHelper
import com.example.lvicto.coultersanskrit.utils.PreferenceHelper

class SetupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup)

        setFragment(getCurrentStep())
    }

    private fun getCurrentStep(): Constants.SetupStep {
        val keyselcted = PreferenceHelper.getKeyboardSelected()
        val keyboardName = PreferenceHelper.getKeyboardName()
        val keyboardPackage = PreferenceHelper.getKeyboardPackage()
        if(!keyselcted || keyboardName.isEmpty() || keyboardPackage.isEmpty()) {
            return Constants.SetupStep.SELECT
        }
        if (!KeyboardHelper.softInputInstalled(packageName = keyboardPackage)) {
            return Constants.SetupStep.INSTALL
        }
        if (!KeyboardHelper.isSoftInputEnabled(name = keyboardName)) {
            return Constants.SetupStep.ENABLE
        }
        if (!KeyboardHelper.isDefaultInputMethod(name = keyboardName)) {
            return Constants.SetupStep.CHOOSE
        }
        return Constants.SetupStep.NONE
    }

    fun setFragment(step: Constants.SetupStep) {
        if(step == Constants.SetupStep.NONE) { // if end of setup, move to main
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            return
        }
        // otherwise move to the next step
        when (step) {
            Constants.SetupStep.SELECT -> {
                val fragment = SelectSetupFragment.create()
                fragmentManager.beginTransaction().replace(R.id.setupFragContainer, fragment).commit()
            }
            Constants.SetupStep.INSTALL -> {
                val fragment = InstallSetupFragment.create()
                fragmentManager.beginTransaction().replace(R.id.setupFragContainer, fragment).commit()
            }
            Constants.SetupStep.ENABLE -> {
                val fragment = EnableSetupFragment.create()
                fragmentManager.beginTransaction().replace(R.id.setupFragContainer, fragment).commit()
            }
            Constants.SetupStep.CHOOSE -> {
                val fragment = ChooseSetupFragment.create()
                fragmentManager.beginTransaction().replace(R.id.setupFragContainer, fragment).commit()
            }
            Constants.SetupStep.DONE -> {
                val fragment = DoneSetupFragment.create()
                fragmentManager.beginTransaction().replace(R.id.setupFragContainer, fragment).commit()
            }
            else -> {

            }
        }
    }
}
