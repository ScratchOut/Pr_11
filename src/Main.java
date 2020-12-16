import java.util.Arrays;
import java.util.LinkedList;

interface Queue {
    void enqueue(Object object);
    Object element();
    Object dequeue();
    int size();
    boolean isEmpty();
    void clear();
}

class ArrayQueueModule {
    private int size;
    private int[] elements;
}

class ArrayQueueADT {
    private int size = 0;
    private Object[] elements = new Object[1];

    private static void ensureCapacity(ArrayQueueADT arrayQueueADT, int capacity, int mod) {
        if (capacity > arrayQueueADT.elements.length && mod == 1) {
            arrayQueueADT.elements = Arrays.copyOf(arrayQueueADT.elements, capacity);
        }
        if (mod == 2) {
            arrayQueueADT.elements = Arrays.copyOf(arrayQueueADT.elements, capacity);
        }
    }

    public static void enqueue(ArrayQueueADT arrayQueueADT, Object object) {
        ensureCapacity(arrayQueueADT, arrayQueueADT.size + 1, 1);
        if (arrayQueueADT.size >= 0) {
            System.arraycopy(arrayQueueADT.elements, 0, arrayQueueADT.elements, 1, arrayQueueADT.size);
        }
        arrayQueueADT.elements[0] = object;
        arrayQueueADT.size++;
    }

    public static Object element(ArrayQueueADT arrayQueueADT) {
        if (arrayQueueADT.size != 0) {
            return arrayQueueADT.elements[arrayQueueADT.size - 1];
        }
        else return null;
    }

    public static Object dequeue(ArrayQueueADT arrayQueueADT) {
        if (arrayQueueADT.size == 0) {
            return null;
        }
        Object obj = arrayQueueADT.elements[arrayQueueADT.size - 1];
        ensureCapacity(arrayQueueADT, arrayQueueADT.size--, 2);
        return obj;
    }

    public static int size(ArrayQueueADT arrayQueueADT) {
        return arrayQueueADT.size;
    }

    public static boolean isEmpty(ArrayQueueADT arrayQueueADT) {
        return arrayQueueADT.size == 0;
    }

    public static void clear(ArrayQueueADT arrayQueueADT) {
        while (arrayQueueADT.size != 0) {
            dequeue(arrayQueueADT);
        }
    }

    public static String toString(ArrayQueueADT arrayQueueADT) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < arrayQueueADT.size; i++) {
            res.append(arrayQueueADT.elements[i]).append(" ");
        }
        return res.toString();
    }
}

class ArrayQueue extends AbstractQueue{
    private int size = 0;
    private Object[] elements = new Object[1];

    private void ensureCapacity(int capacity,int mod) {
        if (capacity > elements.length&&mod==1) {
            elements = Arrays.copyOf(elements, capacity);
        }
        if (mod == 2) {
            elements = Arrays.copyOf(elements, capacity);
        }
    }

    @Override
    public void enqueue(Object object) {
        ensureCapacity(size + 1,1);
        if (size >= 0) {
            System.arraycopy(elements, 0, elements, 1, size);
        }
        elements[0] = object;
        size++;
    }

    @Override
    public Object element() {
        if (size != 0) {
            return elements[size - 1];
        }
        else return null;
    }

    @Override
    public Object dequeue() {
        if (size == 0) {
            return null;
        }
        Object obj = elements[size - 1];
        ensureCapacity(size--,2);
        return obj;
    }

    public String toString(){
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < size; i++) {
            res.append(elements[i]).append(" ");
        }
        return res + "Size: " + size;
    }
}

abstract class AbstractQueue implements Queue {
    int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        while (size != 0) dequeue();
    }
}

class LinkedQueue extends AbstractQueue {
    private LinkedList<Object> elements = new LinkedList<>();

    @Override
    public void enqueue(Object object) {
        elements.addFirst(object);
    }

    @Override
    public Object element() {
        return elements.peekLast();
    }

    @Override
    public Object dequeue() {
        return elements.removeLast();
    }

}

public class Main {
    public static void main(String[] args){
        ArrayQueue arrayQueue = new ArrayQueue();
        for (int i = 1; i <= 10; i++) {
            arrayQueue.enqueue(i);
        }
        System.out.print("Queue: ");
        for (int i = 1; i <= 10; i++) {
            System.out.print(arrayQueue.dequeue() + " ");
        }

        System.out.println();

        ArrayQueueADT arrayQueueADT = new ArrayQueueADT();
        for (int i = 0; i <= 10; i++) {
            ArrayQueueADT.enqueue(arrayQueueADT, i);
        }
        System.out.print("Dequeue: ");
        for (int i = 0; i <= 10; i++) {
            System.out.print(ArrayQueueADT.dequeue(arrayQueueADT) + " ");
        }

        System.out.println();

        LinkedQueue linkedQueue = new LinkedQueue();
        for (int i = 0; i <= 10; i++) {
            linkedQueue.enqueue(i);
        }
        System.out.print("LinkedQueue: ");
        for (int i = 0; i <= 10; i++) {
            System.out.print(linkedQueue.dequeue() + " ");
        }
    }
}
