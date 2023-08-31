package com.gap.messenger.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProvider;

import com.gap.messenger.R;
import com.gap.messenger.login.MainActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private static final String TAG = "RegistrationActivityLog";
    private TextInputEditText etEmail;
    private TextInputEditText etPassword;
    private AppCompatButton signUp;

    private EditText etName;
    private EditText etLastName;
    private EditText etAge;

    private RegistrationViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initViews();
        setOnRegisterButtonClickListener();
        viewModel.setAuth(FirebaseAuth.getInstance());
        viewModelImplement();

    }

    private void viewModelImplement() {
        viewModel.setRegistration(new RegistrationViewModel.Registration() {
            @Override
            public void makeToast(String text) {
                Toast.makeText(RegistrationActivity.this, text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void launchMainActivity() {
                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setOnRegisterButtonClickListener() {
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.registration(workWithText(etEmail), workWithText(etPassword),
                        workWithText(etName), workWithText(etLastName), workWithText(etAge)
                );
            }
        });
    }

    String workWithText(TextView textView) {
        return textView.getText().toString().trim();
    }

    private void initViews() {
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        signUp = findViewById(R.id.sign_up);
        etName = findViewById(R.id.et_name);
        etLastName = findViewById(R.id.et_last_name);
        etAge = findViewById(R.id.et_years);
        viewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);
    }


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, RegistrationActivity.class);
        return intent;
    }


}