package com.example.th2_bottom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.th2_bottom.adapter.FragmentAdapter;
import com.example.th2_bottom.model.CongViec;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivityTablayout extends AppCompatActivity {
    public List<CongViec> list=new ArrayList<>();
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tablayout);
        tabLayout=findViewById(R.id.tab);
        viewPager=findViewById(R.id.viewPager);
        adapter=new FragmentAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_list);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_info);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_search);

    }
}