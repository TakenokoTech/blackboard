package tech.takenoko.blackboardapp.model;

import lombok.Getter;
import lombok.Setter;

import static tech.takenoko.blackboardapp.model.StaticModel.DebugMode.FALSE;

/**
 * Created by たけのこ on 2017/05/04.
 */

public class StaticModel {

    /** 全体状態 */
    public enum AppStatus{ ACTIVE, STOP, RESTART }
    @Getter @Setter
    static AppStatus appStatus = AppStatus.ACTIVE;

    /** メニュー表示状態 */
    public enum MenuMode{ INVISIBLE, VISIBLE }
    @Getter @Setter
    static MenuMode menuMode = MenuMode.INVISIBLE;

    /** ペンモード */
    public enum PenMode{ PEN, ERASER }
    @Getter @Setter
    static PenMode penMode = PenMode.PEN;

    /** クリアモード */
    public enum ClearMode{ NONE, CLEAR }
    @Getter @Setter
    static ClearMode clearMode = ClearMode.NONE;

    /** デバックモード */
    public enum DebugMode{ TRUE, FALSE }
    @Getter @Setter
    static DebugMode debugMode = FALSE;

    /** ステータス表示 */
    public enum ViewStatus{ NONE, VIEW, SUB }
    @Getter @Setter
    static ViewStatus viewStatus = ViewStatus.VIEW;

    /** 入出力ダイアログ表示 */
    public enum IoDialogMode{ NONE, IMPORT, EXPORT }
    @Getter @Setter
    static IoDialogMode ioDialogMode = IoDialogMode.NONE;

    /** ダイアログ表示 */
    public enum DialogMode{ NONE, SHARE , CLEAR}
    @Getter @Setter
    static DialogMode dialogMode = DialogMode.NONE;

    /** オーバーレイ表示 */
    public enum OverlayMode{ SHOW, HIDDEN }
    @Getter @Setter
    static OverlayMode overlayMode = OverlayMode.HIDDEN;

    /** 設定表示 */
    public enum SettingMode{ NONE, VIEW }
    @Getter @Setter
    static SettingMode settingMode = SettingMode.NONE;
}
