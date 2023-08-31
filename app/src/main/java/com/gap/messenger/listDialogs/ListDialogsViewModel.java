package com.gap.messenger.listDialogs;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

public class ListDialogsViewModel extends ViewModel {
    private FirebaseAuth auth;
    public ListDialogsViewModel() {
        auth = FirebaseAuth.getInstance();
    }

    private SignOut signOut;

    public void setSignOut(SignOut signOut) {
        this.signOut = signOut;
    }



    public void signOut() {
        auth.signOut();
        signOut.signOut();
    }

    public interface SignOut {
        void signOut();
    }
}
