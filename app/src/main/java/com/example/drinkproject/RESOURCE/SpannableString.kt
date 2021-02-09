package com.example.drinkproject.RESOURCE

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.StyleSpan

class SpannableString(private var size : Int, private var str: String, private var start: Int, private var end: Int) {
    fun ssb(): SpannableStringBuilder {
        val ssb = SpannableStringBuilder(str)
        ssb.setSpan(AbsoluteSizeSpan(size, true), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        ssb.setSpan(StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return ssb
    }
}

