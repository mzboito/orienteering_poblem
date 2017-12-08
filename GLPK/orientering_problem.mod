



EXEMPLO DE FORMATO
set N;
set C;
set K;

param c{i in N, j in N}; #custo da aresta entre o cliente i e o cliente j.
param d{i in N}, integer; #demanda do cliente i
param Q, integer; #capacidade dos veículos.
param n := card(C); #número de clientes, n=|C|.
param v := card(K); #número de caminhões, n=|K|.

var x{i in N, j in N, k in K: i!=j}, binary; #Binária
var y{i in N, k in K}, >= 0;
var flow{i in N, j in N, k in K: i!=j}, >= 0;

minimize obj: sum{i in N, j in N, k in K: i!=j} c[i,j] * x[i,j,k];

# Restrição de fluxo:
s.t. c1{t in C, k in K}:
	sum{i in N: t!=i} flow[i,t,k] - sum{j in N: t!=j} flow[t,j,k] = d[t]*y[t,k];

# Restrição de fluxo:
s.t. c2{t in C, k in K}:
    sum{i in N: t!=i} x[i,t,k] - sum{j in N: t!=j} x[t,j,k] = 0;

# A demanda do cliente deve ser atendida:
s.t. c3{i in C}:
    sum{k in K} y[i,k] = 1;

# Todos os veículos partem do depósito.
s.t. c4{k in K}:
    sum{j in C} x[0,j,k] = 1;

# A capacidade do caminhão deve ser respeitada
s.t. c5{i in N, j in N, k in K: i!=j}:
    flow[i,j,k] <= Q * x[i,j,k];

# Todos os veículos chegam no depósito.
#s.t. c5{k in K}:
#    sum{j in C} x[j,0,k] = 1;

end;
