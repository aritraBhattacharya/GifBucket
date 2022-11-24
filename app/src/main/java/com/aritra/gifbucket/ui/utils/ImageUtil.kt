package com.aritra.gifbucket.ui.utils

import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.loadUrlIntoImage(urlStr:String,){
    Glide.with(this).load(urlStr).into(this)
}