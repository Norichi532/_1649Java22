package BookStore;

import java.util.NoSuchElementException;

public class OrderQueueADT <T> implements AbtractQueueADT {
    /*public void addAll(ArrayList<T> customerOrders) {
    }*/

    private class Node<T>{
        private T element;
        private Node<T> next;
        public Node(T element){
            this.element = element;
            this.next = null;
        }
    }

    private Node<T> head;
    private int size;

    public OrderQueueADT(){
        this.head = null;
        this.size = 0;
    }


    @Override
    public void offer(Object element) {
        Node<T> newNode = (Node<T>) new Node<>(element);

        if(isEmpty()){
            head = newNode;
        } else {
            Node<T> tempNode = head;
            while(tempNode.next != null){
                tempNode = tempNode.next;
            }
            tempNode.next = newNode;
        }
        this.size++;
    }

    @Override
    public T poll() {
        if(isEmpty()){
            throw new NoSuchElementException("Queue is currently empty.");
        }

        T oldNodeValue = this.head.element;

        if(this.size == 1){
            head = null;
        } else {
            Node<T> tempNode = head.next;
            head.next = null;
            head = tempNode;
        }

        this.size--;

        return oldNodeValue;
    }

    @Override
    public T peek() {
        if(isEmpty()){
            throw new NoSuchElementException("Queue is currently empty.");
        }

        return head.element;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public String toString(){
        // [30, 20, 10]
        StringBuilder results = new StringBuilder();
        results.append("[");

        Node<T> tempNode = head;

        while(tempNode != null){
            results.append(tempNode.element);
            if(tempNode.next != null){
                results.append(", ");
            }
            tempNode = tempNode.next;
        }

        results.append("]");

        return results.toString();
    }
}
