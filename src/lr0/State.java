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
public class State {
    public int stateNumber;
    ArrayList<Rule> al;
    
    public State(){
        al=new ArrayList<Rule>();
        
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
 
        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(obj instanceof State)) {
            return false;
        }
        State c=(State)obj;
        for (Rule k : this.al) {  
            if(!c.al.contains(k)){
               return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
    public void add(Rule e){
        al.add(e);
    }
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder("\"");
        for (Rule rule : al) {
            sb.append(rule+"\\n");
        }
        sb.append("\"");
        return sb.toString();
    }

}
