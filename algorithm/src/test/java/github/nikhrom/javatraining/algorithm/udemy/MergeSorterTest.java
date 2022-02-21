package github.nikhrom.javatraining.algorithm.udemy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MergeSorterTest {

    private final MergeSorter mergeSorter = new MergeSorter();

    @ParameterizedTest
    @MethodSource
    <T> void givenFilledList_whenSort_thenSortedList(List<T> filledList, Comparator<T> comparator, List<T> expectedList) {
        var sortedList = mergeSorter.sort(filledList, comparator);
        assertThat(sortedList).isEqualTo(expectedList);
    }

    Stream<Arguments> givenFilledList_whenSort_thenSortedList() {
        return Stream.of(
                Arguments.of(
                        new ArrayList<>(List.of(10, 9, 11, 100, 0, -10, 50, 11)), Comparator.comparingInt(value -> (Integer) value), List.of(-10, 0, 9, 10, 11, 11, 50, 100)
                )
        );
    }

    @Test
    void givenEmptyList_whenSort_thenEmptyList() {
        List<Integer> emptyList = new ArrayList<>();
        var sortedEmptyList = mergeSorter.sort(emptyList, Integer::compareTo);
        assertThat(sortedEmptyList).isEqualTo(List.of());
    }

    @Test
    void givenNullList_whenSort_thenNullList() {
        List<Integer> nullList = null;
        var sortedNullList = mergeSorter.sort(nullList, Integer::compareTo);
        assertThat(sortedNullList).isNull();
    }

}
