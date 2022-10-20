package com.solanacode.workmanagerchap6.viewmodel

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.solanacode.workmanagerchap6.R
import com.solanacode.workmanagerchap6.utils.BlurWorker
import com.solanacode.workmanagerchap6.utils.KEY_IMAGE_URI

class BlurVIewModel(application : Application):ViewModel() {
    //  var untuk instance  WorkManager di ViewModel
    private val workManager = WorkManager.getInstance(application)
    private var imageUri: Uri? = null


    init {
        // This transformation makes sure that whenever the current work Id changes the WorkInfo
        // the UI is listening to changes
        imageUri = getImageUri(application.applicationContext)

    }

    //    make WorkRequest & beritahu WM untuk jalankan
    internal fun applyBlur(blurLevel: Int) {
        val blurRequest = OneTimeWorkRequestBuilder<BlurWorker>()
            .setInputData(createInputDataForUri())
            .build()
        workManager.enqueue(blurRequest)
    }

    //create URI img
    private fun createInputDataForUri(): Data {
        val builder = Data.Builder()
        imageUri?.let {
            builder.putString(KEY_IMAGE_URI, imageUri.toString())
        }
        return builder.build()
    }

    //    get image URI
    private fun getImageUri(context: Context): Uri {
        val resources = context.resources

        return Uri.Builder()
            .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
            .authority(resources.getResourcePackageName(R.drawable.tokyo))
            .appendPath(resources.getResourceTypeName(R.drawable.tokyo))
            .appendPath(resources.getResourceEntryName(R.drawable.tokyo))
            .build()
    }

}

class BlurViewModelFactory(private val application: Application) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(BlurVIewModel::class.java)) {
            BlurVIewModel(application) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}