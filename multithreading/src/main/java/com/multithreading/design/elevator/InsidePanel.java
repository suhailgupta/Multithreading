package com.multithreading.design.elevator;

public class InsidePanel implements ElevatorObserver{

    private final Elevator elevator;

    public InsidePanel(Elevator elevator) {
        this.elevator = elevator;
    }

    public void pressFloorButton(int floor){
        System.out.printf("Inside Panel Elevator [%d]: Button pressed for floor %d pressed%n", elevator.id(), floor);
        elevator.addRequest(floor);
    }

    @Override
    public void update(ElevatorEvent event) {
        if(event.elevatorId() == elevator.id()){
            System.out.printf("Inside Display Elevator [%d]: Floor %d (%s)%n",elevator.id(), event.floor(),event.eventType());
        }
    }
}
