/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ils;

import static java.lang.Math.sqrt;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author mzboito
 */
public class ILS {

    /**
     * @param args the command line arguments
     */
    ArrayList<Node> usedNodes = new ArrayList();
    ArrayList<Node> auxNodes = new ArrayList();
    Problem p;
    
    public void debuggerPrint(Problem p){
        /* Imprimir o que foi lido no arquivo referente ao node que é o que eu preciso */
        ArrayList<Node> nodes = p.getNodes();
        
        for(Node node : nodes)
        {
            System.out.printf("%s     ", node.getLabel());
            System.out.printf("x: %.0f", node.getX());
            System.out.printf("  y: %.0f\n", node.getY());
        }        
         
        System.out.printf("\nTamanho da matriz de distancias: %d", p.euclDist.length);
        for(int i=0; i<=p.nodes.size(); i++){
            System.out.println("\n");
            //System.out.printf("%d: ", i);
            for(int j=0; j<=p.nodes.size(); j++){
                double dist = p.getEuclDist(i, j);
                System.out.printf("%.0f  ", dist); 
            }
        }
    }
    
    public static void main(String[] args) throws IOException {

        //get from user path and seed 
        String path = args[0];
        int seed = Integer.parseInt(args[1]);
        //System.out.println(path);
        
        //get problem
        Problem p = new ProblemFileReader().read_file(path);
        //debuggerPrint(p);
        
        //initial set of nodes
        
         
        
        // imagino que algum lugar aqui le os arquivos
        Solution best = new Solution();
        ILS ils = new ILS(p);
        //best = ils.iteratedSearch(nodes);      
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
}
