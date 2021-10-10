package com.place_application.demo.service.impl;

import com.place_application.demo.dao.AplRecordDao;
import com.place_application.demo.pojo.AplRecord;
import com.place_application.demo.service.AplRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AplRecordServiceImpl implements AplRecordService {
    @Autowired
    private AplRecordDao aplRecordDao;
    @Override
    public List<AplRecord> getAplRecords(int apl_no) {
       return this.aplRecordDao.selectAplRecords(apl_no);
    }


}
