package wcs.cda;

import static org.junit.jupiter.api.Assertions.*;

class ACodeSimplificationTest {

    @org.junit.jupiter.api.Test
    void hasLegalAge() {
        assert(!ACodeSimplification.hasLegalAge(16));
        assert(!ACodeSimplification.hasLegalAge(17));
        assert(ACodeSimplification.hasLegalAge(18));
        assert(ACodeSimplification.hasLegalAge(19));
    }

    @org.junit.jupiter.api.Test
    void studentGroup() {
        assertEquals('A', ACodeSimplification.studentGroup(16));
        assertEquals('B', ACodeSimplification.studentGroup(17));
        assertEquals('A', ACodeSimplification.studentGroup(18));
        assertEquals('B', ACodeSimplification.studentGroup(19));
    }
}