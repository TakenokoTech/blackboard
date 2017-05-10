package takenoko.tech.blackboardapp.model;

import android.graphics.Color;

import java.io.Serializable;
import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;

import static takenoko.tech.blackboardapp.model.SettingModel.BackColor.BLACK_BOARD;
import static takenoko.tech.blackboardapp.model.SettingModel.BackColor.GREEN_BOARD;
import static takenoko.tech.blackboardapp.model.SettingModel.BackColor.WHITE_BOARD;
import static takenoko.tech.blackboardapp.model.SettingModel.FlameBool.ON;

/**
 * Created by たけのこ on 2017/05/07.
 */

public class SettingModel implements Serializable {

    /** 背景色 */
    public enum BackColor{ BLACK_BOARD, GREEN_BOARD, WHITE_BOARD }
    @Getter @Setter
    public static BackColor backColor = BackColor.GREEN_BOARD;
    @Getter
    public static HashMap<BackColor, Integer> backColorHashMap = new HashMap<BackColor, Integer>() {{
        put(BLACK_BOARD, Color.argb(255, 42, 42, 40));
        put(GREEN_BOARD, Color.argb(255, 20, 50, 20));
        put(WHITE_BOARD, Color.argb(255, 250, 250, 250));
    }};
    public static int getBackColorARGB() {return backColorHashMap.get(backColor);}
    @Getter
    public static HashMap<BackColor, Integer> Flame1HashMap = new HashMap<BackColor, Integer>() {{
        put(BLACK_BOARD, Color.rgb(208, 208, 208));
        put(GREEN_BOARD, Color.rgb(155, 100,   5));
        put(WHITE_BOARD, Color.rgb(245, 215, 170));
    }};
    @Getter
    public static HashMap<BackColor, Integer> Flame2HashMap = new HashMap<BackColor, Integer>() {{
        put(BLACK_BOARD, Color.rgb(192, 192, 192));
        put(GREEN_BOARD, Color.rgb(125,  85,  30));
        put(WHITE_BOARD, Color.rgb(220, 230, 190));
    }};

    /** 枠有無 */
    public enum FlameBool{ON, OFF}
    @Getter @Setter
    public static FlameBool flameBool = ON;
}
