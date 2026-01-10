package nl.han.ica.datastructures;

public class HANLinkedListNode<T> {
    private final T value;
    private HANLinkedListNode<T> next;

    public HANLinkedListNode(T val) {
        this.value = val;
    }

    public HANLinkedListNode<T> getNext() {
        return next;
    }

    public void setNext(HANLinkedListNode<T> node) {
        this.next = node;
    }

    public T getValue() {
        return value;
    }
}
