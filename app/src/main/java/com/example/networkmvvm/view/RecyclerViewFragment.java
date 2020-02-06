package com.example.networkmvvm.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.networkmvvm.R;
import com.example.networkmvvm.adapter.PhotoAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment {
    private RecyclerView recyclerView;
    private PhotoAdapter adapter;
    private List<String> myList;
    private GridLayoutManager gridLayoutManager;

    public RecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        gridLayoutManager = new GridLayoutManager(container.getContext(),1 );

        recyclerView = rootView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new PhotoAdapter(myList, gridLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        return rootView;
    }


    public void setList(List<String> list){
        myList = list;

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.menu_main, menu);
        //menu.findItem(R.id.menu_switch).set
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_switch){
            switchLayout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void switchLayout() {
        if (gridLayoutManager.getSpanCount() == 1) {
            gridLayoutManager.setSpanCount(3);
        } else {
            gridLayoutManager.setSpanCount(1);
        }
        adapter.notifyItemRangeChanged(0, adapter.getItemCount());
    }
}
