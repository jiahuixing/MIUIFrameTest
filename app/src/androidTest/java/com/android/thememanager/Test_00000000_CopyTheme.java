package com.android.thememanager;

import android.support.test.uiautomator.By;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;


public class Test_00000000_CopyTheme extends InstrumentationTestCase {
   
    public Marmot mm;
	public Checker cc;
    @Override
    protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this);
		cc = new Checker(mm);
	}
//  备份主题
    public void testCopyTheme() throws Exception {
    	mm.log("Step 1 : 打开主题客户端.");
        mm.pressHome();
        mm.waitFor(2);
        mm.launchActivity("com.android.thememanager/com.android.thememanager.ThemeResourceTabActivity");
        mm.waitFor(5);
        mm.log("Step 2 : 点击混搭按钮.");
        mm.click(By.clazz("android.widget.TextView").text("混搭"));
        mm.waitFor(10);
        mm.log("Step 3 : 点击更多按钮."); 
        mm.click(By.clazz("android.widget.Button").text("更多"));
        mm.waitFor(5);
        mm.log("Step 4 : 点击备份当前主题设置按钮.");
        mm.click(By.clazz("android.widget.TextView").text("备份当前主题设置"));
        mm.waitFor(5);
        mm.log("Step 5 : 进行备份.");
        mm.click(By.clazz("android.widget.Button").text("备份"));
        mm.waitFor(5);
        mm.log("Step 6 : 备份完成进行确认.");
        mm.click(By.clazz("android.widget.Button").text("确定"));
        mm.waitFor(5);
        mm.log("Step 7 : 回到首页.");
        mm.click(By.clazz("android.widget.TextView").res("miui:id/action_bar_title"));
        mm.waitFor(10);
        mm.log("Step 8 : 回到本地列表页.");
        mm.click(By.clazz("android.widget.TextView").text("本地"));
        mm.waitFor(10);
        mm.log("Step 9 : 查看备份主题.");
        mm.saveScreenshot("copytheme.png");
        mm.waitFor(10);
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


