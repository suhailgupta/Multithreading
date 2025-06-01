package com.multithreading.design.elevator;

import java.util.List;
import java.util.PriorityQueue;

public class GroupCollectiveBatchingStrategy implements ElevatorSelectionStrategy{

    @Override
    public Elevator selectElevator(List<Elevator> elevators, ElevatorRequest request) {
        /*- Matches the direction (Nearest)
                - Fallback on Idle.
        - Least Busy*/
        Elevator bestElevator = elevators.get(0);
        int minPenalty = Integer.MAX_VALUE;
        for (Elevator elevator: elevators){
            int penalty = getPenalty(elevator, request);
            if(penalty < minPenalty){
                minPenalty = penalty;
                bestElevator = elevator;
            }
        }
        return bestElevator;
    }

    private int getPenalty(Elevator elevator, ElevatorRequest request){
        /*- Matches the direction (Nearest)
                - Fallback on Idle.
        - Least Busy*/
        int penalty = 0;
        int distance = Math.abs(elevator.currentFloor() - request.floor());
        penalty += distance * PenaltyValues.DISTANCE_PER_FLOOR;
        boolean sameDirectionMatches = elevator.direction() == request.direction();
        boolean goingUp = elevator.direction() == Direction.UP;
        if(sameDirectionMatches){
            // steps-
            penalty += directionAwarePenalty(elevator, request.floor(), goingUp);
        }else{
            // opposite direction
            penalty +=PenaltyValues.OPPOSITE_DIRECTION;
            int oppositeQueue = goingUp ? elevator.downQueueSize() : elevator.upQueueSize();
            penalty += oppositeQueue* PenaltyValues.OPPOSITE_QUEUE_PENALTY;
        }
        return penalty;
    }

    private int directionAwarePenalty(Elevator elevator, int requestFloor, boolean goingUp) {
        PriorityQueue<Integer> queue = goingUp ? elevator.upQueue() : elevator.downQueue();
        if(queue.contains(requestFloor)){
            return 0;
        }
        // we need to find whether the elevator would definitely pass by that floor.
        for (int stop: queue){
            if((goingUp && stop >= requestFloor) || (!goingUp && stop <= requestFloor)){
                return PenaltyValues.PASS_BY_FLOOR;
            }
        }
        return PenaltyValues.INSERT_PENALTY;
    }


}
