package com.xiaomi.market;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00000000_Search extends InstrumentationTestCase {
    public Marmot mm;
    public Checker cc;
    private UiDevice mDevice;

    @Override
    protected void setUp() {
        mm = new Marmot(this);
        cc = new Checker(mm);
    }

    public void test_Search() throws Exception {
        mm.log("Step 1 : Launch Market Activity and Click search button.");
        mm.pressHome();
        mm.launchActivity("com.xiaomi.market/com.xiaomi.market.ui.MarketTabActivity");
        mm.waitFor(5);
        mm.click(By.clazz("android.widget.ImageView").res("android:id/input"));
        mm.waitFor(5);
        mm.log("Step 2 : Input search words.");
        UiObject searchText = mDevice.findObject(new UiSelector()
                .className("android.widget.EditText"));
        searchText.click();
        mm.waitFor(3);
        mDevice.pressBack();
        mm.waitFor(3);
        searchText.setText("yihaodian");
        mm.waitFor(5);
        mm.log("Step 3 : select search words.");
        mm.click(By.clazz("android.widget.TextView").text("yihaodian"));
        mm.waitFor(5);
        mm.saveScreenshot("searchresult.png");
        mm.waitFor(5);
        mm.log("Step 4 : download App.");
        mm.click(By.clazz("android.widget.TextView").text("1号店"));
        mm.waitFor(5);
        cc.assertTextExist("安装");
        mm.click(By.clazz("android.widget.Button").text("安装"));
        mm.waitFor(20);
        mm.saveScreenshot("安装完成");
        mm.log("Step 5 : back to main.");
        mm.pressBack();
        mm.waitFor(5);
        mm.click(By.clazz("android.widget.ImageView").res(
                "com.xiaomi.market:id/search_btn_cancel"));
        mm.waitFor(5);
        mm.saveScreenshot("主页.png");
        cc.setTestrailResult("c512897", true);
        mm.log("Step 6 : Quit.");
        mm.pressBack();
        mm.pressHome();
    }

    @Override
    protected void tearDown() throws Exception {
    }
}
