Although RAP is a music category, the name of this project stands for Resource Allocation Problem.

This implementation has objects called Resources, and other runnable-objects called Costumers. Costumers can allocate a number of Resource-objects to use them for a time period for the psevdo-calculation(in fact the thread just sleeps for random time only to represent the calculation). If a costumer is not able to allocate the resources he needs he waits until he is able to. If he finds them he allocates them and then he is able to run(psevdo-calculate). Many costumers can use resources the same time. When costumers finally free the resources they had been using, the waiting costumers are being notified in order to check for their needs to run(psevdo-calculate).

run with:
  java -cp ResourceAlocationProblem.jar csd.vamartid.rap.Rap
