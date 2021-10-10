package com.vivek.map.navigator.model;

public class PathNode {

    private final Location location;
    private PathNode prev;
    private PathNode next;

    public PathNode(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public PathNode getPrev() {
        return prev;
    }

    public void setPrev(PathNode prev) {
        this.prev = prev;
    }

    public PathNode getNext() {
        return next;
    }

    public void setNext(PathNode next) {
        this.next = next;
    }
}
