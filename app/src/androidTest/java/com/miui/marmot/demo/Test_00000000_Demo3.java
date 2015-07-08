package com.miui.marmot.demo;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00000000_Demo3 extends InstrumentationTestCase {
    public Marmot mm;
    public Checker cc;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mm = new Marmot(this);
        cc = new Checker(mm);
    }

    public void test_00000000_Demo3() throws Exception {
        mm.log("Step 1 : Launch contacts Activity.");
        mm.pressHome();
        mm.launchActivity("com.android.contacts/.activities.PeopleActivity");

        mm.log("Step 2 : New contact 'Lissa'.");
        mm.click(By.text("新建联系人"));
        mm.setText(By.clazz("android.widget.EditText").text("姓名"), "Lissa");
        mm.click(By.text("确定"));
        mm.pressBack();

        mm.log("Step 3 : Check 'Lissa' in contacts.");
        cc.assertTextExist("Lissa");

        mm.log("Step 4 : Delete 'Lissa'.");
        mm.longClick(By.text("Lissa"));
        mm.waitFor(2);
        mm.click(By.text("删除"));
        mm.click(By.clazz("android.widget.Button").text("删除"));
        mm.waitFor(2);

        mm.log("Step 5 : Move to dial.");
        mm.move(Direction.RIGHT);
        mm.waitFor(2);
        cc.assertTextExist("拨号");

        mm.log("Step 6 : Quit.");
        mm.pressBack(3);
        mm.pressHome();
    }

    @Override
    protected void tearDown() throws Exception {
        mm.pressBack(3);
        super.tearDown();
    }
}