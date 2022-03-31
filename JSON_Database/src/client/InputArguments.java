package client;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

public class InputArguments {

    @Parameter
    private List<String> parameters = new ArrayList<>();

    @Parameter(names = "-t", description = "Type of the request")
    private String type;

    @Parameter(names = "-k", description = "Index of the cell")
    private String index;

    @Parameter(names = "-v", description = "Value to save in the database")
    private String message;

    @Parameter(names = "-in", description = "File input")
    private String path;

    public String getType() {
        return type;
    }

    public String getIndex() {
        return index;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

}
