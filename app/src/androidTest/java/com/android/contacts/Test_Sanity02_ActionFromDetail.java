package com.android.contacts;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_Sanity02_ActionFromDetail extends InstrumentationTestCase {
	public UiDevice mDevice;
	public Marmot mm;
	public Checker cc;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mDevice = UiDevice.getInstance(getInstrumentation());
		mm = new Marmot(this);
		cc = new Checker(mm);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		mm.pressHome();
	}

	public void test_Sanity02_ActionFromDetail()
			throws UiObjectNotFoundException {
		mm.log("Step 1 : Launch Contacts.");
		mm.pressHome();
		mm.launchActivity("com.android.contacts/.activities.PeopleActivity");

		mm.log("Step 2 : Click CONTACT in contacts list.");
		new UiScrollable(new UiSelector().className("android.widget.ListView"))
				.scrollTextIntoView(Lib_Contacts.CONTACT_NAME);
		mm.click(By.text(Lib_Contacts.CONTACT_NAME));

		mm.log("Step 3 : Click phone number to call.");
		mm.click(By.text(Lib_Contacts.ACTUAL_CONTACT_PHONE));
		mm.waitFor(3);
		cc.assertUiObejctExist(By.res("com.android.incallui:id/endButton"));
		mm.click(By.res("com.android.incallui:id/endButton"));// hang up to back
																// to detail
		mm.log("Step 4 : Click mms.");
		mm.click(By.desc("短信"));
		cc.assertUiObejctExist(By.res("com.android.mms:id/recipients_viewer")
				.text(Lib_Contacts.CONTACT_NAME));
		while (mm.getCurrentPackageName().equals("com.android.mms")) {
			mm.pressBack();// back to detail
		}

		mm.log("Step 5 : Click email.");
		mm.click(By.text(Lib_Contacts.CONTACT_EMAIL));
		if (mm.exist(By.text("选择帐号"))) {
			mm.click(By.res("android:id/text1"));
		}
		cc.assertTextExist("<819701562@qq.com>, ");
		mm.pressBack(2);
		mm.click(By.text("不保存"));

		mm.log("Step 6 : Edit phone number to check the yellowpage.");
		mm.click(By.text("编辑"));
		// Delete old phone number then add new phone number 10086.
		mDevice.findObject(By.text(Lib_Contacts.ACTUAL_CONTACT_PHONE))
				.getParent().getParent().getParent().getChildren().get(2)
				.click();
		mm.setText(By.text("电话"), "10086");
		mm.click(By.text("确定"));
		mm.click(By.text("黄页"));
		cc.assertTrue("Calling YellowPage.",
				mm.getCurrentPackageName().equals("com.miui.yellowpage"));

	}
}
