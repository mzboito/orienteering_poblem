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
    int edges[];
    int prob_dimension;
    double totalCost;
    double totalScore;

    
    public Solution(int dimension) {
        this.prob_dimension = dimension + 1;
        this.edges = new int[prob_dimension];
        for(int i = 0; i < prob_dimension; i++){
            edges[i] = -1;
        } 
        this.totalCost = 0.0;
        this.totalScore = 0.0;        
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
        int index = Integer.parseInt(n.getLabel()); 
        this.edges[index] = index;
        this.totalCost = 0;
        this.totalScore = n.getScore();
    }
    
    public int addEdge(int n1, int n2){
        if(this.edges[n1] != -1){ //if it is not free
            return -1;
        }
        this.edges[n1] = n2;
        return 0;
    }
    
    public int remodeEdge(int n1){
        if(this.edges[n1] == -1){ //if it is not free
            return -1;
        }
        this.edges[n1] = -1;
        return 0;
    }
    
    public void updateScore(int s[]){
        Double newScore = 0.0;
        for(int i = 1; i <= prob_dimension; i++){
            if(edges[i] != -1){
                newScore += s[i]; 
            }
        }
        this.totalScore = newScore;
        
    }
    
}
