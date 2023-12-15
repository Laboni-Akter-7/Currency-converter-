package com.saadahmedev.currencyconverter.util;

import android.content.Context;
import android.widget.TextView;
import com.saadahmedev.currencyconverter.R;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class ProgressDialog {

    private final BottomSheetDialog bottomSheetDialog;

    private ProgressDialog(Context context) {
        bottomSheetDialog = new BottomSheetDialog(context);
    }

    public static ProgressDialog getInstance(Context context) {
        return new ProgressDialog(context);
    }

    public void show(String message) {
        bottomSheetDialog.setContentView(R.layout.dialog_loading);
        TextView progressText = bottomSheetDialog.findViewById(R.id.tv_progress_message);
        assert progressText != null;
        progressText.setText(message);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.show();
    }

    public void dismiss() {
        if (bottomSheetDialog.isShowing()) bottomSheetDialog.dismiss();
    }
}