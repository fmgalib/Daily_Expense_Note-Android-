package com.htdeveloper.dailyexpensenote;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UpdateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private ImageView backIv, documentIv, coverImage;
    private EditText addExpenseEt, dateEt, timeEt;
    private Button addDocumentBtn, saveBtnUpper, gotoGalleryBtn, gotoCameraBtn, saveBtn, cancelDocumentBtn, crossBtn;
    private Spinner spinner;
    private LinearLayout buttonLyt;
    private String expenseName, expenseAmount, date, time;
    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        init();
        spinnerClicked();

        dateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker();
            }
        });

        timeEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimePicker();
            }
        });
    }

    private void openTimePicker() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);

        View view = getLayoutInflater().inflate(R.layout.custom_time_picker,null);

        Button doneBtn = view.findViewById(R.id.doneBtn);
        final TimePicker timePicker = view.findViewById(R.id.timePicker);

        builder.setView(view);

        final Dialog dialog = builder.create();
        dialog.show();

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss aa");

                @SuppressLint({"NewApi", "LocalSuppress"}) int hour = timePicker.getHour();
                @SuppressLint({"NewApi", "LocalSuppress"}) int min = timePicker.getMinute();

                Time time = new Time(hour,min,0);

                timeEt.setText(timeFormat.format(time));
                dialog.dismiss();


            }
        });
    }

    private void openDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener =
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        month = month + 1;

                        String currentDate = year+"/"+month+"/"+day;

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

                        Date date = null;

                        try {
                            date = dateFormat.parse(currentDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        dateEt.setText(dateFormat.format(date));
                        long millisec = date.getTime();
                    }
                };

        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateActivity.this, dateSetListener, year, month, day);
        datePickerDialog.show();
    }

    private void spinnerClicked() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(UpdateActivity.this,R.array.expenses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    private void init() {

        backIv = findViewById(R.id.backIv);
        documentIv = findViewById(R.id.documentIv);
        coverImage = findViewById(R.id.coverImage);
        addExpenseEt = findViewById(R.id.addExpenseEt);
        dateEt = findViewById(R.id.dateEt);
        timeEt = findViewById(R.id.timeEt);
        addDocumentBtn = findViewById(R.id.addDocumentBtn);
        saveBtnUpper = findViewById(R.id.saveBtnUpper);
        gotoGalleryBtn = findViewById(R.id.gotoGalleryBtn);
        gotoCameraBtn = findViewById(R.id.gotoCameraBtn);
        saveBtn = findViewById(R.id.saveBtn);
        crossBtn = findViewById(R.id.crossBtn);
        spinner = findViewById(R.id.spinner);
        buttonLyt = findViewById(R.id.buttonLyt);
        cancelDocumentBtn = findViewById(R.id.cancelDocumentBtn);
        helper = new DatabaseHelper(this);
    }

    public void backIv(View view) {
        onBackPressed();
    }

    @Override
    public void onItemSelected(final AdapterView<?> adapterView, View view, final int i, long l) {

        saveBtnUpper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expenseName = adapterView.getItemAtPosition(i).toString();
                expenseAmount = addExpenseEt.getText().toString();
                date = dateEt.getText().toString();
                //  time = timeEt.getText().toString();

                long id = helper.insertData(expenseName,expenseAmount,date);
                Toast.makeText(UpdateActivity.this, "Expense ID "+id, Toast.LENGTH_LONG).show();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expenseName = adapterView.getItemAtPosition(i).toString();
                expenseAmount = addExpenseEt.getText().toString();
                date = dateEt.getText().toString();
                //  time = timeEt.getText().toString();

                long id = helper.insertData(expenseName,expenseAmount,date);
                Toast.makeText(UpdateActivity.this, "Expense ID "+id, Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
