//
// Joaquim Madeira, AlgC, May 2020
// João Manuel Rodrigues, AlgC, May 2020
//
// Graph EXAMPLE
//

#include "Graph.h"
#include "GraphDijkstra.h"

int main(void) {
  Graph* g01 = GraphCreate(6, 0, 1);

  GraphAddWeightedEdge(g01, 0, 5, 1);
  GraphAddWeightedEdge(g01, 2, 4, 2);
  GraphAddWeightedEdge(g01, 2, 3, 3);
  GraphAddWeightedEdge(g01, 1, 2, 4);
  GraphAddWeightedEdge(g01, 0, 1, 5);
  GraphAddWeightedEdge(g01, 3, 4, 6);
  GraphAddWeightedEdge(g01, 3, 5, 1);
  GraphAddWeightedEdge(g01, 0, 2, 8);

  GraphDisplay(g01);

  int vertex = 5;

  GraphDijkstra* shortestPathsDijkstra = GraphDijkstraExecute(g01, vertex);

  for (int i = 0; i < GraphGetNumVertices(g01); i++) {
    printf("Path from %d to %d: ", vertex, i);
    GraphDijkstraShowPath(shortestPathsDijkstra, i);
    printf("\n");
  }

  GraphDijkstraDestroy(&shortestPathsDijkstra);

  GraphDestroy(&g01);

  return 0;
}
