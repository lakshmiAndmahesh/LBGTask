package com.example.mvvm.retrofitResponses

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.mvvm.R
import java.io.Serializable

data class User
    (
    val id:Int,
    var email:String,
    val first_name:String,
    val last_name:String,
    val avatar:String
) {
    companion object {
        @JvmStatic
        @BindingAdapter("android:loadAvatarr")
        fun loadImage(imageView: ImageView, path: String) {
            Glide.with(imageView.context)
                .load(path)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView)
        }
    }
}