package com.example.fangtoutiao.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;
import android.view.View;

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
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCommonClickListener.onClick();
            }
        });
    }
    private OnCommonClickListener onCommonClickListener;
    public interface OnCommonClickListener{
        void onClick();
    }

    public void setOnCommonClickListener(OnCommonClickListener onCommonClickListener){
        this.onCommonClickListener = onCommonClickListener;
    }
}
