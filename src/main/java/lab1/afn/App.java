package lab1.afn;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
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

import lab1.afn.thompson.thompson;

import com.mxgraph.*;
import org.jgrapht.*;
import org.jgrapht.graph.*;

import java.util.*;
/*
 * 
    (a|b)*(b|a)*abb
    ((ε|a)b*)*
    (.|;)*-/.(.|;)*
    (x|t)+((a|m)?)+
    ("(.(;(.;(.|;)+)*)*)*

 */
public class App {

    private static void createAndShowGui() {
        System.out.println("Nota: el simbolo de epsilon es intepretado por java como debe, pero no lo logra mostrar en CLI");
        JFrame frame = new JFrame("DemoGraph");
        frame.setSize(800,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thompson.alg("((ε|a)b*)*");
        Graph<String, RelationshipEdge> g = thompson.graph;
        
        JGraphXAdapter<String,RelationshipEdge> graphAdapter = 
                new JGraphXAdapter<String, RelationshipEdge>(g);

        mxIGraphLayout layout = new mxCircleLayout(graphAdapter,300);
            layout.execute(graphAdapter.getDefaultParent());
        
        mxGraphComponent graphComponent = new mxGraphComponent(graphAdapter);
       // new mxCircleLayout(graphAdapter).execute(graphAdapter.getDefaultParent());
        new mxParallelEdgeLayout(graphAdapter).execute(graphAdapter.getDefaultParent());
        frame.getContentPane().add(graphComponent);

        frame.setTitle("lab afn");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        frame.pack();


        frame.add(graphComponent);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }

  

    public static Graph<String,RelationshipEdge> buildGraph() {
        
            Graph<String, RelationshipEdge> graph = new DefaultDirectedGraph<>(RelationshipEdge.class);

         
        return graph;
    }

}
