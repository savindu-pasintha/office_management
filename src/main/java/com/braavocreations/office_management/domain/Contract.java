package com.braavocreations.office_management.domain;

public class Contract {
    private int cont_id;
    private String cont_name;
    private long cont_date_handover;

    public Contract(int cont_id, String cont_name, long cont_date_handover) {
        this.cont_id = cont_id;
        this.cont_name = cont_name;
        this.cont_date_handover = cont_date_handover;
    }

    public Contract() {
    }

    public int getCont_id() {
        return cont_id;
    }

    public void setCont_id(int cont_id) {
        this.cont_id = cont_id;
    }

    public String getCont_name() {
        return cont_name;
    }

    public void setCont_name(String cont_name) {
        this.cont_name = cont_name;
    }

    public long getCont_date_handover() {
        return cont_date_handover;
    }

    public void setCont_date_handover(long cont_date_handover) {
        this.cont_date_handover = cont_date_handover;
    }
}
