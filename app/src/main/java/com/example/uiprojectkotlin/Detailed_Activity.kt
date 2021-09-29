package com.example.uiprojectkotlin

import android.app.AuthenticationRequiredException
import android.app.DownloadManager
import android.content.Intent
import android.media.Image
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide




class Detailed_Activity : AppCompatActivity() {
    private val AUTHORITY = "com.example.uiprojectkotlin.fileprovider"
    lateinit var img : ImageView
    lateinit var txtName : TextView
    lateinit var txtId : TextView
    lateinit var txtDesc : TextView
    lateinit var converter: ImageView
    lateinit var download: ImageView
    lateinit var share: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        getBindings()

        showData()

        download.setOnClickListener(View.OnClickListener {
            DownloadItem()
        })

        share.setOnClickListener(View.OnClickListener {
            shareItem()
        })
    }

    private fun shareItem() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "image/*"
        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(intent.getStringExtra("Image").toString()))
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(Intent.createChooser(shareIntent, "Share image using"))
    }

    private fun showData() {
        Glide.with(this@Detailed_Activity).load(Uri.parse(intent.getStringExtra("Image").toString())).into(img);
//        Toast.makeText(this, ""+intent.getStringExtra("Image").toString(), Toast.LENGTH_SHORT).show()
        txtName.text = intent.getStringExtra("Name")
        txtId.text = intent.getIntExtra("Id",0).toString()
        txtDesc.text = intent.getStringExtra("Desc")
    }

    private fun getBindings() {
        img = findViewById(R.id.img)
        txtName = findViewById(R.id.txtName)
        txtId = findViewById(R.id.txtId)
        txtDesc = findViewById(R.id.txtDesc)
        download = findViewById(R.id.download)
        converter = findViewById(R.id.converter)
        share = findViewById(R.id.share)
    }

    private fun DownloadItem() {
        var request = DownloadManager.Request(Uri.parse(intent.getStringExtra("Image")))
        request.setTitle("Downloading")
        request.setDescription("Your Item is Being Downloading, Please Wait ...")

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS,
            intent.getStringExtra("Name")
        )

        val downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)

        Toast.makeText(this@Detailed_Activity, "Downloading Started", Toast.LENGTH_SHORT).show()
    }
}
