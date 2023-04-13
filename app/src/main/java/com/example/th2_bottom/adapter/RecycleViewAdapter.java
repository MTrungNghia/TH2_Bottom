package com.example.th2_bottom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.th2_bottom.R;
import com.example.th2_bottom.model.CongViec;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder>{
    private List<CongViec> list;
    private ItemListener itemListener;
    public RecycleViewAdapter() {
        list = new ArrayList<>();
    }

    public List<CongViec> getList() {
        return list;
    }

    public void setList(List<CongViec> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public CongViec getItem(int p) {
        return list.get(p);
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        CongViec congViec = list.get(position);
        holder.name.setText(congViec.getName());
        holder.content.setText(congViec.getContent());
        holder.date.setText(congViec.getDate());
        holder.status.setText(congViec.getStatus());
        holder.together.setText(ChinhTogether(congViec.getTogether()).toString());
    }

    public String ChinhTogether(int a) {
        String ctac = "";
        if(a == 0) {
            ctac = "Làm chung";
        }
        else {
            ctac = "Một mình";
        }
        return ctac;
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name, content, status, date, together;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            content = itemView.findViewById(R.id.tvContent);
            date = itemView.findViewById(R.id.tvDate);
            status = itemView.findViewById(R.id.tvStatus);
            together = itemView.findViewById(R.id.tvTogether);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemListener != null) {
                itemListener.onItemClick(view, getAdapterPosition());
            }
        }
    }
    public interface ItemListener {
        void onItemClick(View view, int position);
    }
}
