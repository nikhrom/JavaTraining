package github.nikhrom.javatraining.algorithm.udemy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SieveTest {

    private final Sieve sieve = new Sieve();

    @Test
    void givenMaximumPrimeNumber_whenSift_thenListWithPrimes(){
        var primes = sieve.sift(30);
        Assertions.assertThat(primes).isEqualTo(
                List.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29)
        );
    }

    @Test
    void givenMaximumPrimeNumberLessThenTwo_whenSift_thenEmptyList(){
        var primes = sieve.sift(1);
        Assertions.assertThat(primes).isEqualTo(List.of());
    }

}
