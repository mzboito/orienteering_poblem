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
        
        //TODO SOLUTION A ORDEM DAS CONEXÕES a[i,j]
    }
    
    
    public Solution localSearch(Solution bestLocal, int maxImprov) { //adiciona n vezes, usa seed randomico pra adicionar nós, tenta maximizar o score e controlar o custo
        return bestLocal;
    }
    
    // Perturba a bestSolution encontrada removendo n nodos da solução
    public Solution perturbSolution(Solution bestLocal) { //remove uma quantidade N de nós da solução randomicamente e cola a solução
        
        //gerar o numero de coisas pra remover
        //pegar indices aleatorios
        //criar uma nova solucao removendo os indices aleatorios
        
        Random generator = new Random(seed);
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        //int max = bestLocal.nodeList.size();
        int min = 0;
        //int randomNum = generator.nextInt((max - min) + 1) + min; //generates a number
        
        
        return null;
    }

    public void writeSolution(String output) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
