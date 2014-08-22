package kozyrenko.com.ctacatcher.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dev on 8/16/14.
 */
@Root(name="ctatt", strict = false)
public class Route {
    @Element
    private TrainList route;

    public TrainList getRoute() {
        return route;
    }

    public void setRoute(TrainList route) {
        this.route = route;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Route{");
        sb.append("route=").append(route);
        sb.append('}');
        return sb.toString();
    }
}
