package org.bd.rosc.parse_json

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.bd.rosc.parse_json.databinding.SimpleJsonBinding
import org.json.JSONObject
import org.json.JSONTokener
import java.net.URL
import javax.net.ssl.HttpsURLConnection


/**
 * Created by Md.Tarikuzzaman on 03-Jun-2021 4:08 PM, ROSC-II MIS Cell, LGED.
 */
class SimpleJSONFragment : Fragment() {

  private lateinit var binding: SimpleJsonBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Retrieve and inflate the layout for this fragment
    binding = SimpleJsonBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    // Clean TextViews
    binding.jsonResultsTextview.text = ""
    binding.employeeIdTextview.text = ""
    binding.employeeNameTextview.text = ""
    binding.employeeSalaryTextview.text = ""
    binding.employeeAgeTextview.text = ""

    parseJSON()
  }


  private fun parseJSON() {
    GlobalScope.launch(Dispatchers.IO) {
      val url =
        URL("https://raw.githubusercontent.com/johncodeos-blog/ParseJSONAndroidExample/main/simple.json")
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

          // ID
          val id = jsonObject.getString("id")
          binding.employeeIdTextview.text = id
          Log.i("ID: ", id)

          // Employee Name
          val employeeName = jsonObject.getString("employee_name")
          binding.employeeNameTextview.text = employeeName
          Log.i("Employee Name: ", employeeName)

          // Employee Salary
          val employeeSalary = jsonObject.getString("employee_salary")
          binding.employeeSalaryTextview.text = employeeSalary
          Log.i("Employee Salary: ", employeeSalary)

          // Employee Age
          val employeeAge = jsonObject.getString("employee_age")
          binding.employeeAgeTextview.text = employeeAge
          Log.i("Employee Age: ", employeeAge)

        }
      } else {
        Log.e("HTTPURLCONNECTION_ERROR", responseCode.toString())
      }
    }
  }

}