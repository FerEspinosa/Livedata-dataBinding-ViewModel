package com.curso.android.app.practica.livedatavmdatabinding;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.curso.android.app.practica.livedatavmdatabinding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    MyViewModel viewModel;
    ActivityMainBinding binding;

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

        //
        viewModel =

                // "this" (the activity) is the owner of the ViewModel. So it will be killed along the activity
                new ViewModelProvider(this)

                // if a ViewModel exists it retrieves it, otherwise it creates a new one
                // Therefore it holds the data for the whole app, surviving configuration changes
                .get(MyViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.incrementCounter();
            }
        });

        binding.textView.setText(" " + viewModel.getCounter());

        // Observing the LiveData
        viewModel.getCounter().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                // Update de UI when LiveData changes
                binding.textView.setText("" + integer);
            }
        });

    }
}