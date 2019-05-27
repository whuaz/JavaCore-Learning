package com.whuaz.java.core.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于数组实现的LRU缓存
 * 空间复杂度O(n)
 * 时间复杂度O(n)
 * 不支持null
 * @author grez
 * @since 19-2-19
 **/
public class LRUBasedArray<T> {

    private static final int DEFAULT_CAPACITY = 1 << 3;

    private int capacity;

    private int count;

    private T[] value;

    private Map<T, Integer> holder;

    public LRUBasedArray() {
        this(DEFAULT_CAPACITY);
    }

    public LRUBasedArray(int capacity) {
        this.capacity = capacity;
        value = (T[]) new Object[capacity];
        count = 0;
        holder = new HashMap<>(capacity);
    }

    /**
     * 模拟访问某个值
     * @param object
     */
    public void offer(T object) {
        if (object == null) {
            throw new IllegalArgumentException("value must be not null");
        }
        Integer index = holder.get(object);
        // 该值不存在缓存中
        if (index == null) {
            if (isFull()) {
                // removeAndCache object
                removeAndCache(object);
            } else {
                // cache object
                cache(object, count);
            }
        } else {
            // update object to cache
            update(count);
        }
    }

    /**
     * 若缓存中存在，则更新位置
     * 将end左边数据右移一位
     * 将该值移动至数组头部
     * @param end
     */
    public void update(int end) {
        T target = value[end];
        rightShift(end);
        value[0] = target;
        holder.put(target, 0);
    }

    /**
     * 将数组元素整体往右移一位
     * 再缓存数据到头部
     * @param object
     * @param end
     */
    public void cache(T object, int end) {
        // right shift
        rightShift(end);
        value[0] = object;
        holder.put(object, 0);
        count++;
    }

    /**
     * end 左边的数据往右移一位
     * @param end
     */
    private void rightShift(int end) {
        if (end == 0) {
            return;
        }
        if (isFull()) {
            throw new RuntimeException("缓存已满");
        }
        for (int i = end - 1; i >= 0; i--) {
            value[i + 1] = value[i];
            holder.put(value[i], i + 1);
        }
    }

    /**
     * 判断key是否存在
     * @param object
     * @return
     */
    public boolean isContain(T object) {
        return holder.containsKey(object);
    }

    /**
     * 缓存满的情况，移除后，缓存object至数组头部
     * @param object
     */
    public void removeAndCache(T object) {
        T key = value[--count];
        holder.remove(key);
        // cache object
        cache(object, count);
    }

    private boolean isFull() {
        return count == capacity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(value[i]);
            sb.append(" ");
        }
        return sb.toString();
    }

    static class TestLRUBasedArray {

        public static void main(String[] args) {
//            testWithException();
//            testDefaultConstructor();
        }

        private static void testWithException() {
            LRUBasedArray<Integer> lru = new LRUBasedArray<>();
            lru.offer(null);
        }

        public static void testDefaultConstructor() {
            System.out.println("=======测试无参构造======");
            LRUBasedArray<Integer> lru = new LRUBasedArray<>();
            lru.offer(1);
            lru.offer(2);
            lru.offer(3);
            lru.offer(4);
            lru.offer(5);
            System.out.println(lru);
            lru.offer(6);
            lru.offer(7);
            lru.offer(8);
            lru.offer(9);
            System.out.println(lru);
        }

        public static void testSpecifiedConstructor(int capacity) {
            System.out.println("======有参测试========");
            LRUBasedArray<Integer> lru = new LRUBasedArray<>(capacity);
            lru.offer(1);
            System.out.println(lru);
            lru.offer(2);
            System.out.println(lru);
            lru.offer(3);
            System.out.println(lru);
            lru.offer(4);
            System.out.println(lru);
            lru.offer(2);
            System.out.println(lru);
            lru.offer(4);
            System.out.println(lru);
            lru.offer(7);
            System.out.println(lru);
            lru.offer(1);
            System.out.println(lru);
            lru.offer(2);
            System.out.println(lru);
        }

    }
}
