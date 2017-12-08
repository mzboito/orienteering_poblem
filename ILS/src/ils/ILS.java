/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ils;

import static java.lang.Math.sqrt;
import java.io.IOException;

/**
 *
 * @author mzboito
 */
public class ILS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        Problem p = new ProblemFileReader().read_file("/home/mzboito/Downloads/instances/a8.oplib");
        
        // imagino que algum lugar aqui le os arquivos
        String str = new String();
        Node node = new Node(str, 5.9, 5.6, 7.8);
        
    }
    
    public Solution initSearch(Node node)
    {
        String emptyStr = "";
        Node initNode = new Node(emptyStr, 0, 0, 0);
        // bestSolution inicial deve ser vazia para ser atualizada
        Solution bestSolution = new Solution(node, 0, 0);
        
        return bestSolution;
    }
    
    /* Coloquei como ta no read me, mas vou fazer testes e ver se é isso mesmo */
    public double euclideanDistance(double x1, double x2, double y1, double y2)
    {
        double distance = 0.0;
        
        double xd = x1 - x2;
        double yd = y1 - y2;
        double dab = (int)(sqrt((xd*xd) + (yd*yd)) + 0.5);
        
        return distance;
    }
    
}
