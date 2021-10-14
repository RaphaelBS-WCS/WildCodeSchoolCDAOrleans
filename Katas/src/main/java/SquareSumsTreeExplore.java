import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.*;

public class SquareSumsTreeExplore {
    private final Set<Integer> squareSet;
    private final int n;
    private Map<Integer, Set<Integer>> neighboursByElement;
    private Map<Integer, ArrayList<Integer>> elementByNbNeighbours;

    // https://www.codewars.com/kata/5a667236145c462103000091/train/java
    public static void main(String[] args) {
        for(int i = 1; i < 50; ++i){
            System.out.println("Solving n = " + i);
            varDump(buildUpTo(i));
        }
    }

    public static List<Integer> buildUpTo(int n) {

        return new SquareSumsTreeExplore(n).buildUp();
    }

    public SquareSumsTreeExplore(int n) {
        this.n = n;
        this.squareSet = constructSquareSet(n);
    }

    public List<Integer> buildUp() {
        neighboursByElement = getNeighboursMap();
        if(neighboursByElement == null) return null;

        elementByNbNeighbours = getElementByNbNeighbours(neighboursByElement);

        if(elementByNbNeighbours.get(1) != null
                && elementByNbNeighbours.get(1).size() > 2) {
            return null;
        }

        List<Integer> initialList = getInitialArray();

        try {
            System.out.println(elementByNbNeighbours.get(2).size());
        }
        catch(Exception e) {
            System.out.println("No 2 neighbours");
        }
        return solve(initialList);
    }

    private List<Integer> solve(List<Integer> list) {
        int firstEmpty = list.indexOf(0);
        if(firstEmpty == -1) return list;

        Set<Integer> presentElement = new TreeSet<>(list);

        if(firstEmpty == 0) {
            for(Map.Entry<Integer, ArrayList<Integer>> entry: elementByNbNeighbours.entrySet()) {
                List<Integer> resList = findFirstSolvable(list, firstEmpty, entry.getValue());
                if (resList != null) return resList;
            }

            return null;
        }
        else {
            Integer lastElement = list.get(firstEmpty - 1);
            Set<Integer> candidates = new TreeSet<>(neighboursByElement.get(lastElement));
            candidates.removeAll(presentElement);

            return findFirstSolvable(list, firstEmpty, candidates);
        }
    }

    private List<Integer> findFirstSolvable(List<Integer> list, int firstEmpty, Collection<Integer> values) {
        for(Integer i: values) {
            List<Integer> resList = solve(list, firstEmpty, i);
            if(resList != null) { return resList; }
        }
        return null;
    }

    private List<Integer> solve(List<Integer> list, int index, Integer value) {
        List<Integer> nextList = new ArrayList<>(list);
        nextList.set(index, value);

        return solve(nextList);
    }

    private List<Integer> getInitialArray() {
        List<Integer> result = new ArrayList<Integer>(n);
        for(int i=0; i<n; ++i) result.add(0);
        return result;
    }

    private Map<Integer, ArrayList<Integer>> getElementByNbNeighbours(Map<Integer, Set<Integer>> neighboursByElement) {
        Map<Integer, ArrayList<Integer>> elementByNbNeighbours = new TreeMap<Integer, ArrayList<Integer>>();

        neighboursByElement.forEach( (k, v) -> {
            int nbElements = v.size();
            if(!elementByNbNeighbours.containsKey(nbElements)) {
                elementByNbNeighbours.put(
                        nbElements,
                        new ArrayList<>()
                        );
            }
            elementByNbNeighbours.get(nbElements).add(k);
        });
        return elementByNbNeighbours;
    }

    private Map<Integer, Set<Integer>> getNeighboursMap() {
        Map<Integer, Set<Integer>> neighboursMap = new TreeMap<>();

        for(int i = 1; i <= n; ++i) {
            Set<Integer> possibleNeighbours = getPotentialNeighbours(i);
            if(possibleNeighbours.isEmpty()) return null;
            neighboursMap.put(i, possibleNeighbours);
        }

        return neighboursMap;
    }

    public Set<Integer> getPotentialNeighbours(int i) {
        Set<Integer> result = new TreeSet<>();

        for(Integer s: squareSet) {
            if(s <= i || s == 2*i) continue;
            if(s - i > n) break;

            result.add(s - i);
        }

        return result;
    }

    private Set<Integer> constructSquareSet(int n) {
        Set<Integer> squareSet = new TreeSet<>();

        for(int i = 2; i*i < 2*n; ++i) {
            squareSet.add(i*i);
        }

        return squareSet;
    }

    public static void varDump(Object o) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            System.out.println(ow.writeValueAsString(o));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
