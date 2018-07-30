package com.example.lvicto.coultersanskrit.utils

object Constants {

    object Keyboard {
        const val SANSKRIT_KEYS_NAME_1 = "CustomKeyboard"
        const val SANSKRIT_KEYS_PACKAGE_PACKAGE_NAME_1 = "com.example.lvicto.coultersanskrit"

        const val SANSKRIT_KEYS_NAME_2 = "SimpleIMEServiceSky"
        const val SANSKRIT_KEYS_PACKAGE_PACKAGE_NAME_2 = "sky.sanskrit.myphotokeyboard"
    }

    enum class Setup {
        FRAG_SETUP_INSTALL,
        FRAG_SETUP_INSTALLED,
        FRAG_SETUP_ENABLE,
        FRAG_SETUP_ENABLED,
        FRAG_SETUP_SELECT,
        FRAG_SETUP_SELECTED,
        FRAG_SETUP_NO_TYPE
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