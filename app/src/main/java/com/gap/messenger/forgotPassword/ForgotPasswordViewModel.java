package com.gap.messenger.forgotPassword;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordViewModel extends AndroidViewModel {
    public ForgotPasswordViewModel(@NonNull Application application) {
        super(application);
    }

    private FirebaseAuth auth;

    public void setAuth(FirebaseAuth auth) {
        this.auth = auth;
    }

    private ForgotPassword forgotPassword;

    public void setForgotPassword(ForgotPassword forgotPassword) {
        this.forgotPassword = forgotPassword;
    }

    public void forgotPassword(String email) {
        auth.sendPasswordResetEmail(email)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        forgotPassword.launchMainActivity();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        forgotPassword.makeToast(e.getMessage());
                    }
                });
    }

    public interface ForgotPassword {
        void launchMainActivity();
        void makeToast(String text);
    }
}
