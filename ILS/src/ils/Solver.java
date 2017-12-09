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
            System.out.println("Iteration number " + maxIt);
            Solution newSol = localSearch(bestLocal,maxImprov);
            bestLocal = acceptSolution(bestLocal, newSol);
            bestLocal = perturbSolution(bestLocal);
            maxIt--;
            
        }
        this.s = bestLocal;
        System.out.println(s.totalScore);
        s.updateCost(p.euclDist);
        System.out.println(s.totalCost);
        s.print_edges();
    }
    
    public Solution acceptSolution(Solution bestLocal, Solution newSol){

        if(newSol.totalScore > bestLocal.totalScore)
            bestLocal = newSol;
        
        else {
            Random generator = new Random(seed);
            int chooseSol = generator.nextInt(100);
            if(chooseSol < 10)
                bestLocal = newSol;
        }
        return bestLocal;
    }
    
    /* Tipo um hill clibing */
    public Solution localSearch(Solution bestLocal, int maxImprov){
    //adiciona n vezes, usa seed randomico pra adicionar nós, tenta maximizar o score e controlar o custo
        Random generator = new Random(seed);
        int nodeNum1;
        int size = bestLocal.getFreeNodesNumber();
        boolean alreadyInserted;
        while((maxImprov > 0)&&(size > 0)){
            //bestLocal.print_edges();
            int nodeToInsert = generator.nextInt(p.nodes.size()) + 1; //pega um num random
            //System.out.println("Random number: " + nodeToInsert);
            //testa se já não tá sendo usado
            if(bestLocal.startingSolution()){ //só tem um nodo
                //System.out.println("SOLUTION SIZE 1");
                int starting_node = Integer.parseInt(p.starting_node);
                if(nodeToInsert != starting_node){ //se o random não é o inicial
                    //System.out.println("IS NOT V0");
                    double newc = p.getEuclDist(nodeToInsert, starting_node);
                    //System.out.println(bestLocal.totalCost);
                    if(newc < p.cost_limit){ //se a edge não é maior que o custo
                        bestLocal.addEdge(starting_node, nodeToInsert);
                        bestLocal.addEdge(nodeToInsert, starting_node);
                        bestLocal.updateScore(p.getScoreList());
                        bestLocal.updateCost(p.euclDist);
                        size = bestLocal.getFreeNodesNumber();
                    }
                    
                }
            }else{
                 if(!(bestLocal.edgeAlreadyInserted(nodeToInsert))){ // senao
                    do{ //enquanto não achar
                        int nodesList[] = bestLocal.getSolutionNodes();
                        int index = generator.nextInt(nodesList.length); //cata um node
                        nodeNum1 = nodesList[index];
                        alreadyInserted = bestLocal.edgeAlreadyInserted(nodeNum1); //esse node tem que estar sendo usado!
                        //System.out.println(alreadyInserted);
                    }while(!alreadyInserted);
                    
                    int nodeNum2 = bestLocal.verifyConectedNode(nodeNum1);
                    System.out.printf("(%d,%d)\n", nodeNum1,nodeNum2);
                    double oldc = p.getEuclDist(nodeNum1, nodeNum2);
                    double newc = p.getEuclDist(nodeToInsert, nodeNum1) + p.getEuclDist(nodeToInsert, nodeNum2);
                    double newCost = bestLocal.getTotalCost() - oldc + newc; // tira o custo das arestas que não vão mais se conectar
                    
                    if(newCost <= p.cost_limit){ //(node1, node2)
                        bestLocal.removeEdge(nodeNum1); //remove edge[node1] = node2
                        //bestLocal.removeEdge(nodeNum2);
                        bestLocal.addEdge(nodeNum1, nodeToInsert); //create edge[node1] = nodeToInsert
                        bestLocal.addEdge(nodeToInsert, nodeNum2); //create edge[nodeToInsert] = node2 
                        bestLocal.updateScore(p.getScoreList());
                        bestLocal.updateCost(p.euclDist);
                        size = bestLocal.getFreeNodesNumber();
                    }
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
        //System.out.printf("dimension %d e N %d\n", p.dimension, N);
        
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
                    //bestLocal.print_edges();
                    bestLocal.removeEdge(randomNum);
                    bestLocal.addEdge(a1, a2); //fix the route
                    //System.out.println("new edge");
                    //bestLocal.print_edges();
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
