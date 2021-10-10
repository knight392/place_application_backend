package com.place_application.demo.dao;

import com.place_application.demo.pojo.Place;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 场地持久层操作
 */
@Mapper
public interface PlaceDao {
    /**
     * 添加场地
     */
    public int insertPlace(Place place);

    /**
     * 添加带图片场地
     */
    public int insertPlaceWithPicture(Place place);

    /**
     * 删除场地
     */
    public int deletePlace(Integer place_no);

    /**
     * 删除图片
     */
    public int removeImage(Integer place_no);

    /**
     * 修改场地信息
     */
    public int updatePlace(Place place);

    /**
     * 根据场地编号或场地名查询场地，编号优先
     */
    public Place selectPlaceByNoOrName(Place place);

    /**
     * 场地所有场地
     */
    public List<Place> selectAllPlaces();

    /**
     * 根据pro_no 清空 pro_no，批量清除流程中的所属场地
     */
    public int clearPro_noByPro_no(Integer pro_no);

    /**
     * 根据place_no 移出 pro_no
     */
    public int removePro_noByPlace_no(List<Integer> place_nos);

    /**
     * 根据pro_no 找到 place_nos
     */

    public List<Integer> selectPlace_nosByPro_no(Integer pro_no);

    /**
     * 找出没有流程的场地
     */
    public List<Place> selectPlacesWithoutProcedure();

    /**
     * 找出可申请的场地
     */
    public List<Place> selectPlacesAvailable();

    /**
     * 判断场地是否有被添加到流程中
     * @param place_no
     * @return
     */
    public Integer getPro_noByPlace_no(Integer place_no);

    /**
     * 获取场地的可使用性
     * @param place_no
     * @return
     */
    public int getAvailableByPlace_no(Integer place_no);
}
