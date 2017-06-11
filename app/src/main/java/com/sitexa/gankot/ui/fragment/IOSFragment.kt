package com.sitexa.gankot.ui.fragment

import com.sitexa.gankot.common.Type

/**
 * Created by caik on 2017/5/31.
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