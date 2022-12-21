package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<CardDataModel> cards;

    public CardAdapter(Context context, List<CardDataModel> cards) {
        this.cards = cards;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.card_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(CardAdapter.ViewHolder holder, int position) {
        CardDataModel cardDataModel = cards.get(position);
        Locale locale = new Locale("ru");
        Locale.setDefault(locale);
        //ANNYS ANNUS АННУС
        //holder.photoView.setImageResource(cardDataModel.getPhoto());
        holder.caseView.setText(cardDataModel.getName_criminal_case());
        holder.fioView.setText(cardDataModel.getFio());
        holder.ageView.setText("Возраст: " + cardDataModel.getAge().toString());
        holder.heightView.setText("Рост: " + cardDataModel.getHeight().toString());
        holder.weightView.setText("Вес: " + cardDataModel.getWeight().toString());
        holder.welcomeView.setText("Прибыл " + String.format(locale, "%tD\n", cardDataModel.getWelcome()));
        holder.byeView.setText("Уедет " + String.format(locale, "%tD\n", cardDataModel.getBye()));
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //final ImageView photoView;
        final TextView ageView, fioView, heightView, weightView, caseView, welcomeView, byeView;
        ViewHolder(View view){
            super(view);
            //ANNYS ANNUS АННУС
            //photoView = view.findViewById(R.id.prisoner_img);
            caseView = view.findViewById(R.id.case_txt);
            fioView = view.findViewById(R.id.fio_txt);
            ageView = view.findViewById(R.id.age_txt);
            heightView = view.findViewById(R.id.height_txt);
            weightView = view.findViewById(R.id.weight_txt);
            welcomeView = view.findViewById(R.id.welcome_txt);
            byeView = view.findViewById(R.id.bye_txt);
        }
    }
}
