package com.gap.messenger.listDialogs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.gap.messenger.R;
import com.gap.messenger.login.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

public class ListDialogsActivity extends AppCompatActivity {


    private ListDialogsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dialogs);
        initViews();
        viewModelSignOut();
    }

    private void viewModelSignOut() {
        viewModel.setSignOut(new ListDialogsViewModel.SignOut() {
            @Override
            public void signOut() {
                Intent intent = new Intent(ListDialogsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        viewModel = new ViewModelProvider(this).get(ListDialogsViewModel.class);
        viewModel.setAuth(FirebaseAuth.getInstance());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.sign_out) {
            viewModel.signOut();
        }

        return super.onOptionsItemSelected(item);
    }




    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, ListDialogsActivity.class);
        return intent;
    }

}