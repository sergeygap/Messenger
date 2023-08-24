package com.gap.messenger.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProvider;

import com.gap.messenger.ListDialogsActivity;
import com.gap.messenger.R;
import com.gap.messenger.forgotPassword.ForgotPasswordActivity;
import com.gap.messenger.register.RegistrationActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
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
        viewModel.setAuth(FirebaseAuth.getInstance());
        clickListeners();
        viewModelLogin();
        auth = FirebaseAuth.getInstance();


        FirebaseUser user = auth.getCurrentUser();
        if (user == null) {
            Log.d(TAG, "user is not authorized");
        } else {
            Log.d(TAG, "Authorized " + user.getUid());
        }


        auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Log.d(TAG, "onAuthStateChanged: " + firebaseAuth);
            }
        });


    }

    private void viewModelLogin() {
        viewModel.setLogin(new LoginViewModel.Login() {
            @Override
            public void launchListDialogs() {
               Intent intent = ListDialogsActivity.newIntent(MainActivity.this);
               startActivity(intent);
            }

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
                viewModel.login(email.getText().toString(), password.getText().toString());
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



    private void initViews() {
        forgotPassword = findViewById(R.id.forgot_password);
        register = findViewById(R.id.register);
        buttonLogin = findViewById(R.id.button_login);
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }
}