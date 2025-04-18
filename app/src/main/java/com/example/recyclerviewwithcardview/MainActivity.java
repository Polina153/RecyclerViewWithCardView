package com.example.recyclerviewwithcardview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initToolbar();
        RecyclerView recyclerView = findViewById(R.id.recycler_view_lines);
        CardSource data = new CardSourceImpl(getResources()).init();
        initRecyclerView(recyclerView, data);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);
        //setSupportActionBar(toolbar);
    }

    private void initRecyclerView(RecyclerView recyclerView, CardSource data) {

        // Эта установка служит для повышения производительности системы
        recyclerView.setHasFixedSize(true);

        // Будем работать со встроенным менеджером
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Установим адаптер
        final MyAdapter adapter = new MyAdapter(data);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.separator, null));
        recyclerView.addItemDecoration(itemDecoration);

        // Установим слушателя
        adapter.SetOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getApplicationContext(), String.format("Позиция - %d", position), Toast.LENGTH_SHORT).show();
            }
        });

    }
}