package takenoko.tech.blackboardapp.util

import android.content.Context
import android.graphics.PointF
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10


/**
 * Created by たけのこ on 2017/04/28.
 */

class GLRenderer(context: Context) : GLSurfaceView.Renderer {

    var context : Context = context

    // 初期描画
    override fun onSurfaceCreated(gl: GL10?, p1: EGLConfig?) {
        if(gl == null) return
        //背景色をクリア
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        //ディザを無効化
        gl.glDisable(GL10.GL_DITHER);
        //深度テストを有効化
        gl.glEnable(GL10.GL_DEPTH_TEST);
        //テクスチャ機能ON
        gl.glEnable(GL10.GL_TEXTURE_2D);
        //透明可能に
        gl.glEnable(GL10.GL_ALPHA_TEST);
        //ブレンド可能に
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
    }

    // レンダリング
    override fun onDrawFrame(gl: GL10?) {
        if(gl == null) return
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        val vertex = floatArrayOf(20f, 20f, 0f, 50f, 50f, 0f)
        //GLES20.glEnableClientState(GL10.GL_VERTEX_ARRAY)
        //GLES20.glVertexPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(vertex))
        GLES20.glDrawArrays(GL10.GL_LINE_STRIP, 0, 2)
    }

    // 回転
    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        if(gl == null) return
        gl?.glViewport(0, 0, width, height)
//        gl?.glMatrixMode(GL10.GL_PROJECTION) //プロジェクションモードに設定
//        GLU.gluOrtho2D(gl, 0.0f, width.toFloat(), 0.0f, height.toFloat()) //平行投影用のパラメータをセット
    }

    /**
     * タッチイベント取得
     */
    fun setTouchParam(point: PointF) {

    }

    fun makeFloatBuffer(arr: FloatArray): FloatBuffer {
        val bb = ByteBuffer.allocateDirect(arr.size * 4)
        bb.order(ByteOrder.nativeOrder())
        val fb = bb.asFloatBuffer()
        fb.put(arr)
        fb.position(0)
        return fb
    }
}