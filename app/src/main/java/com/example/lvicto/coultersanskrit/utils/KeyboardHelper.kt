package com.example.lvicto.coultersanskrit.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.util.Log
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import com.example.lvicto.coultersanskrit.MyApplication.Companion.application
import com.example.lvicto.coultersanskrit.ui.MainActivity
import android.content.pm.ResolveInfo


object KeyboardHelper {
    private val inputManager = application.applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    private val LOG_TAG = this::class.java.simpleName


    fun sendToPlayStore() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("market://details?id=sky.sanskrit.myphotokeyboard")
        application.startActivity(intent)
    }

    fun isSoftInputEnabled(name: String): Boolean {
        val enabledMethods = inputManager.enabledInputMethodList
        return enabledMethods.filter {
            Log.d(LOG_TAG, "installed: ${it.serviceInfo.name}\n")
            it.serviceInfo.name.contains(name)
        }.isNotEmpty()
    }

    fun showSoftInputMethods() {
        inputManager.showInputMethodPicker()
    }

    fun isDefaultInputMethod(name: String): Boolean = Settings.Secure.getString(application.contentResolver, Settings.Secure.DEFAULT_INPUT_METHOD).contains(name)


    fun softInputInstalled(packageName: String): Boolean {
        val pm: PackageManager = application.packageManager
        val pkgAppsList = pm.getInstalledApplications(PackageManager.GET_META_DATA)

        return pkgAppsList.any {
            it.packageName == packageName
        }
    }
}