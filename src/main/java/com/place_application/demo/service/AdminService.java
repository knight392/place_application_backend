package com.place_application.demo.service;

import com.place_application.demo.pojo.Admin;
import org.springframework.stereotype.Service;

/**
 * 管理员服务
 */
@Service
public interface AdminService {
    /**
     * 注册管理员
     */
    public boolean registerAdmin(Admin admin) throws Exception;

    /**
     *
     */

    /**
     * 删除管理员
     */
    public boolean deleteAdmin(String admin_no) throws Exception;

    /**
     * 管理员登录
     */
    public Admin loginAdmin(Admin admin) throws Exception;

    /**
     * 修改管理员信息
     */
    public Admin updateAdmin(Admin admin) throws Exception;

    /**
     * 判断账号是否重复
     */
    public boolean isAdminDuplicate(String admin_no);
}
