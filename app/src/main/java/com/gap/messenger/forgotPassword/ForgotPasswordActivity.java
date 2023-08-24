package com.gap.messenger.forgotPassword;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProvider;

import com.gap.messenger.login.MainActivity;
import com.gap.messenger.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private TextInputEditText etEmail;
    private AppCompatButton buttonResetPassword;

    private ForgotPasswordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        initViews();
        viewModel.setAuth(FirebaseAuth.getInstance());
        setOnForgotPasswordButtonClickLitener();
        viewModelForgotPassword();
    }

    private void viewModelForgotPassword() {
        viewModel.setForgotPassword(new ForgotPasswordViewModel.ForgotPassword() {
            @Override
            public void launchMainActivity() {
                Intent intent = new Intent(ForgotPasswordActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void makeToast(String text) {
                Toast.makeText(ForgotPasswordActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setOnForgotPasswordButtonClickLitener() {
        buttonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.forgotPassword(etEmail.getText().toString());
            }
        });
    }

    private void initViews() {
        etEmail = findViewById(R.id.et_email);
        buttonResetPassword = findViewById(R.id.button_reset_password);
        viewModel = new ViewModelProvider(this).get(ForgotPasswordViewModel.class);
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, ForgotPasswordActivity.class);
        return intent;
    }

}