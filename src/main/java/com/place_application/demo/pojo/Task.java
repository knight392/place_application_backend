package com.place_application.demo.pojo;
/**
 * 任务审批
 */
public class Task {
    private Integer task_no; //任务编号
    private Integer is_finished; //完成状态
    // 0 待完成
    //1 已通过
    //2 已打回
    //3 已拒绝
    //4 已中断
    private PlaceApplication placeApplication; //申请表
    private String teacher_no; //教师编号
    private String generate_time; //产生时间
    private Integer is_reSentence; // 是否 再审 0 否 1 是，教师两次以上审批同一个申请表
    private String refuse_reason; // 打回或拒绝理由
    private String finished_time; // 完成时间




    public Integer getIs_reSentence() {
        return is_reSentence;
    }

    public void setIs_reSentence(Integer is_reSentence) {
        this.is_reSentence = is_reSentence;
    }






    public PlaceApplication getPlaceApplication() {
        return placeApplication;
    }

    public void setPlaceApplication(PlaceApplication placeApplication) {
        this.placeApplication = placeApplication;
    }



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


    public String getFinished_time() {
        return finished_time;
    }

    public void setFinished_time(String finished_time) {
        this.finished_time = finished_time;
    }

    public String getRefuse_reason() {
        return refuse_reason;
    }

    public void setRefuse_reason(String refuse_reason) {
        this.refuse_reason = refuse_reason;
    }

    @Override
    public String toString() {
        return "Task{" +
                "task_no=" + task_no +
                ", is_finished=" + is_finished +
                ", placeApplication=" + placeApplication +
                ", teacher_no='" + teacher_no + '\'' +
                ", generate_time='" + generate_time + '\'' +
                ", is_reSentence=" + is_reSentence +
                ", refuse_reason='" + refuse_reason + '\'' +
                ", finished_time='" + finished_time + '\'' +
                '}';
    }
}
