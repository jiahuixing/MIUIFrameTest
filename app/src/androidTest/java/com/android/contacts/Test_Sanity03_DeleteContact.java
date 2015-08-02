package com.android.contacts;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_Sanity03_DeleteContact extends InstrumentationTestCase {
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

	public void test_Santity03_DeleteContact() throws UiObjectNotFoundException {
		mm.log("Step 1 : Launch Contacts.");
		mm.pressHome();
		mm.launchActivity("com.android.contacts/.activities.PeopleActivity");

		mm.log("Step 2 : Click CONTACT in contacts list.");
		new UiScrollable(new UiSelector().className("android.widget.ListView")).scrollTextIntoView(Lib_Contacts.CONTACT_NAME);
        mm.click(By.text(Lib_Contacts.CONTACT_NAME));

		mm.log("Step 3 : Delete contact on detail page.");
		mm.click(By.text("更多"));
		mm.click(By.text("删除联系人"));
		mm.click(By.clazz("android.widget.Button").text("删除"));
		cc.assertTrue("Delete contact.",
				mm.exist(By.clazz("android.widget.Button").text("新建联系人")));
	}

}
