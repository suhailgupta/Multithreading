package com.multithreading.design.elevator;

public class ElevatorSystem {

    public static void main(String[] args) {

        ElevatorSelectionStrategy strategy = ElevatorStrategyFactory.getStrategy("group-collective");
        ElevatorController controller = new ElevatorController(3, 10, strategy);
        // simulate requests
        controller.pressUpButtonAtFloor(2, 0);
        controller.pressInsideButton(0, 5);
    }
}
