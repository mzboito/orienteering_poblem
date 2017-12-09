/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ils;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author carol
 */
public class Solution {
    ArrayList<Node> nodeList;
    double totalCost;
    double totalScore;
    
    public Solution() {
        this.nodeList = new ArrayList();
        this.totalCost = 0.0;
        this.totalScore = 0.0;        
    }
    
    public void addNodeToList(Node node){
        this.nodeList.add(node);
    }

    public ArrayList<Node> getNodeList() {
        return nodeList;
    }
    
    public void updateTotalCost(double cost){
        this.totalCost += cost;
    }

    public double getTotalCost() {
        return totalCost;
    }
    
    public void updateTotalScore(double score){
        this.totalScore += score;
    }

    public double getTotalScore() {
        return totalScore;
    }
    
    public void setTrivialSolution(Node n) {
        this.nodeList.add(n);
        this.totalCost = 0;
        this.totalScore = n.getScore();
    }
    
}
