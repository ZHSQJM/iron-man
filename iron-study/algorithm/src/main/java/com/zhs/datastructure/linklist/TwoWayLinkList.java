package com.zhs.datastructure.linklist;


/**
 * @project: data-structure
 * @author: zhs
 * @date: 2020/6/30 19:46
 * @package: com.zhs.linkenlist
 * @description:  双向链表
 */
public class TwoWayLinkList<T> {


     /**前置节点*/
     private Node headNode;

     /**后置节点*/
     private Node lastNode;

     private int N;

     public TwoWayLinkList(){
         /**
          *
          *   preNode = null;
          *          lastNode = null;
          *          preNode.nextNode = lastNode;
          *          lastNode.preNode=preNode;
          */
         this.headNode = new Node(null,null,null);
         this.lastNode = null;
       //  this.lastNode = new Node(null,null,null);
         headNode.nextNode = lastNode;
         lastNode.preNode=headNode;
         this.N=0;
     }


     public void clear(){
         this.headNode.nextNode = null;
         this.lastNode = null;
//         headNode.nextNode =  lastNode;
//         lastNode.preNode = headNode;
         N=0;
     }


    public T get(int i){
         Node currentNode = headNode.nextNode;
        for(int index=0;index<i;index++){
            currentNode = currentNode.nextNode;
        }
        return currentNode.item;
    }

    public  void insert(T t){

         if(isEmpty()){
             Node newNode = new Node(t,headNode,null);
             headNode.nextNode = newNode;
             lastNode = newNode;
         }else {
             Node newNode = new Node(t,lastNode,null);
             lastNode.nextNode = newNode;
             lastNode = newNode;
         }
         N++;

     }
    public void  insert(T t,int i){
        Node node = headNode.nextNode;
        for(int index =0;index<i;index ++){
            node = node.nextNode;
        }
        Node pre = node.preNode;
        /**
         * 创建一个新的节点
         */
        Node newNode = new Node(t,pre,node);
        newNode.nextNode = pre;
        node.preNode =newNode;
        N++;
    }
    public T remove(int i){
        /**
         * 1:找到位置为i的节点
         * 2:获取到节点为i的前置节点以及后置节点
           3：将前置检点的后置职位后置节点 将后置节点的前置节点值为简直节点
         */
        Node node = headNode.nextNode;
        for(int index =0;index<=i;index ++){
            node = node.nextNode;
        }
        Node pre = node.preNode;
        Node next = node.nextNode;

        pre.nextNode = next;
        next.preNode = headNode;
        N--;
        return node.item;
    }

    public  int indexOf(T t){

         Node node = headNode.nextNode;
         for(int index=0;index<N;index++){
             if(node.nextNode.item==t){
                 return index;
             }
             node = node.nextNode;
         }
        return -1;
    }
    public T getFirst(){
         if(isEmpty()){
             return null;
         }
         return headNode.nextNode.item;
    }

    public  T getLast(){
        if(isEmpty()){
            return null;
        }
         return lastNode.item;
    }

    public  int  length(){
        return N;
    }

    public boolean isEmpty(){
        return N==0;
    }

    private  class Node{

        private Node preNode;

        private T item;

        private Node nextNode;

        public Node(T t,Node preNode,Node nextNode){
            this.item = t;
            this.nextNode= nextNode;
            this.preNode = preNode;
        }
    }
}
