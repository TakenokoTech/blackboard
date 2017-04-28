package takenoko.tech.blackboardapp

import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * Created by たけのこ on 2017/04/28.
 */

class GLRenderer : GLSurfaceView.Renderer {

    // 初期描画
    override fun onSurfaceCreated(gl: GL10?, p1: EGLConfig?) {
    }

    // レンダリング
    override fun onDrawFrame(gl: GL10?) {
    }

    // 回転
    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
    }

}