package com.gap.messenger.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.gap.messenger.R;
import com.gap.messenger.forgotPassword.ForgotPasswordActivity;
import com.gap.messenger.listDialogs.ListDialogsActivity;
import com.gap.messenger.register.RegistrationActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityBase";
    private FirebaseAuth auth;

    private TextView forgotPassword;
    private TextView register;
    private AppCompatButton buttonLogin;
    private TextInputEditText email;
    private TextInputEditText password;

    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        clickListeners();
        viewModelLogin();
        observeUserLD();
    }

    private void observeUserLD() {
        viewModel.getUser().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                Intent intent = ListDialogsActivity.newIntent(MainActivity.this);
                startActivity(intent);
                finish();
            }
        });
    }

    private void viewModelLogin() {
        viewModel.setLogin(new LoginViewModel.Login() {
            @Override
            public void makeToast(String text) {
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clickListeners() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em = email.getText().toString().trim();
                String pa = password.getText().toString().trim();
                if (em != "" && pa != "") {
                    viewModel.login(em, pa);
                }
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ForgotPasswordActivity.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = RegistrationActivity.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });

    }

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    private void initViews() {
        forgotPassword = findViewById(R.id.forgot_password);
        register = findViewById(R.id.register);
        buttonLogin = findViewById(R.id.button_login);
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }
}