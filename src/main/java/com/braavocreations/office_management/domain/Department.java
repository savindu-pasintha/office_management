package com.braavocreations.office_management.domain;

public class Department {

    private Integer dept_id;
    private String dept_name;
    private String dept_address;
    private Integer dept_sector_id;

    public Department(Integer dept_id, String dept_name, String dept_address, Integer dept_sector_id) {
        this.dept_id = dept_id;
        this.dept_name = dept_name;
        this.dept_address = dept_address;
        this.dept_sector_id = dept_sector_id;
    }

    public Department() {
    }

    public Integer getDept_id() {
        return dept_id;
    }

    public void setDept_id(Integer dept_id) {
        this.dept_id = dept_id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getDept_address() {
        return dept_address;
    }

    public void setDept_address(String dept_address) {
        this.dept_address = dept_address;
    }

    public Integer getDept_sector_id() {
        return dept_sector_id;
    }

    public void setDept_sector_id(Integer dept_sector_id) {
        this.dept_sector_id = dept_sector_id;
    }
}
