package com.htdeveloper.dailyexpensenote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {

    private Button dashboardBtn, expenseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);



        init();

        color();

    }

    private void color() {
        ;
        dashboardBtn.setTextColor(Color.rgb(99, 10, 116 ));
        expenseBtn.setTextColor(Color.rgb(123, 125, 125 ));
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, new DashboardFragment());
        ft.commit();
    }

    private void init() {

        dashboardBtn = findViewById(R.id.dashboardBtn);
        expenseBtn = findViewById(R.id.expenseBtn);


    }

    public void dashboardBtn(View view) {

        dashboardBtn.setTextColor(Color.rgb(99, 10, 116 ));
        expenseBtn.setTextColor(Color.rgb(123, 125, 125 ));
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, new DashboardFragment());
        ft.commit();

    }

    public void expenseBtn(View view) {

        expenseBtn.setTextColor(Color.rgb(99, 10, 116 ));
        dashboardBtn.setTextColor(Color.rgb(123, 125, 125 ));
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, new ExpenseFragment());
        ft.commit();

    }
}
