package com.android.contacts;

import android.support.test.uiautomator.By;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_Sanity01_NewContacts extends InstrumentationTestCase {
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

	public void test_Sanity01_NewContacts() {
		mm.log("Step 1 : Launch Contacts.");
		mm.pressHome();
		mm.launchActivity("com.android.contacts/.activities.PeopleActivity");

		mm.log("Step 2 : Click New contact.");
		mm.click(By.text("新建联系人"));
		if (mm.exist(By.text("小米帐号"))) {
			mm.click(By.text("小米帐号"));
		}

		mm.log("Step 3 : Fill the edit texts");
		mm.setText(By.text("姓名"), Lib_Contacts.CONTACT_NAME);
		mm.setText(By.text("公司"), Lib_Contacts.CONTACT_COMPANY);
		mm.setText(By.text("职位"), Lib_Contacts.CONTACT_TITLE);
		mm.setText(By.text("电话"), Lib_Contacts.CONTACT_PHONE);
		mm.setText(By.text("邮件"), Lib_Contacts.CONTACT_EMAIL);

		mm.log("Step 4 : Set head photo(camera).");
		mm.click(By.res("com.android.contacts:id/frame"));
		mm.click(By.text("拍照"));
		if (mm.exist(By.text("开始拍照"))) {
			mm.click(By.text("开始拍照"));
		}
		mm.click(By.res("com.android.camera:id/v6_shutter_button_internal"));
		mm.waitFor(2);
		mm.click(By.res("com.android.camera:id/v6_btn_done"));
		mm.click(By.text("应用"));

		mm.log("Step 5 : Save.");
		mm.click(By.text("确定"));
		mm.saveScreenshot("contact_detail.png");
		cc.assertTextExist(Lib_Contacts.CONTACT_COMPANY + "，"
				+ Lib_Contacts.CONTACT_TITLE);
	}
}
