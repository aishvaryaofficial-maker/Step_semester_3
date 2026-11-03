package week11.assignment;

public class BusBayRing {
    int[] arr;
    int front, size, capacity;

    BusBayRing(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        front = 0;
        size = 0;
    }

    boolean isEmpty() {
        return size == 0;
    }

    boolean isFull() {
        return size == capacity;
    }

    boolean insertFront(int bus) {
        if (isFull())
            return false;

        front = (front - 1 + capacity) % capacity;
        arr[front] = bus;
        size++;
        return true;
    }

    boolean insertLast(int bus) {
        if (isFull())
            return false;

        int rear = (front + size) % capacity;
        arr[rear] = bus;
        size++;
        return true;
    }

    boolean deleteFront() {
        if (isEmpty())
            return false;

        front = (front + 1) % capacity;
        size--;
        return true;
    }

    boolean deleteLast() {
        if (isEmpty())
            return false;

        size--;
        return true;
    }

    int getFront() {
        return isEmpty() ? -1 : arr[front];
    }

    int getRear() {
        return isEmpty() ? -1 : arr[(front + size - 1) % capacity];
    }
}