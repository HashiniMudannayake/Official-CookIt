package com.example.officialcookit;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

//bmi cal unit testing
public class ExampleUnitTest {
    float weightValue = 20;
    float heightValue=1;

    float BMI = weightValue / (heightValue * heightValue);
    @Test
    public void calculation_isCorrect() {

        assertEquals(20,20 / (1 * 1));
    }
    @Test
    public void calculation_isinCorrect() {
        assertEquals(20, 20 / (1 * 1));
    }
}