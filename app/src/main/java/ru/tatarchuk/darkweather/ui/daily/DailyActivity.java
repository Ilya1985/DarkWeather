package ru.tatarchuk.darkweather.ui.daily;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ru.tatarchuk.darkweather.R;

public class DailyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.DetailThem);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }
}
