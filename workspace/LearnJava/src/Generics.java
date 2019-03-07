import java.util.List;
import java.util.ArrayList;

/**
 * Created by didi on 2019/1/3.
 */
//泛型类
public class Generics<T> {
    private T t;
    public T get(){
        return this.t;
    }
    public void  set(T t){
        this.t=t;
    }

    //泛型方法，
    public static <T>  void test(T a){
        System.out.print(a.getClass());

    }
    //泛型通配符?
    public static void getData(List<?> a){
        System.out.print(a.get(0));
    }


    public static void main(String[] args){
       Generics.test( 4);
        Generics.test("aaaa");
        Generics<Integer> ii=new Generics<Integer>();
        ii.set(7);
        System.out.println("Generics-integer+ "+ii.t);
        Generics<String> mm=new Generics<String>();
        mm.set("meimei");
        System.out.println("Generics-string "+mm.t);
        List<String> a=new ArrayList<String>();
        a.add("aa");
        List<Integer> b=new ArrayList<Integer>();
        b.add(1);
        b.add(2);
        Generics.getData(a);
        Generics.getData(b);
    }
}
