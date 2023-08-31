package com.gap.messenger.listDialogs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.gap.messenger.ChatActivity;
import com.gap.messenger.R;
import com.gap.messenger.login.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListDialogsActivity extends AppCompatActivity {


    private ListDialogsViewModel viewModel;
    private UsersAdapter usersAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dialogs);
        initViews();
        viewModelSignOut();
        adapterClickOnItem();


    }

    private void adapterClickOnItem() {
        usersAdapter.setClickOnItem(new UsersAdapter.ClickOnItem() {
            @Override
            public void clickOnItem(User user) {
                startActivity(ChatActivity.newIntent(ListDialogsActivity.this));
            }
        });
    }

    private void viewModelSignOut() {
        viewModel.setSignOut(new ListDialogsViewModel.SignOut() {
            @Override
            public void signOut() {
                Intent intent = MainActivity.newIntent(ListDialogsActivity.this);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initViews() {
        viewModel = new ViewModelProvider(this).get(ListDialogsViewModel.class);
        recyclerView = findViewById(R.id.main_recycler_view);
        usersAdapter = new UsersAdapter();
        recyclerView.setAdapter(usersAdapter);
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
        return new Intent(context, ListDialogsActivity.class);
    }

}