package com.sitexa.gankot.ui.fragment

import com.sitexa.gankot.common.Type

/**
 * xnpeng (xnpeng@hotmail.com)
 */
class NewFragment : ArticleFragment(){
    override fun getType(): String {
        return Type.all.name
    }
}