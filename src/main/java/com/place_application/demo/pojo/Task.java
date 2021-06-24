package com.place_application.demo.pojo;
/**
 * 任务审批
 */
public class Task {
    private Integer task_no; //任务编号
    private Integer is_finished; //完成状态
    private Integer apl_no; //申请表编号
    private String teacher_no; //教师编号
    private String generate_time; //产生时间

    public Integer getTask_no() {
        return task_no;
    }

    public void setTask_no(Integer task_no) {
        this.task_no = task_no;
    }

    public Integer getIs_finished() {
        return is_finished;
    }

    public void setIs_finished(Integer is_finished) {
        this.is_finished = is_finished;
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

    public String getGenerate_time() {
        return generate_time;
    }

    public void setGenerate_time(String generate_time) {
        this.generate_time = generate_time;
    }

    @Override
    public String toString() {
        return "Task{" +
                "task_no=" + task_no +
                ", is_finished=" + is_finished +
                ", apl_no=" + apl_no +
                ", teacher_no='" + teacher_no + '\'' +
                ", generate_time='" + generate_time + '\'' +
                '}';
    }
}
