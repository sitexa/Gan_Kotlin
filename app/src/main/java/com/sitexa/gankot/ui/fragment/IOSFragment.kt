package com.sitexa.gankot.ui.fragment

import com.sitexa.gankot.common.Type

/**
 * xnpeng (xnpeng@hotmail.com)
 */
class IOSFragment : ArticleFragment(){

    companion object{
        fun newInstance():IOSFragment {
            return IOSFragment()
        }
    }

    override fun getType(): String {
        return Type.iOS.name
    }

}