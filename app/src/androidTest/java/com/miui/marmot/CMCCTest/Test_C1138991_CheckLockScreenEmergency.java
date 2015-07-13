package com.miui.marmot.CMCCTest;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_C1138991_CheckLockScreenEmergency extends InstrumentationTestCase {
    public Marmot mm;
    public Checker cc;

    protected void setUp() throws Exception {
        super.setUp();
        mm = new Marmot(this);
        cc = new Checker(mm);
    }

    public void test_C1138991_CheckLockScreenEmergency() throws Exception {
        mm.log("锁屏");
        mm.pressHome(1);
        mm.move(Direction.DOWN);
        mm.waitFor(1);
        mm.move(Direction.LEFT);
        mm.move(Direction.LEFT);
        mm.click(By.text("锁屏"));
        mm.waitFor(5);
        mm.wakeUp();
        mm.waitFor(3);
        mm.log("检查是否有紧急呼叫");
        cc.assertTextExist("紧急呼叫");
        mm.log("尝试进行紧急呼叫");
        mm.click(By.text("紧急呼叫"));
        cc.assertTextExist("紧急呼救");
        mm.click(By.desc("一"));
        mm.click(By.desc("一"));
        mm.click(By.desc("二"));
        mm.click(By.desc("拨号"));
        mm.waitFor(2);
        cc.assertTextExist("紧急呼叫中心");
        mm.waitFor(2);
        mm.click(By.res("com.android.incallui", "endButton"));
        mm.waitFor(2);
        mm.move(Direction.UP);
        mm.pressHome();


    }   
    
    protected void tearDown() throws Exception {
        mm.pressBack(3);
        super.tearDown();
    }

}
