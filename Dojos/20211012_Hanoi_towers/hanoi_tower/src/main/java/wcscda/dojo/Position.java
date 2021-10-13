package wcscda.dojo;

import java.util.Arrays;
import java.util.Stack;

public class Position implements Comparable<Position> {
    private Position bestPosition;

    public Stack<Integer>[] getStacks() {
        return stacks;
    }

    Stack<Integer>[] stacks;
    int nbCoups;
    Integer signature;

    public int getNbCoupsToSuccess() {
        return nbCoupsToSuccess;
    }

    public void setNbCoupsToSuccess(int nbCoupsToSuccess) {
        this.nbCoupsToSuccess = nbCoupsToSuccess;
    }

    Integer nbCoupsToSuccess;

    public Position(Stack<Integer>[] stacks, int nbCoups) {
        this.stacks = stacks;
        this.nbCoups = nbCoups;
    }

    public void calculateSignature() {
        signature = 0;
        int stackIndice = 0;
        for(Stack<Integer> stack: stacks) {
            for(Integer disk: stack) {
                signature += (int)(stackIndice * Math.pow(3, disk));
            }
            ++stackIndice;
        }
    }

    @Override
    public int compareTo(Position position) {
        return signature.compareTo(position.signature);
    }

    public void setNextPosition(Position bestPosition) {
        this.bestPosition = bestPosition;
    }

    public boolean isSuccess() {
        return stacks[0].isEmpty() && stacks[1].isEmpty();
    }

    public Position apply(int colFrom, int colTo) {
        Position newPosition = copy();
        newPosition.move(colFrom, colTo);
        newPosition.calculateSignature();
        newPosition.nbCoups += 1;

        return newPosition;
    }

    private void move(int colFrom, int colTo) {
        stacks[colTo].add(stacks[colFrom].pop());
    }

    private Position copy() {
        Stack<Integer>[] stacks = new Stack[3];
        for(int i = 0; i < 3; i++) {
            stacks[i] = (Stack<Integer>) this.stacks[i].clone();
        }

        return new Position(stacks, nbCoups);
    }

    public void printPosition() {
        System.out.println("===============");
        Arrays.stream(stacks).forEach(System.out::println);
        System.out.println("Nb coups :" + nbCoups);

        if(bestPosition != null) {
            bestPosition.printPosition();
        }
    }
}
