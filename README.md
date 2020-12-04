# Shakemon

A shakesperian translator for your pokemon description

## Config

See `app/src/main/resources/shakemon/config.properties` for complete configuration options with safe defaults.

That's the one that will be used unless you set `SHAKEMON_CONFIG` variable to an
absolute path pointing to a similar config file.

**NOTE: the app will run by default with "fake" dependencies suitable for offline development and testing.
I.e. it will call a _mock_ "PokeAPI" instead of the real one.
To connect to the real endpoint you MUST create a config file and
set the SHAKEMON_CONFIG environment variable with the absolute file path. \
This is meant to demonstrate a "config injection" technique.**

**NOTE: the translation endpoint is only implemented as a fake.**

## Build and Run

I assume you already have `git` installed and you've cloned and moved in the repo with:

    $ git clone https://github.com/protoboolean/shakemon
    $ cd shakemon

### Via Docker

    $ docker image build -t shakemon .
    […lots of output…]
    $ docker run -t shakemon print_default_config > my_config.properties
    […this will print out a config file for you to customize…]
    $ docker run \
      -p 7000:7000 -p 7001:7001 \
      -v "$PWD/my_config.properties:/home/shakemon/config.properties" \
      -e "SHAKEMON_CONFIG=/home/shakemon/config.properties" \
      -t shakemon
    […expose port 7000 (service) and 7001 (metrics), mount config…]
    […if it all worked you should see something like…]
    [main] INFO shakemon.ShakemonConfig - Loading custom config /home/shakemon/config.properties
    [main] INFO shakemon.ShakemonConfig - shakemon.pokeapi.base_url=fake
    [main] INFO shakemon.ShakemonConfig - shakemon.admin_port=7001
    [main] INFO shakemon.ShakemonConfig - shakemon.port=7000
    [main] INFO shakemon.Main - Admin Service listening on port: 7001
    [main] INFO shakemon.api.AdminEndpoints - GET /metrics
    [main] INFO shakemon.Main - App listening on port: 7000
    [main] INFO shakemon.api.AppEndpoints - GET /pokemon/:name

Connect to http://localhost:7000/pokemon/ditto for a JSON with some metrics.

The fake endpoints will output something like:
```json
{
    "name": "ditto",
    "description": "Pretend it is Shakesperean: best pokemon"
}
```

By setting `shakemon.pokeapi.base_url=https://pokeapi.co/api/v2/` in
`my_config.properties` you will connect to the real PokeAPI.

```json
{
    "name": "ditto",
    "description": "Pretend it is Shakesperean: Ditto experience level is 101.This pokemon holds2 items.Ditto can make1 moves."
}
```

As you can see the translation is not implemented yet.

Connect to http://localhost:7001/metrics for a JSON with some metrics.

### In your Operating System

NOTE: tested on Mac, should work seamlessly on Linux and probably on Windows

1. Install SDKman

       $ curl -s "https://get.sdkman.io" | bash

   Please refer to https://sdkman.io/install for Windows-specific instructions.

2. Install JDK and Gradle

       $ sdk install java 11.0.9.hs-adpt
       $ sdk install gradle 6.7.1

4. Build and run Shakemon in a single command (Control-C to kill)

       $ gradle run
       […runs with default (embedded) config…]
       $ gradle run --args print_default_config
       […will print the embedded config (just after "Task :app:run")

## Testing
    
    $ gradle test

Should you want to run tests depending on network I/O please set in your environment:
    
    SHAKEMON_ALLOW_NETWORK_IO_TEST=true
    SHAKEMON_CONFIG=/path/to/config/with/real_api

## Metrics

The service collects metrics for its own endpoint.
 
Metrics can be obtained as JSON calling `http get http://localhost:7001/metrics`.

Only metric available:

* TIMER on `HTTP GET /pokemon/:name`

It should also have metrics and health-checks for itself and external endpoints
but this should give you an idea.
