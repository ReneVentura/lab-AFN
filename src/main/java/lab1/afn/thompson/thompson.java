package lab1.afn.thompson;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import lab1.afn.preafn.extensionReg;
import lab1.afn.preafn.infpos;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxEdgeLabelLayout;
import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.mxStackLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.*;
import org.jgrapht.*;
import org.jgrapht.graph.*;
import lab1.afn.RelationshipEdge;
import java.util.*;


public class thompson {
    

    private List<Character> symbos;
    String regex;
    state flag;
    Stack<state>inis;
    Stack<state>fin;
    public static int stateCounter;
    public static Graph<String, RelationshipEdge> graph = new DefaultDirectedGraph<>(RelationshipEdge.class);
    public thompson(String preregex){
        String exten= extensionReg.transform_Regex(preregex);
        regex=  extensionReg.transform_Regex(infpos.infixToPostfix(preregex));
        stateCounter=0;
        symbos= new LinkedList<Character>();
        inis = new Stack<>();
        fin= new Stack<>();
        System.out.println("expresion "+preregex);
        System.out.println("postfix "+regex);
        getsymbols();
        
        toAfn();
        
    }


    private void getsymbols(){
        for (int i = 0; i < regex.length(); i++) {
            if(!infpos.precedenceMap.containsKey(regex.charAt(i))) {

                if (!symbos.contains(regex.charAt(i))) {
                    symbos.add(regex.charAt(i));
                    Collections.sort(symbos);
                }
            }
        }

    }

    private void toAfn(){
        for(int i=0;i<regex.length();i++){
            
            if(symbos.contains(regex.charAt(i))){
                edge e1= new edge(Character.toString(regex.charAt(i)));
                state initialstate= e1.getiniState();
                state finalstate= e1.getfinaState();
                e1.addEdge(initialstate, finalstate, Character.toString(regex.charAt(i)));

                inis.push(initialstate);
                fin.push(finalstate);
            }
            if(regex.charAt(i)=='*'){
                
                state initials= inis.pop();
                state finals = fin.pop();

                kleene_star(initials, finals);

            }
            if(regex.charAt(i)=='_'){
                
                flag= fin.pop();
                state initials= inis.pop();
                state finals = fin.pop();

                concatenation(initials, finals);
            }
            if(regex.charAt(i)=='|'){
                state subA1= inis.pop();
                state subA2= inis.pop();
                state subB1= fin.pop();
                state subB2= fin.pop();

                union(subA1, subA2, subB1, subB2);
            }
          
        }
    }

/*
 * union: http://cgosorio.es/Seshat/static/images/expresionUnion.png
 * concatenacion: http://cgosorio.es/Seshat/static/images/expresionConcatenacion.png
 * kleene star: http://cgosorio.es/Seshat/static/images/expresionCierre.png
 * 
 * 
 */



    private void union(state subA1,state subA2,state subB1,state subB2){
        state begin= new state();
        state end = new state();

        edge e1= new edge(begin,subA1,"ε"); 
        e1.addEdge(begin,subA1,"ε");

        edge e2= new edge(begin,subB1,"ε"); 
        e2.addEdge(begin,subA2,"ε");

        edge e3= new edge(subA2,end,"ε"); 
        e3.addEdge(subB1,end,"ε");

        edge e4= new edge(subB2,end,"ε"); 
        e4.addEdge(subB2,end,"ε");

        inis.push(begin);
        fin.push(end);
    }

    private void concatenation(state s1,state s2){
        edge e1= new edge(s1, s2, "ε");
        e1.addEdge(s2, s1, "ε");

        fin.push(flag);
        flag=null;

    }

    private void kleene_star(state s1,state s2){
        state begin= new state();
        state end= new state();

        edge e1= new edge(begin, end, "ε");
        e1.addEdge(begin, end, "ε");

        edge e2= new edge(begin, s1, "ε");
        e2.addEdge(begin, s1, "ε");

        edge e3= new edge(s2, end, "ε");
        e3.addEdge(s2, end, "ε");

        edge e4= new edge(s2, s1, "ε");
        e4.addEdge(s2, s1, "ε");
        inis.push(begin);
        fin.push(end);


    }
    public static void alg(String regex){
        thompson a= new thompson(regex);
    }
    
}
