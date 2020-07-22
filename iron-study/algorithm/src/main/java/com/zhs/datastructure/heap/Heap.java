package com.zhs.datastructure.heap;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/20 10:13
 * @package: com.zhs.datastructure.heap
 * @description: 堆
 */
public class Heap<T extends Comparable<T>> {

    private T[] items;

    private int N;

    public  Heap(int capacity){
        items = (T[]) new Comparable[capacity+1];
        N=0;
    }

    /**
     * 比较i位置的数是否小于j位置的数
     * @param i
     * @param j
     * @return
     */
    private boolean less(int i,int j){
        return items[i].compareTo(items[j])<0;
    }

    /**
     * 交换位置
     * @param i
     * @param j
     */
    private void  exch(int i,int j){
        T temp = items[i];
        items[i] = items[j];
        items[j]= temp;
    }

    /**
     * 插入一盒元素
     * @param t
     */
    public void insert(T t ){
        items[++N] = t;
        swim(N);
    }

    /**
     * 使用上浮算法，使索引k处的元素能在堆中处于一个正确的位置
     * @param k
     */
    public void swim(int k){
        //当k结点位置的的值小于他的父结点的值 循环结束
        while(k>1) {
            if(less(k/2,k)){
                exch(k/2, k);

            }
            k = k / 2;
        }
    }

    /**
     * 删除堆中最大元民兵返回这个元素
     */
    public T delMax(){
        final T item = items[1];
        //交换最大结点与最后一个结点的位置
        exch(1,N);
        items[N] = null;
        N--;
        //最大索引处删除掉
        //元素个数减一
        //下沉
        slink(1);
        return  item;
    }

    /**
     * 使用下沉算法 使索引k处的原属能在堆中处于一个正确的位置
     * @param k
     */
    public void slink(int k){
//        System.out.println("k"+k);
//        //获取k结点位置的左右节点的最大值  如果k结点的值小于左右节点的最大值，则k结点位置与最大致的位置交换
//        int max=0;
//        if(N>=2*k+1){
//            max = less(2*k,2*k+1)?2*k+1:2*k;
//        }else{
//            max = 2*k;
//        }
//        while(less(k,max)){
//            exch(k,max);
//            k = max;
//            if(N>2*k){
//                max = less(2*k,2*k+1)?2*k+1:2*k;
//            }else if(N==2*k){
//                max = 2*k;
//            }else{
//                max = k;
//            }
//
//        }

        while (2*k<=N){
            //获取当前结点的自己点中的较大结点
            int max ;
            if(2*k+1<+N){
                if(less(2*k,2*k+1)){
                    max =2*k+1;
                }else{
                    max = 2*k;
                }
            }else {
                max = 2*k;
            }
            //比较当前结点和较大结点的值
            if(!less(k,max)){
                break;
            }
            //交换k素银的值和max索引的值
            exch(k,max);
            k=max;
        }
    }
}
