package com.android.calendar;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_Sanity01_NewAndDeleteEvent extends InstrumentationTestCase {
	public Marmot mm;
	public Checker cc;
	public static final String EVENT_NAME = "TEST";

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

	public void test_Sanity01_NewEvent() throws UiObjectNotFoundException {
		mm.log("Step 1 : Launch Calendar.");
		mm.pressHome();
		mm.launchActivity("com.android.calendar/.AllInOneActivity");

		mm.log("Step 2 : New event.");
		mm.click(By.text("新建"));
		mm.setText(By.text("活动提醒内容"), EVENT_NAME);
		mm.click(By.text("确定"));
		cc.assertTextExist(EVENT_NAME);

		mm.log("Step 3 : Delete event.");
		mm.click(By.text(EVENT_NAME));
		mm.click(By.text("删除"));
		mm.click(By.text("确定"));
		cc.assertTextNotExist(EVENT_NAME);
	}
}
