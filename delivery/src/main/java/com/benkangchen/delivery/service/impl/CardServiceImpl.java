package com.benkangchen.delivery.service.impl;

import com.benkangchen.delivery.dao.CardMapper;
import com.benkangchen.delivery.model.Card;
import com.benkangchen.delivery.model.CardExample;
import com.benkangchen.delivery.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.benkangchen.delivery.constant.Constant.CATCH_ERR;
import static com.benkangchen.delivery.constant.Constant.NO_CARD;
import static com.benkangchen.delivery.constant.Constant.PASSWD_ERR;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardMapper cardMapper;

    @Override
    public Map findCardByCardNum(Integer cardNum, String passwd) {
        Map<String, Object> result = new HashMap<>();
        List<Card> cards;
        try {
            CardExample cardExample = new CardExample();
            cardExample.createCriteria().andCardNumEqualTo(cardNum);
            cards = cardMapper.selectByExample(cardExample);
            if (cards.size() == 0) {
                result.put("code", NO_CARD);
                result.put("state", null);
                result.put("type", null);
                return result;
            } else {
                Card card = cards.get(0);
                if (passwd.equals(card.getCradPasswd())) {
                    result.put("code", 1);
                    result.put("state", card.getState());
                    result.put("type", card.getType());
                } else {
                    result.put("code", PASSWD_ERR);
                    result.put("state", null);
                    result.put("type", null);
                }
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", CATCH_ERR);
            result.put("state", null);
            result.put("type", null);
            return result;
        }
    }

    @Override
    public Card findCardByCardNum(Integer cardNum) {
        List<Card> cards;
        try {
            CardExample cardExample = new CardExample();
            cardExample.createCriteria().andCardNumEqualTo(cardNum);
            cards = cardMapper.selectByExample(cardExample);
            if (cards.size() == 0) {
                return null;
            } else {
                Card card = cards.get(0);
                return card;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean matchCard(Integer cardNum, String passwd) {
        Map<String, Object> result = new HashMap<>();
        result = findCardByCardNum(cardNum, passwd);
        int code = (int) result.get("code");
        if (code == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int updateCardByCardNum(Integer cardNum) {
        int code;
        Card card = new Card();
        card.setState(new Byte("1"));
        CardExample cardExample = new CardExample();
        cardExample.createCriteria().andCardNumEqualTo(cardNum);
        code = cardMapper.updateByExampleSelective(card, cardExample);
        return code;
    }
}
