package kozyrenko.com.ctacatcher.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dev on 8/17/14.
 */
public enum TrainLine {
    UNKNOWN("Unknown", new String[] {} ),
    RED("Red", new String[] { "Red" }),
    BLUE("Blue", new String[] { "Blue"}),
    BROWN("Brown", new String[] { "Brn" } ),
    GREEN("Green", new String[] { "G"} ),
    PINK("Pink", new String[] { "Pink"}),
    PURPLE("Purple", new String[] { "P", "Pexp" }),
    ORANGE("Orange", new String[] { "Org" }),
    YELLOW("Yellow", new String[] { "Y" });

    private String pretty;
    private List<String> strLines;

    private TrainLine(String pretty, String [] lines) {
        this.pretty = pretty;
        strLines = new LinkedList<String>();
        for (String line : lines) {
            strLines.add(line);
        }
    }

    public static TrainLine from(String strLine) {
        TrainLine result = TrainLine.UNKNOWN;
        for (TrainLine line : values()) {
            for (String str : line.strLines) {
                if (str.equals(strLine)) {
                    result = line;
                    break;
                }
            }
        }

        return result;
    }

    public String pretty() {
        return pretty;
    }

}
