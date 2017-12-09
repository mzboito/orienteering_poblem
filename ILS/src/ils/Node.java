/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mzboito
 */
public class Node {
    String label;
    double x;
    double y;
    double score;
    ArrayList<Double> euclDist;

    public Node(String label, double x, double y, double score) {
        this.label = label;
        this.x = x;
        this.y = y;
        this.score = score;
        this.euclDist = new ArrayList();
    }

    public Node(String label, double x, double y) {
        this.label = label;
        this.x = x;
        this.y = y;
    }
    

    public String getLabel() {
        return label;
    }

    public double getScore() {
        return score;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setScore(double score) {
        this.score = score;
    }
    
    public void addEuclDist(double distance, int node){
        this.euclDist.add(node, distance);
    }
}
