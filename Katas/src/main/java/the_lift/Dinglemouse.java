package the_lift;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Dinglemouse {
    private final int capacity;
    private final ArrayList<Integer> passenger;
    private final ArrayList<Integer> history;
    private final ArrayList<Integer>[] queues;

    private int nbQueuedPeople;
    private boolean isGoingUp;
    private int floor;

    public static int[] theLift(final int[][] queues, final int capacity) {
        // Your code here
        return new Dinglemouse(capacity, queues).lift();
    }

    public Dinglemouse(int capacity, int[][] queues) {
        this.capacity = capacity;
        this.passenger = new ArrayList<Integer>();
        this.isGoingUp = true;
        this.floor = 0;
        this.history = new ArrayList<Integer>();
        this.history.add(0);
        this.queues = new ArrayList[queues.length];

        this.nbQueuedPeople = 0;

        for(int i = 0; i < queues.length; ++i) {
            this.queues[i] = new ArrayList<>(queues[i].length);
            Arrays.stream(queues[i]).forEach(this.queues[i]::add);
            this.nbQueuedPeople += queues[i].length;
        }
    }

    public int[] lift() {
        while (!hasFinished()) {
            move();
        }
        if(history.get(history.size() - 1) != 0) {
            history.add(0);
        }
        return history.stream().mapToInt(Integer::intValue).toArray();
    }

    private void move() {
        loadPeoples();
        if(passenger.isEmpty()) {
            lookForNextPassenger();
        }
        else {
            if(isGoingUp) {
                makeMoveUp();
            }
            else {
                makeMoveDown();
            }
        }

        makePeopleDescend();
    }

    private void makeMoveUp() {
        for(int i = floor + 1; i < queues.length; ++i) {
            final int iFloor = i;
            if(passenger.contains(i) ||
                    queues[i].stream().filter(n -> n > iFloor).findFirst().isPresent())
            {
                setFloorWithHistory(i);
                break;
            }
        }
    }

    private void makeMoveDown() {
        for(int i = floor - 1; i < queues.length; --i) {
            final int iFloor = i;
            if(passenger.contains(i) ||
                    queues[i].stream().filter(n -> n < iFloor).findFirst().isPresent())
            {
                setFloorWithHistory(i);
                break;
            }
        }
    }

    private void lookForNextPassenger() {
        if(isGoingUp) {
            if(!pickNextPersonGoingUp()) {
                pickHighestPersonGoingDown();
            }
        }
        else {
            if (!pickNextPersonGoingDown()) {
                pickLowestPersonGoingUp();
            }
        }
    }

    private void makePeopleDescend() {
        this.nbQueuedPeople -= passenger.stream().filter(i -> floor == i).count();
        passenger.removeAll(Collections.singleton(floor));
    }

    private void pickHighestPersonGoingDown() {
        if(queues[queues.length - 1].isEmpty()) {
            setFloorNoHistory(queues.length - 1);
        }
        else {
            setFloorWithHistory(queues.length - 1);
        }
        changeDirection();
    }

    private void pickLowestPersonGoingUp() {
        if(queues[0].isEmpty()) {
            setFloorNoHistory(0);
        }
        else {
            setFloorWithHistory(0);
        }
        changeDirection();
    }

    private boolean pickNextPersonGoingUp() {
        for(int i = floor + 1; i < queues.length; ++i) {
            for(Integer person: queues[i]) {
                // The person want to go up
                if(person > i) {
                    setFloorWithHistory(i);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean pickNextPersonGoingDown() {
        for(int i = floor - 1; i >= 0; --i) {
            for(Integer person: queues[i]) {
                // The person want to go down
                if(person < i) {
                    setFloorWithHistory(i);
                    return true;
                }
            }
        }
        return false;
    }

    private void setFloorWithHistory(int i) {
        floor = i;
        history.add(floor);
    }

    private void setFloorNoHistory(int i) {
        floor = i;
    }

    private void changeDirection() {
        isGoingUp = !isGoingUp;
    }

    private void loadPeoples() {
        for(int i = 0; i < queues[floor].size(); ++i) {
            if(passenger.size() == capacity) return;
            Integer person = queues[floor].get(i);
            if((person > floor) == isGoingUp) {
                passenger.add(person);
                queues[floor].set(i, null);
            }
        }
        queues[floor].removeAll(Collections.singleton(null));
    }

    private boolean hasFinished() {
        return nbQueuedPeople == 0;
    }

}
