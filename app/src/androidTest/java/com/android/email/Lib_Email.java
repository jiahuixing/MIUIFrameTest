package com.android.email;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObjectNotFoundException;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Lib_Email {
	private Marmot mm;
	private Checker cc;
	public static String[][] infos = {
			{ "15910717150@163.com", "fei209", "IMAP" },
			{ "15910717150@sina.cn", "fei209", "POP3" },
			{ "changyufei01@hotmail.com", "bilibang01", "EXCHANGE" } };

	public Lib_Email(Marmot mm, Checker cc) {
		this.mm = mm;
		this.cc = cc;
	}

	public void signIn(String email, String password, String protocol)
			throws UiObjectNotFoundException {
		mm.getObjectByClass("android.widget.EditText", 0).clearTextField();
		mm.getObjectByClass("android.widget.EditText", 0).setText(email);
		mm.click(By.res("com.android.email:id/account_email"));
		mm.click(By.res("com.android.email:id/account_password"));
		mm.getObjectByClass("android.widget.EditText", 1).clearTextField();
		mm.getObjectByClass("android.widget.EditText", 1).setText(password);
		cc.assertTextExist("默认设置(" + protocol + ")");
		mm.click(By.text("登录"));
		mm.waitFor(10);
		if (protocol.equals("EXCHANGE")) {
			mm.waitFor(10);
		}
		mm.click(By.text("完成"));
		mm.waitFor(5);
		cc.assertUiObejctExist(By.res("com.android.email:id/legacy_title")
				.text("收件箱"));
	}
}
