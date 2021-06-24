package com.place_application.demo.pojo;
/**
 * 补正打回
 */
public class ReturnRecord {
    private Integer return_no; //打回记录编号
    private Integer apl_no; //申请表编号
    private String teacher_no; //教师编号
    private String return_time; //打回时间
    private String return_reason; //打回原因

    public Integer getReturn_no() {
        return return_no;
    }

    public void setReturn_no(Integer return_no) {
        this.return_no = return_no;
    }

    public Integer getApl_no() {
        return apl_no;
    }

    public void setApl_no(Integer apl_no) {
        this.apl_no = apl_no;
    }

    public String getTeacher_no() {
        return teacher_no;
    }

    public void setTeacher_no(String teacher_no) {
        this.teacher_no = teacher_no;
    }

    public String getReturn_time() {
        return return_time;
    }

    public void setReturn_time(String return_time) {
        this.return_time = return_time;
    }

    public String getReturn_reason() {
        return return_reason;
    }

    public void setReturn_reason(String return_reason) {
        this.return_reason = return_reason;
    }

    @Override
    public String toString() {
        return "ReturnRecord{" +
                "return_no=" + return_no +
                ", apl_no=" + apl_no +
                ", teacher_no='" + teacher_no + '\'' +
                ", return_time='" + return_time + '\'' +
                ", return_reason='" + return_reason + '\'' +
                '}';
    }
}
