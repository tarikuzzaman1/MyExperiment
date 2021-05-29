package org.bd.rosc.scoped_storage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.bd.rosc.scoped_storage.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
  lateinit var binding: ActivityResultBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityResultBinding.inflate(layoutInflater)
    setContentView(binding.root)
    binding.buttonSendResult.setOnClickListener {
      setResult(RESULT_OK)
      finish()
    }
  }

  override fun onBackPressed() {
    setResult(RESULT_CANCELED)
    finish()
  }
}