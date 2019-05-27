package com.whuaz.java.core.array;

/**
 * @author grez
 * @since 19-2-20
 **/
public class Temp {

    public static void main(String[] args) {
        int[] a = {5,4,6,1,3,2};
        insertionSort(a, a.length);
    }

    /**
     * 冒泡排序，a 表示数组，n 表示数组大小
     * @param a
     * @param n
     */
    public void bubbleSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }

        for (int i = 0; i < n; ++i) {
            // 提前退出冒泡循环的标志位
            boolean flag = false;
            for (int j = 0; j < n - i - 1; ++j) {
                // 交换
                if (a[j] > a[j+1]) {
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                    // 表示有数据交换
                    flag = true;
                }
            }
            // 没有数据交换，提前退出
            if (!flag) {
                break;
            }
        }
    }


    /**
     * 插入排序，a 表示数组，n 表示数组大小
     * @param a
     * @param n
     */
    public static void insertionSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }

        for (int i = 1; i < n; ++i) {
            int value = a[i];
            int j = i - 1;
            // 查找插入的位置
            for (; j >= 0; --j) {
                if (a[j] > value) {
                    // 数据移动
                    a[j+1] = a[j];
                } else {
                    break;
                }
            }
            // 插入数据
            a[j+1] = value;
        }
    }

}
