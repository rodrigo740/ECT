//
// Joaquim Madeira, AlgC, June 2020
// João Manuel Rodrigues, AlgC, June 2020
//
// GraphShortestPathsWithQueue - QUEUE algorithm for the Shortest Paths Tree
//

#include "GraphShortestPathsWithQueue.h"

#include <assert.h>
#include <limits.h>
#include <stdio.h>
#include <stdlib.h>

#include "Graph.h"
#include "IntegersStack.h"
#include "IntegersQueue.h"

struct _GraphShortestPathsWithQueue {
  int* distance;
  int* predecessor;
  Graph* graph;
  unsigned int startVertex;
};

GraphShortestPathsWithQueue* GraphShortestPathsWithQueueExecute(
    Graph* g, unsigned int startVertex) {
  assert(g != NULL);
  assert(0 <= startVertex && startVertex < GraphGetNumVertices(g));

  GraphShortestPathsWithQueue* ShortestPathsWithQueue =
      (GraphShortestPathsWithQueue*)malloc(
          sizeof(struct _GraphShortestPathsWithQueue));
  assert(ShortestPathsWithQueue != NULL);

  unsigned int numVertices = GraphGetNumVertices(g);

  //
  // COMPLETAR !!
  //
  // CRIAR e INICIALIZAR os campos de ShortestPathsWithQueue
  ShortestPathsWithQueue->distance = (int*) malloc(numVertices* sizeof(int));
  assert(ShortestPathsWithQueue->distance != NULL);
  ShortestPathsWithQueue->predecessor = (int*) malloc(numVertices*  sizeof(int));
  assert(ShortestPathsWithQueue->predecessor != NULL);
  
  //Inicializar os rotulos
  for (int i = 0; i < numVertices; i++) {
    ShortestPathsWithQueue->distance[i] = INT_MAX;
    ShortestPathsWithQueue->predecessor[i] = -1;
  }

  ShortestPathsWithQueue->distance[startVertex] = 0;
  ShortestPathsWithQueue->predecessor[startVertex]=0;

  ShortestPathsWithQueue->graph = g;
  ShortestPathsWithQueue->startVertex = startVertex;

  Queue* q = QueueCreate(numVertices);
  QueueEnqueue(q,ShortestPathsWithQueue->startVertex);

  int pertence[numVertices];
  for(int i = 0;i<numVertices;i++){
    pertence[i]=0;
  }

  while (!QueueIsEmpty(q))
  {
    int u = QueueDequeue(q);
    pertence[u] = 0;
    unsigned int* w = GraphGetAdjacentsTo(g,u);
    int uDist = ShortestPathsWithQueue->distance[u];
    int* vizinhos = GraphGetDistancesToAdjacents(ShortestPathsWithQueue->graph,u);

    for (int i = 1; i <= w[0]; i++)
    { 
      int vDist = ShortestPathsWithQueue->distance[w[i]];
      if ((uDist + vizinhos[i]) < vDist)
      {
        ShortestPathsWithQueue->distance[w[i]] = uDist+vizinhos[i];
        ShortestPathsWithQueue->predecessor[w[i]] = u;
        if(!pertence[w[i]]){
          QueueEnqueue(q,w[i]);
        }
      } 
    }
    free(w);
    free(vizinhos);
  }
  QueueDestroy(&q);
  
  return ShortestPathsWithQueue;
}

void GraphShortestPathsWithQueueDestroy(GraphShortestPathsWithQueue** p) {
  assert(*p != NULL);

  GraphShortestPathsWithQueue* aux = *p;

  free(aux->distance);
  free(aux->predecessor);

  free(*p);
  *p = NULL;
}

// Getting the result

unsigned int GraphShortestPathsWithQueueHasPathTo(
    const GraphShortestPathsWithQueue* p, unsigned int v) {
  assert(0 <= v && v < GraphGetNumVertices(p->graph));

  return (p->distance[v] != INT_MAX);
}

int GraphShortestPathsWithQueueDistanceTo(const GraphShortestPathsWithQueue* p,
                                          unsigned int v) {
  assert(0 <= v && v < GraphGetNumVertices(p->graph));

  return p->distance[v];
}

Stack* GraphShortestPathsWithQueuePathTo(const GraphShortestPathsWithQueue* p,
                                         unsigned int v) {
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

void GraphShortestPathsWithQueueShowPath(const GraphShortestPathsWithQueue* p,
                                         unsigned int v) {
  assert(0 <= v && v < GraphGetNumVertices(p->graph));

  Stack* s = GraphShortestPathsWithQueuePathTo(p, v);

  while (StackIsEmpty(s) == 0) {
    printf("%d ", StackPop(s));
  }

  printf(" --- Distance = %d", p->distance[v]);

  StackDestroy(&s);
}

void GraphShortestPathsWithQueueDisplay(const GraphShortestPathsWithQueue* p) {
  GraphDisplay(p->graph);
}
