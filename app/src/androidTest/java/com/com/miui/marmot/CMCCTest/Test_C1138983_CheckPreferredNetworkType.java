package com.com.miui.marmot.CMCCTest;

import android.support.test.uiautomator.By;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_C1138983_CheckPreferredNetworkType extends InstrumentationTestCase {
    public Marmot mm;
    public Checker cc;

    protected void setUp() throws Exception {
        super.setUp();
        mm = new Marmot(this);
        cc = new Checker(mm);
    }

    public void test_C1138983_CheckPreferredNetworkType() throws Exception {
        mm.log("打开设置");
        mm.pressHome();
        mm.launchActivity("com.android.settings/com.android.settings.MiuiSettings");
        mm.waitFor(3);
        mm.log("找到Sim卡信息");
        mm.click(By.text("双卡和移动网络"));
        mm.click(By.text("中国移动"));
        mm.click(By.text("网络类型选择"));
        mm.log("检查是否变更为移动要求格式");
        cc.assertTextExist("4G/3G/2G");
        cc.assertTextExist("3G/2G");
        mm.pressBack(3);


    }

    protected void tearDown() throws Exception {
        mm.pressBack(3);
        super.tearDown();
    }


}
