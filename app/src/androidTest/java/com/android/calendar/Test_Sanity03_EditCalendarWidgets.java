package com.android.calendar;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_Sanity03_EditCalendarWidgets extends InstrumentationTestCase {
	public Marmot mm;
	public Checker cc;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this);
		cc = new Checker(mm);
		mm.launchActivity("com.android.settings/.MiuiSettings");
		mm.clickOnTextInList("其他高级设置");
		mm.waitFor(1);
		mm.clickOnTextInList("按键");
		mm.waitFor(1);
		mm.click(By.text("任务键"));
		mm.click(By.text("显示菜单"));
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		mm.pressHome();
	}

	public void test_Sanity02_Subscribe() throws UiObjectNotFoundException {
		mm.log("Step 1 : Click Widgets on Menu bar.");
		mm.pressMenu();
		mm.click(By.text("添加小工具"));
	}
}
