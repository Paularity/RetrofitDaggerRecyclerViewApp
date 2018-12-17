package com.paularity.axcd.retrofitdaggerapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.paularity.axcd.retrofitdaggerapp.Models.Commitment;
import com.paularity.axcd.retrofitdaggerapp.Models.Result;
import com.paularity.axcd.retrofitdaggerapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommitmentRecyclerViewAdapter extends RecyclerView.Adapter<CommitmentRecyclerViewAdapter.MyViewHolder>
{
    private Context context;
    private ArrayList<Result> results;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        @BindView(R.id.txt_id)
        TextView txt_id;

        @BindView(R.id.txt_title)
        TextView txt_title;

        @BindView(R.id.txt_desc)
        TextView txt_desc;

        public View view;

        public MyViewHolder(View view)
        {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }
    }

    public CommitmentRecyclerViewAdapter(Context context, ArrayList<Result> results)
    {
        this.context = context;
        this.results = results;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.result_card_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(layout);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int i)
    {
        holder.txt_id.setText( String.valueOf( results.get(i).getId() ) );
        holder.txt_title.setText( String.valueOf( results.get(i).getTitle() ) );
        holder.txt_desc.setText( String.valueOf( results.get(i).getDescription() ) );

        holder.view.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                Toast.makeText(context, results.get(i).getTitle() + " is clicked.", Toast.LENGTH_SHORT ).show();
                return false;
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return this.results.size();
    }


}
