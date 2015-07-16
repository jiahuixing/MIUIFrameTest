package com.android.deskclock;

import android.graphics.Rect;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

import java.util.List;

public class Test_Sanity02_SetDeleteClock extends InstrumentationTestCase {
	public Marmot mm;
	public Checker cc;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this);
		cc = new Checker(mm);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		mm.pressHome();
	}

	public void test_Sanity01_SetDeleteAlarm() throws UiObjectNotFoundException {
		// watcherEndClock();
		mm.log("Step 1 : Launch Deskclock clock tab.");
		mm.pressHome();
		mm.launchActivity("com.android.deskclock/.DeskClockTabActivity");
		mm.click(By.text("时钟"));
		cc.assertTrue("Initial clock: London/New York.",
				mm.exist(By.text("伦敦")) && mm.exist(By.text("纽约")));

		mm.log("Step 2 : Add Clock.");
		mm.click(By.text("添加时钟"));
		mm.scrollListViewToEnd();
		List<UiObject2> timezones = mm.getUiDevice().findObjects(
				By.res("com.android.deskclock:id/timezone_name"));
		String timezone = timezones.get(0).getText();
		timezones.get(0).click();
		mm.waitFor(1);
		cc.assertTextExist(timezone);

		mm.log("Step 3 : Delete Clock.");
		Rect bounds = mm.getUiObject(By.text(timezone)).getVisibleBounds();
		mm.drag(bounds.centerX(), bounds.centerY(), bounds.centerX(),
				bounds.centerY(), 10);
		mm.waitFor(1);
		mm.click(By.text("删除时区"));
		mm.click(By.text("删除"));
		cc.assertTextNotExist(timezone);
	}

}
