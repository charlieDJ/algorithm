package jvm;

public class OnStackTest {
    // User类
    public static class User{
        public int id=0;
        public String name="";
    }
    // 创建User类对象
    public static void alloc(){
        User u=new User();
        u.id=5;
        u.name="geym";
    }
    // 程序入口
    public static void main(String[] args) throws InterruptedException {
        long b=System.currentTimeMillis();
        // 创建大量的对象
        for(int i=0;i<99999999;i++){
            alloc();
        }
        long e=System.currentTimeMillis();
        // 打印执行时间
        System.out.println(e-b);
    }
}
