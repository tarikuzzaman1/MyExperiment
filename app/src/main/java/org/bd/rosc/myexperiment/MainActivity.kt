package org.bd.rosc.myexperiment

import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import org.bd.rosc.myexperiment.databinding.ActivityMainBinding
import org.bd.rosc.myexperiment.databinding.ExperimentBinding
import java.text.DateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

  lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
  }
}