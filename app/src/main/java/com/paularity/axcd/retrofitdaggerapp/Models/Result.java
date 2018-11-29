package com.paularity.axcd.retrofitdaggerapp.Models;

import android.content.Context;

import javax.inject.Inject;

public class Result
{
    private int id;
    private String title;
    private String description;

    private Context context;

    @Inject
    public Result(Context context)
    {
        this.context = context;
    }

    public Result(int id, String title, String description)
    {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
