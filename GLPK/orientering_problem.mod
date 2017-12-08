set NODES;

param DIMENSION := card(NODES); #N número de nodos do problema
param v0; #nodo inicial
param COST_LIMIT; #custo máximo
param score{i in NODES}; #score do nodo i
param d{i in NODES, j in NODES}; #custo da aresta que liga i e j
param a{i in NODES, j in 	NODES}; #Aij, número de arestas que ligam o vértice i ao vértice j

var v{i in NODES}, binary; #Vn, conjunto de vértices que pertencem à rota da solução, binária
var x{i in NODES, j in NODES}, binary; #variavel binaria de presença no conjunto solução
var u{i in NODES}, binary; #Ui ordem do nodo i na rota

maximize obj: sum{i in NODES} score[i]*v[i];

#restrição 1: cada vn aparece só uma vez
s.t. c1{j in NODES}:
	sum{i in NODES} v[i]*a[i,j] <= 1; #(sai no max uma aresta de Vi E à solução)

s.t. c2{i in NODES}:
	sum{j in NODES} v[i]*a[i,j] <= 1; #(chega no max uma aresta para Vj E à solução)

#restrição 2: começa e terminada em v0
s.t. c3:
	sum{j in NODES: v0 != j} x[v0,j] = 1; #(sai de v0)

s.t. c4:
	sum{i in NODES: i != v0} x[i,v0] = 1; #(chega em v0)

#restrição 3: fazer um caminho entre os nodos da solução
s.t. c5{v in NODES: v != v0}:
	sum{i in NODES: i!=v} X[i,v] <= 1; #(vai ter no máximo uma aresta chegando em todo v que está na solução)

s.t. c6{v in NODES: v != v0}:
	sum{j in NODES: j != v} X[v,j] <= 1; #(vai ter no máximo uma aresta saindo de todo v que está na solução)

#restrição 4: não pode ser maior que o custo máximo
s.t. c7:
	sum{i in NODES, j in NODES} x[i,j] * d[i,j] <= COST_LIMIT;

#restrição 5: excluir rotas desconexas (MTZ)
s.t. c8:
	u[1] = 1;

s.t. c9{i in NODES: i!= 1}:
	2 <= u[i] <= DIMENSION;

s.t. c10{i in NODES: i != 1, j in NODES: j!= 1}:
	u[i] - u[j] + 1 <= (DIMENSION - 1)* (1 - x[i,j]);

end;
