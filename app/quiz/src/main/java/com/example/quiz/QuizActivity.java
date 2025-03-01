package com.example.quiz;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import  com.example.quiz.sqlite.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        new Thread(() -> {
            Question question = new Question();
            question.content = "What is the capital of France?";
            question.options = "[\"Paris\", \"London\", \"Berlin\", \"Madrid\"]";
            question.answer = "London";
//            db.questionDao().insert(question);
        }).start();

        DatabaseManager databaseManager = new DatabaseManager(this);
        databaseManager.open();
        // Thêm dữ liệu
        databaseManager.addData("John", 30);
        databaseManager.addData("Alice", 25);
        // Lấy tất cả dữ liệu và hiển thị trong log
        List<Person> dataList = databaseManager.getAllData();
        for (Person person : dataList) {
            Log.d("Person", "ID: " + person.id + ", Name: " + person.name + ", Age: " + person.age);
            Log.d("Person", "ID: " + person.id + ", Name: " + person.name + ", Age: " + person.age);
        }
        databaseManager.close();

        try {
            InputStream is = getAssets().open("questions.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            Log.d("debug", json);
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }

}
