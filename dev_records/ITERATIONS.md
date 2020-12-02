# Iterations Objectives

## Iteration 1

Implement and test use-case that calls two interfaces representing our foreign APIs.

Fake services will be used instead of real ones.

No Error Handling.

No I/O adapters.

## Iteration 2

Let each collaborator throw a domain-specific exception and test it's handled:
* second collaborator in the chain is not called if first fails
* an exception is thrown in turn

Still, no I/O adapters.

## Iteration 3

I've implemented the (basically nonexistent) core-domain logic.

Now I'm going to implement its own API to start implementing integration testing.
