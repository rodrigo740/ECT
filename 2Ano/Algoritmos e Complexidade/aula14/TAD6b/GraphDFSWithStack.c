//
// Joaquim Madeira, AlgC, May 2020
// João Manuel Rodrigues, AlgC, May 2020
//
// GraphDFS - STACK-based Depth-First Search
//

#include "GraphDFSWithStack.h"

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>

#include "Graph.h"
#include "IntegersStack.h"

struct _GraphDFSWithStack {
  unsigned int* marked;
  int* predecessor;
  Graph* graph;
  unsigned int startVertex;
};

GraphDFSWithStack* GraphDFSWithStackExecute(Graph* g,
                                            unsigned int startVertex) {
  assert(g != NULL);
  assert(0 <= startVertex && startVertex < GraphGetNumVertices(g));

  GraphDFSWithStack* traversal =
      (GraphDFSWithStack*)malloc(sizeof(struct _GraphDFSWithStack));
  assert(traversal != NULL);

  unsigned int numVertices = GraphGetNumVertices(g);

  traversal->marked = (unsigned int*)calloc(numVertices, sizeof(unsigned int));
  assert(traversal->marked != NULL);

  traversal->predecessor = (int*)malloc(numVertices * sizeof(int));
  assert(traversal->predecessor != NULL);
  for (int i = 0; i < numVertices; i++) {
    traversal->predecessor[i] = -1;
  }

  traversal->predecessor[startVertex] = 0;
  traversal->graph = g;
  traversal->startVertex = startVertex;

  // EFETUAR A TRAVESSIA

  // COMPLETAR !!

  Stack* stack = StackCreate(numVertices);
  StackPush(stack,startVertex);
  traversal->marked[startVertex] = 1;

  while (!StackIsEmpty(stack))
  {
    int t = StackPop(stack);
    unsigned int* adjacentes = GraphGetAdjacentsTo(g,t);
    
    for (int i = 1; i <= adjacentes[0]; i++)
    {
      unsigned int aux = adjacentes[i];
      if (traversal->marked[aux] == 0)
      {
        traversal->predecessor[aux] = t;
        StackPush(stack,aux);
        traversal->marked[aux] = 1;
      }
    }
  }
  StackDestroy(&stack);
  return traversal;
}

void GraphDFSWithStackDestroy(GraphDFSWithStack** p) {
  assert(*p != NULL);

  GraphDFSWithStack* aux = *p;

  free(aux->marked);
  free(aux->predecessor);

  free(*p);
  *p = NULL;
}

// Getting the result

unsigned int GraphDFSWithStackHasPathTo(const GraphDFSWithStack* p,
                                        unsigned int v) {
  assert(0 <= v && v < GraphGetNumVertices(p->graph));

  return p->marked[v];
}

Stack* GraphDFSWithStackPathTo(const GraphDFSWithStack* p, unsigned int v) {
  assert(0 <= v && v < GraphGetNumVertices(p->graph));

  Stack* s = StackCreate(GraphGetNumVertices(p->graph));

  if (p->marked[v] == 0) {
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

void GraphDFSWithStackShowPath(const GraphDFSWithStack* p, unsigned int v) {
  assert(0 <= v && v < GraphGetNumVertices(p->graph));

  Stack* s = GraphDFSWithStackPathTo(p, v);

  while (StackIsEmpty(s) == 0) {
    printf("%d ", StackPop(s));
  }

  StackDestroy(&s);
}

void GraphDFSWithStackDisplay(const GraphDFSWithStack* p) {
  // COMPLETAR !!
  GraphDisplay(p->graph);
  
}
