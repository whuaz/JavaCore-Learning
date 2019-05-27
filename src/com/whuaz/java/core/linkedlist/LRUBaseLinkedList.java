package com.whuaz.java.core.linkedlist;

/**
 * 基于单链表实现的LRU算法
 * @author grez
 * @since 19-2-19
 **/
public class LRUBaseLinkedList<T> {

    /**
     * 默认容量
     */
    private final static int DEFAULT_CAPACITY = 10;

    /**
     * 头结点
     */
    private Node<T> head;

    /**
     * 链表长度
     */
    private Integer length;

    /**
     * 链表容量
     */
    private Integer capacity;

    public LRUBaseLinkedList() {
        this.head = new Node<>();
        this.capacity = DEFAULT_CAPACITY;
        this.length = 0;
    }

    public LRUBaseLinkedList(Integer capacity) {
        this.head = new Node<>();
        this.capacity = capacity;
        this.length = 0;
    }

    public void add(T data) {
        Node preNode = findPreNode(data);

        // 链表中存在，则删除原来指针，将指针指向链表头部
        if (preNode != null) {
            // TODO 删除
            deleteElementOptim(preNode);
            // TODO 插入链头

        }
    }

    /**
     * 删除preNode节点的下一个节点
     * @param preNode
     */
    private void deleteElementOptim(Node preNode) {
        Node temp = preNode.getNext();
        preNode.setNext(temp.getNext());
        temp = null;
        length--;
    }

    /**
     * 插入元素至链表头部
     * @param data
     */
    private void insertElementAtBegin(T data) {
        Node next = head.getNext();
        head.setNext(new Node(data, next));
        length++;
    }


    /**
     * 获取查找元素的前一个节点
     * @param data
     * @return
     */
    public Node findPreNode(T data) {

        Node node = head;

        while (node.next != null) {
            if (data.equals(node.getNext().getElement())) {
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    public class Node<T> {

        private T element;

        private Node next;

        public Node(T element) {
            this.element = element;
        }

        public Node(T element, Node next) {
            this.element = element;
            this.next = next;
        }

        public Node() {
            this.next = null;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
