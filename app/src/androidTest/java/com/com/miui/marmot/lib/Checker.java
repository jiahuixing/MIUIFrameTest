package com.com.miui.marmot.lib;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiObject;
import android.util.Log;

import com.miui.marmot.lib.Marmot;

import junit.framework.Assert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Y.M. on 2015/5/21.
 */
public class Checker {
    private static double IMG_DIFF_TOLERANCE = 0.02;
    private static int COMPRESS_PERCENT = 90;
    private final String actualImgPath = "/sdcard/actualImg.png";
    private com.miui.marmot.lib.Marmot mMarmot;

    public Checker(com.miui.marmot.lib.Marmot marmot) {
        mMarmot = marmot;
    }

    /**
     * 在当前界面，断言局部小图存在。如不存在，保存当前界面截图。
     *
     * @param imgPath 参照图绝对路径
     * @param fromX   局部图在参照图中左上角的x值
     * @param fromY   局部图在参照图中左上角的y值
     * @param endX    局部图在参照图中右下角的x值
     * @param endY    局部图在参照图中右下角的y值
     */
    public void assertImgExist(String imgPath, int fromX, int fromY, int endX, int endY) {
        boolean result = compareImg(imgPath, fromX, fromY, endX, endY, IMG_DIFF_TOLERANCE);
        if (result) {
            log("PASS, The expected image exists.");
        } else {
            log("FAIL, The expected image does not exist.");

            Bitmap standImage = getSubImage(imgPath, fromX, fromY, endX, endY);
            Bitmap currentImage = getSubImage(actualImgPath, fromX, fromY, endX, endY);
            Bitmap failedImage;

            if ((failedImage = mergeTwoImages(standImage, currentImage)) != null) {
                try {
                    failedImage.compress(Bitmap.CompressFormat.PNG, COMPRESS_PERCENT, new FileOutputStream(new File(mMarmot.mFailedImgName)));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    log("Exception in save Failed image");
                }
            } else {
                log("Exception in merge Failed image");
            }

            Assert.assertTrue(false);
        }
    }

    /**
     * 在当前界面，断言局部小图不存在。如存在，保存当前界面截图。
     *
     * @param imgPath 参照图绝对路径
     * @param fromX   局部图在参照图中左上角的x值
     * @param fromY   局部图在参照图中左上角的y值
     * @param endX    局部图在参照图中右下角的x值
     * @param endY    局部图在参照图中右下角的y值
     * @throws FileNotFoundException
     */
    public void assertImgNotExist(String imgPath, int fromX, int fromY, int endX, int endY) {
        boolean result = this.compareImg(imgPath, fromX, fromY, endX, endY, IMG_DIFF_TOLERANCE);
        if (result == false) {
            log("PASS, The expected image does not exist.");
        } else {
            log("FAIL, The expected image exists.");
            try {
                getSubImage(actualImgPath, fromX, fromY, endX, endY).compress(Bitmap.CompressFormat.PNG, COMPRESS_PERCENT, new FileOutputStream(new File(mMarmot.mFailedImgName)));
            } catch (Exception e) {
                log("Exception in get Failed image");
            }

            Assert.assertTrue(false);
        }
    }

    /**
     * 在当前界面，断言文字存在。如不存在，保存当前界面截图。
     *
     * @param text 文字内容
     */
    public void assertTextExist(String text) {
        if (mMarmot.exist(By.text(text))) {
            log("PASS, Text \"" + text + "\" exists.");
        } else {
            log("FAIL, Text \"" + text + "\" does not exist.");
            saveScreenshot(mMarmot.mFailedImgName);
            Assert.assertTrue(false);
        }
    }

    /**
     * 在当前界面，断言文字不存在。如存在，保存当前界面截图。
     *
     * @param text 文字内容
     */
    public void assertTextNotExist(String text) {
        if (!mMarmot.exist(By.text(text))) {
            log("PASS, Text \"" + text + "\" does not exist.");
        } else {
            log("FAIL, Text \"" + text + "\" exists.");
            saveScreenshot(mMarmot.mFailedImgName);
            Assert.assertTrue(false);
        }
    }

    public void assertTrue(String message, boolean condition) {
        if (condition) {
            log(" PASS, " + message);
        } else {
            log(" FAIL, " + message);
            saveScreenshot(mMarmot.mFailedImgName);
        }

        Assert.assertTrue(condition);
    }

    public void assertFalse(String message, boolean condition) {
        if (!condition) {
            log(" PASS, " + message);
        } else {
            log(" FAIL, " + message);
            saveScreenshot(mMarmot.mFailedImgName);
        }

        Assert.assertFalse(condition);
    }

    public void assertUiObejctExist(BySelector bySelector) {
        if (mMarmot.exist(bySelector)) {
            log("PASS, UiObject exists.");
        } else {
            log("FAIL, UiObject does not exist.");
            saveScreenshot(mMarmot.mFailedImgName);
            Assert.assertTrue(false);
        }
    }

