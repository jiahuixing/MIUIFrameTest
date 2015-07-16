package com.android.calendar;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_Sanity02_Subscribe extends InstrumentationTestCase {
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

	public void test_Sanity02_Subscribe() throws UiObjectNotFoundException {
		mm.log("Step 1 : Launch Calendar.");
		mm.pressHome();
		mm.launchActivity("com.android.calendar/.AllInOneActivity");

		mm.log("Step 2 : Go to Subscribe tab.");
		mm.click(By.text("订阅"));
		if (mm.exist(By.text("同意并继续"))) {
			mm.click(By.text("同意并继续"));
		}
		mm.waitFor(2);

		mm.log("Step 3 : Subscribe.");
		UiObject2 subscribe = mm.getUiObject(By.text("订阅"));
		UiObject2 parent = subscribe.getParent();
		subscribe.click();
		mm.waitFor(5);
		cc.assertTrue("Subscribed.", parent.hasObject(By.text("已订阅")));

		mm.log("Step 4 : Cancel subscription.");
		String title = parent.getChildren().get(1).getChildren().get(0)
				.getChildren().get(0).getText();
		parent.click();
		mm.waitFor(1);
		mm.click(By.text("退订"));
		mm.click(By.text("确定"));
		mm.waitFor(2);
		cc.assertUiObejctExist(By.text("订阅"));
		mm.pressBack();
		// 避免StaleObjectException
		boolean subscribeState = mm.getUiObject(By.text(title)).getParent()
				.getParent().getParent().getChildren().get(2).getText()
				.equals("订阅");
		cc.assertTrue("Cancel subscription.", subscribeState);
	}
}
