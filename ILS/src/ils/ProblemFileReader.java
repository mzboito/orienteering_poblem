/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author mzboito
 */
public class ProblemFileReader {

    public ProblemFileReader(){
   
    }

    public Problem read_file(String file_name) throws IOException{
        Problem p;
        try(BufferedReader br = new BufferedReader(new FileReader(file_name))) {
        String line = br.readLine();
        ArrayList<String> lines = new ArrayList();
        while (line != null) {
            line = br.readLine();
            lines.add(line);
        }
        if(lines.size() < 6){
            return null; //the file is too short
        }
        
        p = new Problem();
        boolean coord_flag = false;
        boolean score_flag = false;
        boolean depot_flag = false;

        for(int i = 0; i < lines.size(); i++){
            //System.out.println(lines.get(i));
            if(lines.get(i).startsWith("NAME")){
                String name = lines.get(i).split(" : ")[1];
                p.setName(name);
            }else{
            if(lines.get(i).startsWith("COMMENT")){
                String comment = lines.get(i).split(" : ")[1];
                p.setComment(comment);
            }else{
            if(lines.get(i).startsWith("DIMENSION")){
                //System.out.println("entrei");
                int dimension = Integer.parseInt(lines.get(i).split(": ")[1]);
                p.setDimension(dimension);
            }else{
            if(lines.get(i).startsWith("COST_LIMIT")){
                Double cost_limit = Double.parseDouble(lines.get(i).split(" : ")[1]);
                p.setCost_limit(cost_limit);
            }else{
            if(lines.get(i).startsWith("NODE_COORD_SECTION")){
                coord_flag = true;
            }else{
             if(lines.get(i).startsWith("NODE_SCORE_SECTION")){
                System.out.println("entrei");
                coord_flag = false;
                score_flag = true;
                System.out.println("entrei");
            }else{  
            if(coord_flag){ //1 140 145
                String name = lines.get(i).split(" ")[0];
                double X = Double.parseDouble(lines.get(i).split(" ")[1]);
                double Y = Double.parseDouble(lines.get(i).split(" ")[2]);
                //System.out.println(lines.get(i).split(" ")[2]);
                Node n = new Node(name, X, Y);
                p.addNode(n);
            }else{
              if(lines.get(i).startsWith("DEPOT_SECTION")){
                score_flag = false;
                depot_flag = true;  
            }else{    
            if(score_flag){ //2 36
                System.out.println("entrei");
                String label = lines.get(i).split(" ")[0];
                double score = Double.parseDouble(lines.get(i).split(" ")[1]);
                Node n = p.getNode(label);
                if(n == null){
                    System.out.println("<o>");
                }
                n.setScore(score);
            }else{
            if(depot_flag){
                p.setStarting_node(lines.get(i));
                break;
            }}}}}}}}}}}
        }
        
        p.setDistEuclSize(p.nodes.size()+1);
             
        for(int i=1; i<=p.nodes.size(); i++){
            for(int j=1; j<=p.nodes.size(); j++){
                p.addEuclDist(i, j, p.nodes.get(i-1).x, p.nodes.get(j-1).x, p.nodes.get(i-1).y, p.nodes.get(j-1).y);
            }
        }
        
        return p;
    }
    
    /* FILE EXAMPLE
        NAME : a8
        COMMENT : drilling problem (190)
        TYPE : OP
        DIMENSION: 8
        COST_LIMIT : 100
        EDGE_WEIGHT_TYPE : EUC_2D
        NODE_COORD_SECTION
        1 140 145
        2 148 169
        3 164 169
        4 172 169
        5 156 169
        6 140 169
        7 132 169
        8 124 169
        NODE_SCORE_SECTION
        1 0
        2 36
        3 32
        4 30
        5 34
        6 38
        7 40
        8 42
        DEPOT_SECTION
        1
        -1
        EOF
    */
    
    
}
