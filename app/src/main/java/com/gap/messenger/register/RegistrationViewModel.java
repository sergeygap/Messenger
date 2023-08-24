package com.gap.messenger.register;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.gap.messenger.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationViewModel extends AndroidViewModel {
    public RegistrationViewModel(@NonNull Application application) {
        super(application);
    }

    private Registration registration;

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    private FirebaseAuth auth;

    public void setAuth(FirebaseAuth auth) {
        this.auth = auth;
    }

    public void registration(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        registration.launchMainActivity();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        registration.makeToast(String.valueOf(e.getMessage()));
                    }
                });
    }

    public interface Registration {
        void makeToast(String text);

        void launchMainActivity();
    }

}
