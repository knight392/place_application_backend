package com.place_application.demo.pojo;

/**
 * 消息通知
 */
public class InfoMsg {
    private int info_no; //通知编号
    private String info_content; //通知内容
    private String s_no; //学号
    private String info_time; //通知时间
    private Integer is_read; //是否已读 1为已读，0为未读

    public int getInfo_no() {
        return info_no;
    }

    public void setInfo_no(int info_no) {
        this.info_no = info_no;
    }

    public String getInfo_content() {
        return info_content;
    }

    public void setInfo_content(String info_content) {
        this.info_content = info_content;
    }

    public String getS_no() {
        return s_no;
    }

    public void setS_no(String s_no) {
        this.s_no = s_no;
    }

    public String getInfo_time() {
        return info_time;
    }

    public void setInfo_time(String info_time) {
        this.info_time = info_time;
    }

    public Integer getIs_read() {
        return is_read;
    }

    public void setIs_read(Integer is_read) {
        this.is_read = is_read;
    }

    @Override
    public String toString() {
        return "InfoMsg{" +
                "info_no=" + info_no +
                ", info_content='" + info_content + '\'' +
                ", s_no='" + s_no + '\'' +
                ", info_time='" + info_time + '\'' +
                ", is_read=" + is_read +
                '}';
    }
}
