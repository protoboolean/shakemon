# Shakemon Requirements and Design

The business-logic boils down to composing bits of information and delegating.
I guess the interesting part is the implementation of the non-functional requirements.

I'll develop the service as if it should be going live… step by step.

## NOTE FOR THE REVIEWER

I've implemented the service following a TDD approach and
"hexagonal architecture" (i.e. core-domain decoupled from I/O implementations and concerns).\
You'll notice I've not used any framework (for dependency-injection and other concerns). I tried instead to rely on
 the language features only.\
This is how I've been working for the past few years: minimalist approach. I'm sure there are frameworks out there that
can be leveraged to implement this kind of functionality quicker (Spring, Micronaut, etc.) but in reality I'm not
 familiar with them for the reason above.\
Java is also not very ergonomic but it's still the one I'm most familiar with.\
So, you'll see a lot of commits where I implement the service bit-by-bit (re-inventing the wheel surely), starting
 from the core-domain, to
   the fake apis, to its own end-point, then metrics, then real-dependencies, etc.\
I hope you'll find it informative and significant for the role I'm applying to.

I managed to pull together a Dockerfile. I know the ropes of it but it's the first time I configure it to build a
 Gradle project, and I didn't manage to find a convincing way to cache the dependencies in the multi-stage sequence, 
 so if you rebuild the image it will download everything again. Sorry about that.

## Functional Requirements

Shakemon returns a translation in Shakesperean of a given pokemon description.
 Pokemons will be identified by their common name and the implementation will delegate the job to two foreign APIs: one to resolve the standard english description, and the other to perform the translation.

The two APIs are:

* https://pokeapi.co/ to resolve the standard description by name
* https://funtranslations.com/api/shakespeare to translate the description in shakesperian

NOTE: more information about access restrictions to those APIs will be added in the "Non-Functional Requirement" section.

## Non-Functional Requirements

### Risk assessment

1. Calling any API, and external ones especially, carries intrinsic risks:

    * the foreign servers may be overloaded or down (therefore slow or unresponsive), we don't want to keep connections open with our client for _too long_
    * their API might have changed or have bugs
    * our request, which contains an input coming from outside which we can't fully validate (at least my assumption is that we don't want to keep a cache of all valid pokemon names), therefore might result in an invalid request for those services

2. Exposing our API to the public carries risks of denial of service attacks, which we might inflict to our partners if we don't handle it

### Failure Handling

* respond with an appropriate error to our client should any of our partners fail to response (e.g. 404 if we get a pokemon not found, etc.)
* log any error: initial implementation will simply log to stderr, ideally we want to funnel also direct logs into an aggregator (i.e. Graylog)

### CI / CD Support

Objective: make the final artifact runnable with different configuration so that it can be _wired_ appropriately in each local, test, and prod environments.

#### Configuration options could include:

1. foreign endpoint URLs
2. fake foreign endpoints that can be used to simulate successful and unsuccessful requests
3. access tokens (TODO: not sure if it's actually required)
4. our HTTP port and base path

### Monitoring / Observability

We should: 
1. expose a "ping" endpoint
2. collect and report metrics for each endpoint (ours and foreign)

### Performance

We should not call the translator if the first failed to respond or responded with invalid text (i.e. empty description).

The pokemon API seems a stateless service (constant pokemon_name -> description mapping). It might be worth to consider caching locally the Pokemon descriptions.