    public void assertUiObejctNotExist(BySelector bySelector) {
        if (!mMarmot.exist(bySelector)) {
            log("PASS, UiObject exists.");
        } else {
            log("FAIL, UiObject does not exist.");
            saveScreenshot(mMarmot.mFailedImgName);
            Assert.assertTrue(false);
        }
    }

    @Deprecated
    public void assertUiObejctExist(UiObject object) {
        if (object.exists()) {
            log("PASS, UiObject exists.");
        } else {
            log("FAIL, UiObject does not exist.");
            saveScreenshot(mMarmot.mFailedImgName);
            Assert.assertTrue(false);
        }
    }

    @Deprecated
    public void assertUiObjectNotExist(UiObject object) {
        if (object.exists() == false) {
            log("PASS, UiObject does not exist.");
        } else {
            log("FAIL, UiObject exists.");
            saveScreenshot(mMarmot.mFailedImgName);
            Assert.assertTrue(false);
        }
    }

    /**
     * 记录testrail中编号为testrailId case的结果
     *
     * @param testrailId 编号
     * @param result     结果
     */
    public void setTestrailResult(String testrailId, boolean result) {
        log("TESTRAILID: " + testrailId + " " + (result ? "PASS" : "FAIL"));
    }

    /**
     * 当前界面截图和参照图的同区域进行比较
     *
     * @param expectedImgPath 参照图绝对路径
     * @param fromX           局部图在参照图中左上角的x值
     * @param fromY           局部图在参照图中左上角的y值
     * @param endX            局部图在参照图中右下角的x值
     * @param endY            局部图在参照图中右下角的y值
     * @param d               容错率
     * @return 比较的结果，成功 true，失败 false。
     */
    private boolean compareImg(String expectedImgPath, int fromX, int fromY, int endX, int endY, double d) {
        saveScreenshot(actualImgPath);
        Bitmap actualBitmap = Bitmap.createBitmap(BitmapFactory.decodeFile(actualImgPath, null), fromX, fromY, (endX - fromX), (endY - fromY));
        Bitmap expectedBitmap = Bitmap.createBitmap(BitmapFactory.decodeFile(expectedImgPath, null), fromX, fromY, (endX - fromX), (endY - fromY));
        if (expectedBitmap == null || actualBitmap == null) {
            return false;
        }

        int nonMatchingPixels = 0;
        int width = expectedBitmap.getWidth();
        int height = expectedBitmap.getHeight();

        if (width != actualBitmap.getWidth() || height != actualBitmap.getHeight()) {
            return false;
        }

        int allowedMaxNonMatchPixels = (int) (width * height * d);
        int[] expectedBmpPixels = new int[width * height];
        int[] actualBmpPixels = new int[width * height];

        expectedBitmap.getPixels(expectedBmpPixels, 0, width, 0, 0, width, height);
        actualBitmap.getPixels(actualBmpPixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < expectedBmpPixels.length; i++) {
            if (expectedBmpPixels[i] != actualBmpPixels[i]) {
                nonMatchingPixels++;
            }
        }

        return nonMatchingPixels < allowedMaxNonMatchPixels ? true : false;
    }

    /**
     * 剪裁图片
     *
     * @param fromX 剪裁起点的x值
     * @param fromY 剪裁起点的y值
     * @param endX  剪裁结束点的x值
     * @param endY  剪裁结束点的y值
     * @return
     */
    private Bitmap getSubImage(String imgPath, int fromX, int fromY, int endX, int endY) {
        Bitmap subImg = null;

        try {
            subImg = Bitmap.createBitmap(BitmapFactory.decodeFile(imgPath, null), fromX, fromY, (endX - fromX), (endY - fromY));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return subImg;
    }

    private Bitmap mergeTwoImages(Bitmap img1, Bitmap img2) {
        int width = 0;
        int height = 0;
        int[] tmpPixels;

        if (img1 == null || img2 == null) {
            return null;
        }

        width = img1.getWidth();
        height = img1.getHeight();
        tmpPixels = new int[width * height];
        Bitmap newImage = Bitmap.createBitmap(width * 2, height, Bitmap.Config.ARGB_8888);

        try {
            img1.getPixels(tmpPixels, 0, width, 0, 0, width, height);
            newImage.setPixels(tmpPixels, 0, width, 0, 0, width, height);
            img2.getPixels(tmpPixels, 0, width, 0, 0, width, height);
            newImage.setPixels(tmpPixels, 0, width, width, 0, width, height);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return newImage;
    }

    private void log(String message) {
        Log.i(Marmot.LOG_TAG, message);
    }

    private void saveScreenshot(String imgPath) {
        mMarmot.takeScreenshot(new File(imgPath), 1.0f, 100);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}