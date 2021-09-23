package com.place_application.demo.dao;

import com.place_application.demo.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * 学生持久层操作
 */
@Mapper
public interface StudentDao {
    /**
     * 查询学生
     */
    public Student selectStudentByNo(String s_no);


    /**
     * 添加学生
     */
    public int insertStudent(Student student);

    /**
     * 修改信息
     */
    public int updateStudent(Student student);
}
