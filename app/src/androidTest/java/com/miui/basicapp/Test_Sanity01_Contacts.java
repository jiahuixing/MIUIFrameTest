package com.miui.basicapp;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_Sanity01_Contacts extends InstrumentationTestCase {
	private static final String CONTACT_NAME = "Test";
	private static final String CONTACT_COMPANY = "XIAOMI";
	private static final String CONTACT_TITLE = "QA";
	private static final String CONTACT_PHONE = "15910717150";
	private static final String CONTACT_EMAIL = "819701562@qq.com";
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

	public void test_Delete_Event_Demo() {
		mm.log("Step 1 : Launch Contacts.");
		mm.pressHome();
		mm.launchActivity("com.android.contacts/.activities.PeopleActivity");

		mm.log("Step 2 : Click New contact.");
		mm.click(By.text("新建联系人"));
		if (mm.exist(By.text("小米账号"))) {
			mm.click(By.text("小米账号"));
		}

		mm.log("Step 3 : Fill the edit texts");
		mm.setText(By.text("姓名"), CONTACT_NAME);
		mm.setText(By.text("公司"), CONTACT_COMPANY);
		mm.setText(By.text("职位"), CONTACT_TITLE);
		mm.setText(By.text("电话"), CONTACT_PHONE);
		mm.setText(By.text("邮件"), CONTACT_EMAIL);

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
		cc.assertTextExist(CONTACT_COMPANY + "，" + CONTACT_TITLE);

		mm.log("Step 6 : Click phone number to call.");
		mm.click(By.text("159 1071 7150"));
		mm.waitFor(3);
		cc.assertUiObejctExist(By.res("com.android.incallui:id/endButton"));
		mm.click(By.res("com.android.incallui:id/endButton"));// back to detail

		mm.log("Step 7 : Click mms.");
		mm.click(By.desc("短信"));
		cc.assertUiObejctExist(By.res("com.android.mms:id/recipients_viewer")
				.text(CONTACT_NAME));
		mm.pressBack();// back to detail

		mm.log("Step 8 : Click email");
		mm.click(By.text(CONTACT_EMAIL));
		cc.assertTextExist("<819701562@qq.com>, ");
		mm.pressBack(2);
		mm.click(By.text("不保存"));

		mm.log("Step 9 : Delete the contact.");
		mm.click(By.text("更多"));
		mm.click(By.text("删除联系人"));
		mm.click(By.text("删除"));

		mm.log("Step 10 : Recents tab.");
		mm.click(By.text("拨号"));
		// mm.longClick(By.text(CONTACT_PHONE));
		UiObject2 parent1 = mDevice.findObject(By.text(CONTACT_PHONE))
				.getParent().getParent().getParent();
		parent1.getChildren().get(parent1.getChildCount() - 1).click();
		mm.waitFor(2);
		UiObject2 parent2 = mDevice
				.findObject(By.res("com.android.contacts:id/number"))
				.getParent().getParent().getParent();
		parent2.getChildren().get(parent2.getChildCount() - 1).click();
		mm.waitFor(2);
		cc.assertUiObejctExist(By.res("com.android.mms:id/recipients_viewer")
				.text(CONTACT_PHONE));
		mm.pressBack();
		mm.waitFor(1);
		mm.click(By.text("新建联系人"));
		mm.click(By.text("新建联系人"));
		cc.assertUiObejctExist(By.clazz("android.widget.EditText").text(
				"159 1071 7150"));
		mm.pressBack(2);
		mm.click(By.text("确定"));
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		mm.pressBack(2);
		mm.pressHome();
	}

}
