package test;

import main.MyStringBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyStringBuilderTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    // without capacity
    @Test
    void capacity_should_be_16_with_default_constructor() {
        MyStringBuilder msb = new MyStringBuilder();
        assertEquals(16, msb.getCapaticy());
    }

    // with capacity
    @Test
    void capacity_should_be_same_to_argument_of_constructor() {
        MyStringBuilder msb = new MyStringBuilder(100);
        assertEquals(100, msb.getCapaticy());
    }

    // with capacity exception
    @Test
    void should_throw_exception_when_capacity_is_not_positive() {
        assertThrows(IllegalArgumentException.class, () -> {
            MyStringBuilder msb = new MyStringBuilder(-1);
        });
    }

    // append
    @Test
    void should_append() {
        MyStringBuilder msb = new MyStringBuilder();
        msb.append("Hello, ").append("world!");
        msb.append(" ");
        msb.append(123);

        StringBuilder sb = new StringBuilder();
        sb.append("Hello, ").append("world!");
        sb.append(" ");
        sb.append(123);

        assertEquals(sb.toString(), msb.toString());
    }

    // delete
    @Test
    void should_delete() {

    }

    // insert
    @Test
    void should_insert() {

    }

    // replace
    @Test
    void should_replace() {

    }

    // reverse
    @Test
    void should_reverse() {
        MyStringBuilder msb = new MyStringBuilder();
        msb.append("Hello, world!");

        StringBuilder sb = new StringBuilder();
        sb.append("Hello, world!");

        assertEquals(sb.reverse().toString(), msb.reverse().toString());
    }
}