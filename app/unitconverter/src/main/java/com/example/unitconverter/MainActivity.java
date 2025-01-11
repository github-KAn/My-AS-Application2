package com.example.unitconverter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
//    TextView textView1;
    Button button1;
    EditText editText1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//        textView1= findViewById(R.id.txt1);
        editText1=findViewById(R.id.editTextText1);
        button1=findViewById(R.id.button1);
        button1.setOnClickListener(view->{
//            textView1.setText("Xin Chao");
            double km =Double.parseDouble(editText1.getText().toString());
            double miles= km*0.62;
            Intent intent =new Intent(this,SecondActivity.class);
            intent.putExtra("miles",miles);
            startActivity(intent);
//            textView1.setText(""+miles);
        });
//        textView1.setText("Lap trinh android");
//        textView1.setText(new Date().toString());
        Log.d("KTQD","Phuong thuc onCreate() da duocgoi");
        Log.i("MainActivity", "Đã hoàn tất");
    }

    @Override
    protected void onStart() {
        super.onStart();
//        textView1.setText("Seconds:"+new Date().getSeconds());
        Log.d("KTQD","Phuong thuc onStart() da duocgoi");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("KTQD","Phuong thuc onResume() da duocgoi");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("KTQD","Phuong thuc onPause() da duocgoi");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("KTQD","Phuong thuc onStop() da duocgoi");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("KTQD","Phuong thuc onDestroy() da duocgoi");
    }
}