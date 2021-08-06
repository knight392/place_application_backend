package com.place_application.demo.pojo;

/**
 * 场地
 */
public class Place {
    private Integer place_no; // 场地编号
    private String place_name; // 场地名称
    private String place_info; // 场地使用说明
    private AplProcedure aplProcedure; // 所属流程
    private Image image; // 图片路径

    public AplProcedure getAplProcedure() {
        return aplProcedure;
    }

    public void setAplProcedure(AplProcedure aplProcedure) {
        this.aplProcedure = aplProcedure;
    }

    public String getPlace_info() {
        return place_info;
    }

    public void setPlace_info(String place_info) {
        this.place_info = place_info;
    }



    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Integer getPlace_no() {
        return place_no;
    }

    public void setPlace_no(Integer place_no) {
        this.place_no = place_no;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    @Override
    public String toString(){
        return "Place [ place_no=" + place_no + ", pro_name=" + place_name + ", place_info=" + place_info + ", aplProcedure=" + aplProcedure + "]";
     }
}
