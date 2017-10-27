package com.conference.lecture;

public class Room {
    private String roomId;
    private String name;
    private String description;
    private int seat;

//    public Room(String roomId, String name, String description, int seat) {
//        this.roomId = roomId;
//        this.name = name;
//        this.description = description;
//        this.seat = seat;
//    }

    public Room(String roomId, String name, String description, int seat) {
        if(roomId.length() <= 20) {
            this.roomId = roomId;
        } else {
            this.roomId = roomId.substring(0, 20);
        }
        if(name.length() <= 20) {
            this.name = name;
        } else {
            this.name = name.substring(0, 20);
        }
        if(description.length() <= 20) {
            this.description = description;
        } else {
            this.description = description.substring(0, 20);
        }
        if(seat >= 0) {
            this.seat = seat;
        } else {
            this.seat = 0;
        }
    }

    public String getRoomId() {
        return roomId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getSeat() {
        return seat;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }
}
