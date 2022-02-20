package com.example.androidproject.Entity;

public class Room {
    private int room_id;
    private int price;
    private int room_number;
    private int room_floor;
    private int room_tel;
    private int booked_condition;
    private int TV_number;
    private int beds_number;
    private int person_number;
    private String img;
    private int balcony_number;



    public Room(int room_id, int price, int room_number, int room_floor, int room_tel, int booked_condition, int TV_number, int beds_number, int person_number, String img, int balcony_number) {
        this.room_id = room_id;
        this.price = price;
        this.room_number = room_number;
        this.room_floor = room_floor;
        this.room_tel = room_tel;
        this.booked_condition = booked_condition;
        this.TV_number = TV_number;
        this.beds_number = beds_number;
        this.person_number = person_number;
        this.img = img;
        this.balcony_number = balcony_number;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRoom_number() {
        return room_number;
    }

    public void setRoom_number(int room_number) {
        this.room_number = room_number;
    }

    public int getRoom_floor() {
        return room_floor;
    }

    public void setRoom_floor(int room_floor) {
        this.room_floor = room_floor;
    }

    public int getRoom_tel() {
        return room_tel;
    }

    public void setRoom_tel(int room_tel) {
        this.room_tel = room_tel;
    }

    public int getBooked_condition() {
        return booked_condition;
    }

    public void setBooked_condition(int booked_condition) {
        this.booked_condition = booked_condition;
    }

    public int getTV_number() {
        return TV_number;
    }

    public void setTV_number(int TV_number) {
        this.TV_number = TV_number;
    }

    public int getBeds_number() {
        return beds_number;
    }

    public void setBeds_number(int beds_number) {
        this.beds_number = beds_number;
    }

    public int getPerson_number() {
        return person_number;
    }

    public void setPerson_number(int person_number) {
        this.person_number = person_number;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getBalcony_number() {
        return balcony_number;
    }

    public void setBalcony_number(int balcony_number) {
        this.balcony_number = balcony_number;
    }
}

