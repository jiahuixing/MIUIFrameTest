package com.android.email;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_Sanity01_SignInSignOut extends InstrumentationTestCase {
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

	public void test_Sanity01_SignInSignOut() throws UiObjectNotFoundException {
		mm.log("Step 1 : Launch Email Sign In.");
		mm.pressHome();
		mm.launchActivity("com.android.email/com.kingsoft.email.activity.setup.AccountSetupBasics");

		mm.log("Step 2 : SignIn email.");
		for (int i = 0; i < Lib_Email.infos.length; i++) {
			mm.log("Step 2.1 : SignIn " + Lib_Email.infos[i][2] + " email.");
			lib.signIn(Lib_Email.infos[i][0], Lib_Email.infos[i][1],
					Lib_Email.infos[i][2]);
			mm.log("Step 2.2 : Click Add account on setting.");
			mm.click(By.res("miui:id/home"));
			mm.click(By.text("设置"));
			mm.click(By.text("添加帐户"));
		}

		mm.log("Step 3 : Delete account");
		mm.pressBack(2);
		for (int i = 0; i < Lib_Email.infos.length; i++) {
			new UiScrollable(new UiSelector().className("android.widget.ListView")).scrollTextIntoView(Lib_Email.infos[i][0]);
			mm.click(By.text(Lib_Email.infos[i][0]));
			mm.waitFor(2);
			new UiScrollable(
					new UiSelector().className("android.widget.ListView"))
					.scrollTextIntoView("删除帐户");
			mm.getUiDevice().findObjects(By.text("删除帐户")).get(1).click();
			mm.waitFor(2);
			mm.click(By.text("确定"));
		}
		cc.assertTextNotExist("帐户");
		mm.pressBack(3);
	}
}
