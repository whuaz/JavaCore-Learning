package com.whuaz.java.core.linkedlist;

/**
 * 单链表的插入、删除、查找操作
 * @author grez
 * @since 19-2-18
 **/
public class SinglyLinkedList {

    /**
     * 头节点，初始化为null
     */
    private Node head = null;

    public Node findByValue(int value) {
        Node p = head;
        while (p != null && p.data != value) {
            p = p.next;
        }
        return p;
    }

    public Node findByIndex(int index) {
        Node p = head;
        int pos = 0;
        while (p != null && pos != index) {
            p = p.next;
            ++pos;
        }
        return p;
    }

    /**
     * 无头节点
     * 从链表的头部插入
     * @param value
     */
    public void insertToHead(int value) {
        Node newNode = new Node(value, null);
        insertToHead(newNode);
    }

    public void insertToHead(Node newNode) {
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            // 将newNode设为头节点
            head = newNode;
        }
    }

    /**
     * 顺序插入
     * 从链表尾部插入
     * @param value
     */
    public void insertTail(int value) {
        Node newNode = new Node(value, null);
        // 空链表
        if (head == null) {
            head = newNode;
        } else {
            Node q = head;
            while (q.next != null) {
                q = q.next;
            }
            // 此时的q.next为null，意思为将newNode作为链表的尾节点
            newNode.next = q.next;
            // 插入链表尾部
            q.next = newNode;
        }
    }

    public void insertAfter(Node p, int value) {
        Node newNode = new Node(value, null);
        insertAfter(p, newNode);
    }

    /**
     * 插入指定节点的下一个节点
     * @param p
     * @param newNode
     */
    public void insertAfter(Node p, Node newNode) {
        if (p == null) {
            return;
        }
        newNode.next = p.next;
        p.next = newNode;
    }

    /**
     * 插入指定节点的前一个节点
     * @param p
     * @param value
     */
    public void insertBefore(Node p, int value) {
        Node newNode = new Node(value, null);
        insertBefore(p, newNode);
    }

    public void insertBefore(Node p, Node newNode) {
        if (p == null) {
            return;
        }
        if (head == p) {
            insertToHead(newNode);
            return;
        }

        Node q = head;
        while (q.next != null && q.next != p) {
            q = q.next;
        }
        if (q == null) {
            return;
        }
        newNode.next = p;
        q.next = newNode;
    }

    /**
     * 删除指定节点
     * 由于需要找到前驱节点，需从头至尾遍历，时间复杂度为O(n)
     * @param p
     */
    public void deleteByNode(Node p) {
        if (p == null || head == null) {
            return;
        }
        if (p == head) {
            head = head.next;
            return;
        }
        Node q = head;
        while (q != null && q.next != p) {
            q = q.next;
        }
        // 说明该节点不存在
        if (q == null) {
            return;
        }
        q.next = q.next.next;
    }

    /**
     * 根据给定值删除节点
     * @param value
     */
    public void deleteByValue(int value) {
        if (head == null) {
            return;
        }

        Node p = head;

        // 存储当前节点，当p.data == value时，q变为前驱节点
        Node q = null;
        while (p != null && p.data != value) {
            q = p;
            p = p.next;
        }

        if (p == null) {
            return;
        }

        // 此时q == null表示第一个元素为要删除的元素，只需将head的next改为head即可
        if (q == null) {
            head = head.next;
        } else {
            q.next = q.next.next;
        }
    }

    public void printAll() {
        Node p = head;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    public static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }

    public static void main(String[] args) {
        SinglyLinkedList link = new SinglyLinkedList();
        int data[] = {1,2,5,3,1};
        for (int i = 0; i < data.length; i++) {
//            link.insertToHead(data[i]);
            link.insertTail(data[i]);
        }
        link.printAll();

        link.deleteByValue(5);
        link.printAll();
    }
}
