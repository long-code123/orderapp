package com.app.orderfood.fb;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.app.orderfood.R;

import java.util.Objects;

public class DialogLoading {
    static Dialog dialogLoading;

    public static void show(Activity context) {
        dialogLoading = new Dialog(context);
        dialogLoading.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(dialogLoading.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogLoading.setContentView(R.layout.dialog_loading);
        dialogLoading.show();
    }

    public static void hideDialog() {
        if (dialogLoading != null) {
            dialogLoading.hide();
        }
    }

}
