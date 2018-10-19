package com.benkangchen.delivery.controller;

import com.benkangchen.delivery.model.Card;
import com.benkangchen.delivery.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.benkangchen.delivery.constant.Constant.NO_CARD;
import static com.benkangchen.delivery.constant.Constant.PASSWD_ERR;

@RestController
@RequestMapping("/")
public class CardController {
    @Autowired
    CardService cardService;

    @RequestMapping(value = "/queryCard", method = RequestMethod.POST)
    public Map<String,Object> findCard(@RequestBody Map<String,Object> reqMap) {
        Map<String, Object> result = new HashMap<>();
        Integer cardNum = (Integer.valueOf((String)reqMap.get("cardNum")));
        String passwd = reqMap.get("password").toString();
        result =  cardService.findCardByCardNum(cardNum, passwd);
        return result;
    }
}
