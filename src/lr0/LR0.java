/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lr0;

import java.util.ArrayList;

/**
 *
 * @author parham
 */
public class LR0 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Apple a=new Apple();
//        a.s="aa";
//        ArrayList<Apple> mammad=new ArrayList<Apple>();
//        mammad.add(a);
//        a.s="aaa";
//        System.out.println(mammad.get(0).s);
        AutomataBuilder n =new AutomataBuilder(
                "Start -> S\n"
                + "S -> a S\n"
                + "S -> b A\n"
                + "S -> c B\n"
                + "A -> d\n"
                + "A -> h\n"
                + "B -> r\n"
                + "B -> g\n");
        AutomataBuilder s = new AutomataBuilder("Str -> S\n"
                + "S -> E\n"
                + "E -> T ;\n"
                + "E -> T + E\n"
                + "T -> int\n"
                + "T -> ( E )\n");
        AutomataBuilder ss = new AutomataBuilder("Str -> S\n"
                + "S -> A\n"
                + "S -> B\n"
                + "A -> b\n"
                + "A -> d\n"
                + "B -> a B\n"
                + "B -> c");
        System.out.println(s);
    }

}
