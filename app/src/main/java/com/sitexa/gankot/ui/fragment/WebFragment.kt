package com.sitexa.gankot.ui.fragment

import com.sitexa.gankot.common.Type

/**
 * Created by caik on 2017/5/31.
 */
class WebFragment : ArticleFragment(){

    companion object{
        fun newInstance():WebFragment {
            return WebFragment()
        }
    }

    override fun getType(): String {
        return Type.前端.name
    }

}