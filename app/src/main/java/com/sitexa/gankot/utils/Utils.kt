package com.sitexa.gankot.utils

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.widget.Toast
import com.sitexa.gankot.ui.fragment.ProgressFragment

/**
 * xnpeng (xnpeng@hotmail.com)
 */

/** Activity 拓展 start */
fun Activity.toast(msg:String) {
    Toast.makeText(applicationContext,msg,Toast.LENGTH_SHORT).show()
}

fun Activity.toast(msgId:Int) {
    Toast.makeText(applicationContext,msgId,Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.showProgress() {
    val dialog = ProgressFragment.newInstance()
    dialog.show(supportFragmentManager,ProgressFragment::class.java.simpleName)
}

fun AppCompatActivity.dismissProgress() {
    (supportFragmentManager.findFragmentByTag(ProgressFragment::class.java.simpleName) as ProgressFragment?)?.dismiss()

}

fun AppCompatActivity.testDimen(): Pair<Int, Int> {
    val dm = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(dm)
    val width = dm.widthPixels
    val height = dm.heightPixels
    val density = dm.density
    val dpi = dm.densityDpi
    println("testDimen:$width * $height : $density : $dpi")

    val width_dip = (width / density + 0.5f).toInt()
    val height_dip = (height / density + 0.5f).toInt()
    println("testDimen: ${width_dip}dp * ${height_dip}dp")

    return Pair(width, height)
}

/** Activity 拓展 end */



/** String 拓展 start */

fun String.isEmpty(str:String):Boolean {
    return str == null || str == ""
}

/** String 拓展 end */