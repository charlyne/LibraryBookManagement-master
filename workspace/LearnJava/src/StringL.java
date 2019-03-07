/**
 * Created by didi on 2019/1/3.
 */
public class StringL {

    public static void main(String[] args){
        StringBuffer sbff=new StringBuffer("1111");
        sbff.append("aaa");
        sbff.insert(2,"bbb");
        StringBuilder sbdlr=new StringBuilder();
        System.out.print(sbff);
    }
}
