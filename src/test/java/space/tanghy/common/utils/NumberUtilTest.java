package space.tanghy.common.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author tanghy
 * @description
 * @date 2020-01-18 8:56 下午
 */
class NumberUtilTest {

    @Test
    void format() {
    }

    @Test
    void add() {

        double a = 4.33;
        double b = 4.31;

        double add = NumberUtil.add(a, b);

        assertEquals(add,8.64);
    }

    @Test
    void sub() {
    }

    @Test
    void mul() {
    }

    @Test
    void div() {
    }
}