package br.com.paulosalvatore.pagseguroandroidturmab

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    companion object {
        private const val TAG = "FMService"
    }

    override fun onNewToken(token: String?) {
        Log.i(TAG, token)

        val instance = FirebaseMessaging.getInstance()
        instance.subscribeToTopic("MAIN")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val notification = remoteMessage.notification

        Log.i(TAG, "FCM Notification: $notification")
        Log.i(TAG, "FCM Message ID: ${remoteMessage.messageId}")
        Log.i(TAG, "FCM Data Message: ${remoteMessage.data}")

        notification?.let {
            val title = it.title ?: ""
            val body = it.body ?: ""

            Log.i(TAG, "FCM Notification Title: $title")
            Log.i(TAG, "FCM Notification Body: $body")

            // Create push notification here
            NotificationCreation.create(this, title, body)
        }
    }
}
