package com.example.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Budget {

    private int income;
    private int food;
    private int rent;
    private int savings;
    private List<Integer> savedBudget;

    public Budget() {
        this.savedBudget = new ArrayList<>();
    }

    public void addIncome(int income){
        savedBudget.add(income);
    }
    public void setIncome(int income) {
        this.income = income;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public void setSavings(int savings) {
        this.savings = savings;
    }

    public int getIncome() {
        return income;
    }

    public int getFood() {
        return food;
    }

    public int getRent() {
        return rent;
    }

    public int getSavings() {
        return savings;
    }

}
