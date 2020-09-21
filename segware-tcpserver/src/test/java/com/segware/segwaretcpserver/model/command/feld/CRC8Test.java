package com.segware.segwaretcpserver.model.command.feld;

import com.segware.segwaretcpserver.model.command.field.CRC8;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CRC8Test {
    @Test
    public void shouldReturn0WhenArrayIs00() {
        byte[] byteArray = new byte[1];
        byteArray[0] = 0x00;

        assertThat(CRC8.calculate(byteArray), is(equalTo((byte) 0x00)));
    }

    @Test
    public void shouldReturn07WhenArrayIs01() {
        byte[] byteArray = new byte[1];
        byteArray[0] = 0x01;

        assertThat(CRC8.calculate(byteArray), is(equalTo((byte) 0x07)));
    }

    @Test
    public void shouldReturn88WhenArrayIs59() {
        byte[] byteArray = new byte[1];
        byteArray[0] = 0x59;

        assertThat(CRC8.calculate(byteArray), is(equalTo((byte) 0x88)));
    }

    @Test
    public void shouldReturn5EWhenArrayIs2A677B1E2110() {
        byte[] byteArray = {0x2A, 0x67, 0x7B, 0x1E, 0x21, 0x10};

        assertThat(CRC8.calculate(byteArray), is(equalTo((byte) 0x5E)));
    }
}
