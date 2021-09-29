package com.example.uiprojectkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


class MyFilesActivity : AppCompatActivity() {
    lateinit var img: ImageView
    lateinit var rclView: RecyclerView
    var dataList = ArrayList<DataModel>()
    lateinit var adapter: DataAdpter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_files)

        img = findViewById(R.id.imgNext)
        rclView = findViewById(R.id.rclView)
        rclView.layoutManager = GridLayoutManager(this, 3)

        img.setOnClickListener(
            View.OnClickListener {
                var intent = Intent(this@MyFilesActivity, Detailed_Activity::class.java)
                startActivity(intent)
            }
        )

        getData()

    }

    private fun getData() {

        val call: Call<List<DataModel>> = ApiClient.getClient.getPhotos()
        call.enqueue(object : Callback<List<DataModel>> {

            override fun onResponse(
                call: Call<List<DataModel>>?,
                response: Response<List<DataModel>>?
            ) {
                dataList.addAll(response!!.body()!!)
//                rclView.adapter?.notifyDataSetChanged()
                rclView.adapter = DataAdpter(dataList, applicationContext)
                Log.e("aaasss", dataList.toString())
            }

            override fun onFailure(call: Call<List<DataModel>>?, t: Throwable?) {
            }

        })
    }
}
