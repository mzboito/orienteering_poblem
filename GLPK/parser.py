import sys
import math

#our variables
v0 = -1
COST_LIMIT = -1
node_coord = False
node_score = False
depot_section = False
score = {}
d = {}
a = {}
n = {}


with open(sys.argv[1], "r") as inputFile:
    for line in inputFile:
        line = line.strip("\n")
        if line.startswith("COST_LIMIT"):
            COST_LIMIT = int(line.split(" : ")[1])
        elif line.startswith("NODE_COORD_SECTION"):
            node_coord = True
        elif line.startswith("NODE_SCORE_SECTION"):
            node_score = True
            node_coord = False
        elif line.startswith("DEPOT_SECTION"):
            node_score = False
            depot_section = True
        elif node_coord:
            index = int(line.split(" ")[0])
            x = float(line.split(" ")[1])
            y = float(line.split(" ")[2])
            n[index] = (x,y)
        elif node_score:
            index = int(line.split(" ")[0])
            s = int(line.split(" ")[1])
            score[index] = s
        elif depot_section:
            v0 = int(line)
            break

dimension = len(n) + 1

for i in range(1, dimension):
    for j in range(1, dimension):
        xd = n[i][0] - n[j][0]
        yd = n[i][1] - n[j][1]
        #print i, j
        d[(i,j)] =  int((math.sqrt(xd*xd + yd*yd))+0.5)
        #print d[(i,j)]

with open(sys.argv[1].split(".")[0]+ ".dat","w") as outputFile:
    outputFile.write("data;\n")
    outputFile.write("set NODES := ")
    for i in range(1, dimension):
        outputFile.write(str(i) + " ")
    outputFile.write(";\n")
    outputFile.write("param v0 := "+str(v0) + ";\n")
    outputFile.write("param COST_LIMIT := " + str(COST_LIMIT) + ";\n")
    outputFile.write("param score := ")
    for i in range(1,dimension):
        outputFile.write(str(i) + " " + str(score[i]) +"\n")
    outputFile.write(";\n")
    outputFile.write("param d : ")
    for i in range(1,dimension):
        outputFile.write(str(i) + " ")
    outputFile.write(":=\n")
    for i in range(1, dimension):
        outputFile.write(str(i))
        for j in range(1, dimension):
            outputFile.write(" "+ str(d[(i,j)]))
        outputFile.write("\n")
    outputFile.write(";")
    outputFile.write("end;")

'''
data;
set NODES := 0 1 2;
param v0 := 0;
param COST_LIMIT := 1000000 ;
param score := 0 200
1 200
2 300;
param d : 0 1 2 :=
0 0 199 0
1 200 0 3
2 100 2 0;
end;
'''

''' FILE EXAMPLE
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
'''
