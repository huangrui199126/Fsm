package tcp;
import Fsm.*;

import java.util.EventListener;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;
public class main {

    public static void main(String[] args) {
        // write your code here
        FSM fsm = new FSM("test",new MyState("ESTABLISHED"));
        Scanner sc = new Scanner(System.in);
        MyAction action = new MyAction();
        MyTransition myTransition = new MyTransition();
//        System.out.println("Please input the next event");
        while(fsm.currentState().getName() != "CLOSE"){
            System.out.println("Current State is:" + fsm.currentState().getName());
            System.out.println("Please input the next event");
            String event = sc.next();
            Event e = new MyEvent(event);
            String nextState = myTransition.getNextState(fsm.currentState().getName(),event);
            if(nextState == "error"){
                System.out.println("invalid input");
                continue;
            }
            State s = new MyState(nextState);
            Transition t = new Transition(fsm.currentState(),e,s,action);

            try {
                fsm.addTransition(t);
                fsm.doEvent(e);
            } catch (FsmException e1) {
                e1.printStackTrace();
            }
        }
    }
}
