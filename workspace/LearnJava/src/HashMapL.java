import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by didi on 2018/12/29.
 */
public class HashMapL {
    private int id;
    private String name;

    @Override
    public boolean equals(Object obj) {

        if(obj==null) return false;
        if(this==obj) return false;
        if(obj.getClass()!=this.getClass()) return false;
        HashMapL objl=(HashMapL) obj;
        if(objl.id==id&&objl.name==name) return true;
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public static void main(String[] args){
        HashMap<String,String> hashmap=new HashMap();
        hashmap.put("name","gaoweiwei");
        hashmap.put("gender","female");
        hashmap.put("age","31");
        hashmap.put("test",null);
        System.out.println(hashmap.containsKey("name"));
        System.out.println(hashmap.get("name")) ;
        System.out.println("删除一个不存在的key"+hashmap.remove("not"));
        System.out.println("删除一个存在的key"+hashmap.remove("age"));
        System.out.println(hashmap.size());

       // hashmap.clear();
        System.out.println(hashmap.put("kk","kkkk"));

        hashmap.put(null,"99 bugs nullvalue");
        System.out.println("key=null,value="+hashmap.get(null));
        System.out.print(hashmap.get("test"));
        Hashtable table=new Hashtable();
        String a="a";
        String b="a";
        String c;
        if((c=a)==b){
            System.out.println("a==b");
        }
        else System.out.println("a!=b");
        int n=-128;
        int m=n>>>2;
        System.out.println(Integer.toBinaryString(-128));
        System.out.println(null==null);

        int array[]={3,4,1,2,5};
        ArrayList list=new ArrayList();
        for(int i=0;i<array.length;i++){
            list.add(array[i]);
        }
        Collections.sort(list);
        for(int i=0;i<array.length;i++){System.out.println(list.get(i));
        }
        ArrayList ll=new ArrayList();




    }

}
