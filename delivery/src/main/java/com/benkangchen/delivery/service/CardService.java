package com.benkangchen.delivery.service;


import com.benkangchen.delivery.model.Card;

import java.util.Map;

public interface CardService {
    Map findCardByCardNum(Integer cardNum, String passwd);
    Card findCardByCardNum(Integer cardNum);
    Boolean matchCard(Integer cardNum, String passwd);
    int updateCardByCardNum(Integer cardNum);
}
