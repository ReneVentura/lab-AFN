package lab1.afn.thompson;
import java.util.*;
public class state {
    List<state> previous;
    List<state> next;
    
    boolean initialState;
    boolean finalState;
    int id;
    public state(){
        id=thompson.stateCounter;
        //System.out.println("stado "+get_current());
        previous= new LinkedList<>();
        next = new LinkedList<>();
        thompson.graph.addVertex(get_current());
        thompson.stateCounter++;

    }

    
    public void addPreviousState(state previous){
       
        this.previous.add(previous);
        thompson.graph.addVertex(get_current());
    }

    public void addNextState(state next){
        
        this.next.add(next);
        thompson.graph.addVertex(get_current());
    }
    public List<state> getPreviousState(){
        return this.previous;
    }
    public List<state> getNextState(){
        return this.next;
    }
    public String get_current(){
        return Integer.toString(id);
    }

}
