package com.example.administrator.newsapp.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.administrator.newsapp.R;

/**
 * Created by Administrator on 2017/1/15.
 */

public class CustomProgressDialog extends Dialog {
    private Context context;
    private static CustomProgressDialog dialog = null;
    public CustomProgressDialog(Context context) {
        super(context);
        this.context = context;
    }
    public CustomProgressDialog(Context context, int theme) {
        super(context, theme);
    }
    public static CustomProgressDialog createDialog(Context context,String msg) {
        dialog = new CustomProgressDialog(context, R.style.CustomProgressDialog);
        View view = LayoutInflater.from(context).inflate(R.layout.define_progress_dialog, null);
        ((TextView) view.findViewById(R.id.loading_msg)).setText(msg);
        dialog.setContentView(view);
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
    public void onWindowFocusChanged(boolean hasFocus) {
        if (dialog == null) {
            return;
        }
        ImageView imageView = (ImageView) dialog.findViewById(R.id.loading_pb);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();
    }
}
