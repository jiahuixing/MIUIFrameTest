package com.miui.phone;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

import java.util.HashMap;
import java.util.Map;

public class Test_00000002_Phone extends InstrumentationTestCase {

    public static final String PACKAGE_NAME_CONTACTS = "com.android.contacts";
    public static final String ACTIVITY_NAME_CONTACTS = "com.android.contacts/.activities.TwelveKeyDialer";
    public static final String PACKAGE_NAME_KEY_GUARD = "com.android.keyguard";
    public static final String PACKAGE_NAME_IN_CALL_UI = "com.android.incallui";

    public static final String IMAGE_EXTENSION = ".png";

    public static final String RES_DIAL_ONE = "com.android.contacts:id/one";
    public static final String RES_DIAL_TWO = "com.android.contacts:id/two";
    public static final String RES_DIAL_THREE = "com.android.contacts:id/three";
    public static final String RES_DIAL_FOUR = "com.android.contacts:id/four";
    public static final String RES_DIAL_FIVE = "com.android.contacts:id/five";
    public static final String RES_DIAL_SIX = "com.android.contacts:id/six";
    public static final String RES_DIAL_SEVEN = "com.android.contacts:id/seven";
    public static final String RES_DIAL_EIGHT = "com.android.contacts:id/eight";
    public static final String RES_DIAL_NINE = "com.android.contacts:id/nine";
    public static final String RES_DIAL_ZERO = "com.android.contacts:id/zero";

    public static final Map<String, String> DIAL_RES = new HashMap<>();

    static {
        DIAL_RES.put("1", RES_DIAL_ONE);
        DIAL_RES.put("2", RES_DIAL_TWO);
        DIAL_RES.put("3", RES_DIAL_THREE);
        DIAL_RES.put("4", RES_DIAL_FOUR);
        DIAL_RES.put("5", RES_DIAL_FIVE);
        DIAL_RES.put("6", RES_DIAL_SIX);
        DIAL_RES.put("7", RES_DIAL_SEVEN);
        DIAL_RES.put("8", RES_DIAL_EIGHT);
        DIAL_RES.put("9", RES_DIAL_NINE);
        DIAL_RES.put("0", RES_DIAL_ZERO);
    }

    public Marmot marmot;
    public Checker checker;
    public UiDevice uiDevice;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        marmot = new Marmot(this);
        checker = new Checker(marmot);
        uiDevice = marmot.getUiDevice();
        if (!marmot.isScreenOn()) {
            marmot.wakeUp();
            marmot.waitFor(1);
        }
        if (marmot.getCurrentPackageName().equals(PACKAGE_NAME_KEY_GUARD)) {
            marmot.move(Direction.UP);
            marmot.waitFor(1);
        }
    }

    public void test_00000002_Phone() throws Exception {
        marmot.log("launch contacts.");
        marmot.launchActivity(ACTIVITY_NAME_CONTACTS);
        marmot.waitFor(2);
        checker.assertTrue("contacts",
                marmot.getCurrentPackageName().equals(PACKAGE_NAME_CONTACTS));
        UiObject2 showDialPad, dialPad;
        dialPad = marmot.getUiObject(By.clazz("android.widget.LinearLayout")
                .res("com.android.contacts:id/dialpad"));
        if (dialPad == null) {
            showDialPad = marmot.getUiObject(By.clazz("android.widget.Button")
                    .text("拨号"));
            showDialPad.click();
            marmot.waitFor(2);
        }
        checker.assertTrue("dialNumber", dialNumber("112"));
        marmot.saveScreenshot("dialNumber" + IMAGE_EXTENSION);
        UiObject2 callSim1;
        callSim1 = marmot.getUiObject(By.clazz("android.widget.Button").res(
                "com.android.contacts:id/call_sim1"));
        callSim1.click();
        marmot.waitFor(2);
        checker.assertTrue("incallui",
                marmot.getCurrentPackageName().equals(PACKAGE_NAME_IN_CALL_UI));
        marmot.waitFor(5);

        marmot.saveScreenshot("incallui" + IMAGE_EXTENSION);

        UiObject2 numberclosed;
        numberclosed = marmot.getUiObject(By.clazz("android.widget.ImageView").res(
                "com.android.incallui:id/dialpadArrow"));
        numberclosed.click();
        marmot.waitFor(1);
        marmot.saveScreenshot("numberclosed" + IMAGE_EXTENSION);

        UiObject2 numberopen;
        numberopen = marmot.getUiObject(By.clazz("android.widget.ImageView").res(
                "com.android.incallui:id/dialpadArrow"));
        numberopen.click();
        marmot.waitFor(1);
        marmot.saveScreenshot("numberopen" + IMAGE_EXTENSION);

        UiObject2 hangOff;
        hangOff = marmot.getUiObject(By.clazz("android.widget.Button").res(
                "com.android.incallui:id/endButton"));
        hangOff.click();
        marmot.waitFor(2);
    }


    private boolean dialNumber(String phoneNumber) {
        UiObject2 dialNumber;
        int phoneNumberLength;
        char temp;
        phoneNumberLength = phoneNumber.length();
        for (int i = 0; i < phoneNumberLength; i++) {
            temp = phoneNumber.charAt(i);
            marmot.log(String.format("temp: %s", temp));
            if (Character.isDigit(temp)) {
                dialNumber = marmot.getUiObject(By.clazz(
                        "android.widget.ImageView").res(
                        DIAL_RES.get(String.valueOf(temp))));
                dialNumber.click();
                marmot.waitFor(0.5);
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
// C513249 通话中开/关键盘
