package com.togogotrain.moumoubicycle.entity;

import java.util.Date;

public class Locations {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column locations.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column locations.journey
     *
     * @mbg.generated
     */
    private Long journey;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column locations.latitude
     *
     * @mbg.generated
     */
    private Double latitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column locations.longitude
     *
     * @mbg.generated
     */
    private Double longitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column locations.uploadTime
     *
     * @mbg.generated
     */
    private Date uploadtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column locations.id
     *
     * @return the value of locations.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column locations.id
     *
     * @param id the value for locations.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column locations.journey
     *
     * @return the value of locations.journey
     *
     * @mbg.generated
     */
    public Long getJourney() {
        return journey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column locations.journey
     *
     * @param journey the value for locations.journey
     *
     * @mbg.generated
     */
    public void setJourney(Long journey) {
        this.journey = journey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column locations.latitude
     *
     * @return the value of locations.latitude
     *
     * @mbg.generated
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column locations.latitude
     *
     * @param latitude the value for locations.latitude
     *
     * @mbg.generated
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column locations.longitude
     *
     * @return the value of locations.longitude
     *
     * @mbg.generated
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column locations.longitude
     *
     * @param longitude the value for locations.longitude
     *
     * @mbg.generated
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column locations.uploadTime
     *
     * @return the value of locations.uploadTime
     *
     * @mbg.generated
     */
    public Date getUploadtime() {
        return uploadtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column locations.uploadTime
     *
     * @param uploadtime the value for locations.uploadTime
     *
     * @mbg.generated
     */
    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }
}