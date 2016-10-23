package dev.kodama.test;

import org.junit.Test;

import dev.kodama.test.utils.HalcyonUtils;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void getLengthIsCorrect() throws Exception {
        assertEquals(HalcyonUtils.getLengthFrom(10,30),10.5,0.01);
        assertEquals(HalcyonUtils.getLengthFrom(10,45),10.75, 0.01);
        assertEquals(HalcyonUtils.getLengthFrom(10,15),10.25, 0.01);
        assertEquals(HalcyonUtils.getLengthFrom(10,0),10.00, 0.01);
        assertEquals(HalcyonUtils.getLengthFrom(10)[0],10);
        assertEquals(HalcyonUtils.getLengthFrom(10)[1],0);
        assertEquals(HalcyonUtils.getLengthFrom(10.50F)[0],10);
        assertEquals(HalcyonUtils.getLengthFrom(10.50F)[1],30);
        assertEquals(HalcyonUtils.getLengthFrom(10.75F)[0],10);
        assertEquals(HalcyonUtils.getLengthFrom(10.75F)[1],45);
        assertEquals(HalcyonUtils.getLengthFrom(10.25F)[0],10);
        assertEquals(HalcyonUtils.getLengthFrom(10.25F)[1],15);
    }
}