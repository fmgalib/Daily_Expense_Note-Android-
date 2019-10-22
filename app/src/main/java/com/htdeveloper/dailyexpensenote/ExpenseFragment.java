package com.htdeveloper.dailyexpensenote;


import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExpenseFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private TextView fromDateTv, toDateTv;
    private RecyclerView recyclerView;
    private ExpenseAdapter adapter;
    private List<Expense> expenses;
    private DatabaseHelper helper;


    public ExpenseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expense, container, false);

        spinner = view.findViewById(R.id.spinner);
        fromDateTv = view.findViewById(R.id.fromDateTv);
        toDateTv = view.findViewById(R.id.toDateTv);


        recyclerView = view.findViewById(R.id.expenseListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        expenses = new ArrayList<>();
        helper = new DatabaseHelper(getContext());
        adapter = new ExpenseAdapter(expenses);
        recyclerView.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.expenses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        getData();

        fromDateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePickerFrom();
            }
        });

        toDateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePickerTo();
            }
        });


        return view;
    }

    private void openDatePickerTo() {
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

                        fromDateTv.setText(dateFormat.format(date));
                        long millisec = date.getTime();
                    }
                };

        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), dateSetListener, year, month, day);
        datePickerDialog.show();
    }

    private void openDatePickerFrom() {
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

                        fromDateTv.setText(dateFormat.format(date));
                        long millisec = date.getTime();
                    }
                };

        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), dateSetListener, year, month, day);
        datePickerDialog.show();
    }


    private void getData() {
        Cursor cursor = helper.showData();
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(helper.COL_ID));
            String name = cursor.getString(cursor.getColumnIndex(helper.COL_NAME));
            String amount = cursor.getString(cursor.getColumnIndex(helper.COL_AMOUNT));
            String date =  cursor.getString(cursor.getColumnIndex(helper.COL_DATE));


            expenses.add(new Expense(name,amount,date));
            adapter.notifyDataSetChanged();

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        //Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getContext(), "Please Select an Expense", Toast.LENGTH_SHORT).show();

    }
}
