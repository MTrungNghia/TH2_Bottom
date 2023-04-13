package com.example.th2_bottom.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.th2_bottom.fragment.FragmentDanhSach;
import com.example.th2_bottom.fragment.FragmentSearch;
import com.example.th2_bottom.fragment.FragmentThongTin;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private int numPage=3;
    public FragmentAdapter(@NonNull FragmentManager fm, int num) {
        super(fm,num);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:return new FragmentDanhSach();
            case 1:return new FragmentThongTin();
            case 2:return new FragmentSearch();
            default:return new FragmentDanhSach();
        }
    }
    @Override
    public int getCount() {
        return numPage;
    }
}
