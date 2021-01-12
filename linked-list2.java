import java.util.*;

public class LinkedList2 {
    public Node head;
    public Node tail;

    public LinkedList2() {
        head = null;
        tail = null;
    }

    public void addInTail(Node _item) {
        if (head == null) {
            this.head = _item;
            this.head.next = null;
            this.head.prev = null;
        } else {
            this.tail.next = _item;
            _item.prev = tail;
        }
        this.tail = _item;
    }

    public Node find(int _value) {
        if (count() == 0)
            return null;
        Node curNode = this.head;
        while (curNode != null) {
            if (curNode.value == _value)
                return curNode;
            curNode = curNode.next;
        }
        return null;
    }

    public ArrayList<Node> findAll(int _value) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        if (count() == 0)
            return nodes;
        Node curNode = this.head;
        while (curNode != null) {
            if (curNode.value == _value)
                nodes.add(curNode);
            curNode = curNode.next;
        }
        // здесь будет ваш код поиска всех узлов по заданному значению
        return nodes;
    }

    public boolean remove(int _value) {
        if (count() == 0)
            return false;
        Node foundNode = find(_value);
        if (foundNode == null)
            return false;
        if (foundNode.prev != null)
            foundNode.prev.next = foundNode.next;
        else
            this.head = foundNode.next;
        if (foundNode.next != null)
            foundNode.next.prev = foundNode.prev;
        else
            this.tail = foundNode.prev;
        return true; // если узел был удалён
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
        if (this.head == null)
            return 0;
        Node node = this.head;
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }
        return count; // здесь будет ваш код подсчёта количества элементов в списке
    }

    public void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
        // здесь будет ваш код вставки узла после заданного узла
        if (_nodeAfter == null) {
            if (count() == 0) {
                this.head = _nodeToInsert;
                this.tail = _nodeToInsert;
            }
            this.head.prev = _nodeToInsert;
            _nodeToInsert.next = this.head;
            this.head = _nodeToInsert;
        } else {
            if (_nodeAfter.next != null) {
                _nodeAfter.next.prev = _nodeToInsert;
            }
            _nodeToInsert.next = _nodeAfter.next;
            _nodeToInsert.prev = _nodeAfter;
            _nodeAfter.next = _nodeToInsert;
            // если _nodeAfter = null
            // добавьте новый элемент первым в списке
        }
    }
}

class Node {
    public int value;
    public Node next;
    public Node prev;

    public Node(int _value) {
        value = _value;
        next = null;
        prev = null;
    }
}
