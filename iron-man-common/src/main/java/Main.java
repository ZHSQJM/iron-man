import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/3 10:16
 * @package: PACKAGE_NAME
 * @description:
 */
public class Main {

    public static void main(String[] args) {

        Main main = new Main();
        final List<Demo> result = main.getResult(main.getlistA(), main.getlistB());
        result.stream().forEach(e-> System.out.println(e));

    }

    private List<Demo> getResult(List<Demo> A,List<Demo> B ){
        final List<Demo> result = new ArrayList<>();
        int[] list = new int[A.size()];
        for (int i = 0; i < list.length; i++) {
            if(i<B.size()){
                list[B.get(i).getId()-1]=B.get(i).getCount();
            }else {
                list[A.get(i-1).getId()-1] = A.get(i-1).getCount();
            }
        }
        for (int i = 0; i < list.length; i++) {
            Demo demo = new Demo();
            demo.setId(i+1);
            demo.setCount(list[i]);
            result.add(demo);
        }
        return result;
    }
    private  List<Demo> getlistA(){
        List<Demo> list = new ArrayList<>();
        Demo demo1 = new Demo(1,10);
        Demo demo2 = new Demo(2,1);
        Demo demo3 = new Demo(3,0);
        Demo demo4 = new Demo(4,11);
        list.add(demo1);
        list.add(demo2);
        list.add(demo3);
        list.add(demo4);
        return list;
    }

    private  List<Demo> getlistB(){
        List<Demo> list = new ArrayList<>();
        Demo demo1 = new Demo(1,5);
        Demo demo3 = new Demo(2,910);
        Demo demo2 = new Demo(4,9);
        list.add(demo1);
        list.add(demo2);
        list.add(demo3);
        return list;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
     class Demo{

        private int id;

        private int count;

    }
}
