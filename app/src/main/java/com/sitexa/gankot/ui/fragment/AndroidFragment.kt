package com.sitexa.gankot.ui.fragment

import com.sitexa.gankot.common.Type

/**
 * Created by caik on 2017/5/31.
 */
class AndroidFragment : ArticleFragment(){

    companion object{
        fun newInstance():AndroidFragment {
            return AndroidFragment()
        }
    }

    override fun getType(): String {
        return Type.Android.name
    }

}