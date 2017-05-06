package takenoko.tech.blackboardapp.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by たけのこ on 2017/05/04.
 */

public class StaticModel {

    /** メニュー表示状態 */
    public enum MenuMode{ INVISIBLE, VISIBLE }
    @Getter @Setter
    static MenuMode menuMode = MenuMode.VISIBLE;

    /** ペンモード */
    public enum PenMode{ PEN, ERASER }
    @Getter @Setter
    static PenMode penMode = PenMode.PEN;

    /** クリアモード */
    public enum ClearMode{ NONE, CLEAR }
    @Getter @Setter
    static ClearMode clearMode = ClearMode.NONE;

    /** デバックモード */
    public enum DebugMode{ NONE, VIEW }
    @Getter @Setter
    static DebugMode debugMode = DebugMode.VIEW;

    /** ステータス表示 */
    public enum ViewStatus{ NONE, VIEW, SUB}
    @Getter @Setter
    static ViewStatus viewStatus = ViewStatus.VIEW;

    /** ダイアログ表示 */
    public enum DialogMode{ NONE, SHARE , CLEAR}
    @Getter @Setter
    static DialogMode dialogMode = DialogMode.NONE;
}
