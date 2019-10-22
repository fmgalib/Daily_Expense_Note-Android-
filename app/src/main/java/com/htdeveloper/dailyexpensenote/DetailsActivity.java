package com.htdeveloper.dailyexpensenote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private TextView expenseNameTv, expenseAmountTv, expenseDateTv;
    private ImageView backIv;
    String name, amount, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        init();

        getData();

        setData();

    }

    private void setData() {
        expenseNameTv.setText(name);
        expenseAmountTv.setText(amount);
        expenseDateTv.setText(date);
    }

    private void getData() {
        name = getIntent().getStringExtra("name");
        amount = getIntent().getStringExtra("amount");
        date = getIntent().getStringExtra("date");
    }

    private void init() {
        expenseNameTv = findViewById(R.id.expenseNameTv);
        expenseAmountTv = findViewById(R.id.expenseDateTv);
        expenseDateTv = findViewById(R.id.expenseDateTv);
    }

    public void backIv(View view){

        onBackPressed();
    }
}
