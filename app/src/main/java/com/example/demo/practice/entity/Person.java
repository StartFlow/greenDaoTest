package com.example.demo.practice.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity
public class Person {
    public static final int Sexy_male  = 0;
    public static final int Sexy_female = 1;

    @Id(autoincrement = true)
    long id;

    @Property
    String name;

    @Property
    int height;

    @Property
    int age;

    @Property
    int Sexy;     // 0 male  1 female

    @Generated(hash = 1634074908)
    public Person(long id, String name, int height, int age, int Sexy) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.age = age;
        this.Sexy = Sexy;
    }

    @Generated(hash = 1024547259)
    public Person() {
    }

    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSexy() {
        return this.Sexy;
    }

    public void setSexy(int Sexy) {
        this.Sexy = Sexy;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return      "id = " + id
                +",名字 = " + name
                +",身高 = "+ height
                +",年龄 = " + age
                +",性别 ="+getSexy(Sexy);
    }

    private String getSexy(int sexy){
        String str = "";
        switch (sexy){
            case Person.Sexy_female:
                str = "女";
                break;
            case Person.Sexy_male:
                str = "男";
                break;
                default:
        }
        return str;
    }
}
