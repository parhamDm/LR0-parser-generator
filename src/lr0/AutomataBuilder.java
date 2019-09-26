/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lr0;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author parham
 */
public class AutomataBuilder {

    ArrayList<SparceMatrix> edge;
    ArrayList<String> rules;
    ArrayList<String> nonTerminals;
    ArrayList<State> states;
    int lastOne = 0;

    public AutomataBuilder(String str) {
        Set<String> hs = new HashSet<>();
        edge = new ArrayList<SparceMatrix>();
        rules = new ArrayList<String>();
        states = new ArrayList<State>();
        nonTerminals = new ArrayList<>();
        Scanner s = new Scanner(str);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            nonTerminals.add(line.replaceAll("\\s+", "").split("->")[0]);
            rules.add(line);
        }
        hs.addAll(nonTerminals);
        nonTerminals.clear();
        nonTerminals.addAll(hs);
        buildSatateZero();
        buildAutimata(states.get(0), "S");
        for (Rule rule : states.get(0).al) {
            if (rule.afterHandle != null) {
                buildAutimata(states.get(0), rule.afterHandle);
            }
        }

        draw();
    }

    private void buildStates(State s) {
        while (true) {
            ArrayList<Rule> alhelper = new ArrayList<Rule>();
            alhelper = (ArrayList<Rule>) s.al.clone();
            String afrhan = null;
            for (Rule rule : s.al) {
                if (rule.index == 0) {
                    afrhan = rule.afterHandle;
                    rule.index = 1;
                    if (!nonTerminals.contains(afrhan)) {
                    } else {
                        break;
                    }
                }
            }
            if (afrhan == null) {
                break;
            }
            for (String rule : rules) {
                if (afrhan.equals(rule.split("->")[0].replaceAll("\\s+", ""))) {
                    Rule r = new Rule(rule);
                    if (!s.al.contains(r)) {
                        s.add(new Rule(rule));
                    }
                }
            }
        }
    }

    public void buildSatateZero() {
        int index[] = new int[states.size()];
        State s = new State();
        s.stateNumber = lastOne++;
        s.add(new Rule(rules.get(0)));
        buildStates(s);
        states.add(s);
    }
        int i=0;

    public void buildAutimata(State ps, String pushFrom) {
        State cs = new State();
        for (Rule rule : ps.al) {
            if (rule.afterHandle.equals(pushFrom)) {
                cs.add(new Rule(rule.toString()));
            }
        }
        ;
        buildStates(cs);
        for (State state : states) {
            if (state.equals(cs)) {//already exists
                SparceMatrix spa = new SparceMatrix(ps.stateNumber, state.stateNumber, pushFrom);
                edge.contains(spa);
                if (!edge.contains(spa)) {
                    //System.out.println(++i);
                    edge.add(spa);
                }
                return;
            }

        }
        states.add(cs);
        cs.stateNumber = lastOne++;
        SparceMatrix spa = new SparceMatrix(ps.stateNumber, cs.stateNumber, pushFrom);
        edge.add(spa);
       
        for (Rule rule : cs.al) {
            if (rule.afterHandle != null) {
                buildAutimata(cs, rule.afterHandle);
            }
        }
    }

    /**
     * 1.having a set of rules 2.check a handle index is false if nonterminal
     * add its states else index is true
     *
     * @param st
     * @param nonTerminal
     */
    public void addToState(State st, String nonTerminal) {
        for (String rule : rules) {
            if (rule.split("->")[0].replaceAll("\\s+", "").equals(nonTerminal)) {
                st.add(new Rule(rule));
            }
        }

    }

    /**
     * 1.build the state 2.check already exists 3.add to state list 4.move from
     * that state
     *
     * @param movedTerm
     * @param whatStateIsMoved
     */
    public void goToNextState(String movedTerm, State whatStateIsMoved) {

    }

    private boolean isNonTerminal(String nonTerminal) {
        return nonTerminals.contains(nonTerminal);
    }

    public void draw() {
        // add elements to al, including duplicates
        StringBuilder sb = new StringBuilder();
        sb.append("digraph  g {\n");
        for (State state : states) {
            sb.append(state).append("[shape=box] ");
        }
        for (SparceMatrix sm : edge) {

            sb.append(states.get(sm.from)).append(" -> ").append(states.get(sm.to))
                    .append("[label=\"" + sm.Label + "\"]").append(";\n");
        }
        sb.append("}");
        System.out.println(sb);
    }

    private class SparceMatrix {

        int from;
        int to;
        String Label;

        public SparceMatrix(int from, int to, String Label) {
            this.from = from;
            this.to = to;
            this.Label = Label;
        }
        public String toString() {
            return from + " " + to + " " + Label;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }

            /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
            if (!(obj instanceof SparceMatrix)) {
                return false;
            }
            SparceMatrix c = (SparceMatrix) obj;
            return (c.from == from && c.to == to && c.Label.equals(Label));
        }

        @Override
        public int hashCode() {
            return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
