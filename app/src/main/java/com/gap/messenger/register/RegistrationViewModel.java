package com.gap.messenger.register;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.gap.messenger.listDialogs.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationViewModel extends AndroidViewModel {
    public RegistrationViewModel(@NonNull Application application) {
        super(application);
    }

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("Users");

    private Registration registration;

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    private FirebaseAuth auth;

    public void setAuth(FirebaseAuth auth) {
        this.auth = auth;
    }

    public void registration(String email, String password, String name, String lastName, String age) {
        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        registration.launchMainActivity();
                        FirebaseUser firebaseUser = authResult.getUser();
                        if (firebaseUser == null) {
                            return;
                        }
                        User user = new User(
                                firebaseUser.getUid(), name, lastName, age, false
                        );
                        databaseReference.child(firebaseUser.getUid()).setValue(user);
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
