package org.bd.rosc.scoped_storage

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import org.bd.rosc.scoped_storage.databinding.ActivityResultApisBinding

class ResultAPIsActivity : AppCompatActivity() {
  val resultContract =
    registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->
      if (result?.resultCode == RESULT_OK) {
        binding.textView.text = "We get the result"
      } else {
        binding.textView.text = "Failed to get result"
      }
    }
  val pickImages = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
    uri?.let { binding.imageView.setImageURI(uri) }
  }
  private val takePicture =
    registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
      bitmap?.let { binding.imageView.setImageBitmap(bitmap) }
    }
  lateinit var binding: ActivityResultApisBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityResultApisBinding.inflate(layoutInflater)
    setContentView(binding.root)
    binding.buttonGetResult.setOnClickListener {
      Intent(this, ResultActivity::class.java).apply {
        resultContract.launch(this)
      }
    }

    binding.buttonGetPicture.setOnClickListener {
      pickImages.launch("image/*")
      //takePicture.launch()
    }

    binding.buttonGetPermission.setOnClickListener {
      askMultiplePermissions.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION))
    }
  }
  /*private val takePicture = registerForActivityResult(ActivityResultContracts.TakePicture()) {bitmap ->
    bitmap?.let {
      bi.imgCameraPic.setImageBitmap(it)
      bi.imgCameraPic.visibility = View.VISIBLE
    }
  }*/

  private val askLocationPermission =
    registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
      if (isGranted) {
        Log.e("TAG", "Permission is granted")
      } else {
        Log.e("TAG", "No Permission")
      }
    }

  private val askMultiplePermissions =
    registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { map ->
      for (entry in map.entries) {
        Toast.makeText(this, "${entry.key} = ${entry.value}", Toast.LENGTH_SHORT).show()
      }
    }

  /*private val pickImages = registerForActivityResult(ActivityResultContracts.GetContent()) {uri ->
    uri?.let {uri ->
      bi.imgCameraPic.setImageURI(uri)
      bi.imgCameraPic.visibility = View.VISIBLE
    }
  }*/

  /*private val pickApi = registerForActivityResult(ApiPickerActivityContract()) {api ->
    if (api == null)
      Toast.makeText(this, "Nothing selected", Toast.LENGTH_SHORT).show()
    else
      Toast.makeText(this, api, Toast.LENGTH_SHORT).show()
  }*/
}