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
            bestLocal = acceptSolution(bestLocal, newSol);
            bestLocal = perturbSolution(newSol);
            maxIt--;
            //System.out.println(maxImprov + "\n");
        }
        this.s = bestLocal;
        
    }
    
    public Solution acceptSolution(Solution bestLocal, Solution newSol){
        
        
        
        return bestLocal;
    }
    
    /* Tipo um hill clibing */
    public Solution localSearch(Solution bestLocal, int maxImprov){
    //adiciona n vezes, usa seed randomico pra adicionar nós, tenta maximizar o score e controlar o custo
        Random generator1 = new Random(seed);
        int nodeNum1 = 0;
        while(maxImprov > 0){
            
            boolean alreadyInserted = false;
            int nodeToInsert = generator1.nextInt(p.nodes.size()) + 1;
            //Node nodeToInsert = p.getNode(Integer.toString(randNum1));
            
            if(!(bestLocal.edgeAlreadyInserted(nodeToInsert))){ // não foi inserido ainda
                while(!alreadyInserted){
                    Random generator2 = new Random(seed); //onde vai ser inserido
                    nodeNum1 = generator2.nextInt(p.nodes.size()) + 1;
                    alreadyInserted = bestLocal.edgeAlreadyInserted(nodeToInsert);
                }
                
                int nodeNum2 = s.verifyConectedNode(nodeNum1);
                double euclDist = p.getEuclDist(nodeNum1, nodeNum2);
                double newCost = bestLocal.getTotalCost() - euclDist; // tira o custo das arestas que não vão mais se conectar
                
                if(p.getEuclDist(nodeToInsert, nodeNum1) + p.getEuclDist(nodeToInsert, nodeNum2) <= bestLocal.totalCost){
                    bestLocal.removeEdge(nodeNum1);
                    bestLocal.removeEdge(nodeNum2);
                    bestLocal.addEdge(nodeToInsert, nodeNum1);
                    bestLocal.addEdge(nodeToInsert, nodeNum1);
                    bestLocal.updateScore(p.getScoreList());
                    bestLocal.updateCost(p.euclDist);
                }
            }
            maxImprov--;
        }
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
        int min = 1;
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
            if(randomNum != Integer.parseInt(p.starting_node)){
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
