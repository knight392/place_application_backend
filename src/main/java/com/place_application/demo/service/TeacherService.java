package com.place_application.demo.service;

import com.place_application.demo.pojo.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 教师服务
 */
@Service
public interface TeacherService {
    /**
     * 教师注册
     */
    public Teacher registerTeacher(Teacher teacher);

    /**
     * 教师登录
     */
    public Teacher loginTeacher(Teacher teacher);

    /**
     * 使用 token 教师登录
     * @param token
     * @return
     */
    public Teacher loginTeacherWithToken(String token);

    /**
     * 教师修改信息
     */
    public boolean updateTeacher(Teacher teacher);

    /**
     * 删除教师账号
     */
    public boolean deleteTeacher(String teacher_no);

    /**
     * 判断账号是否重复
     */
    public boolean isTeacherDuplicate(String teacher_no);

    /**
     * 获取所有的教师
     */
    public List<Teacher> getAllTeachers();


    /**
     * 获取在流程中的教师
     * @param pro_no
     * @return
     */
    public List<Teacher> getTeachersInProcedure(String pro_no);

    /**
     * 获取在流程中的第 k个教师
     * @param pro_no
     * @param order
     * @return
     */
    public Teacher getTeacherInProcedure(int pro_no, int order);
}
