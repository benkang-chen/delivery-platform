package com.benkangchen.delivery.service;

import com.benkangchen.delivery.model.ReceiptInformation;

import java.util.Map;

public interface ReceiptInformationService {
    int insert(ReceiptInformation record);
    ReceiptInformation findByCardNum(Integer cardNum);
    int updateByCardNum(Map<String,Object> param);
}
