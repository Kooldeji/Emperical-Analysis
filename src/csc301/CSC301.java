/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc301;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import javafx.scene.input.KeyCode;

/**
 *
 * @author kooldeji
 */
public class CSC301 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        HashMap<Node, HashSet<Node>> set = new HashMap();
        //HashMap<String, DFNode> nodeDict = new HashMap<>();
//        
//        String[][] inp = {
//            {"U", "V", "X"},
//            {"V", "Y"},
//            {"W", "Y", "Z"},
//            {"X", "V"},
//            {"Y", "X"},
//            {"Z", "Z"}
//        };
//                
//        String[][] inp2 = {
//            {"U", "P", "SHO"},
//            {"P", "B", "SHO"},
//            {"SHI", "B", "T"},
//            {"SOC", "SHO"},
//            {"B", "J"},
//            {"SHO"},
//            {"WATCH"},
//            {"J"},
//            {"T", "J"}};
//        
//        Graph(Arrays.asList(inp2), set);
//        DFNode node;
//        DGraph graph = new DGraph(set);
//        System.out.println(graph.Vertices());
//        System.out.println(graph.Edges());
//        System.out.println(graph.Adj(nodeDict.get("U")));
//        
//        DepthFirst DF = new DepthFirst(graph);
//        DF.Traverse();
//        System.out.println(DF.getTime());
//        node = nodeDict.get("P");
//        System.out.println(node.P+" "+node.D+" "+node.F);

//        Graph(inp2, set, nodeDict);
//        graph = new DGraph(set);
//        TopologicalSort TS = new TopologicalSort(graph);
//        TS.Sort();
//        System.out.println(TS.getSortedList());
        
    }
    
//    public static void Graph(String[][] inp, HashMap<Node, HashSet<Node>> set, HashMap<String, Node> nodeDict) {
//        set.clear();
//        nodeDict.clear();
//        for (String[] list : inp){
//            Node head;
//            HashSet<DFNode> nodes = new HashSet<>();
//            if (!nodeDict.containsKey(list[0])) {
//                head = new Node();
//                head.Tag = list[0];
//                nodeDict.put(list[0], head);
//                set.put(head, nodes);
//            }else {
//                head = nodeDict.get(list[0]);
//                nodes = set.get(head);
//            }
//            for (int i =1; i< list.length; i++){
//                DFNode tail;
//                if (!nodeDict.containsKey(list[i])) {
//                    tail = new DFNode();
//                    tail.Tag = list[i];
//                    nodeDict.put(list[i], tail);
//                    set.put(tail, new HashSet<>());
//                }else tail = nodeDict.get(list[i]);
//                nodes.add(tail);
//            }
//        }
//    }
    
//    public static HashMap<Node, HashSet<Node>> createGraph(ArrayList<ArrayList<Integer>> inp, ArrayList<String> names) {
//        HashMap<Node, HashSet<Node>> set = new HashMap();
//        HashMap<Integer, Node> nodeDict = new HashMap();
//        for (ArrayList<Integer> list : inp){
//            Node head;
//            HashSet<Node> nodes = new HashSet<>();
//            if (!nodeDict.containsKey(list.get(0))) {
//                head = new Node();
//                head.Tag = names.get(list.get(0));
//                nodeDict.put(list.get(0), head);
//                set.put(head, nodes);
//            }else {
//                head = nodeDict.get(list.get(0));
//                nodes = set.get(head);
//            }
//            for (int i =1; i< list.size(); i++){
//                Node tail;
//                if (!nodeDict.containsKey(list.get(i))) {
//                    tail = new DFNode();
//                    tail.Tag = names.get(list.get(i));
//                    nodeDict.put(list.get(i), tail);
//                    set.put(tail, new HashSet<>());
//                }else tail = nodeDict.get(list.get(i));
//                nodes.add(tail);
//            }
//        }
//        return set;
//    }
}
