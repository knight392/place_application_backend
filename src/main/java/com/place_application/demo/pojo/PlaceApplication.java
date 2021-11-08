package com.place_application.demo.pojo;

/**
 * 场地申请表
 */
public class PlaceApplication {

    private Integer apl_no; // 申请表编号
    private AplProcedure aplProcedure; // 所属流程
    private Place place; // 申请的场地
    private String purpose; // 申请目的
    private Student student; // 申请学生
    private int cur_status; // 当前申请状态 1 - 申请中，2 – 通过， 3 – 打回， 4 – 失败， 5 - 中断
    private int cur_step; // 当前在流程中的步骤 起始为1
    private String s_phone; // 联系电话
    private String tutor_name; // 导师姓名
    private String org_name; // 组织名称，申请一定要以组织名义申请
    private String begin_time; // 开始时间
    private String end_time; // 结束时间
    private String apl_date; // 申请日期
    private int max_step; // 最大执行步骤，用来在生成审批任务设置任务类型是 初审 还是 再审
    private int is_copy; // 是否是复制表，复制表用于打回申请表后 task 展示补正前的状态
    private String refuse_reason; // 最新打回或拒绝理由，补正后清空

    public String getApl_date() {
        return apl_date;
    }

    public void setApl_date(String apl_date) {
        this.apl_date = apl_date;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Integer getApl_no() {
        return apl_no;
    }

    public void setApl_no(Integer apl_no) {
        this.apl_no = apl_no;
    }

    public AplProcedure getAplProcedure() {
        return aplProcedure;
    }

    public void setAplProcedure(AplProcedure aplProcedure) {
        this.aplProcedure = aplProcedure;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getCur_status() {
        return cur_status;
    }

    public void setCur_status(int cur_status) {
        this.cur_status = cur_status;
    }

    public int getCur_step() {
        return cur_step;
    }

    public void setCur_step(int cur_step) {
        this.cur_step = cur_step;
    }

    public String getS_phone() {
        return s_phone;
    }

    public void setS_phone(String s_phone) {
        this.s_phone = s_phone;
    }

    public String getTutor_name() {
        return tutor_name;
    }

    public void setTutor_name(String tutor_name) {
        this.tutor_name = tutor_name;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public String getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(String begin_time) {
        this.begin_time = begin_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public int getMax_step() {
        return max_step;
    }

    public void setMax_step(int max_step) {
        this.max_step = max_step;
    }


    public int getIs_copy() {
        return is_copy;
    }

    public void setIs_copy(int is_copy) {
        this.is_copy = is_copy;
    }

    public String getRefuse_reason() {
        return refuse_reason;
    }

    public void setRefuse_reason(String refuse_reason) {
        this.refuse_reason = refuse_reason;
    }

    @Override
    public String toString() {
        return "PlaceApplication{" +
                "apl_no=" + apl_no +
                ", aplProcedure=" + aplProcedure +
                ", place=" + place +
                ", purpose='" + purpose + '\'' +
                ", student=" + student +
                ", cur_status=" + cur_status +
                ", cur_step=" + cur_step +
                ", s_phone='" + s_phone + '\'' +
                ", tutor_name='" + tutor_name + '\'' +
                ", org_name='" + org_name + '\'' +
                ", begin_time='" + begin_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", apl_date='" + apl_date + '\'' +
                ", max_step=" + max_step +
                ", is_copy=" + is_copy +
                ", refuse_reason='" + refuse_reason + '\'' +
                '}';
    }



}
