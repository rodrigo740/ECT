//
// Joaquim Madeira, AlgC, June 2020
// João Manuel Rodrigues, AlgC, June 2020
//
// GraphBellmanFord - Bellman-Ford Alg. for the Shortest Paths Tree
//

#include "GraphBellmanFord.h"

#include <assert.h>
#include <limits.h>
#include <stdio.h>
#include <stdlib.h>

#include "Graph.h"
#include "IntegersStack.h"

struct _GraphBellmanFord {
  int* distance;
  int* predecessor;
  Graph* graph;
  unsigned int startVertex;
};

GraphBellmanFord* GraphBellmanFordExecute(Graph* g, unsigned int startVertex) {
  assert(g != NULL);
  assert(0 <= startVertex && startVertex < GraphGetNumVertices(g));

  GraphBellmanFord* bellmanFord =
      (GraphBellmanFord*)malloc(sizeof(struct _GraphBellmanFord));
  assert(bellmanFord != NULL);

  unsigned int numVertices = GraphGetNumVertices(g);

  //
  // COMPLETAR !!
  //
  // CRIAR e INICIALIZAR os campos de bellmanFord
  bellmanFord->distance = (int*) malloc(numVertices* sizeof(int));
  assert(bellmanFord->distance != NULL);

  bellmanFord->predecessor = (int*) malloc(numVertices* sizeof(int));
  assert(bellmanFord->predecessor != NULL);

  //Inicializar os rotulos
  for (int i = 0; i < numVertices; i++) {
    bellmanFord->distance[i] = INT_MAX;
    bellmanFord->predecessor[i] = -1;
  }
  bellmanFord->distance[startVertex] = 0;

  bellmanFord->graph = g;
  bellmanFord->startVertex = startVertex;

  // EXECUTAR O ALGORITMO
  // na minha implementaçao o algoritmo n funciona para os vertices: 1,2,3 e 4

  Stack* s = StackCreate(numVertices);
  StackPush(s,bellmanFord->startVertex);

  while (!StackIsEmpty(s))
  {
    for (int i = 1; i < (numVertices-1); i++)
    {
      int u = StackPop(s);
      unsigned int* v = GraphGetAdjacentsTo(bellmanFord->graph,u);
      int uDist = bellmanFord->distance[u];
      int* w = GraphGetDistancesToAdjacents(bellmanFord->graph,u);

      for (int j = 1; j <= w[0]; j++)
      {
        int vDist = bellmanFord->distance[v[j]];
        if ((uDist + w[j]) < vDist)  
        {
          bellmanFord->distance[v[j]] = uDist + w[j];
          bellmanFord->predecessor[v[j]] = u;
          StackPush(s,v[j]);
        } 
      }
      
      free(w);
      free(v);
    }
  }
  
  StackDestroy(&s);
  return bellmanFord;
}

void GraphBellmanFordDestroy(GraphBellmanFord** p) {
  assert(*p != NULL);

  GraphBellmanFord* aux = *p;

  free(aux->distance);
  free(aux->predecessor);

  free(*p);
  *p = NULL;
}

// Getting the result

unsigned int GraphBellmanFordHasPathTo(const GraphBellmanFord* p,
                                       unsigned int v) {
  assert(0 <= v && v < GraphGetNumVertices(p->graph));

  return (p->distance[v] != INT_MAX);
}

int GraphBellmanFordDistanceTo(const GraphBellmanFord* p, unsigned int v) {
  assert(0 <= v && v < GraphGetNumVertices(p->graph));

  return p->distance[v];
}

Stack* GraphBellmanFordPathTo(const GraphBellmanFord* p, unsigned int v) {
  assert(0 <= v && v < GraphGetNumVertices(p->graph));

  Stack* s = StackCreate(GraphGetNumVertices(p->graph));

  if (p->distance[v] == INT_MAX) {
    return s;
  }

  // Store the path
  for (unsigned int current = v; current != p->startVertex;
       current = p->predecessor[current]) {
    StackPush(s, current);
  }

  StackPush(s, p->startVertex);

  return s;
}

// DISPLAYING on the console

void GraphBellmanFordShowPath(const GraphBellmanFord* p, unsigned int v) {
  assert(0 <= v && v < GraphGetNumVertices(p->graph));

  Stack* s = GraphBellmanFordPathTo(p, v);

  while (StackIsEmpty(s) == 0) {
    printf("%d ", StackPop(s));
  }

  printf(" --- Distance = %d", p->distance[v]);

  StackDestroy(&s);
}

void GraphBellmanFordDisplay(const GraphBellmanFord* p) {
  // COMPLETAR !!
  GraphDisplay(p->graph);
}
