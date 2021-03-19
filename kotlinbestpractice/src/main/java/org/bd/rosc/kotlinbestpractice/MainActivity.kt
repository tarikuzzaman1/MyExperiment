package org.bd.rosc.kotlinbestpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import org.bd.rosc.kotlinbestpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  /*private val greeting: TextView by lazy {
    findViewById<TextView>(R.id.greeting)
  }*/
  private lateinit var binding: ActivityMainBinding
  //private lateinit var greeting: TextView
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val name = intent.getStringExtra("name") ?: getString(R.string.world)
    binding.greeting.text = getString(R.string.hello, name)
  }

  fun greet(v: View) {
    val name = "World"
    binding.greeting.text = getString(R.string.hello, name)
  }
}