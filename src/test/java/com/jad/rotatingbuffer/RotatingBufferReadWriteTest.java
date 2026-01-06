package com.jad.rotatingbuffer;


import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Random;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class RotatingBufferReadWriteTest {

  private static final int BUFFER_SIZE = 10;
  private static final int NB_VALUES_WRITTEN = 100;
  private static final int READER_WRITER_MAX_SLEEPING_TIME = 200;
  private static final ArrayList<Integer> WRITTEN_VALUES = new ArrayList<>();
  private static final RotatingBuffer<Integer> BUFFER = new RotatingBuffer<>(RotatingBufferReadWriteTest.BUFFER_SIZE);
  private final ArrayList<Integer> readValues = new ArrayList<>();

  @Test
  @Timeout(value = RotatingBufferReadWriteTest.NB_VALUES_WRITTEN
      * RotatingBufferReadWriteTest.READER_WRITER_MAX_SLEEPING_TIME, unit = MILLISECONDS)
  void write() throws Exception {
    final Random rand = new Random();

    for (int i = 0; i < RotatingBufferReadWriteTest.NB_VALUES_WRITTEN; i++) {
      RotatingBufferReadWriteTest.WRITTEN_VALUES.add(i);
    }
    for (int i = 0; i < RotatingBufferReadWriteTest.NB_VALUES_WRITTEN; i++) {
      if (RotatingBufferReadWriteTest.BUFFER.isFull()
          || !RotatingBufferReadWriteTest.BUFFER.write(RotatingBufferReadWriteTest.WRITTEN_VALUES.get(i))) {
        i--;
      }
      Thread.sleep(rand.nextInt(RotatingBufferReadWriteTest.READER_WRITER_MAX_SLEEPING_TIME));
    }
  }

  @Test
  @Timeout(value = RotatingBufferReadWriteTest.NB_VALUES_WRITTEN
      * RotatingBufferReadWriteTest.READER_WRITER_MAX_SLEEPING_TIME, unit = MILLISECONDS)
  @SuppressWarnings({"BusyWait"})
  void read() throws Exception {
    final Random rand = new Random();

    while (this.readValues.size() != RotatingBufferReadWriteTest.NB_VALUES_WRITTEN) {
      final Integer temp = RotatingBufferReadWriteTest.BUFFER.read();
      if (temp != null) {
        this.readValues.add(temp);
      }
      Thread.sleep(rand.nextInt(RotatingBufferReadWriteTest.READER_WRITER_MAX_SLEEPING_TIME));

    }
    final StringBuilder expected = new StringBuilder();
    for (int i = 0; i < RotatingBufferReadWriteTest.WRITTEN_VALUES.size(); i++) {
      expected.append("[").append(RotatingBufferReadWriteTest.WRITTEN_VALUES.get(i)).append("]\n");
    }
    final StringBuilder actual = new StringBuilder();
    for (final Integer readValue : this.readValues) {
      actual.append("[").append(readValue).append("]\n");
    }
    assertEquals(expected.toString(), actual.toString(), "Test read/write");
    assertEquals(RotatingBufferReadWriteTest.NB_VALUES_WRITTEN, this.readValues.size(), "Test read/write");
  }
}