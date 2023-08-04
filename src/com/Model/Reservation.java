package com.Model;

public class Reservation {
    private int id;
    private int customerId;
    private int roomId;
    private Room room;
    private Customer customer;
    private int prize;

    public Reservation(int id, int customerId, int roomId, int prize) {
        this.id = id;
        this.customerId = customer.getId();
        this.roomId=room.getId();
        this.prize=prize;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
