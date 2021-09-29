package com.example.uiprojectkotlin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class DataAdpter(private var dataList: ArrayList<DataModel>, private val context: Context) : RecyclerView.Adapter<DataAdpter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.single_file, parent, false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
    private var url : String = "https://media1.giphy.com/media/miwxrGCEFzI1q/giphy.gif"

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel=dataList.get(position)
        holder.t1.text= dataModel.name
        Picasso.get().load(dataModel.image).into(holder.img1)

        holder.img1.setOnClickListener {
            val intent = Intent(context, Detailed_Activity::class.java)
            intent.putExtra("Image", dataModel.image)
            intent.putExtra("Name", dataModel.name)
            intent.putExtra("Desc", dataModel.desc)
            intent.putExtra("Id", dataModel.id)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }


    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        var t1:TextView
        var img1 : ImageView
        init {
            t1=itemLayoutView.findViewById(R.id.t1)
            img1 = itemLayoutView.findViewById(R.id.img1)
        }

    }

}