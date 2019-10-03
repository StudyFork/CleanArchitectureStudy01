package com.example.common.utils

object TextUtils {

    fun isEmpty(str: String?): Boolean = android.text.TextUtils.isEmpty(str)

    fun isNotEmpty(str: String?): Boolean = !android.text.TextUtils.isEmpty(str)

    fun isEquals(str1: String?, str2: String?): Boolean = android.text.TextUtils.equals(str1, str2)

    fun isNotEquals(str1: String?, str2: String?): Boolean =
        !android.text.TextUtils.equals(str1, str2)
}
