package com.com.miui.frame;

/**
 * Project name : marmot-cases2
 * Package name : com.miui.frame
 * Created by jiahuixing
 * Created on 2015-07-09
 * Created at 10:44
 */

import java.util.HashMap;
import java.util.Map;

public class Lib_Frame_Constants {

    // compass
    public static final String PACKAGE_NAME_COMPASS = "com.miui.compass";
    public static final String ACTIVITY_NAME_COMPASS = "com.miui.compass/.CompassActivity";
    // sound recorder
    public static final String PACKAGE_NAME_SOUND_RECORDER = "com.android.soundrecorder";
    public static final String ACTIVITY_NAME_SOUND_RECORDER = "com.android.soundrecorder/.SoundRecorder";
    // fm
    public static final String PACKAGE_NAME_FM = "com.miui.fm";
    public static final String ACTIVITY_NAME_FM = "com.miui.fm/com.miui.fmradio.FmActivity";
    public static final int MAX_FREQ = 108;
    public static final int MIN_FREQ = 88;
    // notes
    public static final String PACKAGE_NAME_NOTES = "com.miui.notes";
    public static final String ACTIVITY_NAME_NOTES = "com.miui.notes/.ui.NotesListActivity";
    // camera
    public static final String PACKAGE_NAME_CAMERA = "com.android.camera";
    public static final String ACTIVITY_NAME_CAMERA = "com.android.camera/.Camera";
    // settings
    public static final String PACKAGE_NAME_SETTINGS = "com.android.settings";
    public static final String ACTIVITY_NAME_SETTINGS = "com.android.settings/.MiuiSettings";
    // phone
    public static final String PACKAGE_NAME_CONTACTS = "com.android.contacts";
    public static final String ACTIVITY_NAME_CONTACTS = "com.android.contacts/.activities.TwelveKeyDialer";
    public static final String PACKAGE_NAME_IN_CALL_UI = "com.android.incallui";
    public static final Map<Integer, String> DIAL_RES = new HashMap<>();
    // calculator
    public static final String PACKAGE_NAME_CALCULATOR = "com.android.calculator2";
    public static final String ACTIVITY_NAME_CALCULATOR = "com.android.calculator2/.Calculator";
    // addition, subtraction, multiplication, division
    public static final String OPERATE_ADD = "+";
    public static final String OPERATE_SUB = "-";
    public static final String OPERATE_MUL = "*";
    public static final String OPERATE_DIV = "/";
    public static final String OPERATE_EQUAL = "=";
    public static final Map<Integer, String> CALCULATOR_NUMBER_RES = new HashMap<>();
    public static final Map<String, String> CALCULATOR_OPERATE_RES = new HashMap<>();
    // key guard
    public static final String PACKAGE_NAME_KEY_GUARD = "com.android.keyguard";
    // baidu input xiaomi
    public static final String PACKAGE_NAME_INPUT_METHOD_BAIDU = "com.baidu.input_mi";
    // image extension
    public static final String IMAGE_EXTENSION = ".png";
    // drag steps
    public static final int DRAG_STEPS = 10;

    static {
        DIAL_RES.put(1, "com.android.contacts:id/one");
        DIAL_RES.put(2, "com.android.contacts:id/two");
        DIAL_RES.put(3, "com.android.contacts:id/three");
        DIAL_RES.put(4, "com.android.contacts:id/four");
        DIAL_RES.put(5, "com.android.contacts:id/five");
        DIAL_RES.put(6, "com.android.contacts:id/six");
        DIAL_RES.put(7, "com.android.contacts:id/seven");
        DIAL_RES.put(8, "com.android.contacts:id/eight");
        DIAL_RES.put(9, "com.android.contacts:id/nine");
        DIAL_RES.put(0, "com.android.contacts:id/zero");
    }

    static {
        // number
        CALCULATOR_NUMBER_RES.put(0, "com.android.calculator2:id/digit0");
        CALCULATOR_NUMBER_RES.put(1, "com.android.calculator2:id/digit1");
        CALCULATOR_NUMBER_RES.put(2, "com.android.calculator2:id/digit2");
        CALCULATOR_NUMBER_RES.put(3, "com.android.calculator2:id/digit3");
        CALCULATOR_NUMBER_RES.put(4, "com.android.calculator2:id/digit4");
        CALCULATOR_NUMBER_RES.put(5, "com.android.calculator2:id/digit5");
        CALCULATOR_NUMBER_RES.put(6, "com.android.calculator2:id/digit6");
        CALCULATOR_NUMBER_RES.put(7, "com.android.calculator2:id/digit7");
        CALCULATOR_NUMBER_RES.put(8, "com.android.calculator2:id/digit8");
        CALCULATOR_NUMBER_RES.put(9, "com.android.calculator2:id/digit9");
        // operate
        CALCULATOR_OPERATE_RES.put(OPERATE_ADD,
                "com.android.calculator2:id/plus");
        CALCULATOR_OPERATE_RES.put(OPERATE_SUB,
                "com.android.calculator2:id/minus");
        CALCULATOR_OPERATE_RES.put(OPERATE_MUL,
                "com.android.calculator2:id/mul");
        CALCULATOR_OPERATE_RES.put(OPERATE_DIV,
                "com.android.calculator2:id/div");
        CALCULATOR_OPERATE_RES.put(OPERATE_DIV,
                "com.android.calculator2:id/equal");
    }

}
