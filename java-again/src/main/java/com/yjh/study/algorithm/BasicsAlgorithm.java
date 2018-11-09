package com.yjh.study.algorithm;

import org.junit.Test;

import java.util.Arrays;

public class BasicsAlgorithm {

    int[] arr = {3, 6, 9, 1, 0, 8, 4, 2, 5, 7, 8, 8};

    /**
     * 测试选择排序
     */
    @Test
    public void testSelectionSort() {
        SelectionSort.sort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }

    /**
     * 测试冒泡排序
     */
    @Test
    public void testBubbleSort() {
        BubbleSort.sort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }

    /**
     * 测试插入排序
     */
    @Test
    public void testInsertionSort() {
        InsertionSort.sort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }

    /**
     * 测试归并排序
     */
    @Test
    public void testMergeSort() {
        MergeSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testQuickSort() {
        QuickSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    static void exchange(int[] arr, int indexA, int indexB) {
        int p = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = p;
    }


    /**
     * 插入排序
     */
    static class InsertionSort {

        static void sort(int[] arr) {

            for (int i = 0; i < arr.length; i++) {
                int rightPoint = findPoint(arr, i);
                if (i != rightPoint) {
                    move(arr, rightPoint, i);

                }
            }

        }

        static int findPoint(int[] arr, int currentPoint) {
            if (0 == currentPoint) {
                return 0;
            }

            int p = arr[currentPoint], rightPoint = 0;

            for (int i = 0; i < currentPoint; i++) {
                if (arr[i] <= p) {
                    if (p <= arr[i + 1]) {
                        rightPoint = i + 1;
                        break;
                    }
                } else {
                    if (arr[i] > p) {
                        rightPoint = i;
                        break;
                    }
                }
            }
            return rightPoint;
        }

        static void move(int[] arr, int begin, int end) {
            if (0 == end) {
                return;
            }
            int p = arr[end];

            while (begin != end) {
                arr[end] = arr[end - 1];
                end--;
            }

            arr[begin] = p;
        }
    }


    /**
     * 冒泡排序
     */
    static class BubbleSort {

        static void sort(int[] arr) {
            for (int i = 0; i < arr.length - 1; i++) {
                for (int j = 0; j < arr.length - 1 - i; j++) {
                    if (arr[j] > arr[j + 1]) {
                        exchange(arr, j, j + 1);
                    }

                }
            }
        }
    }

    /**
     * 选择排序
     */
    static class SelectionSort {

        static void sort(int[] arr) {
            int maxIndex = arr.length - 1, pIndex;
            while (maxIndex != 0) {
                pIndex = findMax(arr, 0, maxIndex);
                exchange(arr, pIndex, maxIndex);
                maxIndex--;
            }

        }

        static int findMax(int[] arr, int begin, int end) {
            int p = arr[begin], pIndex = begin;
            for (int i = begin + 1; i <= end; i++) {
                if (p < arr[i]) {
                    p = arr[i];
                    pIndex = i;
                }
            }
            return pIndex;
        }
    }

    /**
     * 归并排序
     */
    static class MergeSort {

        static void sort(int[] arr) {

            // 建立一个与原数据同长度的临时数据，避免后面频繁开辟空间
            int[] temp = new int[arr.length];
            sort(arr, 0, arr.length - 1, temp);
        }

        private static void sort(int[] arr, int left, int right, int[] temp) {

            if (left < right) {
                int mid = (left + right) / 2;
                //左归并
                sort(arr, left, mid, temp);
                //右归并
                sort(arr, mid + 1, right, temp);
                //将两个数组合并
                merge(arr, left, mid, right, temp);
            }
        }

        private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
            // 左序列指针
            int i = left;
            // 右序列指针
            int j = mid + 1;
            // 临时序列指针
            int t = 0;

            while (i <= mid && j <= right) {

                if (arr[i] <= arr[j]) {
                    temp[t++] = arr[i++];
                } else {
                    temp[t++] = arr[j++];
                }
            }

            // 将左序列不用再排的数据归置到临时数组
            while (i <= mid) {
                temp[t++] = arr[i++];
            }

            // 将右序列不用再排的数据归置到临时数组
            while (j <= right) {
                temp[t++] = arr[j++];
            }

            // 临时数据指针归0，准备拷贝数据
            t = 0;
            while (left <= right) {
                arr[left++] = temp[t++];
            }
        }
    }

    /**
     * 快速排序
     */
    static class QuickSort {

        static void sort(int[] arr) {
            sort(arr, 0, arr.length - 1);
        }

        private static void sort(int[] arr, int left, int right) {

            // 左边的指针
            int i = left + 1;
            // 右加的指针
            int j = right;
            // 使用第一个元素作用哨兵
            int p = left;

            if(right-left<=0){
                return;
            }

            do {

                while (i <= j) {
                    if (arr[p] > arr[j]) {
                        exchange(arr, p, j);
                        p = j--;
                        break;
                    } else {
                        j--;
                    }
                }

                while (i <= j) {
                    if (arr[p] <= arr[i]) {
                        exchange(arr, p, i);
                        p = i++;
                        break;
                    } else {
                        i++;
                    }
                }

            } while (i <= j);

            sort(arr, left, p-1);
            sort(arr, p+1, right);

        }


    }

}


