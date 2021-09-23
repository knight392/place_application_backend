package com.place_application.demo.service.impl;

import com.place_application.demo.dao.StudentDao;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.place_application.demo.pojo.Student;
import com.place_application.demo.service.StudentService;
import com.place_application.demo.utils.HttpUtil;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Override
    public Student loginStudent(Student student) throws Exception{
        /**
         * 访问第三方数据
         */
        System.out.println(student.getS_password());
        String url ="http://jwxt.gduf.edu.cn/app.do";
        String params = "method=authUser&xh="+student.getS_no()+"&pwd="+student.getS_password();

        // 是否需要异步回调？
        JSONObject stuMap = HttpUtil.doGetStr(url + "?" + params);
        System.out.println(stuMap.get("userrealname"));
        // 返回的结果
        // 登录成功
        if (stuMap != null && stuMap.get("flag").equals("1")) {

            // 构造学生返回对象
            Student newStu = new Student();
            newStu.setS_no(student.getS_no());
            newStu.setS_name(stuMap.get("userrealname").toString());
            newStu.setS_dwmc(stuMap.get("userdwmc").toString());


            // 判断该学生是否第一次登录
            Student stu = this.studentDao.selectStudentByNo(student.getS_no());
            if (stu == null) {
                // 第一次登录，加到自己的数据库中
                newStu.setS_password(student.getS_password());
                this.registerStudent(newStu);
                newStu.setS_password("");
            }else{
                newStu.setS_avatar(stu.getS_avatar());
                newStu.setS_phone(stu.getS_phone());
            }
            return newStu;
        }
        // 登录失败返回Null
        return null;
    }

    @Override
    public boolean registerStudent(Student student) {
        int res = this.studentDao.insertStudent(student);
        return res > 0;
    }




    public void test() throws Exception {
        Student student = new Student();
        student.setS_no("191543214");
        student.setS_password("yya063514");
        this.loginStudent(student);
    }

    @Override
    public Student selectStudent(String s_no) {
        return this.studentDao.selectStudentByNo(s_no);
    }

    @Override
    public boolean updateStudent(Student student) {
        return this.studentDao.updateStudent(student) > 0;
    }
}
