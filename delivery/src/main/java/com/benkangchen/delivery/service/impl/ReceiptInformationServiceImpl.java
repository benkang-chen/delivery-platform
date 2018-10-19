package com.benkangchen.delivery.service.impl;

import com.benkangchen.delivery.dao.ReceiptInformationMapper;
import com.benkangchen.delivery.model.Card;
import com.benkangchen.delivery.model.ReceiptInformation;
import com.benkangchen.delivery.model.ReceiptInformationExample;
import com.benkangchen.delivery.service.ReceiptInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.benkangchen.delivery.constant.Constant.*;

@Service
public class ReceiptInformationServiceImpl implements ReceiptInformationService{

    @Autowired
    private ReceiptInformationMapper receiptInformationMapper;
    @Autowired
    private CardServiceImpl cardService;

    @Override
    public int insert(ReceiptInformation record) {
        int code;
        try {
            Integer cardNum = record.getCardNum();
            Card card = cardService.findCardByCardNum(cardNum);
            if (null == card) {
                code = NO_CARD;
            } else {
                int state = card.getState();
                if (state == 1) {
                    code = CARD_USEING;
                } else {
                    code = receiptInformationMapper.insert(record);
                    if (code == 1) {
                        cardService.updateCardByCardNum(cardNum);
                    }
                }
            }
        } catch (Exception e) {
            code = CATCH_ERR;
            e.printStackTrace();
        }
        return code;
    }

    @Override
    public ReceiptInformation findByCardNum(Integer cardNum) {
        List<ReceiptInformation> receiptInformations;
        try {
            ReceiptInformationExample receiptInformationExample = new ReceiptInformationExample();
            receiptInformationExample.createCriteria().andCardNumEqualTo(cardNum);
            receiptInformations = receiptInformationMapper.selectByExample(receiptInformationExample);
            if (receiptInformations.size() == 0) {
                return null;
            } else {
                return receiptInformations.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int updateByCardNum(Map<String,Object> param) {
        Integer cardNum = (Integer) param.get("cardNum");
        String name = param.get("name").toString();
        String phone = param.get("phone").toString();
        String address = param.get("address").toString();
        ReceiptInformation receiptInformation = findByCardNum(cardNum);
        if (null != receiptInformation) {
            Byte state = receiptInformation.getState();
            if (state.intValue() == 1) {
                receiptInformation.setName(name);
                receiptInformation.setPhone(phone);
                receiptInformation.setAddress(address);
                receiptInformation.setModifyTime(new Date());
                ReceiptInformationExample receiptInformationExample = new ReceiptInformationExample();
                receiptInformationExample.createCriteria().andCardNumEqualTo(cardNum);
                int code = receiptInformationMapper.updateByExampleSelective(receiptInformation, receiptInformationExample);
                return code;
            } else {
                return HAS_SEND;
            }
        } else {
            return NO_CARD;
        }
    }
}
