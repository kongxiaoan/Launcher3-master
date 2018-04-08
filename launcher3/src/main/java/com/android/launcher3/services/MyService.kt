package com.android.launcher3.services

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import com.android.gallery3d.common.Utils
import java.util.*

class MyService : Service() {

    private  var mTimer: Timer? = null
    private val handler = Handler()

    override fun onBind(intent: Intent): IBinder? {
        // TODO: Return the communication channel to the service.
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if(mTimer == null){
            mTimer = Timer()
            mTimer?.scheduleAtFixedRate(RefreshTask(), 0, 500)

        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        mTimer?.cancel()
    }
    /**
     * 定时是否被杀死
     */
    internal inner class RefreshTask : TimerTask() {

        override fun run() {
            handler.post {
                Utils.createHoverWindow(applicationContext)
            }
        }

    }
}
