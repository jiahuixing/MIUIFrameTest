package com.miui.home;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

import junit.framework.Assert;

public class Test_0009C512239_AggregationFullScreenGraphics extends InstrumentationTestCase{
	public Marmot mm;
	public Checker cc;

	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this);
		cc = new Checker(mm);
	}

	public void test_00000000_AggregationFullScreenGraphics() throws Exception {
		int x2 = mm.getDisplayWidth() - 500;
		int y2 = (int)(mm.getDisplayHeight() / 2);
		mm.log("Step 1 : OpenUnlock");
		if(!mm.isScreenOn()){
			mm.wakeUp();
			mm.waitFor(1);
			mm.move(Direction.UP);
		}

		mm.log("Step 2: Launch Settings Activity.");
		mm.pressHome();
		mm.launchActivity("com.android.settings/com.android.settings.MiuiSettings");
		String currentPackageName = mm.getCurrentPackageName();
		Assert.assertEquals(currentPackageName, "com.android.settings");
		mm.log("Step 3: Set edit mode ");
		UiScrollable listView = new UiScrollable(new UiSelector().className("android.widget.ListView"));
		listView.scrollTextIntoView("其他高级设置");
		mm.click(By.text("其他高级设置"));
		mm.click(By.text("按键"));
		if(mm.exist(By.text("启动近期任务"))){
			mm.click(By.text("启动近期任务"));
			mm.click(By.text("显示菜单"));
		}
		mm.pressHome();
		
		mm.log("Step 4：add Full Screen");
		mm.pressMenu();
		mm.drag(10, y2, x2, y2, 8);
		mm.click(By.text("添加小工具"));
		mm.click(By.text("时钟(5)"));
		mm.waitFor(1);
		for(int j=0;j<4;j++){
			mm.click(By.text("时钟2x2"));
			mm.click(By.text("时钟2x1"));
		}
		mm.waitFor(1);
		mm.pressBack();
		mm.pressBack();
		mm.pressBack();
		
		mm.log("Step 4:Click Some App");
		mm.pressHome();
		mm.pressMenu();
		mm.click(By.text("相机"));
		mm.click(By.text("图库"));
		mm.click(By.text("音乐"));
		mm.waitFor(1);
		UiObject view = this.mm.getUiDevice().findObject(new UiSelector().className("android.view.View")
				            .resourceId("com.miui.home:id/multi_select_container"));
		view.swipeRight(6);
		mm.waitFor(1);
		
		mm.log("Step 5: Click Polymerization Graphics");
		mm.drag(10, y2, x2, y2, 8);
		mm.waitFor(1);
		UiObject imageview = this.mm.getUiDevice().findObject(new UiSelector().className("android.widget.ImageView")
				             .resourceId("com.miui.home:id/icon_icon"));
		imageview.click();
		mm.waitFor(1);
		mm.saveScreenshot("imageview.png");
		mm.waitFor(1);
		
		mm.log("Step 6 : Quit.");
		mm.pressBack();
		mm.pressBack();
		mm.pressBack();
		mm.pressHome();

	}

	@Override
	protected void tearDown() throws Exception {
		mm.pressBack(3);
		super.tearDown();
	}
}
