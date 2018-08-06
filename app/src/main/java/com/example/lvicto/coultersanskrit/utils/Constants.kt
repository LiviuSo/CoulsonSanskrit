package com.example.lvicto.coultersanskrit.utils

object Constants {

    object Keyboard {
        const val SANSKRIT_KEYS_NAME_1 = "CustomKeyboard"
        const val SANSKRIT_KEYS_PACKAGE_PACKAGE_NAME_1 = "com.example.lvicto.coultersanskrit"

        const val SANSKRIT_KEYS_NAME_2 = "SimpleIMEServiceSky"
        const val SANSKRIT_KEYS_PACKAGE_PACKAGE_NAME_2 = "sky.sanskrit.myphotokeyboard"

        const val REPLY_ADD_WORD_WORD_SA = "word_sa"
        const val REPLY_ADD_WORD_WORD_EN = "word_en"
        const val REPLY_ADD_WORD_WORD_RO = "word_ro"
        const val NEW_WORD_ACTIVITY_REQUEST_CODE = 111
    }

    enum class SetupStep {
        SELECT,
        INSTALL,
        ENABLE,
        CHOOSE,
        DONE,
        NONE,
    }
}