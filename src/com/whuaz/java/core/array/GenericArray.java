package com.whuaz.java.core.array;

/**
 * learning from geek time
 * 实现一个支持动态扩容的数组
 * @param <T>
 */
public class GenericArray<T> {

    private T[] data;

    private int size;

    // 根据传入容量，构造数组

    public GenericArray(int capacity) {
        data = (T[]) new Object[capacity];
        size = 0;
    }

    // 无参构造方法，默认数组容量为10
    public GenericArray() {
        this(10);
    }

    // 获取数组容量
    public int getCapacity() {
        return data.length;
    }

    // 获取当前元素个数
    public int count() {
        return size;
    }

    // 判断数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 修改index位置的元素
    public void set(int index, T e) {
        // TODO 检查index位置是否合法
        checkIndex(index);
        data[index] = e;
    }

    // 获取对应index位置的元素
    public T get(int index) {
        checkIndex(index);
        return data[index];
    }

    // 查看数组是否包含元素e
    public boolean contains(T e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    // 获取对应元素的下标，未找到，返回-1
    public int find(T e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed! Require index >=0 and index <= size.");
        }
    }


}
