package com.example.newproject.ui.users

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.newproject.R
import com.example.newproject.databinding.CustomViewBinding
import com.example.newproject.model.User
import com.example.newproject.model.UserFromServer

class RecyclerAdaptor(val items: List<UserFromServer>) :
    RecyclerView.Adapter<RecyclerAdaptor.MyViewHolder>() {

    lateinit var itemClick: ItemClick

    inner class MyViewHolder(private var binding: CustomViewBinding) :
        RecyclerView.ViewHolder(binding.root),View.OnClickListener {
        fun bind(pos:Int) {
            binding.customTv.text = items[pos].firstName
        }
        init {
            binding.root.setOnClickListener(this);
        }
        override fun onClick(p0: View?) {
            itemClick.viewClick(adapterPosition, p0)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdaptor.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = CustomViewBinding.inflate(inflater, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerAdaptor.MyViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
       return items.size
    }

    interface ItemClick{
        fun viewClick(position: Int,v:View?)
    }

    fun setItemUserClick(itemClick:ItemClick){
        this.itemClick = itemClick
    }
}