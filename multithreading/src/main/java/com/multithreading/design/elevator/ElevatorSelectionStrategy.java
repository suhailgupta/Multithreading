package com.multithreading.design.elevator;

import java.util.List;

public interface ElevatorSelectionStrategy {

    Elevator selectElevator(List<Elevator> elevators, ElevatorRequest request);
}
