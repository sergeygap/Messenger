package com.gap.messenger.listDialogs;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListDialogsViewModel extends ViewModel {
    private FirebaseAuth auth;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("Users");

    private MutableLiveData<List<User>> listUsersLD = new MutableLiveData<>();

    public LiveData<List<User>> getListUsersLD() {
        return listUsersLD;
    }

    public ListDialogsViewModel() {
        auth = FirebaseAuth.getInstance();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<User> listUsers = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User user = dataSnapshot.getValue(User.class);
                    listUsers.add(user);
                }
                listUsersLD.setValue(listUsers);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

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
