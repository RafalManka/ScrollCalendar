package com.rafalmanka.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NavigationActivity extends AppCompatActivity {


    private class Item {

        private final int id;
        private final String title;

        Item(int id, String title) {
            this.id = id;
            this.title = title;
        }

        @Nullable
        Class<? extends Activity> getDestination() {
            if (id == 1) {
                return DateActivity.class;
            } else if (id == 2) {
                return RangeActivity.class;
            }
            return null;
        }
    }


    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView textView;
        private Item item;

        MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            textView.setOnClickListener(this);
        }

        void refresh(Item item) {
            this.item = item;
            textView.setText(item.title);
        }

        @Override
        public void onClick(View view) {
            startActivityFor(item);
        }
    }

    private void startActivityFor(Item item) {
        Class<? extends Activity> destination = item.getDestination();
        if (destination == null) {
            return;
        }
        startActivity(new Intent(this, destination));
    }

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter = new RecyclerView.Adapter<MyViewHolder>() {

        private final Item[] items = new Item[]{
                new Item(1, "Date"),
                new Item(2, "Range")
        };

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.row_navigation, parent, false)
            );
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.refresh(items[position]);
        }

        @Override
        public int getItemCount() {
            return items.length;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(adapter);
    }
}
