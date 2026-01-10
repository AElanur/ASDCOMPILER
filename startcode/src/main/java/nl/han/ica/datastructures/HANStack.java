package nl.han.ica.datastructures;

public class HANStack<T> implements IHANStack<T> {

    IHANLinkedList<T> list;

    @Override
    public void push(T value) {
        list = new HANLinkedList<>();
    }

    @Override
    public T pop() {
        T temp = list.getFirst();
        list.removeFirst();
        return temp;
    }

    @Override
    public T peek() {
        return list.getFirst();
    }
}
