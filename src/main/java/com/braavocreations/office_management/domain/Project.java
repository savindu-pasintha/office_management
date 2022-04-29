package com.braavocreations.office_management.domain;

public class Project {

    private Integer pro_id;
    private String pro_name;
    private String pro_supervisor;
    private long pro_date_added;
    private Integer pro_emp_id;
    private Integer pro_cont_id;
    private String pro_suggestor_name;

    public Project(Integer pro_id, String pro_name, String pro_supervisor, long pro_date_added, Integer pro_emp_id, Integer pro_cont_id, String pro_suggestor_name) {
        this.pro_id = pro_id;
        this.pro_name = pro_name;
        this.pro_supervisor = pro_supervisor;
        this.pro_date_added = pro_date_added;
        this.pro_emp_id = pro_emp_id;
        this.pro_cont_id = pro_cont_id;
        this.pro_suggestor_name = pro_suggestor_name;
    }

    public Project() {
    }

    public Integer getPro_id() {
        return pro_id;
    }

    public void setPro_id(Integer pro_id) {
        this.pro_id = pro_id;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public String getPro_supervisor() {
        return pro_supervisor;
    }

    public void setPro_supervisor(String pro_supervisor) {
        this.pro_supervisor = pro_supervisor;
    }

    public long getPro_date_added() {
        return pro_date_added;
    }

    public void setPro_date_added(long pro_date_added) {
        this.pro_date_added = pro_date_added;
    }

    public Integer getPro_emp_id() {
        return pro_emp_id;
    }

    public void setPro_emp_id(Integer pro_emp_id) {
        this.pro_emp_id = pro_emp_id;
    }

    public Integer getPro_cont_id() {
        return pro_cont_id;
    }

    public void setPro_cont_id(Integer pro_cont_id) {
        this.pro_cont_id = pro_cont_id;
    }

    public String getPro_suggestor_name() {
        return pro_suggestor_name;
    }

    public void setPro_suggestor_name(String pro_suggestor_name) {
        this.pro_suggestor_name = pro_suggestor_name;
    }
}
