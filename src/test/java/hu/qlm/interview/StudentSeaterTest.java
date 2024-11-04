package hu.qlm.interview;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StudentSeaterTest {
    private static final StudentSeater STUDENT_SEATER = new StudentSeater();

    private static Stream<Arguments> provideArgumentsForExceptionThrowing() {
        return Stream.of(
                Arguments.of((Executable) () -> STUDENT_SEATER.seatingStudents(null)),
                Arguments.of((Executable) () -> STUDENT_SEATER.seatingStudents(new int[0])),
                Arguments.of((Executable) () -> STUDENT_SEATER.seatingStudents(new int[] {1})),
                Arguments.of((Executable) () -> STUDENT_SEATER.seatingStudents(new int[] {25})),
                Arguments.of((Executable) () -> STUDENT_SEATER.seatingStudents(new int[] {5})),
                Arguments.of((Executable) () -> STUDENT_SEATER.seatingStudents(new int[] {2, 1, 2, 3})),
                Arguments.of((Executable) () -> STUDENT_SEATER.seatingStudents(new int[] {2, 2, 1}))
        );
    }

    private static Stream<Arguments> provideArgumentsForTestSeating() {
        return Stream.of(
                Arguments.of(new int[] {2}, 2),
                Arguments.of(new int[] {4}, 8),
                Arguments.of(new int[] {12}, 32),
                Arguments.of(new int[] {24}, 68),
                Arguments.of(new int[] {2, 1}, 0),
                Arguments.of(new int[] {2, 1, 2}, 0),
                Arguments.of(new int[] {4, 1}, 4),
                Arguments.of(new int[] {4, 2}, 4),
                Arguments.of(new int[] {4, 3}, 4),
                Arguments.of(new int[] {4, 4}, 4),
                Arguments.of(new int[] {4, 1, 2}, 2),
                Arguments.of(new int[] {4, 1, 3}, 2),
                Arguments.of(new int[] {4, 1, 3}, 2),
                Arguments.of(new int[] {4, 2, 4}, 2),
                Arguments.of(new int[] {4, 1, 4}, 0),
                Arguments.of(new int[] {24, 1}, 64),
                Arguments.of(new int[] {24, 2}, 64),
                Arguments.of(new int[] {24, 23}, 64),
                Arguments.of(new int[] {24, 24}, 64),
                Arguments.of(new int[] {24, 5}, 62),
                Arguments.of(new int[] {24, 6}, 62),
                Arguments.of(new int[] {24, 1, 2}, 62),
                Arguments.of(new int[] {24, 23, 24}, 62),
                Arguments.of(new int[] {24, 23, 24}, 62),
                Arguments.of(new int[] {24, 5, 6}, 58),
                Arguments.of(new int[] {24, 1, 3, 4, 6, 10, 11}, 40)
        );
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForExceptionThrowing")
    void testExceptionThrowing(Executable executable) {
        assertThrows(IllegalArgumentException.class, executable);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForTestSeating")
    void testSeating(int[] inputArray, int expectedResult) {
        int seatingCombinations = STUDENT_SEATER.seatingStudents(inputArray);
        assertEquals(expectedResult, seatingCombinations);
    }
}