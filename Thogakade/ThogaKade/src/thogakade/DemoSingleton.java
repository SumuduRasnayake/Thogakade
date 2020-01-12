/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thogakade;

/**
 *
 * @author niroth
 */
class Singleton{
    private static Singleton ob;
    private Singleton(){
        
    }
    public static Singleton getInstance(){
        if(ob==null){
            ob=new Singleton();
        }
        return ob;
    }
}

public class DemoSingleton {
    public static void main(String[] args) {
        //Singleton s1=new Singleton();
        Singleton ob1 = Singleton.getInstance();
        Singleton ob2= Singleton.getInstance();
        System.out.println(ob1==ob2);
    }
}
