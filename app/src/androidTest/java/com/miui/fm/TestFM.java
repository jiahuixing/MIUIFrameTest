package com.miui.fm;

/**
 * Project name: MIUIFrameTest
 * Package name: com.miui.fm
 * Created by jiahuixing
 * Created at 2015--07-03 23:17
 */

import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class TestFM extends InstrumentationTestCase {

    public static final String PACKAGE_NAME_FM = "";
    public static final String ACTIVITY_NAME_FM = "";
    public static final String PACKAGE_NAME_INPUT_METHOD_BAIDU = "";
    public Marmot marmot;
    public Checker checker;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        marmot = new Marmot(this);
        checker = new Checker(marmot);
    }

    public void test_FM() throws Exception {
        marmot.log("launch fm.");
        marmot.launchActivity(ACTIVITY_NAME_FM);
        marmot.waitFor(2);
        assertTrue("fm not launched.", marmot.getCurrentPackageName().equals(PACKAGE_NAME_FM));
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

}
