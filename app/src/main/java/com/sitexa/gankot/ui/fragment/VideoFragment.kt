package com.sitexa.gankot.ui.fragment

import com.sitexa.gankot.common.Type

/**
 * Created by caik on 2017/6/2.
 */
class VideoFragment :ArticleFragment(){
    override fun getType(): String {
        return Type.休息视频.name
    }

    companion object{
        fun newInstance():VideoFragment {
            return VideoFragment()
        }
    }
}