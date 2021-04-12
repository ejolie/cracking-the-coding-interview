package main;

import java.util.Arrays;

public class MyStringBuilder {

    private byte[] buf;
    private int capaticy;
    private int count;

    public MyStringBuilder() {
        capaticy = 16;
        buf = new byte[capaticy];
        count = 0;
    }

    public MyStringBuilder(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be positive.");
        }
        this.capaticy = capacity;
        buf = new byte[capacity];
        count = 0;
    }

    public int getCapaticy() {
        return capaticy;
    }

    public MyStringBuilder append(String str) {
        byte[] bytes = str.getBytes();
        checkEnoughCapacity(bytes.length);

        for (byte b : bytes) {
            buf[count++] = b;
        }
        return this;
    }

    public MyStringBuilder append(int i) {
        return append(String.valueOf(i));
    }

    public MyStringBuilder append(Object o) {
        return append(o.toString());
    }

    private void checkEnoughCapacity(int len) {
        if (count + len > capaticy) {
            while (count + len > capaticy) {
                capaticy *= 2;
            }
            buf = Arrays.copyOf(buf, capaticy);
        }
    }

    public MyStringBuilder delete(int start, int end) {
        // TODO: 예외 처리: 인덱스 범위

        byte[] newBuf = new byte[capaticy];
        int j = 0;
        for (int i = 0; i < start; i++, j++) {
            newBuf[j] = buf[i];
        }
        for (int i = end; i < count; i++, j++) {
            newBuf[j] = buf[i];
        }

        count = j + 1;
        buf = newBuf;

        return this;
    }

    public MyStringBuilder insert(int offset, String str) {
        byte[] bytes = str.getBytes();
        checkEnoughCapacity(bytes.length);

        for (int i = offset; i < count; i++) {
            buf[offset + bytes.length + i] = buf[i];
        }
        int i = offset;
        for (byte b : bytes) {
            buf[i++] = b;
        }

        count = count + bytes.length;

        return this;
    }

    public MyStringBuilder replace(int start, int end, String str) {
        // TODO: 예외 처리: 인덱스 범위
    }

    public MyStringBuilder reverse() {
        int half = count / 2;
        for (int i = 0; i < half; i++) {
            swap(buf, i, count - 1 - i);
        }
        return this;
    }

    private void swap(byte[] arr, int i, int j) {
        byte temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    @Override
    public String toString() {
        return new String(buf, 0, count);
    }
}
