package com.miui.backup;

/**
 * Project name : marmot-cases2
 * Package name : com.miui.backup
 * Created by jiahuixing
 * Created on 2015-07-13
 * Created at 17:28
 */

import android.content.Context;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;

import com.miui.frame.Lib_Frame_Constants;
import com.miui.frame.Lib_Frame_Utils;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00000000_Backup extends InstrumentationTestCase {

	public Marmot marmot;
	public Checker checker;
	public UiDevice uiDevice;
	public Context context;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		marmot = new Marmot(this);
		checker = new Checker(marmot);
		context = marmot.getContext();
		Lib_Frame_Utils.unLock(marmot);
	}

	public void test_Backup() throws Exception {
		int testStep = 0;
		testStep += 1;
		marmot.log(String.format("%s. launch settings.", testStep));
		marmot.launchActivity(Lib_Frame_Constants.ACTIVITY_NAME_SETTINGS);
		// marmot.waitFor(2);
		checker.assertTrue(
				"launch",
				marmot.getCurrentPackageName().equals(
						Lib_Frame_Constants.PACKAGE_NAME_SETTINGS));
		testStep += 1;
		marmot.log(String.format("%s. go to advanced settings.", testStep));
		UiScrollable settingsList;
		settingsList = new UiScrollable(
				new UiSelector().className("android.widget.ListView"));
		UiObject advancedSettings;
		advancedSettings = settingsList.getChildByText(
				new UiSelector().className("android.widget.TextView"),
				"其他高级设置", true);
		advancedSettings.click();
		marmot.waitFor(2);
		testStep += 1;
		marmot.log(String.format("%s. go to backup and reset.", testStep));
		UiScrollable advancedSettingsList;
		advancedSettingsList = new UiScrollable(
				new UiSelector().className("android.widget.ListView"));
		UiObject backupAndReset;
		backupAndReset = advancedSettingsList.getChildByText(
				new UiSelector().className("android.widget.TextView"), "备份和重置",
				true);
		backupAndReset.click();
		marmot.waitFor(2);
		testStep += 1;
		marmot.log(String.format("%s. go to auto backup.", testStep));
		UiObject2 autoBackup, autoBackupSwitch, autoBackupChoose, autoBackupAppsChooseChecker, autoBackupAppsChoose, confirm;
		autoBackup = marmot.getUiObject(By.clazz("android.widget.TextView")
				.text("自动备份"));
		autoBackup.click();
		marmot.waitFor(2);
		autoBackupSwitch = marmot.getUiObject(By
				.clazz("android.widget.CheckBox"));
		if (!autoBackupSwitch.isChecked()) {
			testStep += 1;
			marmot.log(String.format("%s. open auto backup.", testStep));
			autoBackupSwitch.click();
			marmot.waitFor(2);
		}
		testStep += 1;
		marmot.log(String.format("%s. auto backup choose.", testStep));
		autoBackupChoose = marmot.getUiObject(By.clazz(
				"android.widget.TextView").text("备份项目"));
		autoBackupChoose.click();
		marmot.waitFor(2);
		testStep += 1;
		marmot.log(String.format("%s. auto backup app choose.", testStep));
		autoBackupAppsChooseChecker = marmot.getUiObject(By.clazz(
				"android.widget.CheckedTextView").text("指定应用"));
		autoBackupAppsChooseChecker.click();
		marmot.waitFor(2);
		autoBackupAppsChoose = marmot.getUiObject(By.clazz(
				"android.widget.TextView").text("选择应用"));
		autoBackupAppsChoose.click();
		marmot.waitFor(2);
		confirm = marmot.getUiObject(By.clazz("android.widget.Button").text(
				"确定"));
		confirm.click();
		marmot.waitFor(2);
		String currentPackageName;
		while (true) {
			currentPackageName = marmot.getCurrentPackageName();
			if (currentPackageName
					.equals(Lib_Frame_Constants.PACKAGE_NAME_BACKUP)) {
				marmot.pressBack();
				marmot.waitFor(2);
			} else {
				break;
			}
		}
		checker.assertTrue(
				"settings",
				marmot.getCurrentPackageName().equals(
						Lib_Frame_Constants.PACKAGE_NAME_SETTINGS));
		UiObject2 backup, encrypt, createBackup;
		testStep += 1;
		marmot.log(String.format("%s. local backup.", testStep));
		backup = marmot.getUiObject(By.clazz("android.widget.TextView").text(
				"本地备份"));
		backup.click();
		marmot.waitFor(2);
		UiObject2 backupApps, backupContacts;
		UiObject2 checkBoxApps, checkBoxContacts, detail, noBackupSummary, startBackup, backupList, backupFile, startRestore, cancel, finish;
		noBackupSummary = marmot.getUiObject(By
				.clazz("android.widget.TextView").text("备份你手机中的数据，设置和应用"));
		if (noBackupSummary == null) {
			testStep += 1;
			marmot.log(String.format("%s. delete backup files.", testStep));
			backupList = marmot
					.getUiObject(By.clazz("android.widget.ListView"));
			backupFile = backupList
					.getChildren()
					.get(0)
					.findObject(
							By.clazz("android.widget.LinearLayout").res(
									"com.miui.backup:id/backup_list_item"));
			Lib_Frame_Utils.longClick(marmot, backupFile);
			UiObject2 selectAll, delete, alertDialog;
			selectAll = marmot.getUiObject(By.clazz("android.widget.Button")
					.text("全选"));
			if (selectAll != null) {
				selectAll.click();
				marmot.waitFor(2);
			}
			delete = marmot.getUiObject(By.clazz("android.widget.Button").text(
					"删除"));
			delete.click();
			marmot.waitFor(2);
			alertDialog = marmot
					.getUiObject(By.clazz("android.widget.TextView").res(
							"miui:id/alertTitle"));
			if (alertDialog != null) {
				confirm = marmot.getUiObject(By.clazz("android.widget.Button")
						.text("确定"));
				confirm.click();
				marmot.waitFor(2);
			}
		}
		testStep += 1;
		marmot.log(String.format("%s. encrypt.", testStep));
		encrypt = marmot.getUiObject(By.clazz("android.widget.ImageButton")
				.res("com.miui.backup:id/backup_start_encrypt"));
		encrypt.click();
		marmot.waitFor(2);
		UiObject2 enableEncrypt;
		enableEncrypt = marmot.getUiObject(By.clazz("android.widget.Button")
				.res("com.miui.backup:id/enable_encrypt"));
		if (enableEncrypt != null) {
			marmot.pressBack();
			marmot.waitFor(2);
		}
		testStep += 1;
		marmot.log(String.format("%s. create a new backup.", testStep));
		createBackup = marmot.getUiObject(By.clazz("android.widget.Button")
				.res("com.miui.backup:id/bakcup_new_backup"));
		createBackup.click();
		marmot.waitFor(2);
		testStep += 1;
		marmot.log(String.format("%s. backup detail.", testStep));
		detail = marmot.getUiObject(By.clazz("android.widget.Button").res(
				"com.miui.backup:id/data_detail"));
		detail.click();
		marmot.waitFor(2);
		marmot.pressBack();
		marmot.waitFor(2);
		testStep += 1;
		marmot.log(String.format("%s. apps checkbox and contacts checkbox.",
				testStep));
		backupApps = marmot.getUiObject(By.clazz("android.widget.TextView")
				.text("软件程序"));
		backupContacts = marmot.getUiObject(By.clazz("android.widget.TextView")
				.text("帐号和通讯录"));
		checkBoxApps = backupApps.getParent().getParent().getParent()
				.findObject(By.res("com.miui.backup:id/data_item_cb"));
		checkBoxContacts = backupContacts.getParent().getParent().getParent()
				.findObject(By.res("com.miui.backup:id/data_item_cb"));
		if (checkBoxApps.isChecked()) {
			checkBoxApps.click();
			marmot.waitFor(1);
		}
		if (checkBoxContacts.isChecked()) {
			checkBoxContacts.click();
			marmot.waitFor(1);
		}
		testStep += 1;
		marmot.log(String.format("%s. start backup.", testStep));
		startBackup = marmot.getUiObject(By.clazz("android.widget.Button")
				.text("开始备份"));
		startBackup.click();
		marmot.waitFor(2);
		while (true) {
			cancel = marmot.getUiObject(By.clazz("android.widget.Button").text(
					"取消"));
			finish = marmot.getUiObject(By.clazz("android.widget.Button").text(
					"完成"));
			if (cancel != null && finish == null) {
				marmot.waitFor(5);
			} else {
				finish.click();
				marmot.waitFor(2);
				break;
			}
		}
		if (noBackupSummary == null) {
			backupList = marmot
					.getUiObject(By.clazz("android.widget.ListView"));
			if (backupList != null) {
				testStep += 1;
				marmot.log(String.format("%s. restore backup.", testStep));
				backupFile = backupList.getChildren().get(0)
						.findObject(By.clazz("android.widget.TextView"));
				backupFile.click();
				marmot.waitFor(2);
				testStep += 1;
				marmot.log(String.format("%s. start restore backup.", testStep));
				startRestore = marmot.getUiObject(By.clazz(
						"android.widget.Button").text("开始恢复"));
				startRestore.click();
				marmot.waitFor(2);
				while (true) {
					cancel = marmot.getUiObject(By.clazz(
							"android.widget.Button").text("取消"));
					finish = marmot.getUiObject(By.clazz(
							"android.widget.Button").text("完成"));
					if (cancel != null && finish == null) {
						marmot.waitFor(5);
					} else {
						finish.click();
						marmot.waitFor(2);
						break;
					}
				}
			}
		}
	}

	@Override
	public void tearDown() throws Exception {
		Lib_Frame_Utils.backToPackage(marmot,
				Lib_Frame_Constants.PACKAGE_NAME_HOME);
		super.tearDown();
	}

}
