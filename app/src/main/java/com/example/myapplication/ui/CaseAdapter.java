package com.example.myapplication.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class CaseAdapter extends RecyclerView.Adapter<CaseAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<CaseDataModel> cases;

    public CaseAdapter(Context context, List<CaseDataModel> names) {
        this.cases = names;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public CaseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.case_item, parent, false);
        return new CaseAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(CaseAdapter.ViewHolder holder, int position) {
        CaseDataModel ccase = cases.get(position);
        holder.nameView.setText(ccase.getName());
        holder.descView.setText(ccase.getDesc());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView, descView;
        ViewHolder(View view){
            super(view);
            nameView = view.findViewById(R.id.nameView);
            descView = view.findViewById(R.id.descView);
        }
    }

    @Override
    public int getItemCount() {
        return cases.size();
    }

}
