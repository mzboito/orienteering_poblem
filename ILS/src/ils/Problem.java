/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ils;

import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author mzboito
 */
public class Problem {
    String name;
    String comment; //so de frescura
    int dimension;
    double cost_limit;
    ArrayList<Node> nodes;
    String starting_node;
    int euclDist[][];

    public Problem(String name, String comment, int dimension, double cost_limit, ArrayList<Node> nodes, String starting_node) {
        this.name = name;
        this.comment = comment;
        this.dimension = dimension;
        this.cost_limit = cost_limit;
        this.nodes = nodes;
        this.starting_node = starting_node;
        //this.euclDist = new double[nodes.size()][nodes.size()];
    }

    public Problem() {
        this.nodes = new ArrayList();         
    }

    public String getComment() {
        return comment;
    }

    public double getCost_limit() {
        return cost_limit;
    }

    public int getDimension() {
        return dimension;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public Node getStarting_node() {
        for(Node n : this.nodes){
            if(starting_node.equals(n.getLabel())){
                return n;
            }
        }
        return null;
    }
    
    public void addNode(Node node) {
        this.nodes.add(node);
    }

    public void setStarting_node(String starting_node) {
        this.starting_node = starting_node;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCost_limit(double cost_limit) {
        this.cost_limit = cost_limit;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Node getNode(String label) {
        for(Node node : this.nodes) {
            if(node.getLabel().equals(label)){
                return node;
            }
        } 
        return null;
    }
    
    public void setDistEuclSize(int size){
        this.euclDist = new int[size][size];
    }
    
    public void addEuclDist(int i, int j, double x1, double x2, double y1, double y2){
        double xd = x1 - x2;
        double yd = y1 - y2;
        int distance = (int)(sqrt((xd*xd) + (yd*yd)) + 0.5);
        
        this.euclDist[i][j] = distance;

    }
    
    public void setEuclDistZero(int i, int j){
        this.euclDist[i][j] = 0;
    }
    
    public double getEuclDist(int i, int j){
        return this.euclDist[i][j];
    }
        
}
