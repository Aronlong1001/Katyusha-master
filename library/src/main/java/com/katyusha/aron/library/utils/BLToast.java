package com.katyusha.aron.library.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

/**
 * Created by aron on 2017/8/1.
 */

public class BLToast {

    private static int checkNotification = 0;
    private Object mToast;
    public static int LENGTH_SHORT = Toast.LENGTH_SHORT;
    public static int LENGTH_LONG = Toast.LENGTH_LONG;

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
            ((BenLaiToast)this.mToast).hideToast();
        } else if(this.mToast instanceof android.widget.Toast) {
            ((android.widget.Toast)this.mToast).cancel();
        }

    }

    public static boolean isNotificationEnabled(Context context) {
        if(Build.VERSION.SDK_INT >= 19) {
            NotificationManagerCompat manager = NotificationManagerCompat.from(context);
            boolean isOpened = manager.areNotificationsEnabled();
            return isOpened;
        } else {
            return false;
        }
    }
}
