package com.multithreading.design.elevator;

public class FloorButton {

    private final int floor;
    private final Elevator elevator;
    private ElevatorController controller;

    public FloorButton(int floor, Elevator elevator, ElevatorController controller) {
        this.floor = floor;
        this.elevator = elevator;
        this.controller = controller;
    }

    public void pressUpButton(){
        System.out.printf("Floor %d UP button pressed%n", floor);
        ElevatorRequest request = new ElevatorRequest(floor, Direction.UP);
        controller.submitRequest(request);
    }

    public void pressDownButton(){
        System.out.printf("Floor %d DOWN button pressed%n", floor);
        ElevatorRequest request = new ElevatorRequest(floor, Direction.DOWN);
        controller.submitRequest(request);
    }
}
