package com.example.splash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Dashboard extends AppCompatActivity {
    Button button;
    RecyclerView recview;
    myAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        button = (Button) findViewById(R.id.button);
        recview = (RecyclerView) findViewById(R.id.recview);


        recview.setHasFixedSize(true);
        recview.setLayoutManager(new LinearLayoutManager(this));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Login.class));
            }
        });

        FirebaseRecyclerOptions<mode> options =
                new FirebaseRecyclerOptions.Builder<mode>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RegistrationDetail"), mode.class)
                        .build();

        adapter = new myAdapter(options,getApplicationContext());
        recview.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processSearch(s);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    private void processSearch(String s) {
        FirebaseRecyclerOptions<mode> options =
                new FirebaseRecyclerOptions.Builder<mode>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RegistrationDetail").orderByChild("specialization").startAt(s).endAt(s + "\uf8ff"), mode.class)
                        .build();
        adapter = new myAdapter(options,getApplicationContext());
        adapter.startListening();
        recview.setAdapter(adapter);


    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
       adapter.startListening();
    }
}