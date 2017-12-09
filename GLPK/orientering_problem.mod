set NODES;

param v0; #nodo inicial
param COST_LIMIT; #custo máximo
param score{i in NODES}; #score do nodo i
param d{i in NODES, j in NODES}; #custo da aresta que liga i e j
param DIMENSION := card(NODES); #N número de nodos do problema

var v{i in NODES}, binary; #Vn, conjunto de vértices que pertencem à rota da solução, binária
var x{i in NODES, j in NODES}, binary; #variavel binaria de presença no conjunto solução
var u{i in NODES}; #Ui ordem do nodo i na rota

maximize obj: sum{i in NODES} score[i]*v[i];

#restrição 1: Usar o nodo
s.t. c1{i in NODES}: v[i] = sum{j in NODES} x[i,j];

#restrição 2: garantir o fluxo
s.t. c2{n in NODES}: sum{j in NODES} x[n,j] - sum{j in NODES} x[j,n] = 0;

#restrição 3: começa e terminada em v0
s.t. c3: sum{j in NODES: v0 != j} x[v0,j] = 1; #(sai de v0)
s.t. c4: sum{i in NODES: i != v0} x[i,v0] = 1; #(chega em v0)

#restrição 4: fazer um caminho entre os nodos da solução
s.t. c5{n in NODES: n != v0}: sum{i in NODES: i!=n} x[i,n] <= 1; #(vai ter no máximo uma aresta chegando em todo v que está na solução)
s.t. c6{n in NODES: n != v0}: sum{j in NODES: j != n} x[n,j] <= 1; #(vai ter no máximo uma aresta saindo de todo v que está na solução)


#restrição 5: não pode ser maior que o custo máximo
s.t. c7: sum{i in NODES, j in NODES} x[i,j] * d[i,j] <= COST_LIMIT;

#restrição 6: excluir rotas desconexas (MTZ)
s.t. c8: u[v0] = 1;
s.t. c9{i in NODES: i!= v0}: 2 <= u[i] <= DIMENSION;
s.t. c10{i in NODES, j in NODES: j!= v0 and i != v0}: u[i] - u[j] + 1 <= (DIMENSION - 1)* (1 - x[i,j]);

end;
