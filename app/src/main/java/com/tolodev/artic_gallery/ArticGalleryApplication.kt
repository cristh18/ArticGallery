package com.tolodev.artic_gallery

import android.app.Application
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.tolodev.artic_gallery.receivers.NotificationReceiver
import com.tolodev.artic_gallery.workers.NotificationWorker
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class ArticGalleryApplication : Application() {


    @Inject
    lateinit var articGalleryNotificationReceiver: NotificationReceiver

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        articGalleryNotificationReceiver.createNotificationChannel(
            this,
            NotificationReceiver.CHANNEL_ID,
            NotificationReceiver.CHANNEL_NAME
        )

        val workRequest = PeriodicWorkRequestBuilder<NotificationWorker>(15, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(this).enqueue(workRequest)
    }
}