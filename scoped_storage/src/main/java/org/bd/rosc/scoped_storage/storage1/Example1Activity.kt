package org.bd.rosc.scoped_storage.storage1

import android.content.ContentValues
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.drawToBitmap
import org.bd.rosc.scoped_storage.R
import org.bd.rosc.scoped_storage.databinding.ActivityExample1Binding
import java.io.File
import java.io.OutputStream
import java.util.*

class Example1Activity : AppCompatActivity() {
  lateinit var binding: ActivityExample1Binding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityExample1Binding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.buttonSaveimage.setOnClickListener {
      val bitmap = binding.imageView.drawToBitmap()
      saveImageToGallery(bitmap)
    }
  }

  private fun saveImageToGallery(bitmap: Bitmap) {
    val fos: OutputStream
    try {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val resolver = contentResolver
        val contentValues = ContentValues()
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "Image_.jpg")
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
        contentValues.put(
          MediaStore.MediaColumns.RELATIVE_PATH,
          Environment.DIRECTORY_PICTURES + File.separator + "iMA"
        )
        val imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        fos = resolver.openOutputStream(Objects.requireNonNull(imageUri)!!)!!
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
        Objects.requireNonNull<OutputStream?>(fos)
        Toast.makeText(this, "Image Saved", Toast.LENGTH_LONG).show()
      }
    } catch (e: Exception) {
      Toast.makeText(this, "Image Not Saved", Toast.LENGTH_LONG).show()
    }
  }
}