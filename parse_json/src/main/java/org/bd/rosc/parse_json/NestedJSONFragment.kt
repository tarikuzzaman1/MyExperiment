package org.bd.rosc.parse_json

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.bd.rosc.parse_json.databinding.ArrayJsonBinding
import org.bd.rosc.parse_json.databinding.NestedJsonBinding
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.net.URL
import java.util.*
import javax.net.ssl.HttpsURLConnection


/**
 * Created by Md.Tarikuzzaman on 03-Jun-2021 4:08 PM, ROSC-II MIS Cell, LGED.
 */
class NestedJSONFragment : Fragment() {

  var itemsArray: ArrayList<Employee> = ArrayList()
  lateinit var adapter: RecyclerViewAdapter

  private lateinit var binding: NestedJsonBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = NestedJsonBinding.inflate(layoutInflater)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    // Clean TextViews
    binding.jsonResultsTextview.text = ""

    setupRecyclerView()
    parseJSON()
  }

  private fun setupRecyclerView() {
    val layoutManager = LinearLayoutManager(context)
    binding.jsonResultsRecyclerview.layoutManager = layoutManager
    binding.jsonResultsRecyclerview.setHasFixedSize(true)
    val dividerItemDecoration =
      DividerItemDecoration(
        binding.jsonResultsRecyclerview.context,
        layoutManager.orientation
      )
    context?.let {
      ContextCompat.getDrawable(it, R.drawable.line_divider)
        ?.let { drawable -> dividerItemDecoration.setDrawable(drawable) }
    }
    binding.jsonResultsRecyclerview.addItemDecoration(dividerItemDecoration)
  }

  @SuppressLint("LongLogTag")
  private fun parseJSON() {

    GlobalScope.launch(Dispatchers.IO) {
      val url =
        URL("https://raw.githubusercontent.com/johncodeos-blog/ParseJSONAndroidExample/main/nested.json")
      val httpsURLConnection = url.openConnection() as HttpsURLConnection
      httpsURLConnection.setRequestProperty(
        "Accept",
        "application/json"
      ) // The format of response we want to get from the server
      httpsURLConnection.requestMethod = "GET"
      httpsURLConnection.doInput = true
      httpsURLConnection.doOutput = false
      // Check if the connection is successful
      val responseCode = httpsURLConnection.responseCode
      if (responseCode == HttpsURLConnection.HTTP_OK) {
        val response = httpsURLConnection.inputStream.bufferedReader()
          .use { it.readText() }  // defaults to UTF-8
        withContext(Dispatchers.Main) {

          // Convert raw JSON to pretty JSON using GSON library
          val gson = GsonBuilder().setPrettyPrinting().create()
          val prettyJson = gson.toJson(JsonParser.parseString(response))
          Log.d("Pretty Printed JSON :", prettyJson)
          binding.jsonResultsTextview.text = prettyJson

          val jsonObject = JSONTokener(response).nextValue() as JSONObject

          val jsonArray = jsonObject.getJSONArray("data")

          for (i in 0 until jsonArray.length()) {

            // ID
            val id = jsonArray.getJSONObject(i).getString("id")
            Log.i("ID: ", id)

            // Employee
            val employee = jsonArray.getJSONObject(i).getJSONObject("employee")

            // Employee Name
            val employeeName = employee.getString("name")
            Log.i("Employee Name: ", employeeName)

            // Employee Salary
            val employeeSalary = employee.getJSONObject("salary")

            // Employee Salary in USD
            val employeeSalaryUSD = employeeSalary.getInt("usd")
            Log.i("Employee Salary in USD: ", employeeSalaryUSD.toString())

            // Employee Salary in EUR
            val employeeSalaryEUR = employeeSalary.getInt("eur")
            Log.i("Employee Salary: ", employeeSalaryEUR.toString())

            // Employee Age
            val employeeAge = employee.getString("age")
            Log.i("Employee Age: ", employeeAge)

            val model = Employee(
              id,
              employeeName,
              "$ $employeeSalaryUSD / € $employeeSalaryEUR",
              employeeAge
            )
            itemsArray.add(model)

            adapter = RecyclerViewAdapter(itemsArray)
            adapter.notifyDataSetChanged()
          }

          binding.jsonResultsRecyclerview.adapter = adapter
        }
      } else {
        Log.e("HTTPURLCONNECTION_ERROR", responseCode.toString())
      }
    }
  }

}