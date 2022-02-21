package github.nikhrom.javatraining.algorithm.leecode.easy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CoveredIntervalsRemoverTest {


    private final CoveredIntervalsRemover remover = new CoveredIntervalsRemover();

    @ParameterizedTest
    @MethodSource
    void givenArrayWithIntervals_whenRemoveCoveredIntervals_thenCountOfIntervalsIsNotCovered(int[][] intervals, int expectedCount) {
        var actualCount = remover.removeCoveredIntervals(intervals);
        Assertions.assertThat(actualCount).isEqualTo(expectedCount);
    }

    Stream<Arguments> givenArrayWithIntervals_whenRemoveCoveredIntervals_thenCountOfIntervalsIsNotCovered() {
        return Stream.of(
                Arguments.of(new int[][]{{1, 3}, {3, 5}, {0, 6}}, 1),
                Arguments.of(new int[][]{{1, 4}, {2, 3}}, 1),
                Arguments.of(new int[][]{{1, 4}, {3, 6}, {2, 8}}, 2),
                Arguments.of(new int[][]{{1, 2}, {1, 4}, {3, 4}}, 1)
        );
    }

}