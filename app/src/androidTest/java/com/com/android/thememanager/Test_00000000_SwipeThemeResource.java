package com.com.android.thememanager;

import android.content.Context;
import android.content.Intent;
import android.support.test.uiautomator.UiDevice;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00000000_SwipeThemeResource extends InstrumentationTestCase {

    public Marmot mm;
    public Checker cc;
    private Context mContext;
    private UiDevice mDevice;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mm = new Marmot(this);
        cc = new Checker(mm);

        try {
            super.setUp();
            mContext = this.getInstrumentation().getContext();
            mDevice = UiDevice.getInstance(getInstrumentation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //   滑动浏览
    public void testSwipeThemeResource() {
        mDevice.pressHome();
        sleep(2);
        Intent intent = new Intent();
        intent.setClassName("com.android.thememanager",
                "com.android.thememanager.ThemeResourceTabActivity");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        mContext.startActivity(intent);
        mm.waitFor(5);

        mContext.startActivity(intent);
        mm.waitFor(5);

        mDevice.swipe(500, 1000, 500, 200, 4);
        sleep(3);
        mDevice.swipe(500, 1000, 500, 200, 4);
        sleep(3);

        mDevice.pressBack();
        mDevice.pressHome();
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
