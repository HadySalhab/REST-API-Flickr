package com.android.flickphoto.util

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.flickphoto.R
import com.android.flickphoto.models.Photo
import com.android.flickphoto.ui.li.PhotoListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("listData")
fun RecyclerView.bindRecyclerView(data:List<Photo>?){
    val adapter = getAdapter() as PhotoListAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun ImageView.bindImage(imgUrl:String?){
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(this.context)
            .load(imgUri)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
            ).into(this)
    }
}