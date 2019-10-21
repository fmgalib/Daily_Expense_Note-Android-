package com.htdeveloper.dailyexpensenote;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddExpenseActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ImageView backIv, documentIv;
    private EditText addExpenseEt, dateEt, timeEt;
    private Button addDocumentBtn, saveBtnUpper, gotoGalleryBtn, gotoCameraBtn, saveBtn, cancelDocumentBtn;
    private Spinner spinner;
    private LinearLayout buttonLyt;
    private String expenseName, expenseAmount, date, time;
    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

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
        AlertDialog.Builder builder = new AlertDialog.Builder(AddExpenseActivity.this);

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


        DatePickerDialog datePickerDialog = new DatePickerDialog(AddExpenseActivity.this, dateSetListener, year, month, day);
        datePickerDialog.show();
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
        dateEt = findViewById(R.id.dateEt);
        timeEt = findViewById(R.id.timeEt);
        addDocumentBtn = findViewById(R.id.addDocumentBtn);
        saveBtnUpper = findViewById(R.id.saveBtnUpper);
        gotoGalleryBtn = findViewById(R.id.gotoGalleryBtn);
        gotoCameraBtn = findViewById(R.id.gotoCameraBtn);
        saveBtn = findViewById(R.id.saveBtn);
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
                 time = timeEt.getText().toString();

                 long id = helper.insertData(expenseName,expenseAmount,date,time);
                 Toast.makeText(AddExpenseActivity.this, "Expense ID "+id, Toast.LENGTH_LONG).show();
            }
        });

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


    public void gotoCameraBtn(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,0);
    }

    public void gotoGalleryBtn(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){

            if(requestCode ==0){

                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
                documentIv.setImageBitmap(bitmap);

            }
            else if(requestCode == 1){

                Uri uri = data.getData();
                documentIv.setImageURI(uri);
            }
        }

    }
}
