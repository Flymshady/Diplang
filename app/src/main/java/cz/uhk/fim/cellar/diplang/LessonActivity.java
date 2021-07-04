package cz.uhk.fim.cellar.diplang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import cz.uhk.fim.cellar.diplang.Classes.User;

public class LessonActivity extends AppCompatActivity {

    private String level;
    private int numberOfLesson;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
    }
}