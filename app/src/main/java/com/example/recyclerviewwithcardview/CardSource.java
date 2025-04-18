package com.example.recyclerviewwithcardview;

public interface CardSource {

    CardData getCardData(int position);
    int size();
}
