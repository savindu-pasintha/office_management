package com.braavocreations.office_management.domain;

public class Employee {
    private Integer emp_id;
    private String emp_firstName;
    private String emp_lastName;
    private String emp_email;
    private String emp_password;
    private String emp_address;
    private long emp_birthday;
    private String emp_designation;
    private Integer emp_dep_id;

    public Employee(Integer emp_id, String emp_firstName, String emp_lastName, String emp_email, String emp_password, String emp_address, long emp_birthday, String emp_designation, Integer emp_dep_id) {
        this.emp_id = emp_id;
        this.emp_firstName = emp_firstName;
        this.emp_lastName = emp_lastName;
        this.emp_email = emp_email;
        this.emp_password = emp_password;
        this.emp_address = emp_address;
        this.emp_birthday = emp_birthday;
        this.emp_designation = emp_designation;
        this.emp_dep_id = emp_dep_id;
    }

    public Employee() {
    }

    public Integer getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Integer emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_firstName() {
        return emp_firstName;
    }

    public void setEmp_firstName(String emp_firstName) {
        this.emp_firstName = emp_firstName;
    }

    public String getEmp_lastName() {
        return emp_lastName;
    }

    public void setEmp_lastName(String emp_lastName) {
        this.emp_lastName = emp_lastName;
    }

    public String getEmp_email() {
        return emp_email;
    }

    public void setEmp_email(String emp_email) {
        this.emp_email = emp_email;
    }

    public String getEmp_password() {
        return emp_password;
    }

    public void setEmp_password(String emp_password) {
        this.emp_password = emp_password;
    }

    public String getEmp_address() {
        return emp_address;
    }

    public void setEmp_address(String emp_address) {
        this.emp_address = emp_address;
    }

    public long getEmp_birthday() {
        return emp_birthday;
    }

    public void setEmp_birthday(long emp_birthday) {
        this.emp_birthday = emp_birthday;
    }

    public String getEmp_designation() {
        return emp_designation;
    }

    public void setEmp_designation(String emp_designation) {
        this.emp_designation = emp_designation;
    }

    public Integer getEmp_dep_id() {
        return emp_dep_id;
    }

    public void setEmp_dep_id(Integer emp_dep_id) {
        this.emp_dep_id = emp_dep_id;
    }
}
