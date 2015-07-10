package com.com.miui.marmot.CMCCTest;

import android.support.test.uiautomator.By;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_C1138985_Check4636mode extends InstrumentationTestCase {

    public Marmot mm;
    public Checker cc;

    protected void setUp() throws Exception {
        super.setUp();
        mm = new Marmot(this);
        cc = new Checker(mm);
    }

    public void test_C1138985_Check4636mode() throws Exception {
        mm.log("打开拨号盘");
        mm.pressHome(2);
        mm.click(By.text("拨号"));
        mm.waitFor(3);
        mm.click(By.desc("星形符号"));
        mm.click(By.desc("英镑符号"));
        mm.click(By.desc("星形符号"));
        mm.click(By.desc("英镑符号"));
        mm.click(By.desc("四"));
        mm.click(By.desc("六"));
        mm.click(By.desc("三"));
        mm.click(By.desc("六"));
        mm.click(By.desc("英镑符号"));
        mm.click(By.desc("星形符号"));
        mm.click(By.desc("英镑符号"));
        mm.click(By.desc("星形符号"));
        mm.waitFor(3);
        mm.log("检查是否进入工程模式");
        cc.assertTextExist("拨号");
        cc.assertTextExist("联系人");
        cc.assertTextExist("黄页");
        cc.assertTextNotExist("测试");
        cc.assertTextNotExist("手机信息");
        mm.waitFor(2);
        mm.pressHome(1);

    }

    protected void tearDown() throws Exception {
        mm.pressBack(3);
        super.tearDown();
    }

}
