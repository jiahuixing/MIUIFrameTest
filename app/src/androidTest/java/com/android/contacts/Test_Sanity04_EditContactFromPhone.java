package com.android.contacts;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_Sanity04_EditContactFromPhone extends InstrumentationTestCase {
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

	public void test_Sanity04_EditContactFromPhone()
			throws UiObjectNotFoundException {
		mm.log("Step 1 : Launch Phone.");
		mm.pressHome();
		mm.launchActivity("com.android.contacts/.activities.TwelveKeyDialer");

		mm.log("Step 2 : Unkonwn contact detail page.");
		mm.getUiObject(By.text(Lib_Contacts.CONTACT_PHONE)).getParent()
				.getParent().getParent().getChildren().get(2).click();
		mm.waitFor(2);

		mm.log("Step 3 : Click mms.");
		mm.click(By.res("com.android.contacts:id/secondary_action_button"));
		cc.assertUiObejctExist(By.res("com.android.mms:id/recipients_viewer")
				.text(Lib_Contacts.CONTACT_PHONE));
		while (mm.getCurrentPackageName().equals("com.android.mms")) {
			mm.pressBack();// back to detail
		}
		mm.waitFor(1);

		mm.log("Step 4 : New contact.");
		mm.click(By.text("新建联系人"));
		mm.click(By.text("新建联系人"));
		cc.assertTrue(
				"Calling edit contact.",
				mm.exist(By.clazz("android.widget.EditText").text(
						Lib_Contacts.ACTUAL_CONTACT_PHONE)));
		mm.pressBack(2);
		mm.click(By.text("确定"));
	}

}
