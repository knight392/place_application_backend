package com.place_application.demo.service.impl;

import com.place_application.demo.config.JwtConfig;
import com.place_application.demo.dao.AdminDao;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import com.place_application.demo.pojo.Admin;
import com.place_application.demo.service.AdminService;

import javax.annotation.Resource;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Resource
    private JwtConfig jwtConfig;

    @Override
    public boolean registerAdmin(Admin admin) throws Exception{
        int res = adminDao.insertAdmin(admin);
        return res > 0;
    }

    @Override
    public Admin loginAdminWithToken(String token) throws Exception {

        Claims claims;
        try{
            claims = jwtConfig.getTokenClaim(token);
            if(claims == null || jwtConfig.isTokenExpired(claims.getExpiration())){
                throw new SignatureException(jwtConfig.getHeader() + "失效，请重新登录。");
            }
        }catch (Exception e){
            throw new SignatureException(jwtConfig.getHeader() + "失效，请重新登录。");
        }
        // 验证成功，根据账号返回
        String admin_no = claims.getSubject();
        Admin admin = this.adminDao.selectAdminByNo(admin_no);
        if(admin != null) {
            admin.setAdmin_password("");
        }
        return admin;
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
