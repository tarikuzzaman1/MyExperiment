package org.bd.rosc.parse_json

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import org.bd.rosc.parse_json.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)

    supportActionBar?.hide()

    val bottomNavigationView = binding.bottomNavigatinView
    val navController = findNavController(R.id.fragment)


    bottomNavigationView.setupWithNavController(navController)
  }
}