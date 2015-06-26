package com.miui.miuiframetest;

/**
 * Project name: MIUIFrameTest
 * Package name: com.miui.miuiframetest
 * Created by jiahuixing
 * Created at 2015--06-26 22:29
 */

import android.content.Context;
import android.content.Intent;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

public class FrameTest extends InstrumentationTestCase {
    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;
    private static final int DRAG_STEPS = 10;
    private static final String PACKAGE_NAME_NOTES = "";
    private static final String ACTIVITY_NAME_NOTES = "";
    private Context mContext;
    private UiDevice uiDevice;
    private int mHeight;
    private int mWidth;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mContext = getInstrumentation().getContext();
        uiDevice = UiDevice.getInstance(getInstrumentation());
        mHeight = uiDevice.getDisplayHeight();
        mWidth = uiDevice.getDisplayWidth();
    }

    public void test001_Notes() throws Exception {
        Intent intent = new Intent();
        intent.setClassName(PACKAGE_NAME_NOTES, ACTIVITY_NAME_NOTES);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
        uiDevice.waitForWindowUpdate(PACKAGE_NAME_NOTES, 5);
        sleep(1);
        swipe(UP);
        UiObject2 newNote = uiDevice.findObject(By.textContains(""));
        newNote.click();
        sleep(1);
    }

    private void swipe(int direction) {
        int startX, startY, endX, endY;
        switch (direction) {
            case UP:
                startX = mWidth / 2;
                startY = mHeight * 3 / 4;
                endX = mWidth / 2;
                endY = mHeight / 4;
                break;
            case DOWN:
                startX = mWidth / 2;
                startY = mHeight / 4;
                endX = mWidth / 2;
                endY = mHeight * 3 / 4;
                break;
            case LEFT:
                startX = mWidth * 3 / 4;
                startY = mHeight / 2;
                endX = mWidth / 4;
                endY = mHeight / 2;
                break;
            case RIGHT:
                startX = mWidth / 4;
                startY = mHeight * 3 / 4;
                endX = mWidth * 3 / 4;
                endY = mHeight / 2;
                break;
            default:
                startX = mWidth / 2;
                startY = mHeight * 3 / 4;
                endX = mWidth / 2;
                endY = mHeight / 4;
                break;
        }
        uiDevice.swipe(startX, startY, endX, endY, DRAG_STEPS);
    }

    private void sleep(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
