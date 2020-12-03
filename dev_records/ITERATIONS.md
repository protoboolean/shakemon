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

Now I'm going to start implementing Shakemon own API.

I've realized PokemonName should be only ascii alpha and that validation test
is growing. I implement stricter rules and extract test fixtures in CSV file.
I check a number of valid PokemonName variations raise no exception.

## Iteration 4

Extract configuration.

I create a configuration file that will contain all properties available to 
configure the app. Currently limited to the API port. The values should be valid
to run the app with fake external dependencies.

It will be possible to override the file via ENV variable pointing to a path
on the file system.

## Iteration 5

Expose Monitoring/Metrics endpoint

## Iteration 6

Add logging configuration, add logging of ports and endpoints on start-up.

## Iteration 7

Start implementation of PokeAPI.
