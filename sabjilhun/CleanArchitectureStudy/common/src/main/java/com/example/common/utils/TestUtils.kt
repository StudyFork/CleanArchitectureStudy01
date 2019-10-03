package com.example.common.utils

class TextUtils private constructor() {

    companion object {
        @JvmStatic
        fun isEmpty(str: String?): Boolean = android.text.TextUtils.isEmpty(str)

        @JvmStatic
        fun isNotEmpty(str: String?): Boolean = !android.text.TextUtils.isEmpty(str)

        @JvmStatic
        fun isEquals(str1: String?, str2: String?): Boolean =
            android.text.TextUtils.equals(str1, str2)

        @JvmStatic
        fun isNotEquals(str1: String?, str2: String?): Boolean =
            !android.text.TextUtils.equals(str1, str2)
    }

}
