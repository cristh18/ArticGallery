package com.tolodev.artic_gallery.workers

import android.content.Context
import android.content.Intent
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.tolodev.artic_gallery.receivers.NotificationReceiver

class NotificationWorker(private val appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    override fun doWork(): Result {
        val intent = Intent(appContext, NotificationReceiver::class.java)
        appContext.sendBroadcast(intent)

        return Result.success()
    }
}