#include "Graph.h"
#include "GraphDFSWithStack.h"

int main(void) {
  Graph* g01 = GraphCreate(6, 0, 0);

  GraphAddEdge(g01, 0, 5);
  GraphAddEdge(g01, 2, 4);
  GraphAddEdge(g01, 2, 3);
  GraphAddEdge(g01, 1, 2);
  GraphAddEdge(g01, 0, 1);
  GraphAddEdge(g01, 3, 4);
  GraphAddEdge(g01, 3, 5);
  GraphAddEdge(g01, 0, 2);

  GraphDisplay(g01);

  GraphDFSWithStack* traversal = GraphDFSWithStackExecute(g01, 0);

  printf("Path from 0 to 5: ");
  GraphDFSWithStackShowPath(traversal, 5);
  printf("\n");

  GraphDFSWithStackDestroy(&traversal);

  GraphDestroy(&g01);

  return 0;
}