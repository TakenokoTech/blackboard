package tech.takenoko.blackboardapp.model;

import android.graphics.Paint;

import lombok.Getter;
import lombok.Setter;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;

/**
 * Created by たけのこ on 2017/04/30.
 */

public class DrawSurfaceModel {

    @Getter @Setter
    float surfaceX = 0, surfaceY = 0;
    @Getter @Setter
    float penX = 100, penY = 100;
    @Getter @Setter
    float penDx = 8, penDy = 8;

    //================================================
    // PAINT
    @Getter Paint redLine = new Paint();
    @Getter Paint blueLine = new Paint();
    @Getter Paint whiteLine = new Paint();

    public DrawSurfaceModel() {
        redLine.setColor(RED);
        blueLine.setColor(BLUE);
        whiteLine.setColor(WHITE);
    }

    public void moveSurface() {
        // 領域チェック
        if (getPenX() < 0 || getSurfaceX() < getPenX()) setPenDx(-getPenDx());
        if (getPenY() < 0 || getSurfaceY() < getPenY()) setPenDy(-getPenDy());
        // 円の座標を移動させる
        setPenX(getPenX() + getPenDx());
        setPenY(getPenY() + getPenDy());
    }

}
