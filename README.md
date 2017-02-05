# csaTest
Technical test using CSA

## Preliminaries

The code seems to be a copy from C++ in the writing :).
I've done some changes to transform it in a true java form :

- dispatch class to separate files
- provide accessors
- fix variables visibility
- use try with resources for IO operations
- The program can run without ruby, full Java Power ! ^^
- Use parallelism to efficient reading/computing on multicore CPU
- Incorporate bench in execution

## Limitations

While this algorithm find routes, there are the following limitations to be aware of:

- 1 - it computes the earliest arrival. However, a better solution might leaver later and arrive at the same time
- 2 - the route with the least connections will not be computed
- 3 - no connection time is considered: you might have to run to get the next train
- 4 - multiple stations in a City like Paris are not considered

In my opinion the first and second limitations are comfort.

The third and fourth ones are more important because they can't be bypassed:
- Cities with multiple stations are real.
- User must know if he can't reach next connection departure at time.

In ascending order we should solve 3 and 4, then 2 and 1.

For this exercise i solved Limitation 3.

## Conclusion
- By using parallelism the data load very faster (3 to 4s) than the original
code (35 to 40s) it's 10 times faster!
- By refactoring some part of code, mainly IO, the bench take less than 3s
it's 25% faster in average than original implementation with less than 4s.
- To Solve the Limitation 3 i've added a change time to early arrival 
and test if user have enough time to arrive to next connection departure.
TWith the limitation 3 implementation the bench take less than 3,4s.

## Optimization
- The code could be optimized using Parallel Streams on CSA computation.