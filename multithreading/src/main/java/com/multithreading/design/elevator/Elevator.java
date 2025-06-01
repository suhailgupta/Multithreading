package com.multithreading.design.elevator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Elevator implements Runnable{
    private int id;
    private int currentFloor = 0;
    // directions- UP/DOWN/IDLE
    private Direction direction = Direction.IDLE;
    private InsidePanel insidePanel;
    private volatile boolean running = true;
    // we somehow need to have buffer where we will store each and every request
    private PriorityQueue<Integer> upQueue = new PriorityQueue<>();
    private PriorityQueue<Integer> downQueue = new PriorityQueue<>(Comparator.reverseOrder());
    private List<ElevatorObserver> observers = new ArrayList<>();

    public Elevator(int id) {
        this.id = id;
        insidePanel = new InsidePanel(this);
        addObserver(insidePanel);
    }

    public void addObserver(ElevatorObserver elevatorObserver){
        observers.add(elevatorObserver);
    }

    public void notify(ElevatorEvent.Type eventType){
        ElevatorEvent elevatorEvent = new ElevatorEvent(currentFloor, direction, id, eventType);
        for (ElevatorObserver observer: observers){
            observer.update(elevatorEvent);
        }
    }

    public synchronized void addRequest(int floor){
        // floor= 5, currentFloor = 2
        if(floor < currentFloor){
            downQueue.add(floor);
        }else if(floor > currentFloor){
            upQueue.add(floor);
        }else{
            System.out.println("Elevator "+ id + " is already at floor "+ floor);
        }
        if(direction == Direction.IDLE){
            direction = floor > currentFloor ? Direction.UP : Direction.DOWN;
        }
    }

    @Override
    public void run() {
        while(running){
            try{
                Integer nextFloor = getNextFloor();
                if(nextFloor != null){
                    // elevator has to move to the floor
                    moveToFloor(nextFloor);
                }else{
                    Thread.sleep(200);
                }
            }catch (InterruptedException ex){
                Thread.currentThread().interrupt();
            }
        }
    }

    private synchronized Integer getNextFloor(){
        return switch (direction){
            case UP -> upQueue.poll();
            case DOWN -> downQueue.poll();
            case IDLE -> null;
        };
    }


    private void moveToFloor(int floor) throws InterruptedException{
        while(currentFloor != floor){
            currentFloor += direction == Direction.UP ? 1 : -1;
            notify(ElevatorEvent.Type.MOVING);
            System.out.println("Elevator " + id + " reached at floor "+currentFloor);
        }
        // exit of while, elevator must have reached the destination floor.
        notify(ElevatorEvent.Type.ARRIVED);
        Thread.sleep(200);
        notify(ElevatorEvent.Type.DOORS_OPEN);
        Thread.sleep(300);
        //if queue is empty, we have to decide wheyther the elevator should reverse or should stay.
        synchronized (this){
            if(upQueue.isEmpty() && downQueue.isEmpty()){
                markElevatorIdle();

            }else if(upQueue.isEmpty() && direction == Direction.UP && !downQueue.isEmpty()){
                direction = Direction.DOWN;
            }else if(!upQueue.isEmpty() && direction == Direction.DOWN && downQueue.isEmpty()){
                direction = Direction.UP;
            }
        }
    }

    private void markElevatorIdle() throws InterruptedException{
        direction = Direction.IDLE;
        notify(ElevatorEvent.Type.IDLE);
        Thread.sleep(200);
    }

    public int id() {
        return id;
    }

    public int currentFloor() {
        return currentFloor;
    }

    public Direction direction() {
        return direction;
    }

    public InsidePanel insidePanel() {
        return insidePanel;
    }

    public PriorityQueue<Integer> upQueue() {
        return upQueue;
    }

    public PriorityQueue<Integer> downQueue() {
        return downQueue;
    }

    public int upQueueSize(){
        return upQueue.size();
    }

    public int downQueueSize(){
        return downQueue.size();
    }
}
