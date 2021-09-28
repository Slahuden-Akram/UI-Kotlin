package com.example.uiprojectkotlin

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
import org.w3c.dom.Text
import java.io.File

class Detailed_Activity : AppCompatActivity() {
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

        img = findViewById(R.id.img)
        txtName = findViewById(R.id.txtName)
        txtId = findViewById(R.id.txtId)
        txtDesc = findViewById(R.id.txtDesc)
        download = findViewById(R.id.download)
        converter = findViewById(R.id.converter)
        share = findViewById(R.id.share)

        img.setImageResource(intent.getIntExtra("Image", 0))
        txtName.text = intent.getStringExtra("Name")
        txtId.text = intent.getStringExtra("Id")
        txtDesc.text = intent.getStringExtra("Desc")

        download.setOnClickListener(View.OnClickListener {
            DownloadItem()
        })

        share.setOnClickListener(View.OnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "image/png"
            val photoFile = File(Environment.DIRECTORY_DOWNLOADS, intent.getStringExtra("Name"))
            Toast.makeText(this@Detailed_Activity, "" + photoFile, Toast.LENGTH_SHORT).show()
            shareIntent.putExtra(Intent.EXTRA_FROM_STORAGE, Uri.fromFile(photoFile))
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivity(Intent.createChooser(shareIntent, "Share image using"))
        })
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
