package com.example.fangtoutiao.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.fangtoutiao.R;

public class CommonDialog extends Dialog {
    public CommonDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_common);

        Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
    }
}
