package com.example.th2_bottom.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.th2_bottom.DAO.SQLiteHelper;
import com.example.th2_bottom.R;
import com.example.th2_bottom.UpdateDeleteActivity;
import com.example.th2_bottom.adapter.RecycleViewAdapter;
import com.example.th2_bottom.model.CongViec;

import java.util.List;

public class FragmentThongTin extends Fragment implements RecycleViewAdapter.ItemListener{
    private RecycleViewAdapter adapter;
    private RecyclerView recyclerView;
    private SQLiteHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thongtin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclrView);
        adapter = new RecycleViewAdapter();
        db = new SQLiteHelper((getContext()));
//        CongViec i = new CongViec( "Quat nha1", "Quet 3 tang", "04/04/2023", "Da hoan thanh", 1);
//        CongViec i2 = new CongViec ("Quat nha 2", "Quet 3 tang", "13/04/2023", "Da hoan thanh", 1);
//        db.addItem(i);
//        db.addItem(i2);
        List<CongViec> list = db.getAll();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        CongViec congViec = adapter.getItem(position);
        Intent intent = new Intent(getActivity(), UpdateDeleteActivity.class);
        intent.putExtra("congViec", congViec);
        startActivity(intent);
//        Toast.makeText(getContext(), congViec.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        List<CongViec>  list = db.getAll();
        adapter.setList(list);
    }
}
