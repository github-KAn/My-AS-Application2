package com.example.note;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;

public class NoteActivity extends AppCompatActivity {

    CalendarView calendarView;
    EditText noteEditText;
    Button saveButton;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_note);
        calendarView=findViewById(R.id.calendarView);
        noteEditText=findViewById(R.id.noteEditText);
        saveButton=findViewById(R.id.saveButton);
        SharedPreferences pref = getPreferences(MODE_PRIVATE);
        int savedYear = pref.getInt("year", 0);
        if(savedYear!=0){
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR,savedYear);
            cal.set(Calendar.MONTH,pref.getInt("month", 0));
            cal.set(Calendar.DAY_OF_MONTH,pref.getInt("dayOfMonth", 0));
            calendarView.setDate(cal.getTimeInMillis());
        }
        // Xử lý sự kiện khi ngày trên CalendarView được chọn
        calendarView.setOnDateChangeListener((calendarView, year, month, dayOfMonth) -> {
            fileName = String.format("%02d_%02d_%04d", dayOfMonth, month + 1, year);
            noteEditText.setText("");
            try {
                FileInputStream fis = openFileInput(fileName);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                fis.close();
                noteEditText.setText(sb);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        saveButton.setOnClickListener(view -> {
            String noteContent = noteEditText.getText().toString();
            try {
                // Ghi nội dung vào file
                FileOutputStream fos = openFileOutput(fileName, MODE_PRIVATE);
                fos.write(noteContent.getBytes());
                fos.close();
                Toast.makeText(this, "Đã lưu ghi chú", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Lỗi khi lưu ghi chú", Toast.LENGTH_LONG).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        pref.edit()
                .putInt("font size", 30)
                .putString("dulieu", "Gia tri luu tru trong Pref").apply();
//        pref.edit().putString("dulieu", "Gia tri luu tru trong Pref").commit();
//        String s = pref.getString("dulieu", "Gia tri mac dịnh");
//        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        Integer fontSize = pref.getInt("font size",0);
        Toast.makeText(this, fontSize.toString(), Toast.LENGTH_LONG).show();
        try {
            FileInputStream fis = openFileInput("data.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            fis.close();
            Toast.makeText(this, sb, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}