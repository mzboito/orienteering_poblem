/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ils;

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

    public Problem(String name, String comment, int dimension, double cost_limit, ArrayList<Node> nodes, String starting_node) {
        this.name = name;
        this.comment = comment;
        this.dimension = dimension;
        this.cost_limit = cost_limit;
        this.nodes = nodes;
        this.starting_node = starting_node;
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

    public String getStarting_node() {
        return starting_node;
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
        
}
