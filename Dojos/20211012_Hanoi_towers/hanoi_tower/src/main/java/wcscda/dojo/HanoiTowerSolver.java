package wcscda.dojo;

import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

public class HanoiTowerSolver {
    public static void main(String[] args) {
        new HanoiTowerSolver(8).solve();
    }

    private int nbDisks;
    private TreeMap<Position, Integer> visitedPositions = new TreeMap<>();

    public HanoiTowerSolver(int nbDisks) {
        this.nbDisks = nbDisks;
    }

    public void solve() {
        Position position = getInitialPosition();
        position.calculateSignature();
        solve(position).printPosition();
        System.out.println(visitedPositions.size());
    }

    public Position solve(Position position) {
        if(position.isSuccess()) {
            position.setNbCoupsToSuccess(0);
            return position;
        }
        if(visitedPositions.containsKey(position)) {
            if(visitedPositions.get(position) <= position.nbCoups) {
                return null;
            }
        }
        visitedPositions.put(position, position.nbCoups);

        ArrayList<Position> nextPosition = new ArrayList<>();

        for(int colFrom = 0; colFrom < 3; ++colFrom) {
            for(int colTo = 0; colTo < 3; ++colTo) {
                if(isPossible(position, colFrom, colTo)) {
                    nextPosition.add(solve(position.apply(colFrom, colTo)));
                }
            }
        }

        Position bestPosition = findBestPosition(nextPosition);
        if(bestPosition == null) {
            return null;
        }
        position.setNbCoupsToSuccess(bestPosition.getNbCoupsToSuccess() + 1);
        position.setNextPosition(bestPosition);

        return position;
    }

    private Position findBestPosition(ArrayList<Position> nextPosition) {
        Position bestPosition = null;
        for(Position possibleNextPosition: nextPosition) {
            if(possibleNextPosition == null) {
                continue;
            }
            if(bestPosition == null) {
                bestPosition = possibleNextPosition;
            }
            else if(possibleNextPosition.getNbCoupsToSuccess()
                    < bestPosition.getNbCoupsToSuccess())
            {
                bestPosition = possibleNextPosition;
            }
        }
        return bestPosition;
    }

    public boolean isPossible(Position position,
                              int colFrom,
                              int colTo) {
        if(colFrom == colTo) return false;
        if(position.getStacks()[colFrom].isEmpty()) return false;
        if(position.getStacks()[colTo].isEmpty()) return true;

        return position.getStacks()[colTo].peek()
                > position.getStacks()[colFrom].peek();
    }

    private Position getInitialPosition() {
        Stack<Integer>[] stacks = new Stack[3];

        stacks = new Stack[3];
        for(int i = 0; i < 3; ++i) {
            stacks[i] = new Stack();
        }
        for (int i = nbDisks; i > 0; --i) {
            stacks[0].add(i);
        }

        return new Position(stacks, 0);
    }


}
