package com.htdeveloper.dailyexpensenote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class AddExpenseActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ImageView backIv, documentIv;
    private TextView cancelTv;
    private EditText addExpenseEt, dateEt, timeEt;
    private Button addDocumentBtn, saveBtnUpper, gotoGalleryBtn, gotoCameraBtn, saveBtn, cancelDocumentBtn;
    private Spinner spinner;
    private LinearLayout buttonLyt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        init();
        spinnerClicked();

    }

    private void spinnerClicked() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(AddExpenseActivity.this,R.array.expenses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    private void init() {

        backIv = findViewById(R.id.backIv);
        documentIv = findViewById(R.id.documentIv);
        addExpenseEt = findViewById(R.id.addExpenseEt);
        timeEt = findViewById(R.id.timeEt);
        addDocumentBtn = findViewById(R.id.addDocumentBtn);
        saveBtnUpper = findViewById(R.id.saveBtnUpper);
        gotoGalleryBtn = findViewById(R.id.gotoGalleryBtn);
        gotoCameraBtn = findViewById(R.id.gotoCameraBtn);
        saveBtn = findViewById(R.id.saveBtn);
        spinner = findViewById(R.id.spinner);
        buttonLyt = findViewById(R.id.buttonLyt);
        cancelDocumentBtn = findViewById(R.id.cancelDocumentBtn);
    }

    public void backIv(View view) {
        onBackPressed();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void addDocumentBtn(View view) {
        documentIv.setVisibility(View.VISIBLE);
        buttonLyt.setVisibility(View.VISIBLE);
        saveBtn.setVisibility(View.VISIBLE);
        saveBtnUpper.setVisibility(View.INVISIBLE);
        cancelDocumentBtn.setVisibility(View.VISIBLE);
        addDocumentBtn.setVisibility(View.INVISIBLE);
    }

    public void cancelDocumentBtn(View view) {
        documentIv.setVisibility(View.INVISIBLE);
        buttonLyt.setVisibility(View.INVISIBLE);
        saveBtn.setVisibility(View.INVISIBLE);
        saveBtnUpper.setVisibility(View.VISIBLE);
        cancelDocumentBtn.setVisibility(View.INVISIBLE);
        addDocumentBtn.setVisibility(View.VISIBLE);
    }


}
