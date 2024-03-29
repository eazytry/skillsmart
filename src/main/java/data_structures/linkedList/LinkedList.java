package data_structures.linkedList;

import java.util.*;

public class LinkedList {
    public Node head;
    public Node tail;

    public LinkedList() {
        head = null;
        tail = null;
    }

    public void addInTail(Node item) {
        if (this.head == null)
            this.head = item;
        else
            this.tail.next = item;
        this.tail = item;
    }

    public Node find(int value) {
        Node node = this.head;
        while (node != null) {
            if (node.value == value)
                return node;
            node = node.next;
        }
        return null;
    }

    public ArrayList<Node> findAll(int _value) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        Node node = this.head;
        while (node != null) {
            if (_value == node.value)
                nodes.add(node);
            node = node.next;
        }
        return nodes;
    }

    public boolean remove(int _value) {
        if (this.head == null || this.tail == null)
            return false;

        Node curNode = this.head;
        Node prevNode = null;

        while (curNode != null) {
            if (curNode.value == _value) {
                if (this.tail == curNode) {
                    this.tail = prevNode;
                }
                if (this.head == curNode) {
                    this.head = curNode.next;
                }
                if (prevNode != null) {
                    prevNode.next = curNode.next;
                }
                return true;
            }
            prevNode = curNode;
            curNode = curNode.next;
        }
        return false;
    }

    public void removeAll(int _value) {
        while (remove(_value)) ;
        // здесь будет ваш код удаления всех узлов по заданному значению
    }

    public void clear() {
        this.head = null;
        this.tail = null;
        // здесь будет ваш код очистки всего списка
    }

    public int count() {
        int count = 0;
        Node node = this.head;
        while (node != null) {
            count++;
            node = node.next;// здесь будет ваш код подсчёта количества элементов в списке
        }
        return count;
    }

    public void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
        if (_nodeAfter == null) {
            if (count() == 0) {
                this.head = _nodeToInsert;
                this.tail = _nodeToInsert;
            } else {
                _nodeToInsert.next = this.head;
                this.head = _nodeToInsert;
            }
        } else {
            if (_nodeAfter == this.tail) {
                this.tail.next = _nodeToInsert;
                this.tail = _nodeToInsert;
            } else {
                Node node = _nodeAfter.next;
                _nodeAfter.next = _nodeToInsert;
                _nodeToInsert.next = node;
            }
        }
    }
}

class Node {
    public int value;
    public Node next;

    public Node(int _value) {
        value = _value;
        next = null;
    }
}
