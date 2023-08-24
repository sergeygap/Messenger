package com.gap.messenger.listDialogs;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.google.firebase.auth.FirebaseAuth;

public class ListDialogsViewModel extends AndroidViewModel {
    public ListDialogsViewModel(@NonNull Application application) {
        super(application);
    }

    private SignOut signOut;

    public void setSignOut(SignOut signOut) {
        this.signOut = signOut;
    }

    private FirebaseAuth auth;

    public void setAuth(FirebaseAuth auth) {
        this.auth = auth;
    }

    public void signOut() {
        auth.signOut();
        signOut.signOut();
    }

    public interface SignOut {
        void signOut();
    }
}
