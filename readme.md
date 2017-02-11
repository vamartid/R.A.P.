Although RAP is a music category,
the name of this project stands for Resource Allocation Problem.

This implementation has objects called resources,
 and other runnable-objects called Costumers.
Costumers can allocate a number of resource object to run and use them for some time.
If a costumer is not able to allocate the number of the resources he 
needs he waits until he is able to.
If he finds them he is able to run.
Many costumers can use resources the same time.
When costumers free the resources they use the waiting costumers
can check for their  needed resources.
