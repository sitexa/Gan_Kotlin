package com.sitexa.gankot.ui.fragment

import com.sitexa.gankot.common.Type

/**
 * xnpeng (xnpeng@hotmail.com)
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