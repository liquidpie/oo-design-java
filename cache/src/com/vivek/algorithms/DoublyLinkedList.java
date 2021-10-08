package com.vivek.algorithms;

import com.vivek.algorithms.exceptions.InvalidElementException;
import com.vivek.algorithms.exceptions.NoSuchElementException;

public class DoublyLinkedList<E> {

    DoublyLinkedListNode<E> head;
    DoublyLinkedListNode<E> tail;

    public DoublyLinkedList() {
        head = new DoublyLinkedListNode<>(null);
        tail = new DoublyLinkedListNode<>(null);
        head.next = tail;
        tail.prev = head;
    }

    /**
     * Method to detach a random node from the doubly linked list. The node itself will not be removed from the memory.
     * Just that it will be removed from the list and becomes orphaned.
     */
    public void detachNode(DoublyLinkedListNode<E> node) {
        if (node != null) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    /**
     * Helper method to add a node at the end of the list.
     */
    public void addNodeAtLast(DoublyLinkedListNode<E> node) {
        var tailPrev = tail.prev;
        tailPrev.next = node;
        node.next = tail;
        tail.prev = node;
        node.prev = tailPrev;
    }

    /**
     * Helper method to add an element at the end.
     */
    public DoublyLinkedListNode<E> addElementAtLast(E element) {
        if (element == null) {
            throw new InvalidElementException();
        }
        DoublyLinkedListNode<E> newNode = new DoublyLinkedListNode<>(element);
        addNodeAtLast(newNode);
        return newNode;
    }

    public boolean isItemPresent() {
        return head.next != tail;
    }

    public DoublyLinkedListNode<E> getFirstNode() throws NoSuchElementException {
        DoublyLinkedListNode<E> item = null;
        if (!isItemPresent()) {
            return null;
        }
        return head.next;
    }

    public DoublyLinkedListNode<E> getLastNode() throws NoSuchElementException {
        DoublyLinkedListNode<E> item = null;
        if (!isItemPresent()) {
            return null;
        }
        return tail.prev;
    }

}
