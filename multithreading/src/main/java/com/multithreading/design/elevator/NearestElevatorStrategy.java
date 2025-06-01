package com.multithreading.design.elevator;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class NearestElevatorStrategy implements ElevatorSelectionStrategy{

    @Override
    public Elevator selectElevator(List<Elevator> elevators, ElevatorRequest request) {
        Elevator bestElevator = elevators.get(0);
        int minDistance = Integer.MAX_VALUE;
        for (Elevator elevator: elevators){
            int distance = Math.abs(elevator.currentFloor() - request.floor());
            if(elevator.direction() == request.direction()
                    || elevator.direction() == Direction.IDLE){

                if(distance < minDistance){
                    minDistance = distance;
                    bestElevator = elevator;
                }
            }
        }
        return bestElevator;
    }
}
