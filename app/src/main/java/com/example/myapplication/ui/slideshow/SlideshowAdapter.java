package com.example.myapplication.ui.slideshow;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import java.util.List;

public class SlideshowAdapter extends RecyclerView.Adapter<SlideshowAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<SlideDataModel> names;

    public SlideshowAdapter(Context context, List<SlideDataModel> names) {
        this.names = names;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public SlideshowAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.check_item, parent, false);
        return new SlideshowAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(SlideshowAdapter.ViewHolder holder, int position) {
        SlideDataModel slide = names.get(position);
        holder.fioView.setText(slide.getFio());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView fioView;
        ViewHolder(View view){
            super(view);
            fioView = view.findViewById(R.id.fiotextView);
        }
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

}
