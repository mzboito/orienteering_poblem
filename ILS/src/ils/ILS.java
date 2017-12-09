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
        /*for(String s: args){
            System.out.println(s);
        }*/
        
        String path = args[0];
        path = "C:/Ble/UFRGS/CIC/5SEMESTRE/Otimização/instances/a8.oplib";
        int seed = Integer.parseInt(args[1]);
        String output = args[2];
        System.out.print(output);
        int maxSteps = Integer.parseInt(args[3]);
        System.out.println(maxSteps);
        //System.out.println(path);
        
        //path = "/home/mzboito/Documents/orienteering_poblem/ILS/dist/ILS.jar";
        
        //get problem
        Problem p = new ProblemFileReader().read_file(path);
        //debuggerPrint(p);

        Solver ils = new Solver(p, seed, maxSteps);
        ils.exec();
        //ils.writeSolution(output);   
    } 
        //ils.writeSolution(output);
}
