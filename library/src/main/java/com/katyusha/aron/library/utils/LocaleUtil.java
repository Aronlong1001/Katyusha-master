package com.katyusha.aron.library.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.katyusha.aron.library.BaseApplication;

import java.util.Locale;
import java.util.UUID;

/**
 * Created by aron on 2017/10/11.
 */

public class LocaleUtil {

    /**
     * @return eg:en-us
     */
    public static String getLanguage() {
        Locale locale = Locale.getDefault();
        return (locale.getLanguage() + "-" + locale.getCountry()).toLowerCase(locale);
    }

    /**
     * @return
     */
    public static String getVersionName() {
        PackageInfo packInfo = getPackageInfo();
        if (packInfo != null) {
            return packInfo.versionName;
        } else {
            return "";
        }
    }

    /**
     * @return
     */
    public static PackageInfo getPackageInfo() {
        try {
            Context context = BaseApplication.getAppContext();
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @return
     */
    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * @return
     */
    public static String getPhoneModel() {
        return Build.MODEL;
    }

    /**
     * @return
     */
    public static String getDeviceID() {
        Context context = BaseApplication.getAppContext();
        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        final String tmDevice, tmSerial, androidId;
        tmDevice = tm.getDeviceId() + "";
        tmSerial = tm.getSimSerialNumber() + "";
        androidId = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider
                .Settings.Secure.ANDROID_ID) + "";

        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String deviceId = deviceUuid.toString();
        return deviceId;
    }
}
