package com.miui.frame;

/**
 * Project name : marmot-cases2
 * Package name : com.miui.frame
 * Created by jiahuixing
 * Created on 2015-07-09
 * Created at 10:44
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Lib_Frame_Constants {

    // camera
    public static final String PACKAGE_NAME_CAMERA = "com.android.camera";
    public static final String ACTIVITY_NAME_CAMERA = "com.android.camera/.Camera";
    // gallery
    public static final String PACKAGE_NAME_GALLERY = "com.miui.gallery";
    public static final String ACTIVITY_NAME_GALLERY = "com.miui.gallery/.app.Gallery";
    // music
    public static final String PACKAGE_NAME_MUSIC = "com.miui.player";
    public static final String ACTIVITY_NAME_MUSIC = "com.miui.player/.ui.MusicBrowserActivity";
    public static final String ACTIVITY_NAME_MUSIC_PAD = "com.miui.player/.pad.ui.MusicMainActivity";
    // market
    public static final String PACKAGE_NAME_MARKET = "com.xiaomi.market";
    public static final String ACTIVITY_NAME_MARKET = "com.xiaomi.market/.ui.MarketTabActivity";
    // theme manager
    public static final String PACKAGE_NAME_THEME_MANAGER = "com.android.thememanager";
    public static final String ACTIVITY_NAME_THEME_MANAGER = "com.android.thememanager/.ThemeResourceTabActivity";
    // settings
    public static final String PACKAGE_NAME_SETTINGS = "com.android.settings";
    public static final String ACTIVITY_NAME_SETTINGS = "com.android.settings/.MiuiSettings";
    // mi account
    public static final String PACKAGE_NAME_MI_ACCOUNT = "com.xiaomi.account";
    public static final String ACTIVITY_NAME_MI_ACCOUNT = "com.xiaomi.account/.ui.AccountSettingsActivity";
    // email
    public static final String PACKAGE_NAME_EMAIL = "com.android.email";
    public static final String ACTIVITY_NAME_EMAIL = "com.android.email/.activity.Welcome";
    // updater
    public static final String PACKAGE_NAME_UPDATER = "com.android.updater";
    public static final String ACTIVITY_NAME_UPDATER = "com.android.updater/.MainActivity";
    // clock
    public static final String PACKAGE_NAME_CLOCK = "com.android.deskclock";
    public static final String ACTIVITY_NAME_CLOCK = "com.android.deskclock/.DeskClockTabActivity";
    // sound recorder
    public static final String PACKAGE_NAME_SOUND_RECORDER = "com.android.soundrecorder";
    public static final String ACTIVITY_NAME_SOUND_RECORDER = "com.android.soundrecorder/.SoundRecorder";
    // fm
    public static final String PACKAGE_NAME_FM = "com.miui.fm";
    public static final String ACTIVITY_NAME_FM = "com.miui.fm/com.miui.fmradio.FmActivity";
    public static final String PACKAGE_NAME_FM_RADIO = "com.miui.fmradio";
    public static final String ACTIVITY_NAME_FM_RADIO = "com.miui.fmradio/.FmRadioActivity";
    public static final int MAX_FREQ = 108;
    public static final int MIN_FREQ = 88;
    // file explorer
    public static final String PACKAGE_NAME_FILE_EXPLORER = "com.android.fileexplorer";
    public static final String ACTIVITY_NAME_FILE_EXPLORER = "com.android.fileexplorer/.FileExplorerTabActivity";
    // compass
    public static final String PACKAGE_NAME_COMPASS = "com.miui.compass";
    public static final String ACTIVITY_NAME_COMPASS = "com.miui.compass/.CompassActivity";
    // mi financial
    public static final String PACKAGE_NAME_MI_FINANCIAL = "com.xiaomi.jr";
    public static final String ACTIVITY_NAME_MI_FINANCIAL = "com.xiaomi.jr/.CTAActivity";
    // mi wallet
    public static final String PACKAGE_NAME_MI_WALLET = "com.mipay.wallet";
    public static final String ACTIVITY_NAME_MI_WALLET = "com.mipay.wallet/.ui.MipayEntryActivity";
    // bug report
    public static final String PACKAGE_NAME_BUG_REPORT = "com.miui.bugreport";
    public static final String ACTIVITY_NAME_BUG_REPORT = "com.miui.bugreport/.ui.MainActivity";
    // voice assist
    public static final String PACKAGE_NAME_VOICE_ASSIST = "com.miui.voiceassist";
    public static final String ACTIVITY_NAME_VOICE_ASSIST = "com.miui.voiceassist/.CTAAlertActivity";
    // download manager
    public static final String PACKAGE_NAME_DOWNLOAD_MANAGER = "com.android.providers.downloads.ui";
    public static final String ACTIVITY_NAME_DOWNLOAD_MANAGER = "com.android.providers.downloads.ui/.DownloadList";
    // bar code
    public static final String PACKAGE_NAME_BAR_CODE = "com.miui.barcodescanner";
    public static final String ACTIVITY_NAME_BAR_CODE = "com.miui.barcodescanner/.activity.CaptureActivity";
    // security center
    public static final String PACKAGE_NAME_SECURITY_CENTER = "com.miui.securitycenter";
    public static final String ACTIVITY_NAME_SECURITY_CENTER = "com.miui.securitycenter/.MainActivity";
    // browser
    public static final String PACKAGE_NAME_BROWSER = "com.android.browser";
    public static final String ACTIVITY_NAME_BROWSER = "com.android.browser/.BrowserActivity";
    // sms
    public static final String PACKAGE_NAME_SMS = "com.android.mms";
    public static final String ACTIVITY_NAME_SMS = "com.android.mms/.ui.MmsTabActivity";
    // calender
    public static final String PACKAGE_NAME_CALENDER = "com.android.calendar";
    public static final String ACTIVITY_NAME_CALENDER = "com.android.calendar/.AllInOneActivity";
    // notes
    public static final String PACKAGE_NAME_NOTES = "com.miui.notes";
    public static final String ACTIVITY_NAME_NOTES = "com.miui.notes/.ui.NotesListActivity";
    // weather
    public static final String PACKAGE_NAME_WEATHER = "com.miui.weather2";
    public static final String ACTIVITY_NAME_WEATHER = "com.miui.weather2/.ActivityWeatherMain";
    // smart home
    public static final String PACKAGE_NAME_SMART_HOME = "com.xiaomi.smarthome";
    public static final String ACTIVITY_NAME_SMART_HOME = "com.xiaomi.smarthome/.StartupActivity";
    // game center
    public static final String PACKAGE_NAME_GAME_CENTER = "com.xiaomi.gamecenter";
    public static final String ACTIVITY_NAME_GAME_CENTER = "com.xiaomi.gamecenter/.ui.MainTabActivity";
    public static final String PACKAGE_NAME_GAME_CENTER_PAD = "com.xiaomi.gamecenter.pad";
    public static final String ACTIVITY_NAME_GAME_CENTER_PAD = "com.xiaomi.gamecenter.pad/.MainTabActivity";
    // video
    public static final String PACKAGE_NAME_VIDEO = "com.miui.video";
    public static final String ACTIVITY_NAME_VIDEO = "com.miui.video/.HomeActivity";
    // mi life
    public static final String PACKAGE_NAME_MI_LIFE = "com.xiaomi.o2o";
    public static final String ACTIVITY_NAME_MI_LIFE = "com.xiaomi.o2o/.activity.O2OTabActivity";
    // backup
    public static final String PACKAGE_NAME_BACKUP = "com.miui.backup";
    // public static final String ACTIVITY_NAME_BACKUP =
    // "com.miui.backup/.BackupActivity";
    // home
    public static final String PACKAGE_NAME_HOME = "com.miui.home";
    // phone
    public static final String PACKAGE_NAME_CONTACTS = "com.android.contacts";
    public static final String ACTIVITY_NAME_CONTACTS = "com.android.contacts/.activities.PeopleActivity";
    public static final String ACTIVITY_NAME_CONTACTS_DIALER = "com.android.contacts/.activities.TwelveKeyDialer";
    // public static final String ACTIVITY_NAME_PHONE_SETTINGS =
    // "com.android.phone/.CallFeaturesSetting";
    public static final String PACKAGE_NAME_IN_CALL_UI = "com.android.incallui";
    public static final Map<Integer, String> DIAL_RES = new HashMap<>();
    // calculator
    public static final String PACKAGE_NAME_CALCULATOR = "com.android.calculator2";
    public static final String ACTIVITY_NAME_CALCULATOR = "com.android.calculator2/.Calculator";
    // addition, subtraction, multiplication, division
    public static final String OPERATION_ADD = "+";
    public static final String OPERATION_SUB = "-";
    public static final String OPERATION_MUL = "*";
    public static final String OPERATION_DIV = "/";
    public static final String OPERATION_EQUAL = "=";
    public static final Map<Integer, String> CALCULATOR_NUMBER_RES = new HashMap<>();
    public static final Map<String, String> CALCULATOR_OPERATION_RES = new HashMap<>();
    // key guard
    public static final String PACKAGE_NAME_KEY_GUARD = "com.android.keyguard";
    // baidu input xiaomi
    public static final String PACKAGE_NAME_INPUT_METHOD_BAIDU = "com.baidu.input_mi";
    // image extension
    public static final String IMAGE_EXTENSION = ".png";
    // drag steps
    public static final int DRAG_STEPS = 10;

    static {
        DIAL_RES.put(0, "com.android.contacts:id/zero");
        DIAL_RES.put(1, "com.android.contacts:id/one");
        DIAL_RES.put(2, "com.android.contacts:id/two");
        DIAL_RES.put(3, "com.android.contacts:id/three");
        DIAL_RES.put(4, "com.android.contacts:id/four");
        DIAL_RES.put(5, "com.android.contacts:id/five");
        DIAL_RES.put(6, "com.android.contacts:id/six");
        DIAL_RES.put(7, "com.android.contacts:id/seven");
        DIAL_RES.put(8, "com.android.contacts:id/eight");
        DIAL_RES.put(9, "com.android.contacts:id/nine");
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
        CALCULATOR_OPERATION_RES.put(OPERATION_ADD,
                "com.android.calculator2:id/plus");
        CALCULATOR_OPERATION_RES.put(OPERATION_SUB,
                "com.android.calculator2:id/minus");
        CALCULATOR_OPERATION_RES.put(OPERATION_MUL,
                "com.android.calculator2:id/mul");
        CALCULATOR_OPERATION_RES.put(OPERATION_DIV,
                "com.android.calculator2:id/div");
        CALCULATOR_OPERATION_RES.put(OPERATION_EQUAL,
                "com.android.calculator2:id/equal");
    }

    // device not supports fm
    public static final ArrayList<String> PRODUCT_NAME_NO_FM = new ArrayList<>();
    public static final String PRODUCT_NAME_VIRGO = "virgo";
    public static final String PRODUCT_NAME_LEO = "leo";

    static {
        PRODUCT_NAME_NO_FM.add(PRODUCT_NAME_VIRGO);
        PRODUCT_NAME_NO_FM.add(PRODUCT_NAME_LEO);
    }

    // loop test times for timeline test
    public static final int TEST_LOOPS = 12;

}
