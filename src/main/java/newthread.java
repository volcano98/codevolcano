/**
 * @author : can
 * create at:  2021/12/8  01:11
 * @description:
 */
public class newthread {
    public static void main(String[] args) {
        int i=0;
       while (i!=3){
           System.out.println(i);
           i++;
       }

    }
}

class Singleton{
    private static volatile Singleton intance;
    private Singleton(){}
    public  static Singleton getIntance(){
        if(intance==null){
            synchronized (Singleton.class){
            if(intance==null){
                intance=new Singleton();
            }
            }
        }
        return intance;
    }
}