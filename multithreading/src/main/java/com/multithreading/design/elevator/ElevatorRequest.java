package com.multithreading.design.elevator;

public class ElevatorRequest {

    private final int floor;
    private final Direction direction;
    private final RequestType requestType;

    public ElevatorRequest(int floor, Direction direction) {
        this.floor = floor;
        this.direction = direction;
        requestType = RequestType.OUTSIDE;
    }

    public int floor() {
        return floor;
    }

    public Direction direction() {
        return direction;
    }

    public RequestType requestType() {
        return requestType;
    }
}
