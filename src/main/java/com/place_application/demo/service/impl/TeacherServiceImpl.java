package com.place_application.demo.service.impl;

import com.place_application.demo.dao.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import com.place_application.demo.pojo.Teacher;
import com.place_application.demo.service.TeacherService;

import java.util.List;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherDao teacherDao;

    @Override
    public Teacher registerTeacher(Teacher teacher) {
        int res = this.teacherDao.insertTeacher(teacher);
        if(res > 0) {
            teacher.setTeacher_password("");
            return teacher;
        }
        return null;
    }

    @Override
    public Teacher loginTeacher(Teacher teacher) {
        Teacher teacherTarget = this.teacherDao.selectTeacherByNo(teacher.getTeacher_no());
        if (teacherTarget != null && teacherTarget.getTeacher_password().equals(teacher.getTeacher_password())) {
            return  teacherTarget;
        }
        return null;
    }

    @Override
    public boolean updateTeacher(Teacher teacher) {
        int res = this.teacherDao.updateTeacher(teacher);
        return res > 0;
    }

    @Override
    public boolean deleteTeacher(String teacher_no) {
        int res = this.teacherDao.deleteTeacher(teacher_no);
        return res > 0;
    }

    // 避免脏读
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public boolean isTeacherDuplicate(String teacher_no) {
        Teacher teacher = this.teacherDao.selectTeacherByNo(teacher_no);
        return teacher != null;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        List<Teacher> teacherList = this.teacherDao.getAllTeachers();
        for(Teacher teacher : teacherList) {
            teacher.setTeacher_password("");
        }
        return teacherList;
    }

    @Override
    public List<Teacher> getTeachersInProcedure(String pro_no) {
        List<Teacher> teacherList = this.teacherDao.getTeachersInProcedure(pro_no);
        for(Teacher teacher : teacherList) {
            teacher.setTeacher_password("");
        }
        return teacherList;
    }
}
