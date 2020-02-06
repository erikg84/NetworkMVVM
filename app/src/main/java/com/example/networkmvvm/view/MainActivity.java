package com.example.networkmvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.networkmvvm.R;
import com.example.networkmvvm.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DisplayFragment.
        DisplayFragmentListener {
    private DisplayFragment displayFragment;
    private RecyclerViewFragment recyclerViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayFragment = new DisplayFragment();
        recyclerViewFragment = new RecyclerViewFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_manager,displayFragment)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void sendList(List<String> list) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_manager,recyclerViewFragment)
                .addToBackStack(null)
                .commit();
        recyclerViewFragment.setList(list);
    }
}
