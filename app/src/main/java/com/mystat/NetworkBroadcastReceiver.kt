package com.mystat

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import androidx.annotation.RequiresApi
import com.mystat.programming.ConnectionTypeObject
import com.mystat.programming.ProgrammingRepository

class NetworkBroadcastReceiver: BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent) {

        val conn = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = conn.activeNetworkInfo

        when {
            networkInfo?.type == ConnectivityManager.TYPE_WIFI -> {
                ConnectionTypeObject.connectionType = ProgrammingRepository.WIFI
            }
            networkInfo != null -> {
                ConnectionTypeObject.connectionType = ProgrammingRepository.ANY
            }
            else -> {
                ConnectionTypeObject.connectionType = ProgrammingRepository.NO
            }
        }
    }
}