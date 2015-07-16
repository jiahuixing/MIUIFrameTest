package com.android.thememanager;

import android.support.test.uiautomator.By;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;
public class Test_00000000_ApplyTheme extends InstrumentationTestCase {
    public Marmot mm;
	public Checker cc;
    @Override
    protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this);
		cc = new Checker(mm);

	}
//  应用主题   
    public void testApplyTheme() throws Exception {
    	mm.log("Step 1 : 打开主题客户端.");
        mm.pressHome();
        mm.waitFor(2);
        mm.launchActivity("com.android.thememanager/com.android.thememanager.ThemeResourceTabActivity");
        mm.waitFor(5);
        mm.log("Step 2 : 进入本地列表页.");
        mm.click(By.clazz("android.widget.TextView").text("本地"));
        mm.waitFor(10);
        mm.log("Step 3 : 进入默认主题详情页.");
        mm.click(By.clazz("android.widget.FrameLayout").res("com.android.thememanager:id/root_flag"));
        mm.waitFor(10);   
        mm.log("Step 4 : 应用默认主题并回到桌面.");
        mm.click(By.clazz("android.widget.Button").res("com.android.thememanager:id/applyBtn"));
        mm.waitFor(10);
        mm.saveScreenshot("applytheme.png");
        mm.waitFor(5);
        mm.log("Step 5 : 回到默认主题详情页.");
        mm.click(By.clazz("android.widget.TextView").text("个性主题"));
        mm.waitFor(5);
        mm.log("Step 6 : 本地列表页截图，查看当前应用主题标记.");
        mm.pressBack();
        mm.saveScreenshot("Marktheme.png");
        mm.log("Step 7 : 回到桌面.");
        mm.waitFor(5);
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


