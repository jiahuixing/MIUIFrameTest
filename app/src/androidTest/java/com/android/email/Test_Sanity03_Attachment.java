package com.android.email;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_Sanity03_Attachment extends InstrumentationTestCase {
	public Marmot mm;
	public Checker cc;
	public Lib_Email lib;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this);
		cc = new Checker(mm);
		lib = new Lib_Email(mm, cc);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		mm.pressBack(2);
		mm.pressHome();
	}

	public void test_Sanity03_Attachment() throws UiObjectNotFoundException {
		mm.log("Step 1 : Launch Email Inbox.");
		mm.pressHome();
		mm.launchActivity("com.android.email/.activity.Welcome");

		mm.log("Step 2 : Attachments list page.");
		mm.click(By.text("附件"));
		mm.click(By.res("com.android.email:id/download_status_area"));
		mm.waitFor(60);
		cc.assertUiObejctExist(By
				.res("com.android.email:id/attachment_mgr_save_icon"));
	}
}
