/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lr0;

import java.util.Scanner;

/**
 *
 * @author parham
 */
public class Rule {

    public String ruleWithHandle;
    public String afterHandle;
    public String Starter;
    /**
     * i dont know is this good or bad
     * im just using it
     */
    public int index;
    boolean Isfinished;

    public Rule(String ruleWithHandle,int a) {

    }

    public Rule(String start) {
        //A -> B . c
        if(start.split("\\.").length<2){
            this.ruleWithHandle=start.split("->")[0]+"-> ."+start.split("->")[1];
            this.afterHandle=start.split("->")[1].split("\\s+")[1];
            clearing();
            return;
        }
        String ruleWithHandler="",afterHandle = null;
        String divide[]=start.split("->");
        Scanner s = new Scanner(divide[1]);
        while (s.hasNext()) {
            String nxt=s.next().replaceAll("\\s+", "");
            if(nxt.equals(".")){
                break;
            }
            ruleWithHandler+=" "+nxt+" ";
        }
        if(s.hasNext()){
            ruleWithHandler+=s.next()+" . ";
        }
        if(s.hasNext()){
            afterHandle=s.next();
            ruleWithHandler+=afterHandle+" ";
        }else{
            afterHandle = null;
        }
        while (s.hasNext()) {
            ruleWithHandler+=s.next();
        }
        
        this.ruleWithHandle=divide[0]+" -> "+ruleWithHandler;
        this.afterHandle=afterHandle;
        clearing();
    }
    private void clearing(){
        this.ruleWithHandle=ruleWithHandle.replaceAll("\\s+"," ");
        Starter=ruleWithHandle.split("->")[0];
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(obj instanceof Rule)) {
            return false;
        }
        Rule c=(Rule) obj;
//        System.out.println(c.ruleWithHandle);
//        System.out.println(ruleWithHandle);
        return c.ruleWithHandle.replaceAll("\\s+", "").equals(ruleWithHandle.replaceAll("\\s+", ""));
        
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }    

    @Override
    public String toString() {
        return ruleWithHandle; //To change body of generated methods, choose Tools | Templates.
    }
    
}
