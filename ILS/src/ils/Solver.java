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
    int seed;

    public Solver(Problem p, int seed) {
        this.p = p;
        this.seed = seed;
        this.usedNodes = usedNodes = new ArrayList();
        this.auxNodes = new ArrayList();
    }
    
    
    
    
}
