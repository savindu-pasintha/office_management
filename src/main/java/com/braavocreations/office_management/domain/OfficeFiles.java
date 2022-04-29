package com.braavocreations.office_management.domain;

public class OfficeFiles {
    private Integer file_id;
    private String file_name;
    private String file_title;
    private String file_sender;
    private long file_created_date;

    public OfficeFiles(Integer file_id, String file_name, String file_title, String file_sender, long file_created_date) {
        this.file_id = file_id;
        this.file_name = file_name;
        this.file_title = file_title;
        this.file_sender = file_sender;
        this.file_created_date = file_created_date;
    }

    public OfficeFiles() {
    }

    public Integer getFile_id() {
        return file_id;
    }

    public void setFile_id(Integer file_id) {
        this.file_id = file_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_title() {
        return file_title;
    }

    public void setFile_title(String file_title) {
        this.file_title = file_title;
    }

    public String getFile_sender() {
        return file_sender;
    }

    public void setFile_sender(String file_sender) {
        this.file_sender = file_sender;
    }

    public long getFile_created_date() {
        return file_created_date;
    }

    public void setFile_created_date(long file_created_date) {
        this.file_created_date = file_created_date;
    }
}
