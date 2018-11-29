package com.paularity.axcd.retrofitdaggerapp.Models;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class Commitment
{
    private Context context;

    @SerializedName("count")
    private int count;

    @SerializedName("next")
    private int next;

    @SerializedName("previous")
    private int previous;

    @SerializedName("results")
    private ArrayList<Result> results;

    @Inject
    public Commitment(Context context)
    {
        this.context = context;
    }

    public Commitment(int count, int next, int previous, ArrayList<Result> results)
    {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public int getNext()
    {
        return next;
    }

    public void setNext(int next)
    {
        this.next = next;
    }

    public int getPrevious()
    {
        return previous;
    }

    public void setPrevious(int previous)
    {
        this.previous = previous;
    }

    public ArrayList<Result> getResults()
    {
        return results;
    }

    public void setResults(ArrayList<Result> results)
    {
        this.results = results;
    }
}
