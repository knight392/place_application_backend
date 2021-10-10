package com.place_application.demo.service;

import com.place_application.demo.pojo.AplRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AplRecordService {
    /**
     * 获取 申请表 的申请记录
     * @param apl_no
     * @return
     */
    public List<AplRecord> getAplRecords(int apl_no);
}
