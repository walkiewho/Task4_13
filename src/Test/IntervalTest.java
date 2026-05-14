package Test;

import Task.Segment;
import Task.Solution;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntervalTest {
    @Test
    void testSingleMaximumInterval() {

        ArrayList<Segment> segments = new ArrayList<>();
        segments.add(new Segment(1, 5));
        segments.add(new Segment(2, 7));
        segments.add(new Segment(3, 6));

        ArrayList<Segment> expected = new ArrayList<>();
        expected.add(new Segment(3, 5));

        ArrayList<Segment> actual = Solution.solve(segments);

        assertEquals(expected, actual);
    }

    @Test
    void testTwoDisjointMaxIntervals() {
        ArrayList<Segment> segments = new ArrayList<>();
        segments.add(new Segment(1, 3));
        segments.add(new Segment(2, 4));  // максимум 2 на [2,3]
        segments.add(new Segment(5, 7));
        segments.add(new Segment(6, 8));  // максимум 2 на [6,7]

        ArrayList<Segment> expected = new ArrayList<>();
        expected.add(new Segment(2, 3));
        expected.add(new Segment(6, 7));

        ArrayList<Segment> actual = Solution.solve(segments);
        assertEquals(expected, actual);
    }

    @Test
    void testSinglePointMaxCoverage() {
        ArrayList<Segment> segments = new ArrayList<>();
        segments.add(new Segment(1, 4));
        segments.add(new Segment(2, 5));
        segments.add(new Segment(3, 6));
        segments.add(new Segment(3, 4));  // точка 3 и 4 имеют покрытие 4

        ArrayList<Segment> expected = new ArrayList<>();
        expected.add(new Segment(3, 4));  // интервал [3,4]

        ArrayList<Segment> actual = Solution.solve(segments);
        assertEquals(expected, actual);
    }

    @Test
    void testAdjacentSegments() {
        ArrayList<Segment> segments = new ArrayList<>();
        segments.add(new Segment(1, 2));
        segments.add(new Segment(2, 3));
        segments.add(new Segment(3, 4));

        ArrayList<Segment> expected = new ArrayList<>();
        // покрытие везде 1, возвращаем все отрезки
        expected.add(new Segment(1, 2));
        expected.add(new Segment(2, 3));
        expected.add(new Segment(3, 4));

        ArrayList<Segment> actual = Solution.solve(segments);
        assertEquals(expected, actual);
    }

    @Test
    void testNestedSegments() {
        ArrayList<Segment> segments = new ArrayList<>();
        segments.add(new Segment(1, 10));
        segments.add(new Segment(2, 9));
        segments.add(new Segment(3, 8));
        segments.add(new Segment(4, 7));
        segments.add(new Segment(5, 6));

        ArrayList<Segment> expected = new ArrayList<>();
        expected.add(new Segment(5, 6));  // все 5 отрезков покрывают [5,6]

        ArrayList<Segment> actual = Solution.solve(segments);
        assertEquals(expected, actual);
    }

    @Test
    void testThreePeaksWithSameHeight() {
        ArrayList<Segment> segments = new ArrayList<>();
        segments.add(new Segment(1, 4));
        segments.add(new Segment(2, 3));  // пик 2 на [2,3]
        segments.add(new Segment(5, 8));
        segments.add(new Segment(6, 7));  // пик 2 на [6,7]
        segments.add(new Segment(9, 12));
        segments.add(new Segment(10, 11)); // пик 2 на [10,11]

        ArrayList<Segment> expected = new ArrayList<>();
        expected.add(new Segment(2, 3));
        expected.add(new Segment(6, 7));
        expected.add(new Segment(10, 11));

        ArrayList<Segment> actual = Solution.solve(segments);
        assertEquals(expected, actual);
    }

    @Test
    void testGradualIncreaseAndDecrease() {
        ArrayList<Segment> segments = new ArrayList<>();
        segments.add(new Segment(1, 6));
        segments.add(new Segment(2, 6));
        segments.add(new Segment(3, 6));
        segments.add(new Segment(4, 5));

        ArrayList<Segment> expected = new ArrayList<>();
        expected.add(new Segment(4, 5));  // покрытие 4 на [4,5]

        ArrayList<Segment> actual = Solution.solve(segments);
        assertEquals(expected, actual);
    }

    @Test
    void testEmptyInput() {
        ArrayList<Segment> segments = new ArrayList<>();
        ArrayList<Segment> expected = new ArrayList<>();

        ArrayList<Segment> actual = Solution.solve(segments);
        assertEquals(expected, actual);
    }

    @Test
    void testAllSegmentsSame() {
        ArrayList<Segment> segments = new ArrayList<>();
        segments.add(new Segment(2, 8));
        segments.add(new Segment(2, 8));
        segments.add(new Segment(2, 8));
        segments.add(new Segment(2, 8));
        segments.add(new Segment(2, 8));

        ArrayList<Segment> expected = new ArrayList<>();
        expected.add(new Segment(2, 8));

        ArrayList<Segment> actual = Solution.solve(segments);
        assertEquals(expected, actual);
    }

    @Test
    void testComplexMixedOverlaps() {
        ArrayList<Segment> segments = new ArrayList<>();
        segments.add(new Segment(1, 20));
        segments.add(new Segment(2, 8));
        segments.add(new Segment(3, 7));
        segments.add(new Segment(4, 6));
        segments.add(new Segment(10, 15));
        segments.add(new Segment(11, 14));
        segments.add(new Segment(12, 13));
        segments.add(new Segment(16, 18));

        ArrayList<Segment> expected = new ArrayList<>();
        expected.add(new Segment(4, 6));
        expected.add(new Segment(12, 13));

        ArrayList<Segment> actual = Solution.solve(segments);
        assertEquals(expected, actual);
    }
}