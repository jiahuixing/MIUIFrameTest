package com.miui.marmot.demo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;
import android.util.Log;

import junit.framework.Assert;

public class Test_Uiautomator2_Demo extends InstrumentationTestCase{
    private Context mContext;
    private UiDevice mDevice;

    @Override
    protected void setUp() {
        try {
            super.setUp();
            mContext = this.getInstrumentation().getContext();
            mDevice = UiDevice.getInstance(getInstrumentation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void test_AddContact() {
        log("Step 1 : Launch contacts Activity.");
        mDevice.pressHome();
        sleep(1);
        Intent intent = new Intent();
        intent.setClassName("com.android.contacts",
                "com.android.contacts.activities.PeopleActivity");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
        sleep(5);
        String currentPackageName = mDevice.getCurrentPackageName();
        Assert.assertEquals(currentPackageName, "com.android.contacts");

        log("Step 2 : New contact 'amy'.");
        mDevice.findObject(By.text("新建联系人")).click();
        sleep(2);
        UiObject2 name = mDevice.findObject(By.clazz("android.widget.EditText").text("姓名"));
        name.setText("amy");
        sleep(2);
        mDevice.findObject(By.text("确定")).click();
        sleep(2);
        mDevice.pressBack();
        sleep(2);

        log("Step 3 : Check 'amy' in contacts.");
        UiScrollable listView = new UiScrollable(new UiSelector().className("android.widget.ListView"));
        try {
            listView.scrollTextIntoView("amy");
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        sleep(3);
        UiObject2 amy = mDevice.findObject(By.text("amy"));
        if(amy == null) {
            Assert.assertTrue("amy not exist!", false);
        }

        log("Step 4 : Delete 'amy'.");
        //mDevice.findObject(By.text("amy")).longClick();
        Rect bounds = mDevice.findObject(By.text("amy")).getVisibleBounds();
        mDevice.drag(bounds.centerX(), bounds.centerY(), bounds.centerX(), bounds.centerY(), 6);
        sleep(2);
        mDevice.findObject(By.text("删除")).click();
        sleep(2);
        mDevice.findObject(By.clazz("android.widget.Button").text("删除")).click();
        sleep(2);

        log("Step 5 : Move to dial.");
        int x2 = mDevice.getDisplayWidth() - 5;
        int y2 = (int)(mDevice.getDisplayHeight() / 2);
        mDevice.swipe(5, y2, x2, y2, 6);
        sleep(2);
        UiObject2 dial = mDevice.findObject(By.text("拨号"));
        if(dial == null) {
            Assert.assertTrue("'dial' not exist!", false);
        }

        log("Step 6 : Quit.");
        mDevice.pressBack();
        mDevice.pressBack();
        mDevice.pressBack();
        mDevice.pressHome();
    }

    private void log(String message) {
        Log.i("MIUIAUTOTest", message);
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
        mDevice.pressBack();
        mDevice.pressBack();
        mDevice.pressHome();
        super.tearDown();
    }
}
