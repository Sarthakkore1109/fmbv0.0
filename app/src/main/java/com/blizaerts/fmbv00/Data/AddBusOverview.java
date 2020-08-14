package com.blizaerts.fmbv00.Data;

public class AddBusNo {

    private String busNo = "";

    public String getBusNo() {
        return busNo;
    }

    public void setBusNo(String busNo) {
        this.busNo = busNo;
    }

    public AddBusNo() {
    }

    public AddBusNo(String BUSNO){
        this.busNo = BUSNO;
    }
}
