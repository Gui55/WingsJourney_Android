package com.example.wingsjourney.util

import com.example.wingsjourney.BuildConfig

class LocalHostNameUtils {
    companion object {
        fun replaceLocalHostString(s: String): String {
            return s.replace("http://localhost:8081/", BuildConfig.BASE_URL)
        }
    }
}