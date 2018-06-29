package com.rafalmanka.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rafalmanka.example.example1.DateActivity;
import com.rafalmanka.example.example2.RangeActivity;
import com.rafalmanka.example.example3.DefaultAdapterActivity;
import com.rafalmanka.example.example4.DefaultRangeAdapterActivity;
import com.rafalmanka.example.example5.RedLayoutActivity;

public class NavigationActivity extends AppCompatActivity {


    private class Item {

        private final int id;
        @StringRes
        private final int title;
        @StringRes
        private final int description;
        @DrawableRes
        private final int preview;

        Item(int id, @StringRes int title, @StringRes int description, @DrawableRes int preview) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.preview = preview;
        }

        @Nullable
        Class<? extends Activity> getDestination() {
            switch (id) {
                case 1:
                    return DateActivity.class;
                case 2:
                    return RangeActivity.class;
                case 3:
                    return DefaultAdapterActivity.class;
                case 4:
                    return DefaultRangeAdapterActivity.class;
                case 5:
                    return RedLayoutActivity.class;
                default:
                    return null;
            }
        }

    }


    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView textViewTitle;
        private final TextView textViewDescription;
        private final ImageView image;
        private Item item;

        MyViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            image = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);
        }

        void refresh(Item item) {
            this.item = item;
            textViewTitle.setText(item.title);
            textViewDescription.setText(item.description);
            image.setImageResource(item.preview);
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
                new Item(1, R.string.title_example_1, R.string.description_example_1, R.drawable.example1),
                new Item(2, R.string.title_example_2, R.string.description_example_2, R.drawable.example2),
                new Item(3, R.string.title_example_3, R.string.description_example_3, R.drawable.example3),
                new Item(4, R.string.title_example_4, R.string.description_example_4, R.drawable.example4),
                new Item(5, R.string.title_example_5, R.string.description_example_5, R.drawable.example5),
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
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
