package takenoko.tech.blackboardapp.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by たけのこ on 2017/05/04.
 */

public class StaticModel {

    /** メニュー表示状態 */
    public enum MenuMode{ INVISIBLE, NONE, PEN_SIZE, PEN_COLOR}
    @Getter @Setter
    static MenuMode menuMode = MenuMode.NONE;

    /** ペンモード */
    public enum PenMode{ PEN, ERASER }
    @Getter @Setter
    static PenMode penMode = PenMode.PEN;

    /** クリアモード */
    public enum ClearMode{ NONE, CLEAR }
    @Getter @Setter
    static ClearMode clearMode = ClearMode.NONE;
}
