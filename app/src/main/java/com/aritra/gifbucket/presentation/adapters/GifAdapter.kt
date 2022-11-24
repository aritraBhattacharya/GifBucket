package com.aritra.gifbucket.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aritra.gifbucket.data.models.GifUIModel
import com.aritra.gifbucket.databinding.GifItemContainerBinding
import com.aritra.gifbucket.ui.utils.loadUrlIntoImage

class GifAdapter(val handleGifClick:(urlStr:String,title:String)->Unit):ListAdapter<GifUIModel, GifAdapter.GifViewHolder>(GifItemCallBack){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        //if u dont want to use view binding
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.gif_item_container,parent,false)
        //return GifViewHolder(view)

        val binding = GifItemContainerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GifViewHolder(binding,handleGifClick)

    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        val gifItem = getItem(position)
        holder.bindGifData(gifItem)
    }

    //If u dont use view binding class GifViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    class GifViewHolder(binding: GifItemContainerBinding,val handleGifClick:(urlStr:String,title:String)->Unit):RecyclerView.ViewHolder(binding.root){
        //If u dont use view binding
        //val image  = itemView.findViewById<ImageView>(R.id.gif_img)
        //val desc  = itemView.findViewById<TextView>(R.id.gif_txt)

        private val image  = binding.gifImg
        private val desc  = binding.gifTxt
        private var currentGif:GifUIModel?=null

        init {
            binding.root.setOnClickListener {

                currentGif?.let {
                    handleGifClick(it.urlStr, it.desc)
                }
            }
        }
        fun bindGifData(gifUIModel: GifUIModel){
            currentGif = gifUIModel
            gifUIModel.urlStr?.let {image.loadUrlIntoImage(it)}
            gifUIModel.desc?.let {desc.text = it}
        }
    }
}


