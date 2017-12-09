/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ils;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author mzboito
 */
public class Solver {
    Problem p;
    Solution s;
    int seed;
    int maxSteps;

    public Solver(Problem p, int seed, int maxSteps) {
        this.p = p;
        this.seed = seed;
        this.maxSteps = maxSteps;
        this.s = new Solution(p.getDimension());
        Node n = p.getStarting_node();
        if(n != null){
            s.setTrivialSolution(n);
        }
    }
    
    public void exec(){ //here goes the logic
        
        int maxImprov = 50;
        int maxIt = this.maxSteps;
        
        Solution bestLocal = s;
        
        while(maxIt > 0){
            Solution newSol = localSearch(bestLocal,maxImprov);
            bestLocal = perturbSolution(newSol);
            maxIt--;
        }
        this.s = bestLocal;
        
    }
    
    
    public Solution localSearch(Solution bestLocal, int maxImprov) { //adiciona n vezes, usa seed randomico pra adicionar nós, tenta maximizar o score e controlar o custo
        return bestLocal;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // Perturba a bestSolution encontrada removendo n nodos da solução
    public Solution perturbSolution(Solution bestLocal) { //remove uma quantidade N de nós da solução randomicamente e cola a solução
        
        //gerar o numero de coisas pra remover
        int N = 20 * p.dimension / 100;
        System.out.printf("dimension %d e N %d\n", p.dimension, N);
        
        //pegar indices aleatorios
        Random generator = new Random(seed);
        int max = bestLocal.prob_dimension;
        int min = 0;
        int randomNum; 
        
        //N = 3;
        //bestLocal.addEdge(1, 3);
        //bestLocal.addEdge(3, 7);
        //bestLocal.addEdge(7, 5);
        //bestLocal.addEdge(5, 1);
        //criar uma nova solucao removendo os indices aleatorios
        while(N > 0){
            randomNum = generator.nextInt((max - min) + 1) + min; //generates a number
            //System.out.printf("Entering with rand num %d \n", randomNum);
            int edges[] = bestLocal.edges;
            int a1 = -1;
            int a2 = edges[randomNum];
            if(bestLocal.startingSolution()){
                a1 = Integer.parseInt(p.starting_node);
            }else{
                for(int i=1; i < bestLocal.prob_dimension; i++){
                if(edges[i] == randomNum){
                    a1 = i;
                }
               }
            }
            if((a2 != -1)&&(a1 != -1)){
                //System.out.printf("(%d,%d)\n", a1,a2);
                //System.out.println("old edge");
                bestLocal.print_edges();
                bestLocal.removeEdge(randomNum);
                bestLocal.addEdge(a1, a2); //fix the route
                //System.out.println("new edge");
                bestLocal.print_edges();
            }
            N--;
        }
        bestLocal.updateCost(p.euclDist);
        bestLocal.updateScore(p.getScoreList());
        return bestLocal;
    }

    public void writeSolution(String output) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
