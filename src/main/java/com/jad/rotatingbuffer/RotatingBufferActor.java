package com.jad.rotatingbuffer;

abstract class RotatingBufferActor<E> {
    private final RotatingBuffer<E> rotatingBuffer;
    private int index = 0;

    RotatingBufferActor(final RotatingBuffer<E> rotatingBuffer) {
        this.rotatingBuffer = rotatingBuffer;
    }

    protected RotatingBuffer<E> getRotatingBuffer() {
        return this.rotatingBuffer;
    }

    int getIndex() {
        return this.index;
    }

    protected void incrementIndex() {
        this.index = (this.index + 1) % this.rotatingBuffer.getSize();
    }
}