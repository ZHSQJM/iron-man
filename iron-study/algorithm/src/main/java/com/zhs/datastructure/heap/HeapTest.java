package com.zhs.datastructure.heap;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/20 11:23
 * @package: com.zhs.datastructure.heap
 * @description:
 */
public class HeapTest {

    public static void main(String[] args) {

        //创建堆对象
//        Heap<String> heap = new Heap<>(10);
//        heap.insert("A");
//        heap.insert("B");
//        heap.insert("C");
//        heap.insert("D");
//        heap.insert("E");
//        heap.insert("F");
//        heap.insert("G");
//
//        //往堆中存入字符串数据
//        String result = null;
//        //通过循环从推涨删除数据
//        while((result= heap.delMax())!=null){
//            System.out.print(result+"==");
//        }


        Integer[] source = new Integer[]{1, 2, 3, 4, 5, 2, 7, 11, 9, 14,0} ;
        HeapSort.sort(source);
        for (Integer integer : source) {
            System.out.println(integer);
        }
    }
}
