package com.htdeveloper.dailyexpensenote;

public class Expense {
    private int id;
    private String expenseName;
    private String expenseAmount;
    private String date;
    private String time;
    private int image;

    public int getId() {
        return id;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public String getExpenseAmount() {
        return expenseAmount;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getImage() {
        return image;
    }

    public Expense(String expenseName, String expenseAmount, String date) {
       // this.id = id;
        this.expenseName = expenseName;
        this.expenseAmount = expenseAmount;
        this.date = date;
       // this.time = time;
       // this.image = image;
    }
}
