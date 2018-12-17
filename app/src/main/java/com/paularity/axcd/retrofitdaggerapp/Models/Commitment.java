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
    private String next;

    @SerializedName("previous")
    private String previous;

    @SerializedName("results")
    private ArrayList<Result> results;

    @Inject
    public Commitment(Context context)
    {
        this.context = context;
    }

    public Commitment(int count, String next, String previous, ArrayList<Result> results)
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

    public String getNext()
    {
        return next;
    }

    public void setNext(String next)
    {
        this.next = next;
    }

    public String getPrevious()
    {
        return previous;
    }

    public void setPrevious(String previous)
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
