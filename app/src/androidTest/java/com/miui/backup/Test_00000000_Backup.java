package com.miui.backup;

/**
 * Project name : marmot-cases2
 * Package name : com.miui.backup
 * Created by jiahuixing
 * Created on 2015-07-13
 * Created at 17:28
 */

import android.support.test.uiautomator.UiDevice;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00000000_Backup extends InstrumentationTestCase {

    public Marmot marmot;
    public Checker checker;
    public UiDevice uiDevice;

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void test_Backup() throws Exception {
        int testStep = 0;
        testStep += 1;
        marmot.log(String.format("%s. launch backup.", testStep));
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
