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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel=dataList.get(position)

        //https://api.giphy.com/v1/gifs/search?q=all&api_key=dc6zaTOxFJmzC
//        var Url : String= "https://media3.giphy.com/media/"+dataModel.id.toString()+"/giphy.gif"

        holder.t1.text= dataModel.name
        Picasso.get().load(dataModel.image).into(holder.img1)
//        Picasso.get().load(Url).into(holder.img1)

        holder.img1.setOnClickListener {
            val intent = Intent(context, Detailed_Activity::class.java)
            intent.putExtra("Image", dataModel.image)
            intent.putExtra("Name", dataModel.name)
            intent.putExtra("Desc", dataModel.desc)
            intent.putExtra("Id", dataModel.id)
////            intent.putExtra("Id", dataModel.id)
//            intent.putExtra("Url", dataModel.url)
//            intent.putExtra("Title", dataModel.title)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }


    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        lateinit var t1:TextView
        lateinit var img1 : ImageView
        init {
            t1=itemLayoutView.findViewById(R.id.t1)
            img1 = itemLayoutView.findViewById(R.id.img1)

        }

    }

}