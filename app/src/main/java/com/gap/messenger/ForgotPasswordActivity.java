package com.gap.messenger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.textfield.TextInputEditText;

public class ForgotPasswordActivity extends AppCompatActivity {

    private TextInputEditText etEmail;
    private AppCompatButton buttonResetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        initViews();
    }

    private void initViews() {
        etEmail = findViewById(R.id.et_email);
        buttonResetPassword = findViewById(R.id.button_reset_password);
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, ForgotPasswordActivity.class);
        return intent;
    }

}