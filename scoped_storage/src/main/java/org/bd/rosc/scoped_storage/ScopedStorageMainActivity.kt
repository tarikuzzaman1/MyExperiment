package org.bd.rosc.scoped_storage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ScopedStorageMainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_scoped_storage_main)

    /*when {
      checkSelfPermission(Manifest.permission.CAMERA) == PERMISSION_GRANTED -> {
        // Perform action.
        // You can use the API that requires the permission.
        // performAction(...)
      }
      shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
        // In an educational UI, explain to the user why your app requires this permission
        // showInContextUI(...)
      }
      else -> {
        // You can directly ask for the permission.
        requestPermissions(arrayOf(Manifest.permission.CAMERA), 10)
      }
    }*/
    //val appOpsManager = getSystemService(AppOpsManager::class.java)
    //appOpsManager.setOnOpNotedCallback(mainExecutor, appOpsCallback)
  }


}