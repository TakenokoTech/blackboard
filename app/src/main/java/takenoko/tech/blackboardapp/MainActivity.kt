package takenoko.tech.blackboardapp

import android.graphics.PointF
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceView
import android.view.View
import takenoko.tech.blackboardapp.util.GLRenderer

class MainActivity : AppCompatActivity() {

    var log = "----MainActivity----"
    var gLSurfaceView : GLSurfaceView? = null
    var gLRender: GLRenderer? = null
    var backgroundView : View? = null
    var surfaceView : SurfaceView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(log, "onCreate")
        setContentView(R.layout.activity_main)

//        gLSurfaceView = GLSurfaceView(this)
//        gLRender = GLRenderer(this)
//        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
//        val configurationInfo = activityManager.deviceConfigurationInfo
//        Log.e(log, "----------------------");
//
//        // 端末がOpenGL ES 2.0をサポートしているかチェック
//        if (configurationInfo.reqGlEsVersion >= 0x20000) {
//            Log.e(log, configurationInfo.reqGlEsVersion.toString());
//            gLSurfaceView!!.setEGLContextClientVersion(2)  // OpenGLバージョンを設定
//            gLSurfaceView!!.setRenderer(gLRender)  // レンダラを設定
//        } else {
//            throw (AndroidException("Not Support"));
//            return
//        }
//        setContentView(gLSurfaceView)
    }

    override fun onResume() {
        super.onResume()
//        gLSurfaceView!!.onResume();
    }

    override fun onPause() {
        super.onPause()
//        gLSurfaceView!!.onResume();
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var point: PointF = PointF(event!!.getX(), event!!.getY())
//        gLRender!!.setTouchParam(point)
        Log.i(log, "("+ point.x +","+ point.y +")")
        return true
    }
}
