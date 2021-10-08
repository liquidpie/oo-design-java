package com.vivek.algorithms;

public class DoublyLinkedListNode<E> {

    DoublyLinkedListNode<E> prev;
    DoublyLinkedListNode<E> next;
    E element;

    public DoublyLinkedListNode(E element) {
        this.element = element;
        this.prev = null;
        this.next = null;
    }

    public E getElement() {
        return element;
    }

}
