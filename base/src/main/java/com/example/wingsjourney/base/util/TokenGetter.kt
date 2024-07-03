package com.example.wingsjourney.base.util

import android.content.Context
import com.example.wingsjourney.base.R

class TokenGetter {
    companion object{
        fun getToken(ctx: Context?) : String{
            val sharedPref = ctx?.getSharedPreferences(
                ctx.getString(R.string.jwt_shared_pref_key),
                Context.MODE_PRIVATE
            ) ?: return ""

            return sharedPref.getString(ctx.getString(R.string.jwt_shared_pref_key), "").toString()
        }
    }
}