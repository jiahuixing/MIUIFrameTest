package com.android.thememanager;

import android.support.test.uiautomator.By;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;
public class Test_00000000_CheckAccount extends InstrumentationTestCase {
    public Marmot mm;
	public Checker cc;
    @Override
    protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this);
		cc = new Checker(mm);

	}
//  查看帐户
    public void testApplyTheme() throws Exception {
    	mm.log("Step 1 : 打开主题客户端.");
        mm.pressHome();
        mm.waitFor(2);
        mm.launchActivity("com.android.thememanager/com.android.thememanager.ThemeResourceTabActivity");
        mm.waitFor(5);
        mm.log("Step 2 : 进入我的帐户页.");
        mm.click(By.clazz("android.widget.ImageView").res("com.android.thememanager:id/icon_account"));
        mm.saveScreenshot("MyAccount.png");
        mm.waitFor(10);
        mm.log("Step 3 : 进入我的收藏页.");
        cc.assertTextExist("我的收藏");
        mm.click(By.text("我的收藏"));
        mm.waitFor(5);
        mm.pressBack();
        mm.log("Step 4 : 进入已购列表页.");
        cc.assertTextExist("已购列表");
        mm.click(By.text("已购列表"));
        mm.waitFor(5);
        mm.pressBack();
        mm.log("Step 5 : 进入我的礼物页.");
        cc.assertTextExist("我的礼物");
        mm.click(By.text("我的礼物"));
        mm.waitFor(5);
        mm.pressBack();
        mm.log("Step 6 : 主题设置.");
        cc.assertTextExist("主题设置");
        mm.click(By.text("主题设置"));
        mm.waitFor(5);
        mm.pressBack();
        mm.pressBack();
        mm.pressBack();
        mm.pressHome();
    }
    @Override
    protected void tearDown() throws Exception {
        mm.pressBack();
        mm.pressBack();
        mm.pressHome();
        super.tearDown();
    }
}

