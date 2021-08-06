package com.place_application.demo.daoTest;




import com.place_application.demo.dao.ImageDao;
import com.place_application.demo.pojo.Image;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 管理员测试
 */
class ImageDaoTest {
    /**
     * 添加管理员测试
     */
    @Autowired
    ImageDao imageDao;
    @Test
    public void insertAdminTest() {
        Image image = new Image();
        image.setPath("/images/student/swiper/bg1007.jpg");
        image.setType("stu_swiper");
        int a =  imageDao.insertImage(image);

        System.out.println(a);
    }


}
