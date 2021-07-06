package org.bd.rosc.myexperiment

import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.bd.rosc.myexperiment.databinding.BeginningThreadsBinding
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by Md.Tarikuzzaman on 05-Jul-2021 9:36 PM.
 */
class KotlinCoroutinesFundamentals : AppCompatActivity() {

  private lateinit var binding: BeginningThreadsBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = BeginningThreadsBinding.inflate(layoutInflater)
    setContentView(binding.root)

    // Your code
    // android.os.NetworkOnMainThreadException
    // Only the original thread that created a view hierarchy can touch its views

    /*val mainLooper = mainLooper // or Looper.getMainLooper()
    Thread(Runnable {
      val imageUrl = URL("https://wallpaperplay.com/walls/full/1/c/7/38027.jpg")
      val connection = imageUrl.openConnection() as HttpURLConnection
      connection.doInput = true
      connection.connect()

      val inputStream = connection.inputStream
      val bitmap = BitmapFactory.decodeStream(inputStream)

      //binding.imageView.setImageBitmap(bitmap)
      //runOnUiThread { binding.imageView.setImageBitmap(bitmap) }
      Handler(mainLooper).post { binding.imageView.setImageBitmap(bitmap) }
    }).start()*/

    // Beginning Coroutines
    GlobalScope.launch(context = Dispatchers.IO) {
      val imageUrl = URL("https://wallpaperplay.com/walls/full/1/c/7/38027.jpg")
      val connection = imageUrl.openConnection() as HttpURLConnection
      connection.doInput = true
      connection.connect()

      val inputStream = connection.inputStream
      val bitmap = BitmapFactory.decodeStream(inputStream)

      //binding.imageView.setImageBitmap(bitmap)
      //runOnUiThread { binding.imageView.setImageBitmap(bitmap) }
      launch(context = Dispatchers.Main) {
        binding.imageView.setImageBitmap(bitmap)
      }
    }
  }


}