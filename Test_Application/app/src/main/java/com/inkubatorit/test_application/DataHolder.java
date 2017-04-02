package com.inkubatorit.test_application;

/**
 * Created by Ali-pc on 4/2/2017.
 */

public class DataHolder {
    private String data[];
    public String getData(int i) {return data[i];}
    public void setData(String data, int i) {this.data[i] = data;}
    public DataHolder(){
        data = new String[3];
    }
    private static final DataHolder holder = new DataHolder();
    public static DataHolder getInstance() {return holder;}
}
