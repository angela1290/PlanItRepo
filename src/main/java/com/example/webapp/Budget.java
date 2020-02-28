package com.example.webapp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int income;
    @Column (name="food_expenses")
    private int food;
    @Column (name="rent_expenses")
    private int rent;
    private int savings;
//    private List<Integer> savedBudget;

    public Budget() {

    }

//    public void addIncome(int income){
//        this.income ;
//    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
