package com.jad.rotatingbuffer;

public class RotatingBuffer<E> {

  private final E[] data;
  private final RotatingBufferReader<E> reader;
  private final RotatingBufferWriter<E> writer;

  @SuppressWarnings("unchecked")
  public RotatingBuffer(final int size) {
    this.data = (E[]) new Object[this.getSize()];

    // TODO: Continue the constructor implementation here You have to change the two lines below
    this.reader = null;
    this.writer = null;
  }

  public final int getSize() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  public final void reset() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  public final boolean isEmpty() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  public final boolean isFull() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  int getReaderIndex() {
    return this.reader.getIndex();
  }

  public final synchronized E read() {
    return this.reader.read();
  }

  public final synchronized boolean write(final E data) {
    return this.writer.write(data);
  }

  final int getWriterIndex() {
    return this.writer.getIndex();
  }
}
