package shakemon;

public class TestEnablers {
    public static boolean testPokeAPI() {
        return System.getenv("SHAKEMON_POKEAPI_TESTING") != null;
    }
}
