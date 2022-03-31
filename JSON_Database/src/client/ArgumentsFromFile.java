package client;

public class ArgumentsFromFile {
    private final String type;
    private final String key;
    private final String value;
    private final String path;

    public ArgumentsFromFile (String type, String key, String value, String path) {
        this.type = type;
        this.key = key;
        this.value = value;
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
    public String getPath() {
        return path;
    }
}
