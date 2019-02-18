package com.whuaz.java.core.array;

/**
 * 实现一个大小固定的有序数组，支持动态增删改操作
 * 1) 数组的插入、删除、按照下标随机访问操作；
 * 2) 数组中的数据是int类型的；
 */
public class ArrayTest {

    public static void main(String[] args) {
        ArrayTest array = new ArrayTest(5);
        array.insert(0, 3);
        array.insert(0, 4);
        array.insert(1, 5);
        array.insert(3, 9);
        array.insert(3, 10);
        array.printAll();


        GenericArray<Integer> genericArray = new GenericArray<>(5);
        genericArray.add(0, 10);
        genericArray.add(1, 100);
        System.out.println(genericArray.count());
        System.out.println(genericArray.getCapacity());
//        System.out.println(genericArray.remove(0));
        System.out.println(genericArray.toString());
    }

    // 定义整形数据data保存数据
    public int data[];

    // 定义数组长度
    public int n;

    // 定义数组中实际个数
    private int count;

    public ArrayTest(int capacity) {
        this.data = new int[capacity];
        this.n = capacity;
        // 初始化数据个数为0
        this.count = 0;
    }

    // 根据索引找到数据中的元素并返回
    public int find(int index) {
        if (index < 0 || index >= count) {
            return -1;
        }
        return data[index];
    }

    // 插入元素：头部插入、尾部插入
    public boolean insert(int index, int value) {
        // 提前考虑边界条件，尽可能避免程序bug
        // 数组中无元素

        // 数组空间已满
        if (count == n) {
//            throw new IllegalStateException("没有可插入的位置，空间已满");
            System.out.println("没有可插入的位置，空间已满");
            return false;
        }

        // 位置不合法
        if (index < 0 || index > count) {
//            throw new ArrayIndexOutOfBoundsException("位置不合法");
            System.out.println("位置不合法");
            return false;
        }

        // 位置合法,当index 小于 count时，表示从头部插入，此时将index后面所有元素往后移一位
        for (int i = count; i > index; --i) {
            data[i] = data[i - 1];
        }
        data[index] = value;
        ++count;
        return true;
    }

    /**
     * 根据索引删除数组中的元素
     * @param index
     * @return
     */
    public boolean delete(int index) {
        if (index < 0 || index >= count) {
            return false;
        }

        // 从删除的位置开始，将后面的元素向前移动一位
        for (int i = index + 1; i < count; ++i) {
            data[i - 1] = data[i];
        }

        --count;
        return true;
    }
    /**
     * 会出现ArrayIndexOutOfBoundsException
     * 在C语言中会无限循环，在 C 语言中，只要不是访问受限的内存，所有的内存空间都是可以自由访问的。
     * a[3] 也会被定位到某块不属于数组的内存地址上，而这个地址正好是存储变量 i 的内存地址，那么 a[3]=0 就相当于 i=0，所以就会导致代码无限循环
     */
    private void ArrayIndexOutOfBoundsExceptionTest() {
        int i = 0;
        int[] arr = {3};
        for (; i <= 3; i++) {
            arr[i] = 0;
            System.out.println("hello world\n");
        }
        return;
    }

    public void printAll() {
        for (int i = 0; i < count; ++i) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }
}
