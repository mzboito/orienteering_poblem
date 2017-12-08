#set DIMENSION; N número de nodos do problema
set ROUTE; #R, conjunto de vértices da rota encontrada

param DIMENSION;
param v0; #nodo inicial
param COST_LIMIT; #custo máximo
param score{i in DIMENSION}; #score do nodo i
param d{i in DIMENSION, j in DIMENSION}; #custo da aresta que liga i e j
param a{i in DIMENSION, j in 	DIMENSION}; #Aij, número de arestas que ligam o vértice i ao vértice j

var v{i in DIMENSION}, binary; #Vn, conjunto de vértices que pertencem à rota R, binária
var x{i in N, i in N}, binary; #variavel binaria de presença no conjunto solução
var u{i in N}, binary; #Ui ordem do nodo i na rota

maximize obj: sum{i in DIMENSION} score[i]*v[i];

#restrição 1: cada vn aparece só uma vez
s.t. c1{j in R}:
	sum{i in R} v[i]*a[i,j] <= 1; #(sai no max uma aresta de Vi E à solução)

s.t. c2{i in R}:
	sum{j in R} v[i]*a[i,j] <= 1; #(chega no max uma aresta para Vj E à solução)

#restrição 2: começa e terminada em v0
s.t. c3{v0 != j}:
	sum{j in R} x[v0,j] = 1; #(sai de v0)

s.t. c4{v0 != i}:
	sum{i in R} x[i,v0] = 1; #(chega em v0)

#restrição 3: fazer um caminho entre os nodos da solução
s.t. c5{v in R, v != v0}:
	sum{i in R} X[i,v] <= 1; #(vai ter no máximo uma aresta chegando em todo v que está na solução)

s.t. c6{v in R, v != v0}:
	sum{j in R} X[v,j] <= 1; #(vai ter no máximo uma aresta saindo de todo v que está na solução)

#restrição 4: não pode ser maior que o custo máximo
s.t. c7:
	sum{i in DIMENSION, j in DIMENSION} x[i,j] * d[i,j] <= COST_LIMIT;

#restrição 5: excluir rotas desconexas (MTZ)
s.t. c8:
	u[1] = 1;

s.t. c9{i in DIMENSION, i!= 1}:
	2 <= u[i] <= DIMENSION;

s.t. c10{i in DIMENSION, j in DIMENSION, i != 1, j!= 1}:
	u[i] - u[j] + 1 <= (DIMENSION - 1)* (1 - x[i,j]);

end;
