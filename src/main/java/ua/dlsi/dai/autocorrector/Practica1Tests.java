package ua.dlsi.dai.autocorrector;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)

@SuiteClasses ({ ContentTest.class, StyleTest.class})
public class Practica1Tests {
    @BeforeClass
    public static void setUp() {
    }

    @AfterClass
    public static void tearDown() {
    }
}
