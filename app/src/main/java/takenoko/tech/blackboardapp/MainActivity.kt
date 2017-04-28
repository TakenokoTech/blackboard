package takenoko.tech.blackboardapp

import android.app.ActivityManager
import android.content.Context
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    var gLSurfaceView : GLSurfaceView = null!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gLSurfaceView = GLSurfaceView(this)
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val configurationInfo = activityManager.deviceConfigurationInfo

        // 端末がOpenGL ES 2.0をサポートしているかチェック
        if (configurationInfo.reqGlEsVersion >= 0x20000) {
            gLSurfaceView.setEGLContextClientVersion(2)  // OpenGLバージョンを設定
            gLSurfaceView.setRenderer(GLRenderer())  // レンダラを設定
        } else {
            return
        }
        setContentView(gLSurfaceView)
    }

    override fun onResume() {
        super.onResume()
        gLSurfaceView.onResume();
    }

    override fun onPause() {
        super.onPause()
        gLSurfaceView.onResume();
    }
}
