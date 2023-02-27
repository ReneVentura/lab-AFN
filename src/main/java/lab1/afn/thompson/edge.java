package lab1.afn.thompson;

import lab1.afn.RelationshipEdge;



public class edge {
    state iniState;
    state finaState;
    String symbol;

    public  edge(String symbol) {/// para simbolos
        this.symbol = symbol;
        iniState = new state();
        finaState = new state();

        
        iniState.addNextState(iniState);
        finaState.addPreviousState(finaState);
        
    }


    public edge( state iniState, state finaState,String symbol) {
        this.symbol = symbol;
        this.iniState = iniState;
        this.finaState = finaState;
        this.iniState.addNextState(finaState);
        this.finaState.addPreviousState(iniState);
        
    }
    public void addEdge( state iniState, state finaState,String symbol){
        System.out.println("estado "+iniState.get_current()+" hacia "+finaState.get_current()+" con simbolo "+symbol);
        thompson.graph.addEdge(iniState.get_current(), finaState.get_current(), new RelationshipEdge(symbol));
    }
  
    public state getiniState() {
        return iniState;
    }

    public state getfinaState() {
        return finaState;
    }

}
