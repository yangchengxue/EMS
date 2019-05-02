package com.example.ycx36.ems.recyclerview;

public class muc_data {

    public String getInfoName() {
        return infoName;
    }

    public void setInfoName(String infoName) {
        this.infoName = infoName;
    }

    public String getInfoValue() {
        return infoValue;
    }

    public void setInfoValue(String infoValue) {
        this.infoValue = infoValue;
    }

    public String getInfoStandard() {
        return infoStandard;
    }

    public void setInfoStandard(String infoStandard) {
        this.infoStandard = infoStandard;
    }

    private String infoName;
    private String infoValue;
    private String infoStandard;

    public muc_data(String infoName,String infoValue,String infoStandard){
        this.infoName = infoName;
        this.infoValue = infoValue;
        this.infoStandard = infoStandard;
    }

}
