package com.example.th2_bottom.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FragmentDanhSach extends Fragment implements RecycleViewAdapter.ItemListener{
    private RecyclerView recyclerView;
    RecycleViewAdapter adapter;
    private SQLiteHelper db;
    private TextView tvTong;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frangment_danh_sach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclrView);
        tvTong = view.findViewById(R.id.tvTong);
        adapter = new RecycleViewAdapter();
        db = new SQLiteHelper(getContext());
        Date d = new Date();
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        List<CongViec> list = db.getByDate(f.format(d));
        adapter.setList(list);
        tvTong.setText("Tong so cong viec: " + (list.size()));
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
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
    }

    @Override
    public void onResume() {
        super.onResume();
        Date d = new Date();
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        List<CongViec> list = db.getByDate(f.format(d));
        adapter.setList(list);
        tvTong.setText("Tong so cong viec: " + (list.size()));
    }
}
