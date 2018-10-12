package com.togogotrain.moumoubicycle.mappers;

import com.togogotrain.moumoubicycle.entity.Journey;
import com.togogotrain.moumoubicycle.entity.JourneyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JourneyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table journey
     *
     * @mbg.generated
     */
    long countByExample(JourneyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table journey
     *
     * @mbg.generated
     */
    int deleteByExample(JourneyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table journey
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table journey
     *
     * @mbg.generated
     */
    int insert(Journey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table journey
     *
     * @mbg.generated
     */
    int insertSelective(Journey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table journey
     *
     * @mbg.generated
     */
    List<Journey> selectByExample(JourneyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table journey
     *
     * @mbg.generated
     */
    Journey selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table journey
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Journey record, @Param("example") JourneyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table journey
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Journey record, @Param("example") JourneyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table journey
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Journey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table journey
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Journey record);
}