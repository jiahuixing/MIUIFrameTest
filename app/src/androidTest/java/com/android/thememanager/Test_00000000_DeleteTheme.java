package com.android.thememanager;
import android.support.test.uiautomator.By;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;
public class Test_00000000_DeleteTheme extends InstrumentationTestCase {
    public Marmot mm;
	public Checker cc;

    @Override
    protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this);
		cc = new Checker(mm);
		

	}
//  删除主题   
   
	public void testDeleteTheme() throws Exception {
    	mm.log("Step 1 : 打开主题客户端.");
        mm.pressHome();
        mm.waitFor(2);
        mm.launchActivity("com.android.thememanager/com.android.thememanager.ThemeResourceTabActivity");
        mm.waitFor(5);
        mm.log("Step 2 : 进入本地列表页.");
        mm.click(By.clazz("android.widget.TextView").text("本地"));
        mm.waitFor(5);
        mm.log("Step 3 : 进入备份主题详情页.");
        mm.click(507,312);
        mm.waitFor(5);  
        mm.log("Step 4 : 向下滑动");
        mm.drag(700, 2000, 700, 500, 6);
        mm.waitFor(5);  
        mm.log("Step 5 : 删除主题并确认");
        mm.click(By.clazz("android.widget.Button").res("com.android.thememanager:id/delete"));
        mm.click(By.clazz("android.widget.Button").text("确定"));
        mm.waitFor(2);
        mm.saveScreenshot("DeleteTheme.png");
        mm.waitFor(2);
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





