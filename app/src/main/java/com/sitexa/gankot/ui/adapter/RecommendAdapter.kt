package com.sitexa.gankot.ui.adapter

import android.text.Html
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sitexa.gankot.R
import com.sitexa.gankot.repository.Article

/**
 * Created by caik on 2017/6/5.
 */
class RecommendAdapter(layoutRes:Int,data:List<Article>) :BaseQuickAdapter<Article,BaseViewHolder>(layoutRes,data){
    override fun convert(viewHolder: BaseViewHolder?, article: Article?) {
        val format = "<font color='#757575' >${article!!.who}</font>"
        val desc = viewHolder!!.getView<TextView>(R.id.content)
        desc.text = Html.fromHtml("${article.desc}(${format})")
        val titleTv = viewHolder.getView<TextView>(R.id.title)
        titleTv.text = article.type
        val position = viewHolder.getLayoutPosition() - this.getHeaderLayoutCount()
        when (position) {
            0 -> {
                titleTv.visibility = VISIBLE
            }
            else ->{
                if (article!!.type == getItem((position-1))!!.type) {
                    titleTv.visibility = GONE
                }else{
                    titleTv.visibility = VISIBLE
                }
            }
        }

    }
}