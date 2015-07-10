package com.com.miui.marmot.CMCCTest;

import android.support.test.uiautomator.By;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_C1138986_CheckBrowserBookmarks extends InstrumentationTestCase {

    public Marmot mm;
    public Checker cc;

    protected void setUp() throws Exception {
        super.setUp();
        mm = new Marmot(this);
        cc = new Checker(mm);
    }

    public void test_C1138986_CheckBrowserBookmarks() throws Exception {
        mm.log("打开浏览器");
        mm.pressHome();
        mm.launchActivity("com.android.browser/com.android.browser.BrowserActivity");

        mm.log("检查是否预置移动书签");
        mm.click(By.res("com.android.browser", "action_more"));
        mm.click(By.text("书签/历史"));
        mm.waitFor(3);
        cc.assertTextExist("MM网");
        cc.assertTextExist("http://a.10086.cn/j/6061/");
        cc.assertTextExist("手机阅读");
        cc.assertTextExist("http://wap.cmread.com");
        cc.assertTextExist("手机游戏");
        cc.assertTextExist("http://g.10086.cn");
        cc.assertTextExist("手机视频");
        cc.assertTextExist("http://wap.cmvideo.cn");
        mm.log("我要向下滑动");
        mm.waitFor(2);
        mm.drag(100, 600, 100, 400, 55);
        mm.waitFor(2);
        mm.log("滑动完毕");
        cc.assertTextExist("无线音乐");
        cc.assertTextExist("http://m.10086.cn/wireless/html5/index.html");
        cc.assertTextExist("冲浪网址");
        cc.assertTextExist("http://go.10086.cn/?coc=6GG2GGRq");
        cc.assertTextExist("飞信首页");
        cc.assertTextExist("http://f.10086.cn");
        cc.assertTextExist("139邮箱");
        cc.assertTextExist("http://mail.10086.cn");
        cc.assertTextExist("12580");
        cc.assertTextExist("http://12580wap.10086.cn/?pid=PCM002942000");


    }


    protected void tearDown() throws Exception {
        mm.pressBack(3);
        super.tearDown();
    }

}
