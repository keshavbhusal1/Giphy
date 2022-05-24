package com.keshav.giphy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.keshav.giphy.R
import com.keshav.giphy.databinding.SingleItemOneBinding
import com.keshav.giphy.fragment.one.OneFragment
import com.keshav.giphy.model.response.TrendingResponse
import com.keshav.giphy.myinterface.ClickListener


class TrendingAdapter(private val mList: ArrayList<TrendingResponse.Data>, private val clickListener: ClickListener) : RecyclerView.Adapter<TrendingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_item_one, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val model = mList[position]
        Glide.with(holder.imageView)
            .asGif()
            .load(model.images.original.url)
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.imageView)

        holder.imageView.setOnClickListener {
            clickListener.click(model)
        }


    }

    override fun getItemCount(): Int {
        return mList.size
    }
    fun addList(mList:  List<TrendingResponse.Data>) {
        this.mList.apply {
            clear()
            addAll(mList)
        }

    }
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }
}