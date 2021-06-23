package com.place_application.demo.service.impl;

import com.place_application.demo.dao.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import com.place_application.demo.pojo.Admin;
import com.place_application.demo.service.AdminService;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public boolean registerAdmin(Admin admin) throws Exception{
        int res = adminDao.insertAdmin(admin);
        return res > 0;
    }

    @Override
    public boolean deleteAdmin(String admin_no) throws Exception{
        int res = adminDao.deleteAdmin(admin_no);
        return res > 0;
    }

    @Override
    public Admin loginAdmin(Admin admin) throws Exception{
        Admin adminTarget = this.adminDao.selectAdminByNo(admin.getAdmin_no());
        if (adminTarget != null && adminTarget.getAdmin_password().equals(admin.getAdmin_password())){
            adminTarget.setAdmin_password("");
            return adminTarget;
        }
        return null;
    }

    @Override
    public Admin updateAdmin(Admin admin) throws Exception{
        int res = this.adminDao.updateAdmin(admin);
        if (res > 0) {
            admin = this.adminDao.selectAdminByNo(admin.getAdmin_no());
            admin.setAdmin_password("");
            return admin;
        }
        return null;
    }

    // 避免脏读
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public boolean isAdminDuplicate(String admin_no) {
        Admin admin = this.adminDao.selectAdminByNo(admin_no);
        return admin != null;
    }
}
