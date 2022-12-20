package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<CardDataModel> cards;

    public CardAdapter(Context context, List<CardDataModel> states) {
        this.cards = states;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardAdapter.ViewHolder holder, int position) {
        CardDataModel cardDataModel = cards.get(position);
        //holder.photoView.setImageResource(cardDataModel.getPhoto());
        holder.idView.setText(cardDataModel.getId());
        holder.fioView.setText(cardDataModel.getFio());
        holder.ageView.setText(cardDataModel.getAge().toString());
        holder.heightView.setText(cardDataModel.getHeight().toString());
        holder.weightView.setText(cardDataModel.getWeight().toString());
        holder.welcomeView.setText(cardDataModel.getWelcome().toString());
        holder.byeView.setText(cardDataModel.getBye().toString());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //final ImageView photoView;
        final TextView ageView, fioView, heightView, weightView, idView, welcomeView, byeView;
        ViewHolder(View view){
            super(view);
            //photoView = view.findViewById(R.id.prisoner_img);
            idView = view.findViewById(R.id.id_txt);
            fioView = view.findViewById(R.id.fio_txt);
            ageView = view.findViewById(R.id.age_txt);
            heightView = view.findViewById(R.id.height_txt);
            weightView = view.findViewById(R.id.weight_txt);
            welcomeView = view.findViewById(R.id.welcome_txt);
            byeView = view.findViewById(R.id.bye_txt);
        }
    }
}
