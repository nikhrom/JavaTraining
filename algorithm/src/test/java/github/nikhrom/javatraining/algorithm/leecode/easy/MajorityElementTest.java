package github.nikhrom.javatraining.algorithm.leecode.easy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MajorityElementTest {

    private final MajorityElement majorityElement = new MajorityElement();

    @ParameterizedTest
    @MethodSource
    void givenFilledArray_whenGetMajorityElement_thenMajorityElement(int[] filledArray, int expectedElement) {
        var majorityElement = this.majorityElement.getMajorityElement(filledArray);
        Assertions.assertThat(majorityElement).isEqualTo(majorityElement);
    }

    Stream<Arguments> givenFilledArray_whenGetMajorityElement_thenMajorityElement() {
        return Stream.of(
                Arguments.of(new int[]{3, 2, 3}, 3),
                Arguments.of(new int[]{3, 3, 4, 5, 2, 1, 3, 3, 3}, 3),
                Arguments.of(new int[]{3, 3, 4, 5, 2, 1, 3, 3, 3}, 3)
        );
    }

}