/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ils;

/**
 *
 * @author mzboito
 */
public class Node {
    String label;
    double x;
    double y;
    double score;

    public Node(String label, double x, double y, double score) {
        this.label = label;
        this.x = x;
        this.y = y;
        this.score = score;
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
    
    

}
