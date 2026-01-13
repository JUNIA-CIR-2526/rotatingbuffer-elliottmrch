package com.jad.rotatingbuffer;

public class RotatingBuffer<E> {

    private final int size;
    private final E[] data;
    private boolean empty;
    private RotatingBufferReader<E> reader;
    private RotatingBufferWriter<E> writer;

    @SuppressWarnings("unchecked")
    public RotatingBuffer(final int size) {
        this.size = Math.max(1, size);
        this.data = (E[]) new Object[this.getSize()];
        this.reset();
    }

    public final int getSize() {
        return this.size;
    }

    public final void reset() {
        this.empty = true;
        this.reader = new RotatingBufferReader<>(this);
        this.writer = new RotatingBufferWriter<>(this);
    }

    public final synchronized E read() {
        if (this.isEmpty()) return null;
        E result = this.reader.read();
        if (this.getWriterIndex() == this.getReaderIndex()) this.empty = true;
        return result;
    }

    public final boolean isEmpty() {
        return this.empty;
    }

    final int getWriterIndex() {
        return this.writer.getIndex();
    }

    int getReaderIndex() {
        return this.reader.getIndex();
    }

    public final synchronized boolean write(final E data) {
        if (this.isFull()) return false;
        this.empty = false;
        return this.writer.write(data);
    }

    public final boolean isFull() {
        return (!this.isEmpty()) && (this.getReaderIndex() == this.getWriterIndex());
    }

    public E getFromIndex(final int index) {
        if (index < 0 || index >= this.getSize()) throw new IndexOutOfBoundsException();
        return this.data[index];
    }

    public void setAtIndex(final E data, final int index) {
        if (index < 0 || index >= this.getSize()) throw new IndexOutOfBoundsException();
        this.data[index] = data;
    }
}