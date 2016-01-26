package com.example.ktds.myapplication.store;

/**
 * Created by KTDS on 2016-01-26.
 */
public class StoreInfo {

    private String name;
    private String grade;
    private String number;
    private String image;

    public StoreInfo(String name,String grade,String number,String image){
        super();
        this.name = name;
        this.grade = grade;
        this.number = number;
        this.image = image;
    }

    public void setName(String name){
        name = this.name;
    }

    public String getName(){
        return name;
    }

    public void setGrade(String grade){
        grade = this.grade;
    }

    public String getGrade(){
        return grade;
    }

    public void setNumber(String number){
        number = this.number;
    }

    public String getNumber(){
        return number;
    }

    public void setImage(String image) { image = this.image; }

    public String getImage() { return image; }
}
