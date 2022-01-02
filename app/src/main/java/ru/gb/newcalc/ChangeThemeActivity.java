package ru.gb.newcalc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class ChangeThemeActivity extends AppCompatActivity {

    private Button lightMode;
    private Button darkMode;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES); // ночная
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); // дневная
        setContentView(R.layout.activity_main_theme);


        lightMode = findViewById(R.id.light_theme_mode);
        darkMode = findViewById(R.id.dark_theme_mode);


        lightMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTheme(R.style.newCalcDay);
            }
        });
        darkMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTheme(R.style.newCalcNight);
            }
        });
    }
}
