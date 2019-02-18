package com.whuaz.java.core.array;

/**
 * learning from geek time
 * 实现一个支持动态扩容的数组
 * @param <T>
 */
public class GenericArray<T> {

    private T[] data;

    private int size;

    /**
     * 根据传入容量，构造数组
     * @param capacity
     */
    public GenericArray(int capacity) {
        data = (T[]) new Object[capacity];
        size = 0;
    }

    /**
     * 无参构造方法，默认数组容量为10
     */
    public GenericArray() {
        this(10);
    }

    /**
     * 获取数组容量
     * @return
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 获取当前元素个数
     * @return
     */
    public int count() {
        return size;
    }

    /**
     * 判断数组是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 修改index位置的元素
     * @param index
     * @param e
     */
    public void set(int index, T e) {
        // TODO 检查index位置是否合法
        checkIndex(index);
        data[index] = e;
    }

    /**
     * 获取对应index位置的元素
     * @param index
     * @return
     */
    public T get(int index) {
        checkIndex(index);
        return data[index];
    }

    /**
     * 查看数组是否包含元素e
     * @param e
     * @return
     */
    public boolean contains(T e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取对应元素的下标，未找到，返回-1
     */
    public int find(T e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 在 index 位置，插入元素e, 时间复杂度 O(m+n)
     * @param index
     * @param e
     */
    public void add(int index, T e) {
        checkIndex(index);
        // 当前元素个数等于数组容量时,进行扩容,扩容至原来的2倍
        if (size == data.length) {
            // TODO 扩容操作
            resize(2 * data.length);
        }

        for (int i = size - 1; i >= index; i--) {
            // index之后所有元素往后移一位
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * 向数组头部插入元素
     * @param e
     */
    public void addFirst(T e) {
        add(0, e);
    }

    /**
     * 向数组尾部插入元素
     * @param e
     */
    public void addLast(T e) {
        add(size, e);
    }

    /**
     * 删除index位置的元素,并返回
     * @param index
     * @return
     */
    public T remove(int index) {
        checkIndexForRemove(index);
        T ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null;

        // 缩容
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }

        return ret;
    }

    /**
     * 删除第一个元素
     * @return
     */
    public T removeFirst() {
        return remove(0);
    }

    /**
     * 删除最后一个元素
     * @return
     */
    public T removeLast() {
        return remove(size - 1);
    }

    /**
     * 删除指定元素
     * @param e
     * @return
     */
    public void removeElement(T e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    /**
     * 数组扩容,时间复杂度为O(n)
     * @param capacity
     */
    private void resize(int capacity) {
        T[] newData = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed! Require index >=0 and index <= size.");
        }
    }

    private void checkIndexForRemove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("remove failed! Require index >=0 and index < size.");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Array size = %d, capacity = %d \n", size, data.length));
        sb.append('[');
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }
}
