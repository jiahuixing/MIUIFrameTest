package com.android.browser;

import android.content.Context;
import android.content.Intent;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00030001_AddOneTab extends InstrumentationTestCase {

	public Marmot mm;
	public Checker cc;
	private Context mContext;
	private UiDevice mDevice;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this);
		cc = new Checker(mm);

		try {
			super.setUp();
			mContext = this.getInstrumentation().getContext();
			mDevice = UiDevice.getInstance(getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void test_AddOneTab() throws Exception {
		mm.log("Step 1 : Launch Browser Activity.");
		Intent intent = new Intent();
		intent.setClassName("com.android.browser",
				"com.android.browser.BrowserActivity");
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW);
		// intent.setData(Uri.parse("http://www.baidu.com"));
		mContext.startActivity(intent);
		mm.waitFor(5);

		mm.log("Step 2 : Add One Tab.");
		UiObject2 browserTabManager = mDevice.findObject(By.clazz(
				"android.widget.TextView").res(
				"com.android.browser:id/action_tabs"));
		String tabNum = browserTabManager.getText();

		// 判断tabs为16
		assertTrue(Integer.parseInt(tabNum) == 1);

		mDevice.findObject(
				By.clazz("android.widget.ImageButton").res(
						"com.android.browser:id/action_new_tab")).click();
		mm.waitFor(5);

		String tabNum2 = browserTabManager.getText();
		// 判断tabs为1
		assertTrue(Integer.parseInt(tabNum2) == 2);

	}

	@Override
	protected void tearDown() throws Exception {
		if (mDevice.findObject(By.clazz("android.widget.ImageButton").res(
				"com.android.browser:id/action_more")) != null) {
			// log("pass");
			mDevice.findObject(
					By.clazz("android.widget.ImageButton").res(
							"com.android.browser:id/action_more")).click();
			mm.waitFor(3);
			mDevice.findObject(
					By.clazz("android.widget.TextView").textContains("退出"))
					.click();
			mm.waitFor(3);
		}

		mm.pressBack(3);
		super.tearDown();
	}

}
