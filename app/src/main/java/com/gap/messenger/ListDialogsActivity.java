package com.gap.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ListDialogsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dialogs);
    }


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, ListDialogsActivity.class);
        return intent;
    }

}