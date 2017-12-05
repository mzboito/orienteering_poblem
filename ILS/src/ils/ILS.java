/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ils;

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
    }
    
}
