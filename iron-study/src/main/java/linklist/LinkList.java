package linklist;

import java.util.Iterator;

/**
 * @project: data-structure
 * @author: zhs
 * @date: 2020/6/28 16:08
 * @package: com.zhs.linkenlist
 * @description:
 */
public class LinkList<T>  implements Iterable<T>{

    private Node head;

    private int N;

    private class Node{

        T item;

        Node next;

        public Node(T item,Node next){
            this.item = item;
            this.next = next;
        }
    }

    public LinkList(){
        this.head =new Node(null,null);
        N=0;
    }
    public void clear(){
        head.next = null;
        N =0;
    }

    public int length(){
        return N;
    }

    public boolean isEmpty(){
        return N==0;
    }

    public T get(int i){
        /**
         * 通过循环从头节点开始找
         *如果当前的count不是获取的I值
         * 那么久将当前节点的下一个节点当作当前节点继续查找
         */
        int count = 1;
        Node currentNode = head.next;
        while(count<i){
            currentNode = currentNode.next;
            count++;
        }

//        for (int index = 0;index<i;index++){
//            currentNode = currentNode.next;
//        }
       return currentNode.item;
    }

    /**
     * 往链表的最后一个元素插入
     * @param t
     */
    public void insert(T t){
        int count = N;
        Node currentNode = head;
        while (count!=0){
            currentNode = currentNode.next;
            count--;
        }

//        Node currentNode = head;
//        while (currentNode.next!=null){
//            currentNode = currentNode.next;
//        }
        Node newNode = new Node(t,null);
        currentNode.next = newNode;
        N++;
    }

    /**
     * 向指定位置插入一个节点
     * @param t
     * @param position
     */
    public  void  insert(T t,int position){

        /**
         *获取到position的前面一个节点preNode
         *将preNode的前面一个节点的指针指向新的节点
         *新增节点的指针指向前一个前的prNode的后一个节点
         *
         */
        Node preNode = head;
        for(int index =0;index<position;index++){
            preNode = preNode.next;
        }
        Node newNode = new Node(t,null);
        newNode.next = preNode.next;
        preNode.next = newNode;
        N++;
    }

    public T  remove(int position){

        /**
         * 找到position的前一个节点
         * 找到position的节点和下一个节点
         */

        Node preNode = head;
        for(int index =0;index<position;index++){
            preNode = preNode.next;
        }
        //获取待删除的节点
        final Node currentNode = preNode.next;
        Node next = currentNode.next;
        preNode.next = next;
        N--;
        return currentNode.item;
    }

    /**
     * 查找到T在链表的位置
     * @param t
     * @return
     */
    public int indexOf(T t){
        Node preNode = head.next;
        //    for (int i=0;n.next!=null;i++){
        for (int i=0;i<N;i++){
            if(preNode.item==t){
                return i;
            }else{
                preNode = preNode.next;
            }
        }
        return -1;
    }

    //反转链表
    public void reverse(){
        if(!isEmpty()){
            reverse(head.next);
        }
    }

    private Node reverse(Node node){

        if(node.next!=null){
            node.next = node;
            Node pre = reverse(node);
            pre.next = node;
            node.next = null;
            return node;
        }else {
            head.next = node;
            return head;
        }
//        while (node.next!=null){
//            node = node.next;
//            reverse(node);
//        }
//        head.next = node;
//        return head;
    }
    @Override
    public Iterator<T> iterator() {
        return new LIterator();
    }

    private class LIterator implements Iterator{

        private Node node;

        public LIterator(){
            this.node =head;
        }

        @Override
        public boolean hasNext() {
            return node.next!=null;
        }

        @Override
        public Object next() {
            node = node.next;
            return node.item;
        }
    }
}
