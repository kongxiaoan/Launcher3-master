package com.android.launcher.utils;

import android.os.Build;
import android.text.TextUtils;

/**
 * 布局工具
 */
public class UIUtils {
    private UIUtils() {

    }

    private static String mCurrentCompany;
    private static String mCurrentModel;

    private static class SingleInstance {
        private static UIUtils mUiUtils = new UIUtils();
    }

    public static UIUtils getInstance() {
        return SingleInstance.mUiUtils;
    }

    /**
     * 手机名称
     *
     * @return
     */
    public String getDeviceName() {
        if (!TextUtils.isEmpty(mCurrentModel)) return mCurrentModel;
        String model = Build.MODEL;
        if (model == null) {
            model = "";
        }
        mCurrentModel = model;
        return mCurrentModel;
    }

    public String getCompany() {
        if (!TextUtils.isEmpty(mCurrentCompany)) return mCurrentCompany;
        String company = Build.BRAND;
        if (company == null) {
            mCurrentCompany = "";
        }
        mCurrentCompany = company;
        return company;
    }


}
