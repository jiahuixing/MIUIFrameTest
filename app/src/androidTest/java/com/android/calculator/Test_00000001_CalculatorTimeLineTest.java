package com.android.calculator;

/**
 * Project name : marmot-cases2
 * Package name : com.android.calculator
 * Created by jiahuixing
 * Created on 2015-07-13
 * Created at 14:06
 */

import android.content.Context;
import android.support.test.uiautomator.UiDevice;
import android.test.InstrumentationTestCase;

import com.miui.frame.Lib_Frame_Constants;
import com.miui.frame.Lib_Frame_Utils;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00000001_CalculatorTimeLineTest extends
        InstrumentationTestCase {

    public Marmot marmot;
    public Checker checker;
    public UiDevice uiDevice;
    public Context context;

    public static int testStep = 0;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        marmot = new Marmot(this);
        checker = new Checker(marmot);
        context = marmot.getContext();
        Lib_Frame_Utils.unLock(marmot);
    }

    public void test_CalculatorTimeLineTest() throws Exception {
        for (int i = 0; i < Lib_Frame_Constants.TEST_LOOPS; i++) {
            testStep += 1;
            marmot.log(String.format("%s. launch calculator.", testStep));
            marmot.launchActivity(Lib_Frame_Constants.ACTIVITY_NAME_CALCULATOR);
            // Lib_Frame_Utils.launchActivityNoHistory(context,
            // Lib_Frame_Constants.ACTIVITY_NAME_CALCULATOR);
            // marmot.waitFor(2);
            checker.assertTrue(
                    "launch calculator",
                    marmot.getCurrentPackageName().equals(
                            Lib_Frame_Constants.PACKAGE_NAME_CALCULATOR));
            testStep += 1;
            marmot.log(String.format("%s. quit calculator.", testStep));
            Lib_Frame_Utils.backToPackage(marmot,
                    Lib_Frame_Constants.PACKAGE_NAME_HOME);
        }
    }

    @Override
    public void tearDown() throws Exception {
        Lib_Frame_Utils.backToPackage(marmot,
                Lib_Frame_Constants.PACKAGE_NAME_HOME);
        super.tearDown();
    }
}
