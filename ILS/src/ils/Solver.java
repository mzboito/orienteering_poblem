/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ils;

import java.util.ArrayList;

/**
 *
 * @author mzboito
 */
public class Solver {
    ArrayList<Node> usedNodes;
    ArrayList<Node> auxNodes;
    Problem p;
    Solution s;
    int seed;
    int maxSteps;

    public Solver(Problem p, int seed, int maxSteps) {
        this.p = p;
        this.seed = seed;
        this.maxSteps = maxSteps;
        this.usedNodes = usedNodes = new ArrayList();
        this.auxNodes = new ArrayList();
        if(p.getStarting_node() != null){
            s.setTrivialSolution(p.getStarting_node());
        }
        System.out.println("sai");
    }
    
    public void exec(){
        
        //here goes the logic
        
        
 
    }
    
    
    
    // busca local iterada
    public Solution iteratedSearch(ArrayList<Node> nodes)
    {
        int maxIt = 100;
        int maxImprov = 50;
        
        auxNodes.add(nodes.get(0));
        

        Solution bestSolution = new Solution();
        Solution auxSolution = new Solution();
        
        bestSolution.nodeList.add(nodes.get(0));
        
        while(maxIt > 0){
            
            //local search
            // perturbation
            // accept
            
            maxIt--;
        }
        
        return bestSolution;
    }
    
    /* Tipo um hill clibing */
    public Solution localSearch(Solution bestLocal, ArrayList<Node> nodes, int maxImprov)
    {
        return bestLocal;
    }
    
    // Perturba a bestSolution encontrada removendo n nodos da solução
    public Solution perturbSolution(Solution bestLocal)
    {
        return bestLocal;
    }

    // Perturba a bestSolution trocando 2 nodos de lugar (talvez mudar pra k-opt)
    public Solution twoOpt(Solution bestLocal)
    {
        return bestLocal;
    }

    public void writeSolution(String output) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
