package com.miui.video;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;

import com.miui.marmot.lib.Marmot;

import java.util.List;

public class Lib_VideoUtil {
    public static String addFavouriteVideo(Marmot mm) {
        mm.click(By.clazz(android.widget.TextView.class).text("电视剧"));
        String newFavouriteTitle = null;

        List<UiObject2> objestList = mm.getUiDevice().findObjects(
                By.clazz(android.widget.ImageView.class).res(
                        Lib_VideoConst.PACKAGE_NAME + ":id/poster"));
        for (UiObject2 videoImage : objestList) {
            videoImage.click();
            mm.waitFor(2);

            UiObject2 addFavouriteButton = mm.getUiObject(By.clazz(
                    android.widget.TextView.class).text("收藏"));

            if (addFavouriteButton != null && !addFavouriteButton.isSelected()) {
                UiObject2 titleBar = mm.getUiObject(By.clazz(
                        android.widget.LinearLayout.class).res(
                        Lib_VideoConst.PACKAGE_NAME + ":id/title_top"));
                newFavouriteTitle = titleBar.findObject(
                        By.clazz(android.widget.TextView.class)).getText();
                addFavouriteButton.click();
                mm.pressBack();

                break;
            }
            mm.pressBack();
        }
        mm.pressBack();

        return newFavouriteTitle;
    }
}
