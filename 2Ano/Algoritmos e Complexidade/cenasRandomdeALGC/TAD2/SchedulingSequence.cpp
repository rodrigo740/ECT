//NMEC: 93264
//NOME: Rodrigo Lopes Martins

// Complete the functions (marked by ...)
// so that it passes all tests in DateTimeTest.

#include <assert.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "SchedulingSequence.h"

// You may add auxiliary definitions and declarations here, if you need to.

SchedulingSequence* sortIntervals(SchedulingSequence *ss){
  int i,troca = 0;

  for ( i = 0; i < ss->size-1; i++)
  {
    if (TimeIntervalCompare(ss->intervals[i],ss->intervals[i+1])==1)
    {
      TimeInterval* temp = ss->intervals[i];
      ss->intervals[i] = ss->intervals[i+1];
      ss->intervals[i+1] = temp;
      troca = 1;
    }
  }
  if (troca)
  {
    sortIntervals(ss);
  }
  
  return ss;
}



// Create a SchedulingSequence capable of storing up to (capacity) intervals.
SchedulingSequence* SchedulingSequenceCreate(int capacity) {
  assert(capacity >= 0);
  // You must allocate space for the struct and for the intervals array!
  TimeInterval** times = (TimeInterval**)malloc(capacity * sizeof(**times));
  
  SchedulingSequence* sq = (SchedulingSequence*) malloc(sizeof(*sq));
  sq->intervals = times;
  sq->capacity = capacity;
  sq->size = 0;
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
  return ss->size == ss->capacity;
}

// Add interval *ti to *ss.
// Return true on success,
// return false if *ti overlaps any of the intervals in *ss.
int SchedulingSequenceAdd(SchedulingSequence *ss, TimeInterval *ti) {
  assert(!SchedulingSequenceIsFull(ss));
  int i;
  sortIntervals(ss);

  for ( i = 0; i < ss->size; i++)
  {
    if (TimeIntervalOverlaps(ss->intervals[i],ti)>0)
    {
      return 0;
    }
  }

  ss->intervals[ss->size] = ti;
  ss->size++;
  sortIntervals(ss);
  return 1;
}

// Get the interval at position (idx) of *ss.
// idx=0 is the first position idx=ss->size-1 is the last position.
// Precondition: 0 <= idx < ss->size.
TimeInterval *SchedulingSequenceGet(SchedulingSequence *ss, int idx) {
  assert (0 <= idx && idx < ss->size);
  return ss->intervals[idx];
}

// Remove the interval at position (idx) of *ss, and return it.
// idx=0 is the first position idx=ss->size-1 is the last position.
// Precondition: 0 <= idx < ss->size.
TimeInterval *SchedulingSequencePop(SchedulingSequence *ss, int idx) {
  assert (0 <= idx && idx < ss->size);
  // This implies !SchedulingSequenceIsEmpty(ss).
  assert(!SchedulingSequenceIsEmpty(ss));
  TimeInterval *temp = ss->intervals[idx];
  ss->intervals[idx] = NULL;
  
  int i;
  for ( i = idx; i < ss->size-1; i++)
  {
    ss->intervals[i] = ss->intervals[i+1];
  }
  
  ss->size--;
  sortIntervals(ss);
  return temp;
}

// You may add auxiliary definitions and declarations here, if you need to.


