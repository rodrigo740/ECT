//NMEC: 93264
//NOME: Rodrigo Lopes Martins
//
// João Manuel Rodrigues, AlgC, May 2020
// Joaquim Madeira, AlgC, May 2020
//
// A SchedulingSequence based on a BINARY SEARCH TREE.

//// PROCURE ... E COMPLETE ////

#include "SchedulingSequence.h"

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// You may add auxiliary definitions and declarations here, if you need to.



// Comparison function to be used in generic BSTree.
static int cmpTI(const void *a, const void *b) {
  return TimeIntervalCompare((TimeInterval *)a, (TimeInterval *)b);
}

// Printing function to be used in generic BSTree.
static void printTI(void *ti) {
  printf("%s\n", TimeIntervalFormat((TimeInterval *)ti));
}

// Create a SchedulingSequence capable of storing intervals.
// (Argument capacity is ignored!)
SchedulingSequence *SchedulingSequenceCreate(int capacity) {
  assert(capacity >= 0);
  // You must allocate space for the struct and create an empty intervals list!
  SchedulingSequence* sq = (SchedulingSequence*) malloc(sizeof(*sq));
  if (sq != NULL)
  {
    sq->size = 0;
    struct _BSTree* bst = (struct _BSTree*)malloc(sizeof(bst));
    sq->intervals = BSTreeCreate(cmpTI, printTI);
  }

  return sq;
}

// Destroy SchedulingSequence *pss
void SchedulingSequenceDestroy(SchedulingSequence **pss) {
  assert(*pss != NULL);
  free(*pss);
  *pss = NULL;
  
}

int SchedulingSequenceIsEmpty(SchedulingSequence *ss) { return ss->size == 0; }

int SchedulingSequenceIsFull(SchedulingSequence *ss) {
  return 0;  // NEVER Full!
}

// Add interval *ti to *ss.
// Return true on success,
// return false if *ti overlaps any of the intervals in *ss.
int SchedulingSequenceAdd(SchedulingSequence *ss, TimeInterval *ti) {
  assert(!SchedulingSequenceIsFull(ss));

  BSTree* bst = ss->intervals;
  
  if (BSTreeContains(bst,ti))
  {
    return 0;
  }
  BSTreeAdd(bst,ti);
  ss->size++;
  return 1;
  
}

// Get the interval at position (idx) of *ss.
// idx=0 is the first position idx=ss->size-1 is the last position.
// Precondition: 0 <= idx < ss->size.
TimeInterval *SchedulingSequenceGet(SchedulingSequence *ss, int idx) {
  assert(0 <= idx && idx < ss->size);
  
  BSTree* bst1 = ss->intervals;
  return (TimeInterval*)BSTreeGetKthItem(bst1,idx);
}

// Remove the interval at position (idx) of *ss, and return it.
// idx=0 is the first position idx=ss->size-1 is the last position.
// Precondition: 0 <= idx < ss->size.
TimeInterval *SchedulingSequencePop(SchedulingSequence *ss, int idx) {
  assert(0 <= idx && idx < ss->size);
  // This implies !SchedulingSequenceIsEmpty(ss).
  assert(!SchedulingSequenceIsEmpty(ss));
  
  BSTree* bst = ss->intervals;
  TimeInterval* t = (TimeInterval*)BSTreeRemoveKthItem(bst,idx);
  ss->size--;
  return t;
}

// You may add auxiliary definitions and declarations here, if you need to.

