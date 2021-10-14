import org.junit.jupiter.api.Test;

class SquareSumsTreeExploreTest {
    @Test
    public void getPotentialNeighbours() {
        assert(new SquareSumsTreeExplore(6).getPotentialNeighbours(2).isEmpty());
        assert(new SquareSumsTreeExplore(7).getPotentialNeighbours(2).size() == 1);
        assert(new SquareSumsTreeExplore(15).getPotentialNeighbours(9).size() == 1);
    }

    @Test
    public void buildUp() {
        assert(new SquareSumsTreeExplore(6).buildUp() == null);
    }
}