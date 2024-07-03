package com.example.wingsjourney.base.util

import com.example.wingsjourney.base.BuildConfig

class LocalHostNameUtils {
    companion object {
        fun replaceLocalHostString(s: String): String {
            return s.replace("http://localhost:8081/", BuildConfig.BASE_URL)
        }
    }
}