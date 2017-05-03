package takenoko.tech.blackboardapp.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by たけのこ on 2017/05/04.
 */

public class StaticModel {

    /** ペンモード */
    public enum PenMode{ PEN, ERASER }
    @Getter @Setter
    static PenMode penMode = PenMode.PEN;
}
