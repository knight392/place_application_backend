package com.place_application.demo.service.impl;

import com.place_application.demo.config.JwtConfig;
import com.place_application.demo.dao.TeacherDao;
import com.place_application.demo.pojo.Admin;
import com.place_application.demo.pojo.Position;
import com.place_application.demo.pojo.TeacherQueryAssist;
import com.place_application.demo.service.PositionService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import com.place_application.demo.pojo.Teacher;
import com.place_application.demo.service.TeacherService;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private PositionService positionService;
    @Resource
    private JwtConfig jwtConfig;

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
            teacherTarget.setTeacher_password("");
            return  teacherTarget;
        }
        return null;
    }

    @Override
    public Teacher loginTeacherWithToken(String token) {
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
        String teacher_no = claims.getSubject();
        Teacher teacher = this.teacherDao.selectTeacherByNo(teacher_no);
        if(teacher != null) {
            teacher.setTeacher_password("");
        }
        return teacher;
    }

    @Override
    public boolean updateTeacher(Teacher teacher) {
        int res = this.teacherDao.updateTeacher(teacher);
        return res > 0;
    }

    @Override
    public boolean deleteTeacher(String teacher_no) {
        // 判断是否有职位，若有则不可删除
        List<Position> positionList =  this.positionService.findPositionsByTeacher_no(teacher_no);
        if(positionList == null || positionList.size() == 0){
            int res = this.teacherDao.deleteTeacher(teacher_no);
            return res > 0;
        }else{
            // 有职位不可删除
            return false;
        }

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

    @Override
    public Teacher getTeacherInProcedure(int pro_no, int order) {
        TeacherQueryAssist teacherQueryAssist = new TeacherQueryAssist();
        teacherQueryAssist.setOrder(order);
        teacherQueryAssist.setPro_no(pro_no);
        return this.teacherDao.getTeacherInProcedure(teacherQueryAssist);
    }
}
