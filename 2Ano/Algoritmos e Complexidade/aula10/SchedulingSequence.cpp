//NMEC: 93264
//NOME: Rodrigo Lopes Martins
//
// João Manuel Rodrigues, AlgC, May 2020
//
// A SchedulingSequence based on a SORTED LIST.

//// PROCURE ... E COMPLETE ////

#include <assert.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "SchedulingSequence.h"

// You may add auxiliary definitions and declarations here, if you need to.



// Comparison function to be used in generic SortedList.
static int cmpTI(const void* a, const void* b) {
  return TimeIntervalCompare((TimeInterval*)a, (TimeInterval*)b);
}

// Create a SchedulingSequence capable of storing intervals.
// (Argument capacity is ignored!)
SchedulingSequence* SchedulingSequenceCreate(int capacity) {
  assert(capacity >= 0);
  // You must allocate space for the struct and create an empty intervals list!
  SchedulingSequence* sq = (SchedulingSequence*) malloc(sizeof(*sq));
  if (sq != NULL)
  {
    struct _SortedList* list = (struct _SortedList*)malloc(sizeof(list));
    sq->size = 0;
    sq->intervals = ListCreate(cmpTI);
  }
  
  return sq;
}

// Destroy SchedulingSequence *pss
void SchedulingSequenceDestroy(SchedulingSequence **pss) {
  assert(*pss != NULL);
  free(*pss);
  *pss = NULL;
  
}

int SchedulingSequenceIsEmpty(SchedulingSequence *ss) {
  return ss->size == 0;
}

int SchedulingSequenceIsFull(SchedulingSequence *ss) {
  return 0;  // NEVER Full!
}

// Add interval *ti to *ss.
// Return true on success,
// return false if *ti overlaps any of the intervals in *ss.
int SchedulingSequenceAdd(SchedulingSequence *ss, TimeInterval *ti) {
  assert(!SchedulingSequenceIsFull(ss));
  
  int i;

  struct _SortedList* l = ss->intervals;
  ListMoveToHead(l);
  
  for ( i = 0; i < ss->size; i++)
  {
    TimeInterval* t = (TimeInterval*)ListGetCurrentItem(l);
    
    if (TimeIntervalOverlaps(ti,t)==1)
    {
      return 0;
    }
    ListMoveToNext(l);
  }
  
  ListInsert(l,ti);
  ss->intervals = l;
  ss->size++;
  return 1;
}

// Get the interval at position (idx) of *ss.
// idx=0 is the first position idx=ss->size-1 is the last position.
// Precondition: 0 <= idx < ss->size.
TimeInterval *SchedulingSequenceGet(SchedulingSequence *ss, int idx) {
  assert (0 <= idx && idx < ss->size);
  
  struct _SortedList* l = ss->intervals;
  ListMoveToHead(l);
  int i;
  
  for ( i = 0; i < ss->size; i++)
  {
    if (ListGetCurrentPos(l)==idx)
    {
      return (TimeInterval*)ListGetCurrentItem(l);
    }
    ListMoveToNext(l);
  }
  return NULL;
}

// Remove the interval at position (idx) of *ss, and return it.
// idx=0 is the first position idx=ss->size-1 is the last position.
// Precondition: 0 <= idx < ss->size.
TimeInterval *SchedulingSequencePop(SchedulingSequence *ss, int idx) {
  assert (0 <= idx && idx < ss->size);
  // This implies !SchedulingSequenceIsEmpty(ss).
  assert(!SchedulingSequenceIsEmpty(ss));

  struct _SortedList* l = ss->intervals;
  ListMove(l,idx);
  
  TimeInterval* t = (TimeInterval*)ListRemoveCurrent(l);
  ss->intervals = l;
  ss->size--;
  return t;
}

// You may add auxiliary definitions and declarations here, if you need to.


