package enums;

public class AppiumServerDetails {

    public enum Server {
        // @formatter:off
        IP("127.0.0.1"),
        PORT("4723");
        // @formatter:on

        private final String label;

        Server(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }
}

