package com.android.email;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_Sanity02_ComposeSendDelete extends InstrumentationTestCase {
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
		mm.pressHome();
	}

	public void test_Sanity02_ComposeSendDelete()
			throws UiObjectNotFoundException {
		mm.log("Step 1 : Launch Email Sign In.");
		mm.pressHome();
		mm.launchActivity("com.android.email/com.kingsoft.email.activity.setup.AccountSetupBasics");

		mm.log("Step 2 : SignIn email.");
		lib.signIn(Lib_Email.infos[0][0], Lib_Email.infos[0][1],
				Lib_Email.infos[0][2]);

		mm.log("Step 3 : Compose and send.");
		mm.click(By.clazz("android.widget.Button").text("写邮件"));
		mm.click(By.res("com.android.email:id/add_to_address_list_btn"));
		new UiScrollable(new UiSelector().className("android.widget.ListView"))
				.scrollTextIntoView(Lib_Email.infos[0][0]);
		mm.waitFor(2);
		mm.getUiDevice().findObject(By.text(Lib_Email.infos[0][0])).click();
		mm.waitFor(2);
		mm.click(By.text("添加"));
		cc.assertTextExist("15910717150 <15910717150@163.com>, ");
		mm.getUiObject(By.text("主题")).clear();
		mm.getUiObject(By.res("com.android.email:id/subject")).setText("TEST");
		mm.waitFor(1);
		mm.click(By.desc("添加附件"));
		cc.assertTextExist("选择附件来源");
		mm.pressBack();
		mm.click(By.res("com.android.email:id/body"));
		mm.click(By.res("com.android.email:id/compose_toolsbar_media_picture"));
		cc.assertTextExist("选择要使用的应用");
		mm.pressBack();
		mm.getUiObject(By.res("com.android.email:id/body")).clear();
		mm.setText(By.res("com.android.email:id/body"), "TEST");
		mm.click(By.text("发送"));
	}

}
