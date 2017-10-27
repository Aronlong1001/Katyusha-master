package com.katyusha.aron.library.utils;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by aron on 2017/8/1.
 */

public class BLToast {

    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
    private static int checkNotification = 0;
    private Object mToast;

    public BLToast(Context context, String message, int duration) {
        checkNotification = isNotificationEnabled(context)?0:1;
        if(checkNotification == 1) {
            this.mToast = BenLaiToast.makeText(context, message, duration);
        } else {
            this.mToast = android.widget.Toast.makeText(context, message, duration);
        }
    }

    private BLToast(Context context, int resId, int duration) {
        if(checkNotification == -1) {
            checkNotification = isNotificationEnabled(context)?0:1;
        }

        if(checkNotification == 1 && context instanceof Activity) {
            this.mToast = BenLaiToast.makeText(context, resId, duration);
        } else {
            this.mToast = android.widget.Toast.makeText(context, resId, duration);
        }

    }

    public static BLToast makeText(Context context, String message, int duration) {
        return new BLToast(context, message, duration);
    }

    public static BLToast makeText(Context context, int resId, int duration) {
        return new BLToast(context, resId, duration);
    }

    public void show() {
        if(this.mToast instanceof BenLaiToast) {
            ((BenLaiToast)this.mToast).show();
        } else if(this.mToast instanceof android.widget.Toast) {
            ((android.widget.Toast)this.mToast).show();
        }

    }

    public void cancel() {
        if(this.mToast instanceof BenLaiToast) {
            ((BenLaiToast)this.mToast).cancel();
        } else if(this.mToast instanceof android.widget.Toast) {
            ((android.widget.Toast)this.mToast).cancel();
        }

    }

    public void setText(CharSequence s) {
        if(this.mToast instanceof BenLaiToast) {
            ((BenLaiToast)this.mToast).setText(s);
        } else if(this.mToast instanceof android.widget.Toast) {
            ((android.widget.Toast)this.mToast).setText(s);
        }

    }

    private static boolean isNotificationEnabled(Context context) {
        if(Build.VERSION.SDK_INT >= 19) {
            AppOpsManager mAppOps = (AppOpsManager)context.getSystemService("appops");
            ApplicationInfo appInfo = context.getApplicationInfo();
            String pkg = context.getApplicationContext().getPackageName();
            int uid = appInfo.uid;
            Class appOpsClass = null;

            try {
                appOpsClass = Class.forName(AppOpsManager.class.getName());
                Method e = appOpsClass.getMethod("checkOpNoThrow", new Class[]{Integer.TYPE, Integer.TYPE, String.class});
                Field opPostNotificationValue = appOpsClass.getDeclaredField("OP_POST_NOTIFICATION");
                int value = ((Integer)opPostNotificationValue.get(Integer.class)).intValue();
                return ((Integer)e.invoke(mAppOps, new Object[]{Integer.valueOf(value), Integer.valueOf(uid), pkg})).intValue() == 0;
            } catch (Exception var9) {
                var9.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }
}
