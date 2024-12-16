package BookStore;

import java.util.Arrays;
import java.util.Comparator;

public class BookListADT<T> implements AbstractList<T> {
    private static final int DEFAULT_CAPACITY = 5;
    private T[] elements;
    private int size = 0;

    public BookListADT(){
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public boolean add(T element) {
        if (size == elements.length){
            elements = Arrays.copyOf(elements, elements.length * 2);
        }

        elements[size] = element;
        size++;
        return false;
    }

    @Override
    public T remove(int index) {
        if(index < 0 ||  index >= size)
        {
            throw new IndexOutOfBoundsException("Index out of bound: " + index + " out of size.");
        }

        T oldElement = elements[index];


        // shifting
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        size--;
        elements[size] = null;

        // reducing length of array
        if (size < elements.length / 3){
            elements = Arrays.copyOf(elements, elements.length / 2);
        }

        return oldElement;
    }

    @Override
    public T get(int index) {
        if(index < 0 ||  index >= size)
        {
            throw new IndexOutOfBoundsException("Index out of bound: " + index + " out of size.");
        }
        return elements[index];
    }

    @Override
    public T set(int index, T element) {
        if(index < 0 ||  index >= size)
        {
            throw new IndexOutOfBoundsException("Index out of bound: " + index + " out of size.");
        }

        T oldElement = elements[index];
        elements[index] = element;
        return oldElement;
    }

    @Override
    public int size() {

        return size;
    }

    @Override
    public int indexOf(T element) {
        for (int i = 0; i < size - 1 ; i++) {
            if (elements[i] == element) {
                return i;
            }
        }
        return -1;
    }
    @Override
    public boolean contains(T element) {
        for (int i = 0; i < size - 1 ; i++) {
            if (elements[i] == element) {
                return true;
            }
        }

        if (indexOf(element) != -1){
            return true;
        }

        return false;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0)
            return true;

        return false;
    }

    @Override
    public String toString(){
        StringBuilder results = new StringBuilder();
        results.append("[");

        for (int i = 0; i < size; i++) {
            results.append(elements[i]);
            if (i < size - 1){
                results.append(", ");
            }
        }

        results.append("]");

        return results.toString();

    }
    public void sort(Comparator<T> comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }

        // Bubble Sort implementation
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                T element1 = (T) elements[j];
                T element2 = (T) elements[j + 1];
                if (comparator.compare(element1, element2) > 0) {
                    // Swap elements
                    T temp = element1;
                    elements[j] = element2;
                    elements[j + 1] = temp;
                }
            }
        }
    }
    public static void ExecutionTimeMeasurement(Runnable task) {
        long startTime = System.nanoTime(); // Thời điểm bắt đầu thực thi
        task.run(); // Thực thi tác vụ được cung cấp
        long endTime = System.nanoTime(); // Thời điểm kết thúc thực thi

        long elapsedTimeInNano = endTime - startTime; // Thời gian thực thi trong nanosecond
        double elapsedTimeInMilli = (double) elapsedTimeInNano / 1_000_000; // Thời gian thực thi trong millisecond

        System.out.println("Execution Time in nanoseconds: " + elapsedTimeInNano + " ns");
        System.out.println("Execution Time in milliseconds: " + elapsedTimeInMilli + " ms");
    }
}


