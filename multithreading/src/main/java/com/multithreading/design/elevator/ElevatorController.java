package com.multithreading.design.elevator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElevatorController {

    private List<Elevator> elevators = new ArrayList<>();
    private Map<Integer, List<FloorButton>> floorButtons = new HashMap<>();
    private ElevatorSelectionStrategy strategy;

    public ElevatorController(int numElevators, int numFloors, ElevatorSelectionStrategy strategy) {

        this.strategy = strategy;

        for (int i = 0; i < numElevators; i++) {
            Elevator elevator = new Elevator(i);
            for (int floor = 0; floor < numFloors; floor++){
                FloorDisplay floorDisplay = new FloorDisplay(floor, elevator);
                elevator.addObserver(floorDisplay);
                floorButtons.computeIfAbsent(floor, k -> new ArrayList<>()).add(new FloorButton(floor, elevator, this));
            }
            elevators.add(elevator);
            Thread elevatorThread = new Thread(elevator);
            elevatorThread.start();
        }
    }

    public void submitRequest(ElevatorRequest request){
        // assign this request to an elevator
        Elevator bestElevator = strategy.selectElevator(elevators, request);
        bestElevator.addRequest(request.floor());
    }

    public void pressUpButtonAtFloor(int floor, int elevatorId){
        floorButtons.get(floor).get(elevatorId).pressUpButton();
    }

    public void pressDownButtonAtFloor(int floor, int elevatorId){
        floorButtons.get(floor).get(elevatorId).pressDownButton();
    }

    public void pressInsideButton(int elevatorId, int floor){
        InsidePanel insidePanel = elevators.get(elevatorId).insidePanel();
        insidePanel.pressFloorButton(floor);
    }

}
