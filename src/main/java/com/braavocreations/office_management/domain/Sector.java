package com.braavocreations.office_management.domain;

public class Sector {
    private Integer sector_id;
    private String sector_name;

    public Sector(Integer sector_id, String sector_name) {
        this.sector_id = sector_id;
        this.sector_name = sector_name;
    }

    public Sector() {
    }

    public Integer getSector_id() {
        return sector_id;
    }

    public void setSector_id(Integer sector_id) {
        this.sector_id = sector_id;
    }

    public String getSector_name() {
        return sector_name;
    }

    public void setSector_name(String sector_name) {
        this.sector_name = sector_name;
    }
}
