package com.android.launcher;

import com.orhanobut.logger.Logger;

public class CommonClassTest {

    public void test() {
        DeviceProfile deviceProfile = new DeviceProfile();
        Logger.e(deviceProfile.toString() + "nihao");
    }
}
