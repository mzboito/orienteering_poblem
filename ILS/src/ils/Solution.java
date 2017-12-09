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
    
    public void addEdge(int n1, int n2){
        /*if(this.edges[n1] != -1){ //if it is not free
            return -1;
        }*/
        this.edges[n1] = n2;
        //return 0;
    }
    
    public int removeEdge(int n1){
        if(this.edges[n1] == -1){ //if it is not free
            return -1;
        }
        this.edges[n1] = -1;
        return 0;
    }
    
    public void updateScore(double s[]){
        Double newScore = 0.0;
        for(int i = 1; i < prob_dimension; i++){
            if(edges[i] != -1){
                newScore += s[i]; 
            }
        }
        this.totalScore = newScore;
    }
    
    boolean edgeAlreadyInserted(int randNum, boolean alreadyInserted){
        if(this.edges[randNum] != -1)
            alreadyInserted = true;
        else
            alreadyInserted = false;
        
        return alreadyInserted;
    }
    
    public void updateCost(int d[][]){
        Double newCost = 0.0;
        for(int i = 1; i < prob_dimension; i++){
            for(int j = 1; i < prob_dimension; i++){
                if(edges[i] != -1){
                    if(edges[i] == j){ //it has a connection
                        newCost += d[i][j];
                    }
                }
            }
        }
    }

    boolean startingSolution() {
        int count = 0;
        for(int i = 1; i < prob_dimension; i++){
            if(edges[i] != -1){
                count++;
            }
        }
        if(count == 1){
            return true;
        }else{
            return false;
        }
    }

    void print_edges(){
        for(int i = 1; i < prob_dimension; i++){
            System.out.printf("%d", edges[i]);
            System.out.printf(" ");
        }
        System.out.println();
    }
    
}
