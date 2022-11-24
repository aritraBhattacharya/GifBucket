package com.aritra.gifbucket.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.aritra.gifbucket.data.models.GifUIModel

class GifDiffUtilCallBack(private val oldGifs:List<GifUIModel>, private val newGifs:List<GifUIModel>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldGifs.size
    }

    override fun getNewListSize(): Int {
        return newGifs.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldGifs[oldItemPosition] == newGifs[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldGifs[oldItemPosition].urlStr == newGifs[newItemPosition].urlStr
    }
}

object GifItemCallBack: DiffUtil.ItemCallback<GifUIModel>() {
    override fun areItemsTheSame(oldItem: GifUIModel, newItem: GifUIModel): Boolean {
        return oldItem.urlStr == newItem.urlStr
    }

    override fun areContentsTheSame(oldItem: GifUIModel, newItem: GifUIModel): Boolean {
        return oldItem ==newItem
    }

}