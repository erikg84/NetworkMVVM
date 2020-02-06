package com.example.networkmvvm.view;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.networkmvvm.R;
import com.example.networkmvvm.viewmodel.MainViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayFragment extends Fragment {
    private DisplayFragmentListener listener;
    private MainViewModel viewModel;
    private TextView tvDisplay;
    private Button btnLoad;
    private EditText etCount;
    private RecyclerViewFragment recyclerViewFragment;

    public interface DisplayFragmentListener{
        void sendList(List<String> list);
    }
    public DisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_display, container, false);
        tvDisplay = rootView.findViewById(R.id.tvDisplay);
        btnLoad = rootView.findViewById(R.id.btnLoad);
        etCount = rootView.findViewById(R.id.etCount);
        viewModel = new ViewModelProvider.NewInstanceFactory().create(MainViewModel.class);
        recyclerViewFragment = new RecyclerViewFragment();




        setupObservers();
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String count = etCount.getText().toString();
                viewModel.fetchShibeData(Integer.parseInt(count));
            }
        });

        return rootView;
    }
    private void setupObservers() {
        viewModel.getShibesLiveData().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                if (strings != null) {
                    if (strings.isEmpty())
                        tvDisplay.setText("EMPTY LIST");
                    else{
                        listener.sendList(strings);
                    }
                }
            }
        });

        viewModel.getErrorLiveData().observe(this, isError -> {
            if (!isError.isEmpty())
                Toast.makeText(getActivity(), isError, Toast.LENGTH_SHORT).show();
        });
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof DisplayFragmentListener) {
            listener = (DisplayFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement DisplayFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
