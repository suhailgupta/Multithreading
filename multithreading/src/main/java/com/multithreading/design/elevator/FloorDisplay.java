package com.multithreading.design.elevator;

public class FloorDisplay implements ElevatorObserver{
    private final int floor;
    private final Elevator elevator;

    public FloorDisplay(int floor, Elevator elevator) {
        this.floor = floor;
        this.elevator = elevator;
    }

    public void display(ElevatorEvent event){
        System.out.printf("Floor Display [%d] [Elevator %d]: Elevator Status- Floor %d (%s) [%s]%n",
                floor, elevator.id(), event.floor(),event.direction(),event.eventType());
    }

    @Override
    public void update(ElevatorEvent event) {
        if(event.elevatorId() == elevator.id()){
            display(event);
        }
    }
}
