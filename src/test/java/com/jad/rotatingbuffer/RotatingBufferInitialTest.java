package com.jad.rotatingbuffer;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;

class RotatingBufferInitialTest {

  private static final int BUFFER_SIZE = 10;
  private static RotatingBuffer<Integer> buffer;

  @BeforeEach
  void initEach() {
    RotatingBufferInitialTest.buffer = new RotatingBuffer<>(RotatingBufferInitialTest.BUFFER_SIZE);
  }

  @Test
  @ResourceLock(value = "buffer")
  void reset() {
    RotatingBufferInitialTest.buffer.reset();
    assertEquals(0, RotatingBufferInitialTest.buffer.getReaderIndex(), "reset()");
    assertEquals(0, RotatingBufferInitialTest.buffer.getWriterIndex(), "reset()");
    RotatingBufferInitialTest.buffer.write(1);
    RotatingBufferInitialTest.buffer.read();
    RotatingBufferInitialTest.buffer.reset();
    assertEquals(0, RotatingBufferInitialTest.buffer.getReaderIndex(), "reset()");
    assertEquals(0, RotatingBufferInitialTest.buffer.getWriterIndex(), "reset()");
  }

  @Test
  @ResourceLock(value = "buffer")
  void negativeSize() {
    RotatingBufferInitialTest.buffer = new RotatingBuffer<>(-1);
    assertEquals(1, RotatingBufferInitialTest.buffer.getSize(), "negativeSize()");
  }

  @Test
  @ResourceLock(value = "buffer")
  void zeroSize() {
    RotatingBufferInitialTest.buffer = new RotatingBuffer<>(0);
    assertEquals(1, RotatingBufferInitialTest.buffer.getSize(), "zeroSize()");
  }

  @Test
  @ResourceLock(value = "buffer")
  void getSize() {
    assertEquals(RotatingBufferInitialTest.BUFFER_SIZE, RotatingBufferInitialTest.buffer.getSize(), "getSize()");
  }

  @Test
  @ResourceLock(value = "buffer")
  void isEmpty() {
    assertTrue(RotatingBufferInitialTest.buffer.isEmpty(), "Test empty buffer");
    RotatingBufferInitialTest.buffer.write(1);
    assertFalse(RotatingBufferInitialTest.buffer.isEmpty(), "Test non-empty buffer");
    RotatingBufferInitialTest.buffer.read();
    assertTrue(RotatingBufferInitialTest.buffer.isEmpty(), "Test empty buffer");
  }

  @Test
  @ResourceLock(value = "buffer")
  void isFull() {
    assertFalse(RotatingBufferInitialTest.buffer.isFull(), "Test empty buffer");
    for (int i = 0; i < RotatingBufferInitialTest.BUFFER_SIZE; i++) {
      assertFalse(RotatingBufferInitialTest.buffer.isFull(),
          "Test buffer with " + i + " / " + RotatingBufferInitialTest.BUFFER_SIZE + " elements");
      RotatingBufferInitialTest.buffer.write(i);
    }
    assertTrue(RotatingBufferInitialTest.buffer.isFull(), "Test full buffer");
  }
}