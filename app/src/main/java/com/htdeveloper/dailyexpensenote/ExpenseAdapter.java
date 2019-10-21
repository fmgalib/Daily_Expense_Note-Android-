package com.htdeveloper.dailyexpensenote;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder>{

    private List<Expense> expenses;
    private Context context;

    public ExpenseAdapter(Context context) {
        this.context = context;
    }

    public ExpenseAdapter(List<Expense> expenses) {
        this.expenses = expenses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_model_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        Expense expense = expenses.get(position);

        holder.nameTv.setText(expense.getExpenseName());
        holder.amountTv.setText(expense.getExpenseAmount());
        holder.dateTv.setText(String.valueOf(expense.getDate()));


    }


    @Override
    public int getItemCount() {
        return expenses.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {


        private TextView nameTv, dateTv, amountTv;
        private ImageView menuIv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTv = itemView.findViewById(R.id.nameTv);
            dateTv = itemView.findViewById(R.id.dateTv);
            amountTv = itemView.findViewById(R.id.amountTv);
            menuIv = itemView.findViewById(R.id.menuIv);

        }
    }
}
