package shakemon.api;

import io.javalin.http.Context;

class ExceptionResponseBody {
    public final Endpoint endpoint;
    public final String error;
    public final Request request;

    ExceptionResponseBody(IllegalArgumentException exc, Context ctx) {
        this.request = new Request(ctx);
        this.endpoint = new Endpoint(ctx);
        this.error = exc.getMessage();
    }

    static class Request {
        public final String path;

        Request(Context ctx) {
            this.path = ctx.path();
        }
    }

    static class Endpoint {
        public final String path;

        Endpoint(Context ctx) {
            this.path = ctx.endpointHandlerPath();
        }
    }
}
