package com.benkangchen.delivery.dao;

import com.benkangchen.delivery.model.ReceiptInformation;
import com.benkangchen.delivery.model.ReceiptInformationExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptInformationMapper {
    int countByExample(ReceiptInformationExample example);

    int deleteByExample(ReceiptInformationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReceiptInformation record);

    int insertSelective(ReceiptInformation record);

    List<ReceiptInformation> selectByExample(ReceiptInformationExample example);

    ReceiptInformation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReceiptInformation record, @Param("example") ReceiptInformationExample example);

    int updateByExample(@Param("record") ReceiptInformation record, @Param("example") ReceiptInformationExample example);

    int updateByPrimaryKeySelective(ReceiptInformation record);

    int updateByPrimaryKey(ReceiptInformation record);
}