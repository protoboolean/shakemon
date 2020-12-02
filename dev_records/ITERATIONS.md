# Iterations Objectives

## Iteration 1

Implement and test use-case that calls two interfaces representing our foreign APIs.

Fake services will be used instead of real ones.

No Error Handling.

No I/O adapters.

## Iteration 2

Let each collaborator throw a domain-specific exception and test it's handled:
* second collaborator in the chain should not be called if first fails
* a use-case exception wrapping any of the collaborators exceptions should be rethrown

With this the basically nonexistent core-domain logic is done.

No I/O adapters.

## Iteration 3

Now I'm going to start implementing its own API.
