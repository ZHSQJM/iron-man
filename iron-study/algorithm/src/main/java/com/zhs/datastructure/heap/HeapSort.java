package com.zhs.datastructure.heap;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/20 14:59
 * @package: com.zhs.datastructure.heap
 * @description:
 */
public class HeapSort<T extends Comparable<T>> {


    /**
     * 判断堆中索引i处的原属是否小于索引j处的元素
     * @param heap
     * @param i
     * @param j
     * @return
     */
    private static boolean less(Comparable[] heap,int i,int j){

        return heap[i].compareTo(heap[j])<0;
    }

    /**
     * 交换heap堆中i索引和j索引处的值
     * @param heap
     * @param i
     * @param j
     */
    private static void exch(Comparable[] heap,int i,int j){
        final Comparable temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    /**
     * 构建堆
     * @param source
     * @param heap
     */
    private static void  createHeap(Comparable[] source,Comparable[] heap){

        //把数组source的元素copy到heap中。heap中的元素就形成了一个无序的堆
        System.arraycopy(source,0,heap,1,source.length);
        //对堆中的元素做下沉调整（从长度的一半处开始往索引1处扫描）
        /**
         * 为什么从堆长度的一半处开始调整 因为一般处的长度才是有左右节点的根节点
         * 举个例之  1 2 3 4 5 6 7 8 9 10 11 12 从第6个开始调整
         *           1
         *         /   \
         *        3     4
         *       / \    / \
         *      5   6   7  8
         *     / \ / \
         *    10 1112
         *    此时从第6开始下称  这样可与即将每一个数据都能够下称
         */
        for (int i=(heap.length/2);i>0;i--){
            sink(heap,i,heap.length-1);
        }
    }


    /**
     * 排序
     * @param source
     */
    public static void sort(Comparable[] source){

        /**
         * //堆排序的过程
         * 将第一个值放入到数组的最后一个之中，将最后一个值放入堆中
         * 将第一个值进行下沉操作，但是已经在最大值的最后一个数不参与下沉
         */
        Comparable[] heap = new Comparable[source.length+1];
       createHeap(source,heap);
//        for (int i = 1;i<heap.length;i++){
//            heap[heap.length-i] = heap[1];
//            sink(heap,1,heap.length-i);
//        }

        //定义一个变量 记录未排序的元素中最大的索引
        //通过循环，交换1索引的处的元素和排序的元素中最大的索引处的元素
        //排序交换后最大元素所在的索引，让他不参与堆的下沉调整
        //需要对索引1处的元素进行下沉
        int N =heap.length-1;
        while(N!=1){
            exch(heap,1,N);
            N--;
            sink(heap,1,N);
        }
        //把heap中的数据赋值到原数组中
        System.arraycopy(heap,1,source,0,source.length);
    }

    private static  void sink(Comparable[] heap,int target,int range){
         while(2*target<=range){
             //找出当前结点的较大的子节点
              int max;
              if(2*target+1<=range){
                  if(less(heap,2*target,2*target+1)){
                      max = 2*target+1;
                  }else{
                      max = 2*target;
                  }
              }else {
                  max = 2*target;
              }
             //比较当前结点的值与较大结点的值
              if(!less(heap,target,max)){
                  break;
              }
              exch(heap,target,max);
              target=max;


         }
    }
}
