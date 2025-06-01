package com.multithreading.design.elevator;

public class ElevatorEvent {

    public enum Type {DOORS_OPEN, MOVING, IDLE, ARRIVED};
    private final int floor;
    private final Direction direction;
    private final int elevatorId;

    private final ElevatorEvent.Type eventType;

    public ElevatorEvent(int floor, Direction direction, int elevatorId, Type eventType) {
        this.floor = floor;
        this.direction = direction;
        this.elevatorId = elevatorId;
        this.eventType = eventType;
    }

    public int floor() {
        return floor;
    }

    public Direction direction() {
        return direction;
    }

    public int elevatorId() {
        return elevatorId;
    }

    public Type eventType() {
        return eventType;
    }
}
