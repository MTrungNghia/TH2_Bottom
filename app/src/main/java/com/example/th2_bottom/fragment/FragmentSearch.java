package com.example.th2_bottom.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.th2_bottom.DAO.SQLiteHelper;
import com.example.th2_bottom.R;
import com.example.th2_bottom.adapter.RecycleViewAdapter;
import com.example.th2_bottom.model.CongViec;

import java.util.List;
import java.util.Map;

public class FragmentSearch extends Fragment implements View.OnClickListener{
    private RecyclerView recyclerView;
    private TextView tvTong, tvtke;
    private Button btSearch, btTke;
    private SearchView searchView;
    private Spinner spCategory;
    private RecycleViewAdapter adapter;
    private SQLiteHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        adapter = new RecycleViewAdapter();
        db = new SQLiteHelper(getContext());
        List<CongViec> list = db.getAll();
        adapter.setList(list);
        tvTong.setText("Tong cong viec: " + list.size());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<CongViec> list = db.searchByTitle(s);
                tvTong.setText("Tong cong viec: " + list.size());
                adapter.setList(list);
                return false;
            }
        });
//        btSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String s = searchView.get
//                List<CongViec> list = db.searchByTitle(s);
//                tvTong.setText("Tong cong viec: " + list.size());
//                adapter.setList(list);
//                return false;
//            }
//        });
        btTke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = db.countByStatus();
                tvtke.setText("Thống kê số công việc theo tình trạng: \n"+ result);
            }
        });
        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int p, long l) {
                String cate = spCategory.getItemAtPosition(p).toString();
                List<CongViec> list;
                if(!cate.equalsIgnoreCase("All")) {
                    list = db.searchByCategory(cate);

                }else{
                    list = db.getAll();
                }
                adapter.setList(list);
                tvTong.setText("Tong cong viec: " + list.size());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recyclrView);
        tvTong = view.findViewById(R.id.tvTong);
        tvtke = view.findViewById(R.id.tke);
        btTke = view.findViewById(R.id.btTke);
        searchView = view.findViewById(R.id.search);
        spCategory = view.findViewById(R.id.spCategory);
        String[] arr = getResources().getStringArray(R.array.status);
        String[] arr1 = new String[arr.length+1];
        arr1[0] = "All";
        for(int i = 0;i < arr.length;i++) {
            arr1[i+1] = arr[i];
        }
        spCategory.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.item_spinner, arr1));
    }

    @Override
    public void onClick(View view) {

    }
}
