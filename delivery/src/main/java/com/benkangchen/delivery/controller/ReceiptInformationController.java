package com.benkangchen.delivery.controller;

import com.benkangchen.delivery.model.ReceiptInformation;
import com.benkangchen.delivery.service.CardService;
import com.benkangchen.delivery.service.ReceiptInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class ReceiptInformationController {
    @Autowired
    ReceiptInformationService receiptInformationService;
    @Autowired
    CardService cardService;

    @RequestMapping(value = "/insertRF", method = RequestMethod.POST)
    public Map<String,Object> insertReceiptInformation(@RequestBody Map<String,Object> reqMap) {
        Map<String, Object> result = new HashMap<>();
        Integer cardNum = (Integer.valueOf((String)reqMap.get("cardNum")));
        String name = reqMap.get("name").toString();
        String phone = reqMap.get("phone").toString();
        String address = reqMap.get("address").toString();
        ReceiptInformation receiptInformation = new ReceiptInformation();
        receiptInformation.setCardNum(cardNum);
        receiptInformation.setName(name);
        receiptInformation.setPhone(phone);
        receiptInformation.setAddress(address);
        Byte state = new Byte("1");
        receiptInformation.setState(state);
        receiptInformation.setCreateTime(new Date());
        int code = receiptInformationService.insert(receiptInformation);
        result.put("code", code);
        return result;
    }

    @RequestMapping(value = "/updateRF", method = RequestMethod.POST)
    public Map<String,Object> updateReceiptInformation(@RequestBody Map<String,Object> reqMap) {
        Map<String, Object> result = new HashMap<>();
        int code = receiptInformationService.updateByCardNum(reqMap);
        result.put("code", code);
        return result;
    }

    @RequestMapping(value = "/findRF", method = RequestMethod.POST)
    public Map<String,Object> findByCardNum(@RequestBody Map<String,Object> reqMap) {
        Map<String, Object> result = new HashMap<>();
        Integer cardNum = (Integer.valueOf((String)reqMap.get("cardNum")));
        String passwd = reqMap.get("password").toString();
        if (cardService.matchCard(cardNum, passwd)) {
            ReceiptInformation receiptInformation =  receiptInformationService.findByCardNum(cardNum);
            if (null != receiptInformation) {
                result.put("code", 1);
                result.put("cardNum", receiptInformation.getCardNum());
                result.put("name", receiptInformation.getName());
                result.put("phone", receiptInformation.getPhone());
                result.put("address", receiptInformation.getAddress());
            } else {
                result.put("code", 0);
                result.put("state", null);
                result.put("type", null);
            }
        } else {
            result.put("code", 0);
            result.put("state", null);
            result.put("type", null);
        }
        return result;
    }
}
