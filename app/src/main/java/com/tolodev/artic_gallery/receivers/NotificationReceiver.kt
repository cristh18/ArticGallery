package com.tolodev.artic_gallery.receivers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.tolodev.artic_gallery.R
import kotlin.random.Random

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        notifyPushNotification(context)
    }

    fun createNotificationChannel(
        context: Context,
        channelId: String,
        channelName: String
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = "Channel description"
                enableVibration(true)
                vibrationPattern = longArrayOf(0, 1000, 500, 1000)
                setShowBadge(true)
                enableLights(true)
                lightColor = Color.GREEN
            }
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager
                .createNotificationChannel(channel)
        }
    }

    private fun notifyPushNotification(context: Context) {
        val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(context.getString(R.string.copy_push_notification_title))
            .setContentText(context.getString(R.string.copy_push_notification_message))
            .setSmallIcon(R.drawable.ic_notifications_active)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVibrate(longArrayOf(0, 1000, 500, 1000))
            .build()

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(Random.nextInt(), notificationBuilder)
    }

    companion object {
        const val CHANNEL_ID = "ARTWORKS_CHANNEL_ID"
        const val CHANNEL_NAME = "ARTWORKS_CHANNEL_NAME"
    }
}