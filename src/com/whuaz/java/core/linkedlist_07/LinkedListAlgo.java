package com.whuaz.java.core.linkedlist_07;

/**
 * @author whuaz
 * 1)单链表反转
 * 2)
 */
public class LinkedListAlgo {

    /**
     * 单链表反转
     * @param head
     * @return
     */
    public static Node reverse(Node head) {

        Node pre = null;

        Node next = null;

        while (head != null) {
            next = head.next;

            head.next = pre;

            pre = head;

            head = next;
        }
        return pre;
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

    private static void printAll(Node node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node node = new Node(1,new Node(2,new Node(3, new Node(4, null))));
        printAll(node);
        Node reverse = reverse(node);
        printAll(reverse);
    }
}
