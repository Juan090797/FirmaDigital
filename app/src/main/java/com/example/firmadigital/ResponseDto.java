package com.example.firmadigital;

public class ResponseDto {

    private String data;
    private String namefile;

    @Override
    public String toString() {
        return "RequestDto{" + "data=" + data + ", namefile=" + namefile + '}';
    }



    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNamefile() {
        return namefile;
    }

    public void setNamefile(String namefile) {
        this.namefile = namefile;
    }


}
