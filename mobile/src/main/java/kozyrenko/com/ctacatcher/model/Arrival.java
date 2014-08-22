package kozyrenko.com.ctacatcher.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.LinkedList;
import java.util.List;
/**
 * Created by dev on 8/17/14.
 */
@Root(name="ctatt", strict = false)
public class Arrival {
    @ElementList(inline = true)
    private List<TrainEta> etas;

    public List<TrainEta> getEtas() {
        return etas;
    }

    public void setEtas(List<TrainEta> etas) {
        this.etas = etas;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Arrival{");
        sb.append("etas=").append(etas);
        sb.append('}');
        return sb.toString();
    }
}
