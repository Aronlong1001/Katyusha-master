package com.katyusha.aron.library.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.katyusha.aron.library.R;


/**
 * Created by aron on 2017/10/24.
 */

public class LoadingDialog extends Dialog {

    private String loadingMessage;

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.NiceDialog);
        init(context);
    }

    public void setMessage(String message){
        this.loadingMessage = message;
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.loading_layout, null);
        setContentView(view);

        TextView message = (TextView) view.findViewById(R.id.loading_message);
        if (!TextUtils.isEmpty(loadingMessage)) {
            message.setText(loadingMessage);
        }

//        Window window = getWindow();
//        if (window != null) {
//            WindowManager.LayoutParams lp = window.getAttributes();
//            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
//            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//            window.setAttributes(lp);
//        }

        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

}
