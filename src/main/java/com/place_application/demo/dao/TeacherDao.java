package com.place_application.demo.dao;

import com.place_application.demo.pojo.Teacher;
import com.place_application.demo.pojo.TeacherQueryAssist;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 教师持久层操作
 */
@Mapper
public interface TeacherDao {
    /**
     * 添加教师
     */
    public int insertTeacher(Teacher teacher);

    /**
     * 根据账号查找教师
     */
    public Teacher selectTeacherByNo(String teacher_no);

    /**
     * 删除教师
     */
    public int deleteTeacher(String teacher_no);

    /**
     * 修改教师
     */
    public int updateTeacher(Teacher teacher);

    /**
     * 获取所有的教师
     */
    public List<Teacher> getAllTeachers();


    /**
     * 获取在某流程中的教师
     */
    public List<Teacher> getTeachersInProcedure(String pro_no);

    /**
     * 获取在某流程中的第 k 个教师
     * @param teacherQueryAssist
     * @return
     */
    public Teacher getTeacherInProcedure(TeacherQueryAssist teacherQueryAssist);

}
