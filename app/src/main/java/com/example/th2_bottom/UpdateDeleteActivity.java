package com.example.th2_bottom;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;


import com.example.th2_bottom.DAO.SQLiteHelper;
import com.example.th2_bottom.model.CongViec;

import java.util.Calendar;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener {
    public Spinner sp;
    private EditText eName, eContent, eDate;
    private Button btUpdate, btBack, btRemove, btDate;
    private RadioButton btChung, btRieng;
    private CongViec congViec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
        btBack.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        btRemove.setOnClickListener(this);

        btDate.setOnClickListener(this);
        Intent intent = getIntent();
        congViec = (CongViec)intent.getSerializableExtra("congViec");
        eName.setText(congViec.getName());
        eContent.setText(congViec.getContent());
        eDate.setText(congViec.getDate());
        int p = 0 ;
        for(int i = 0;i < sp.getCount();i++) {
            if(sp.getItemAtPosition(i).toString().equalsIgnoreCase(congViec.getStatus())) {
                p = i;
                break;
            }
        }
        int together = congViec.getTogether();
        if(together == 0) {
            btChung.setChecked(true);
        }else if(together == 1) {
            btRieng.setChecked(true);
        }

        sp.setSelection(p);
    }
    private void initView() {
        sp = findViewById(R.id.spCategory);
        eName = findViewById(R.id.tvTitle);
        eContent = findViewById(R.id.tvContent);
        eDate = findViewById(R.id.tvDate);
        btRieng = findViewById(R.id.rRieng);
        btChung = findViewById(R.id.rChung);
        btUpdate = findViewById(R.id.btUpdate);
        btDate = findViewById(R.id.btSedate);
        btBack = findViewById(R.id.btBack);
        btRemove = findViewById(R.id.btRemove);
        sp.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner,getResources().getStringArray(R.array.status)));
    }

    @Override
    public void onClick(View view) {
        SQLiteHelper db = new SQLiteHelper(this);
        if(view == btDate){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(UpdateDeleteActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    String date = "";
                    if(m > 8) {
                        date = d+"/"+(m+1)+"/"+y;

                    }else{
                        date = d+"/0"+(m+1)+"/"+y;
                    }
                    if(d <= 9) {
                        date = "0" + date;
                    }
                    eDate.setText(date);
                }
            },year, month, day);
            dialog.show();
        }
        if(view == btBack) {
            finish();
        }
        if(view == btUpdate){
            String t = eName.getText().toString();
            String p = eContent.getText().toString();
            String c = sp.getSelectedItem().toString();
            String d = eDate.getText().toString();
            int select = 0;
            if(btRieng.isChecked()) {
                select = 1;
            }
            if(btChung.isChecked()) {
                select = 0;
            }
            if(!t.isEmpty()) {
                int id = congViec.getId();
                CongViec i = new CongViec(id,t,p,d,c,select);
                db = new SQLiteHelper(this);
                db.update(i);
                finish();

            }
        }
        if(view==btRemove) {
            int id = congViec.getId();
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Thong bao xoa");
            builder.setMessage("Ban co chac muon xoa " + congViec.getName() + " nay khong?");
            builder.setIcon(R.drawable.remove);
            builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SQLiteHelper bb = new SQLiteHelper(getApplicationContext());
                    bb.delete(id);

                    finish();
                }
            });
            builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}