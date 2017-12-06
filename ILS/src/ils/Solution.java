/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ils;

/**
 *
 * @author carol
 */
public class Solution {
    Node node;
    double totalCost;
    double totalScore;
    
    public Solution(Node node, double totalCost, double totalScore) {
        this.node = node;
        this.totalCost = totalCost;
        this.totalScore = totalScore;
    }

    public Node getNode() {
        return node;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public double getTotalScore() {
        return totalScore;
    }
    
}
