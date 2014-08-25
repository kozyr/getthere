package com.kozyrenko.ctacatcher.common.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.kozyrenko.ctacatcher.common.model.TrainStop.Direction;
import com.kozyrenko.ctacatcher.common.util.Util;

public final class TrainStationManager {

    private final List<TrainStop> stops;
    private final Map<String, TrainStation> stations;

    private static class LazyHolder {
        private static final TrainStationManager INSTANCE = new TrainStationManager();
    }

    public static TrainStationManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    public TrainStation getNearestStation(double lat, double lon) {
        TrainStation result = null;
        float min = Float.MAX_VALUE;
        for (String id : stations.keySet()) {
            TrainStation station = stations.get(id);
            float delta = Util.distance(station, lat, lon);

            if (delta < min) {
                min = delta;
                result = station;
            }
        }

        return result;
    }

    private TrainStationManager() {
        stops = new LinkedList<TrainStop>();
        stations = new HashMap<String, TrainStation>();
        loadStops();
        setupStations();
    }

    private void setupStations() {
        for (TrainStop stop : stops) {
            TrainStation station = stations.get(stop.getParentStopId());
            if (station == null) {
                station = new TrainStation(stop);
                stations.put(stop.getParentStopId(), station);
            } else {
                station.addStop(stop);
            }
        }
    }

    private List<TrainStop> loadStops() {
        stops.add(new TrainStop(
                "30162", Direction.W, "18th (54th/Cermak-bound)", -87.669147, 41.857908, "18th", "18th (Pink Line)", "40830", true, TrainLine.PINK));
        stops.add(new TrainStop(
                "30161", Direction.E, "18th (Loop-bound)", -87.669147, 41.857908, "18th", "18th (Pink Line)", "40830", true, TrainLine.PINK));
        stops.add(new TrainStop(
                "30022", Direction.N, "35th/Archer (Loop-bound)", -87.680622, 41.829353, "35th/Archer", "35th/Archer (Orange Line)", "40120", true, TrainLine.ORANGE));
        stops.add(new TrainStop(
                "30023", Direction.S, "35th/Archer (Midway-bound)", -87.680622, 41.829353, "35th/Archer", "35th/Archer (Orange Line)", "40120", true, TrainLine.ORANGE));
        stops.add(new TrainStop(
                "30214", Direction.S, "35-Bronzeville-IIT (63rd-bound)", -87.625826, 41.831677, "35th-Bronzeville-IIT", "35th-Bronzeville-IIT (Green Line)", "41120", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30213", Direction.N, "35-Bronzeville-IIT (Harlem-bound)", -87.625826, 41.831677, "35th-Bronzeville-IIT", "35th-Bronzeville-IIT (Green Line)", "41120", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30246", Direction.S, "43rd (63rd-bound)", -87.619021, 41.816462, "43rd", "43rd (Green Line)", "41270", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30245", Direction.N, "43rd (Harlem-bound)", -87.619021, 41.816462, "43rd", "43rd (Green Line)", "41270", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30210", Direction.S, "47th (63rd-bound) Elevated (63rd-bound)", -87.618826, 41.809209, "47th", "47th (Green Line)", "41080", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30209", Direction.N, "47th (SB) Elevated (Harlem-bound)", -87.618826, 41.809209, "47th", "47th (Green Line)", "41080", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30238", Direction.S, "47th-Dan Ryan (95th-bound)", -87.63094, 41.810318, "47th", "47th (Red Line)", "41230", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30237", Direction.N, "47th-Dan Ryan (Howard-bound)", -87.63094, 41.810318, "47th", "47th (Red Line)", "41230", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30025", Direction.S, "51st (63rd-bound)", -87.618487, 41.80209, "51st", "51st (Green Line)", "40130", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30024", Direction.N, "51st (Harlem-bound)", -87.618487, 41.80209, "51st", "51st (Green Line)", "40130", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30113", Direction.E, "54th/Cermak (Loop-bound)", -87.75669201, 41.85177331, "54th/Cermak", "54th/Cermak (Pink Line)", "40580", true, TrainLine.PINK));
        stops.add(new TrainStop(
                "30114", Direction.W, "54th/Cermak (Terminal arrival)", -87.75669201, 41.85177331, "54th/Cermak", "54th/Cermak (Pink Line)", "40580", true, TrainLine.PINK));
        stops.add(new TrainStop(
                "30178", Direction.S, "63rd-Dan Ryan (95th-bound)", -87.630952, 41.780536, "63rd", "63rd (Red Line)", "40910", false, TrainLine.RED));
        stops.add(new TrainStop(
                "30177", Direction.N, "63rd-Dan Ryan (Howard-bound)", -87.630952, 41.780536, "63rd", "63rd (Red Line)", "40910", false, TrainLine.RED));
        stops.add(new TrainStop(
                "30192", Direction.S, "69th (95th-bound)", -87.625724, 41.768367, "69th", "69th (Red Line)", "40990", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30191", Direction.N, "69th (Howard-bound)", -87.625724, 41.768367, "69th", "69th (Red Line)", "40990", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30047", Direction.S, "79th (95th-bound)", -87.625112, 41.750419, "79th", "79th (Red Line)", "40240", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30046", Direction.N, "79th (Howard-bound)", -87.625112, 41.750419, "79th", "79th (Red Line)", "40240", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30276", Direction.S, "87th (95th-bound)", -87.624717, 41.735372, "87th", "87th (Red Line)", "41430", false, TrainLine.RED));
        stops.add(new TrainStop(
                "30275", Direction.N, "87th (Howard-bound)", -87.624717, 41.735372, "87th", "87th (Red Line)", "41430", false, TrainLine.RED));
        stops.add(new TrainStop(
                "30089", Direction.S, "95th/Dan Ryan (95th-bound)", -87.624342, 41.722377, "95th/Dan Ryan", "95th/Dan Ryan (Red Line)", "40450", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30088", Direction.N, "95th/Dan Ryan (Howard-bound)", -87.624342, 41.722377, "95th/Ran Ryan", "95th/Ran Ryan (Red Line)", "40450", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30132", Direction.S, "Adams/Wabash (Inner Loop)", -87.626037, 41.879507, "Adams/Wabash", "Adams/Wabash (Brown, Green, Orange, Pink & Purple Lines)", "40680", false, TrainLine.GREEN, TrainLine.PURPLE, TrainLine.PINK, TrainLine.ORANGE));
        stops.add(new TrainStop(
                "30131", Direction.N, "Adams/Wabash (Outer Loop)", -87.626037, 41.879507, "Adams/Wabash", "Adams/Wabash (Brown, Green, Orange, Pink & Purple Lines)", "40680", false, TrainLine.BROWN, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30240", Direction.S, "Addison (O'Hare Branch) (Forest Pk-bound)", -87.71906, 41.94738, "Addison", "Addison (Blue Line)", "41240", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30239", Direction.N, "Addison (O'Hare Branch) (O'Hare-bound)", -87.71906, 41.94738, "Addison", "Addison (Blue Line)", "41240", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30277", Direction.N, "Addison (Kimball-bound)", -87.674642, 41.947028, "Addison", "Addison (Brown Line)", "41440", true, TrainLine.BROWN));
        stops.add(new TrainStop(
                "30278", Direction.S, "Addison (Loop-bound)", -87.674642, 41.947028, "Addison", "Addison (Brown Line)", "41440", true, TrainLine.BROWN));
        stops.add(new TrainStop(
                "30274", Direction.S, "Addison (95th-bound)", -87.653626, 41.947428, "Addison", "Addison (Red Line)", "41420", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30273", Direction.N, "Addison (Howard-bound)", -87.653626, 41.947428, "Addison", "Addison (Red Line)", "41420", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30230", Direction.S, "Argyle (95th-bound)", -87.65853, 41.973453, "Argyle", "Argyle (Red Line)", "41200", false, TrainLine.RED));
        stops.add(new TrainStop(
                "30229", Direction.N, "Argyle (Howard-bound)", -87.65853, 41.973453, "Argyle", "Argyle (Red Line)", "41200", false, TrainLine.RED));
        stops.add(new TrainStop(
                "30127", Direction.N, "Armitage (Kimball-Linden-bound)", -87.652644, 41.918217, "Armitage", "Armitage (Brown & Purple Lines)", "40660", true, TrainLine.BROWN, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30128", Direction.S, "Armitage (Loop-bound)", -87.652644, 41.918217, "Armitage", "Armitage (Brown & Purple Lines)", "40660", true, TrainLine.BROWN, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30205", Direction.N, "Ashland (Loop-bound)", -87.665317, 41.839234, "Ashland", "Ashland (Orange Line)", "41060", true, TrainLine.ORANGE));
        stops.add(new TrainStop(
                "30206", Direction.S, "Ashland (Midway-bound)", -87.665317, 41.839234, "Ashland", "Ashland (Orange Line)", "41060", true, TrainLine.ORANGE));
        stops.add(new TrainStop(
                "30032", Direction.E, "Ashland (Harlem-54th/Cermak-bound)", -87.666969, 41.885269, "Ashland", "Ashland (Green & Pink Lines)", "40170", true, TrainLine.GREEN, TrainLine.PINK));
        stops.add(new TrainStop(
                "30033", Direction.W, "Ashland (Loop-63rd-bound)", -87.666969, 41.885269, "Ashland", "Ashland (Green & Pink Lines)", "40170", true, TrainLine.GREEN, TrainLine.PINK));
        stops.add(new TrainStop(
                "30056", Direction.E, "Ashland/63rd (Harlem-bound)", -87.663766, 41.77886, "Ashland/63rd", "Ashland/63rd (Green Line)", "40290", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30057", Direction.W, "Ashland/63rd (Terminal arrival)", -87.663766, 41.77886, "Ashland/63rd", "Ashland/63rd (Green Line)", "40290", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30002", Direction.W, "Austin (Forest Pk-bound)", -87.776812, 41.870851, "Austin", "Austin (Blue Line)", "40010", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30001", Direction.E, "Austin (O'Hare-bound)", -87.776812, 41.870851, "Austin", "Austin (Blue Line)", "40010", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30243", Direction.E, "Austin (63rd-bound)", -87.774135, 41.887293, "Austin", "Austin (Green Line)", "41260", false, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30244", Direction.W, "Austin (Harlem-bound)", -87.774135, 41.887293, "Austin", "Austin (Green Line)", "41260", false, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30013", Direction.S, "Belmont (O'Hare Branch) (Forest Pk-bound)", -87.712359, 41.938132, "Belmont", "Belmont (Blue Line)", "40060", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30012", Direction.N, "Belmont (O'Hare Branch) (O'Hare-bound)", -87.712359, 41.938132, "Belmont", "Belmont (Blue Line)", "40060", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30256", Direction.S, "Belmont (95th-bound)", -87.65338, 41.939751, "Belmont", "Belmont (Red, Brown & Purple Lines)", "41320", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30255", Direction.N, "Belmont (Howard-bound)", -87.65338, 41.939751, "Belmont", "Belmont (Red, Brown & Purple Lines)", "41320", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30257", Direction.N, "Belmont (Kimball-Linden-bound)", -87.65338, 41.939751, "Belmont", "Belmont (Red, Brown & Purple Lines)", "41320", true, TrainLine.BROWN, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30258", Direction.S, "Belmont (Loop-bound)", -87.65338, 41.939751, "Belmont", "Belmont (Red, Brown & Purple Lines)", "41320", true, TrainLine.BROWN, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30067", Direction.S, "Berwyn (95th-bound)", -87.658668, 41.977984, "Berwyn", "Berwyn (Red Line)", "40340", false, TrainLine.RED));
        stops.add(new TrainStop(
                "30066", Direction.N, "Berwyn (Howard-bound)", -87.658668, 41.977984, "Berwyn", "Berwyn (Red Line)", "40340", false, TrainLine.RED));
        stops.add(new TrainStop(
                "30268", Direction.S, "Bryn Mawr (95th-bound)", -87.65884, 41.983504, "Bryn Mawr", "Bryn Mawr (Red Line)", "41380", false, TrainLine.RED));
        stops.add(new TrainStop(
                "30267", Direction.N, "Bryn Mawr (Howard-bound)", -87.65884, 41.983504, "Bryn Mawr", "Bryn Mawr (Red Line)", "41380", false, TrainLine.RED));
        stops.add(new TrainStop(
                "30087", Direction.W, "California (54th/Cermak-bound)", -87.694774, 41.854109, "California", "California (Pink Line)", "40440", true, TrainLine.PINK));
        stops.add(new TrainStop(
                "30086", Direction.E, "California (Loop-bound)", -87.694774, 41.854109, "California", "California (Pink Line)", "40440", true, TrainLine.PINK));
        stops.add(new TrainStop(
                "30112", Direction.S, "California/Milwaukee (Forest Pk-bound)", -87.69689, 41.921939, "California", "California  (Blue Line)", "40570", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30265", Direction.E, "California (63rd-bound)", -87.696234, 41.88422, "California", "California (Green Line)", "41360", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30266", Direction.W, "California (Harlem-bound)", -87.696234, 41.88422, "California", "California (Green Line)", "41360", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30111", Direction.N, "California/Milwaukee (O'Hare-bound)", -87.69689, 41.921939, "California", "California (Blue Line)", "40570", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30242", Direction.S, "Central-Evanston (Howard-Loop-bound)", -87.685617, 42.063987, "Central", "Central (Purple Line)", "41250", false, TrainLine.PURPLE, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30241", Direction.N, "Central-Evanston (Linden-bound)", -87.685617, 42.063987, "Central", "Central (Purple Line)", "41250", false, TrainLine.PURPLE, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30054", Direction.E, "Central (63rd-bound)", -87.76565, 41.887389, "Central", "Central (Green Line)", "40280", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30055", Direction.W, "Central (Harlem-bound)", -87.76565, 41.887389, "Central", "Central (Green Line)", "40280", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30152", Direction.W, "Central Park (54th/Cermak-bound)", -87.714842, 41.853839, "Central Park", "Central Park (Pink Line)", "40780", true, TrainLine.PINK));
        stops.add(new TrainStop(
                "30151", Direction.E, "Central Park (Loop-bound)", -87.714842, 41.853839, "Central Park", "Central Park (Pink Line)", "40780", true, TrainLine.PINK));
        stops.add(new TrainStop(
                "30194", Direction.S, "Cermak-Chinatown (95th-bound)", -87.630968, 41.853206, "Cermak-Chinatown", "Cermak-Chinatown (Red Line)", "41000", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30193", Direction.N, "Cermak-Chinatown (Howard-bound)", -87.630968, 41.853206, "Cermak-Chinatown", "Cermak-Chinatown (Red Line)", "41000", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30272", Direction.S, "Chicago/Milwaukee (Forest Pk-bound)", -87.655214, 41.896075, "Chicago", "Chicago (Blue Line)", "41410", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30271", Direction.N, "Chicago/Milwaukee (O'Hare-bound)", -87.655214, 41.896075, "Chicago", "Chicago (Blue Line)", "41410", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30137", Direction.N, "Chicago/Franklin (Kimball-Linden-bound)", -87.635924, 41.89681, "Chicago", "Chicago (Brown & Purple Lines)", "40710", true, TrainLine.BROWN, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30138", Direction.S, "Chicago/Franklin (Loop-bound)", -87.635924, 41.89681, "Chicago", "Chicago (Brown & Purple Lines)", "40710", true, TrainLine.BROWN, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30280", Direction.S, "Chicago/State (95th-bound)", -87.628176, 41.896671, "Chicago", "Chicago (Red Line)", "41450", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30279", Direction.N, "Chicago/State (Howard-bound)", -87.628176, 41.896671, "Chicago", "Chicago (Red Line)", "41450", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30083", Direction.W, "Cicero (54th/Cermak-bound)", -87.745336, 41.85182, "Cicero", "Cicero (Pink Line)", "40420", true, TrainLine.PINK));
        stops.add(new TrainStop(
                "30082", Direction.E, "Cicero (Loop-bound)", -87.745336, 41.85182, "Cicero", "Cicero (Pink Line)", "40420", true, TrainLine.PINK));
        stops.add(new TrainStop(
                "30188", Direction.W, "Cicero (Forest Pk-bound)", -87.745154, 41.871574, "Cicero", "Cicero (Blue Line)", "40970", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30187", Direction.E, "Cicero (O'Hare-bound)", -87.745154, 41.871574, "Cicero", "Cicero (Blue Line)", "40970", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30094", Direction.E, "Cicero (63rd-bound)", -87.744698, 41.886519, "Cicero", "Cicero (Green Line)", "40480", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30009", Direction.W, "Cicero (Harlem-bound)", -87.744698, 41.886519, "Cicero", "Cicero (Green Line)", "40480", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30122", Direction.S, "Clark/Division (95th-bound)", -87.631412, 41.90392, "Clark/Division", "Clark/Division (Red Line)", "40630", false, TrainLine.RED));
        stops.add(new TrainStop(
                "30121", Direction.N, "Clark/Division (Howard-bound)", -87.631412, 41.90392, "Clark/Division", "Clark/Division (Red Line)", "40630", false, TrainLine.RED));
        stops.add(new TrainStop(
                "30074", Direction.E, "Clark/Lake (Inner Loop)", -87.630886, 41.885737, "Clark/Lake", "Clark/Lake (Blue, Brown, Green, Orange, Purple & Pink Lines)", "40380", true, TrainLine.GREEN, TrainLine.PURPLE, TrainLine.PINK, TrainLine.ORANGE));
        stops.add(new TrainStop(
                "30075", Direction.W, "Clark/Lake (Outer Loop)", -87.630886, 41.885737, "Clark/Lake", "Clark/Lake (Blue, Brown, Green, Orange, Purple & Pink Lines)", "40380", true, TrainLine.BROWN, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30374", Direction.S, "Clark/Lake (Forest Pk-bound)", -87.630886, 41.885737, "Clark/Lake", "Clark/Lake (Blue, Brown, Green, Orange, Purple & Pink Lines)", "40380", true, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30375", Direction.N, "Clark/Lake (O'Hare-bound)", -87.630886, 41.885737, "Clark/Lake", "Clark/Lake (Blue, Brown, Green, Orange, Purple & Pink Lines)", "40380", true, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30085", Direction.W, "Clinton (Forest Pk-bound)", -87.640984, 41.875539, "Clinton", "Clinton (Blue Line)", "40430", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30084", Direction.E, "Clinton (O'Hare-bound)", -87.640984, 41.875539, "Clinton", "Clinton (Blue Line)", "40430", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30222", Direction.W, "Clinton (Harlem-54th/Cermak-bound)", -87.641782, 41.885678, "Clinton", "Clinton (Green & Pink Lines)", "41160", true, TrainLine.GREEN, TrainLine.PINK));
        stops.add(new TrainStop(
                "30221", Direction.E, "Clinton (Loop-63rd-bound)", -87.641782, 41.885678, "Clinton", "Clinton (Green & Pink Lines)", "41160", true, TrainLine.GREEN, TrainLine.PINK));
        stops.add(new TrainStop(
                "30291", Direction.E, "Conservatory (63rd-bound)", -87.716523, 41.884904, "Conservatory", "Conservatory (Green Line)", "41670", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30292", Direction.W, "Conservatory (Harlem-bound)", -87.716523, 41.884904, "Conservatory", "Conservatory (Green Line)", "41670", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30139", Direction.E, "Cottage Grove (Terminal arrival)", -87.605857, 41.780309, "Cottage Grove", "Cottage Grove (Green Line)", "40720", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30140", Direction.W, "East 63rd-Cottage Grove (Harlem-bound)", -87.605857, 41.780309, "Cottage Grove", "Cottage Grove (Green Line)", "40720", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30045", Direction.S, "Cumberland (Forest Pk-bound)", -87.838028, 41.984246, "Cumberland", "Cumberland (Blue Line)", "40230", true, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30044", Direction.N, "Cumberland (O'Hare-bound)", -87.838028, 41.984246, "Cumberland", "Cumberland (Blue Line)", "40230", true, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30041", Direction.W, "Damen (54th/Cermak-bound)", -87.675975, 41.854517, "Damen", "Damen (Pink Line)", "40210", true, TrainLine.PINK));
        stops.add(new TrainStop(
                "30040", Direction.E, "Damen (Loop-bound)", -87.675975, 41.854517, "Damen", "Damen (Pink Line)", "40210", true, TrainLine.PINK));
        stops.add(new TrainStop(
                "30116", Direction.S, "Damen/Milwaukee (Forest Pk-bound)", -87.677437, 41.909744, "Damen", "Damen (Blue Line)", "40590", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30115", Direction.N, "Damen/Milwaukee (O'Hare-bound)", -87.677437, 41.909744, "Damen", "Damen (Blue Line)", "40590", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30018", Direction.N, "Damen (Kimball-bound)", -87.678639, 41.966286, "Damen", "Damen (Brown Line)", "40090", true, TrainLine.BROWN));
        stops.add(new TrainStop(
                "30019", Direction.S, "Damen (Loop-bound)", -87.678639, 41.966286, "Damen", "Damen (Brown Line)", "40090", true, TrainLine.BROWN));
        stops.add(new TrainStop(
                "30011", Direction.S, "Davis (Howard-Loop-bound)", -87.683543, 42.04771, "Davis", "Davis (Purple Line)", "40050", true, TrainLine.PURPLE, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30010", Direction.N, "Davis (Linden-bound)", -87.683543, 42.04771, "Davis", "Davis (Purple Line)", "40050", true, TrainLine.PURPLE, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30134", Direction.S, "Dempster (Howard-Loop-bound)", -87.681602, 42.041655, "Dempster", "Dempster (Purple Line)", "40690", false, TrainLine.PURPLE, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30133", Direction.N, "Dempster (Linden-bound)", -87.681602, 42.041655, "Dempster", "Dempster (Purple Line)", "40690", false, TrainLine.PURPLE, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30103", Direction.N, "Diversey (Kimball-Linden-bound)", -87.653131, 41.932732, "Diversey", "Diversey (Brown & Purple Lines)", "40530", true, TrainLine.BROWN, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30104", Direction.S, "Diversey (Loop-bound)", -87.653131, 41.932732, "Diversey", "Diversey (Brown & Purple Lines)", "40530", true, TrainLine.BROWN, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30063", Direction.S, "Division/Milwaukee (Forest Pk-bound)", -87.666496, 41.903355, "Division", "Division (Blue Line)", "40320", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30062", Direction.N, "Division/Milwaukee (O'Hare-bound)", -87.666496, 41.903355, "Division", "Division (Blue Line)", "40320", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30076", Direction.E, "Forest Park (O'Hare-bound)", -87.817318, 41.874257, "Forest Park", "Forest Park (Blue Line)", "40390", true, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30077", Direction.W, "Forest Park (Terminal Arrival)", -87.817318, 41.874257, "Forest Park", "Forest Park (Blue Line)", "40390", true, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30102", Direction.S, "Foster (Howard-Loop-bound)", -87.68356, 42.05416, "Foster", "Foster (Purple Line)", "40520", false, TrainLine.PURPLE, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30101", Direction.N, "Foster (Linden-bound)", -87.68356, 42.05416, "Foster", "Foster (Purple Line)", "40520", false, TrainLine.PURPLE, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30167", Direction.N, "Francisco (Kimball-bound)", -87.701644, 41.966046, "Francisco", "Francisco (Brown Line)", "40870", true, TrainLine.BROWN));
        stops.add(new TrainStop(
                "30168", Direction.S, "Francisco (Loop-bound)", -87.701644, 41.966046, "Francisco", "Francisco (Brown Line)", "40870", true, TrainLine.BROWN));
        stops.add(new TrainStop(
                "30234", Direction.S, "Fullerton (95th-bound)", -87.652866, 41.925051, "Fullerton", "Fullerton (Red, Brown & Purple Lines)", "41220", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30233", Direction.N, "Fullerton (Howard-bound)", -87.652866, 41.925051, "Fullerton", "Fullerton (Red, Brown & Purple Lines)", "41220", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30235", Direction.N, "Fullerton (Kimball-Linden-bound)", -87.652866, 41.925051, "Fullerton", "Fullerton (Red, Brown & Purple Lines)", "41220", true, TrainLine.BROWN, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30236", Direction.S, "Fullerton (Loop-bound)", -87.652866, 41.925051, "Fullerton", "Fullerton (Red, Brown & Purple Lines)", "41220", true, TrainLine.BROWN, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30100", Direction.S, "Garfield (63rd-bound)", -87.618327, 41.795172, "Garfield", "Garfield (Green Line)", "40510", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30099", Direction.N, "Garfield (Harlem-bound)", -87.618327, 41.795172, "Garfield", "Garfield (Green Line)", "40510", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30224", Direction.S, "Garfield-Dan Ryan (95th-bound)", -87.631157, 41.79542, "Garfield", "Garfield (Red Line)", "41170", false, TrainLine.RED));
        stops.add(new TrainStop(
                "30223", Direction.N, "Garfield-Dan Ryan (Howard-bound)", -87.631157, 41.79542, "Garfield", "Garfield (Red Line)", "41170", false, TrainLine.RED));
        stops.add(new TrainStop(
                "30096", Direction.S, "Grand/Milwaukee (Forest Pk-bound)", -87.647578, 41.891189, "Grand", "Grand (Blue Line)", "40490", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30095", Direction.N, "Grand/Milwaukee (O'Hare-bound)", -87.647578, 41.891189, "Grand", "Grand (Blue Line)", "40490", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30065", Direction.S, "Grand/State (95th-bound)", -87.628021, 41.891665, "Grand", "Grand (Red Line)", "40330", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30064", Direction.N, "Grand/State (Howard-bound)", -87.628021, 41.891665, "Grand", "Grand (Red Line)", "40330", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30148", Direction.S, "Granville (95th-bound)", -87.659202, 41.993664, "Granville", "Granville (Red Line)", "40760", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30147", Direction.N, "Granville (Howard-bound)", -87.659202, 41.993664, "Granville", "Granville (Red Line)", "40760", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30215", Direction.N, "Halsted (Loop-bound)", -87.648088, 41.84678, "Halsted", "Halsted (Orange Line)", "41130", true, TrainLine.ORANGE));
        stops.add(new TrainStop(
                "30216", Direction.S, "Halsted (Midway-bound)", -87.648088, 41.84678, "Halsted", "Halsted (Orange Line)", "41130", true, TrainLine.ORANGE));
        stops.add(new TrainStop(
                "30184", Direction.W, "Halsted/63rd (Ashland-bound)", -87.644244, 41.778943, "Halsted", "Halsted (Green Line)", "40940", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30183", Direction.E, "Halsted/63rd (Harlem-bound)", -87.644244, 41.778943, "Halsted", "Halsted (Green Line)", "40940", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30190", Direction.W, "Harlem (Forest Pk-bound)", -87.806961, 41.87349, "Harlem", "Harlem (Blue Line - Forest Park Branch)", "40980", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30189", Direction.E, "Harlem (O'Hare-bound)", -87.806961, 41.87349, "Harlem", "Harlem (Blue Line - Forest Park Branch)", "40980", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30146", Direction.S, "Harlem (O'Hare Branch) (Forest Pk-bound)", -87.8089, 41.98227, "Harlem", "Harlem (Blue Line - O'Hare Branch)", "40750", true, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30145", Direction.N, "Harlem (O'Hare Branch) (O'Hare-bound)", -87.8089, 41.98227, "Harlem", "Harlem (Blue Line - O'Hare Branch)", "40750", true, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30004", Direction.W, "Harlem (Terminal arrival)", -87.803176, 41.886848, "Harlem/Lake", "Harlem/Lake (Green Line)", "40020", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30003", Direction.E, "Harlem (63rd-bound)", -87.803176, 41.886848, "Harlem/Lake", "Harlem/Lake (Green Line)", "40020", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30166", Direction.W, "Library (Inner Loop)", -87.628196, 41.876862, "Harold Washington Library-State/Van Buren", "Harold Washington Library-State/Van Buren (Brown, Orange, Purple & Pink Lines)", "40850", true, TrainLine.PURPLE, TrainLine.PINK, TrainLine.ORANGE));
        stops.add(new TrainStop(
                "30165", Direction.E, "Library (Outer Loop)", -87.628196, 41.876862, "Harold Washington Library-State/Van Buren", "Harold Washington Library-State/Van Buren (Brown, Orange, Purple & Pink Lines)", "40850", true, TrainLine.BROWN));
        stops.add(new TrainStop(
                "30286", Direction.S, "Harrison (95th-bound)", -87.627479, 41.874039, "Harrison", "Harrison (Red Line)", "41490", false, TrainLine.RED));
        stops.add(new TrainStop(
                "30285", Direction.N, "Harrison (Howard-bound)", -87.627479, 41.874039, "Harrison", "Harrison (Red Line)", "41490", false, TrainLine.RED));
        stops.add(new TrainStop(
                "30175", Direction.N, "Howard (NB) (Linden, Skokie-bound)", -87.672892, 42.019063, "Howard", "Howard (Red, Purple & Yellow Lines)", "40900", true, TrainLine.PURPLE, TrainLine.PURPLE, TrainLine.YELLOW));
        stops.add(new TrainStop(
                "30176", Direction.S, "Howard (Terminal arrival)", -87.672892, 42.019063, "Howard", "Howard (Red, Purple & Yellow Lines)", "40900", true, TrainLine.PURPLE, TrainLine.PURPLE, TrainLine.YELLOW));
        stops.add(new TrainStop(
                "30173", Direction.N, "Howard (Terminal arrival)", -87.672892, 42.019063, "Howard", "Howard (Red, Purple & Yellow Lines)", "40900", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30174", Direction.S, "Howard (95th-Bound)", -87.672892, 42.019063, "Howard", "Howard (Red, Purple & Yellow Lines)", "40900", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30158", Direction.W, "Illinois Medical District (Forest Pk-bound)", -87.673932, 41.875706, "Illinois Medical District", "Illinois Medical District (Blue Line)", "40810", true, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30157", Direction.E, "Illinois Medical District (O'Hare-bound)", -87.673932, 41.875706, "Illinois Medical District", "Illinois Medical District (Blue Line)", "40810", true, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30059", Direction.S, "Indiana (63rd-bound)", -87.621371, 41.821732, "Indiana", "Indiana (Green Line)", "40300", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30058", Direction.N, "Indiana (Harlem-bound)", -87.621371, 41.821732, "Indiana", "Indiana (Green Line)", "40300", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30108", Direction.S, "Irving Park (O'Hare Branch) (Forest Pk-bound)", -87.729229, 41.952925, "Irving Park", "Irving Park (Blue Line)", "40550", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30107", Direction.N, "Irving Park (O'Hare Branch) (O'Hare-bound)", -87.729229, 41.952925, "Irving Park", "Irving Park (Blue Line)", "40550", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30281", Direction.N, "Irving Park (Kimball-bound)", -87.674868, 41.954521, "Irving Park", "Irving Park (Brown Line)", "41460", true, TrainLine.BROWN));
        stops.add(new TrainStop(
                "30282", Direction.S, "Irving Park (Loop-bound)", -87.674868, 41.954521, "Irving Park", "Irving Park (Brown Line)", "41460", true, TrainLine.BROWN));
        stops.add(new TrainStop(
                "30015", Direction.S, "Jackson/Dearborn (Forest Pk-bound)", -87.629296, 41.878183, "Jackson", "Jackson (Blue Line)", "40070", true, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30014", Direction.N, "Jackson/Dearborn (O'Hare-bound)", -87.629296, 41.878183, "Jackson", "Jackson (Blue Line)", "40070", true, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30110", Direction.S, "Jackson/State (95th-bound)", -87.627596, 41.878153, "Jackson", "Jackson (Red Line)", "40560", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30109", Direction.N, "Jackson/State (Howard-bound)", -87.627596, 41.878153, "Jackson", "Jackson (Red Line)", "40560", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30228", Direction.S, "Jarvis (95th-bound)", -87.669092, 42.015876, "Jarvis", "Jarvis (Red Line)", "41190", false, TrainLine.RED));
        stops.add(new TrainStop(
                "30227", Direction.N, "Jarvis (Howard-bound)", -87.669092, 42.015876, "Jarvis", "Jarvis (Red Line)", "41190", false, TrainLine.RED));
        stops.add(new TrainStop(
                "30248", Direction.S, "Jefferson Park (Forest Pk-bound)", -87.760892, 41.970634, "Jefferson Park", "Jefferson Park (Blue Line)", "41280", true, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30247", Direction.N, "Jefferson Park (O'Hare-bound)", -87.760892, 41.970634, "Jefferson Park", "Jefferson Park (Blue Line)", "41280", true, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30219", Direction.N, "Kedzie (Loop-bound)", -87.704406, 41.804236, "Kedzie", "Kedzie (Orange Line)", "41150", true, TrainLine.ORANGE));
        stops.add(new TrainStop(
                "30220", Direction.S, "Kedzie (Midway-bound)", -87.704406, 41.804236, "Kedzie", "Kedzie (Orange Line)", "41150", true, TrainLine.ORANGE));
        stops.add(new TrainStop(
                "30202", Direction.W, "Kedzie (54th/Cermak-bound)", -87.705408, 41.853964, "Kedzie", "Kedzie (Pink Line)", "41040", true, TrainLine.PINK));
        stops.add(new TrainStop(
                "30201", Direction.E, "Kedzie (Loop-bound)", -87.705408, 41.853964, "Kedzie", "Kedzie (Pink Line)", "41040", true, TrainLine.PINK));
        stops.add(new TrainStop(
                "30225", Direction.N, "Kedzie (Kimball-bound)", -87.708821, 41.965996, "Kedzie", "Kedzie (Brown Line)", "41180", true, TrainLine.BROWN));
        stops.add(new TrainStop(
                "30226", Direction.S, "Kedzie (Loop-bound)", -87.708821, 41.965996, "Kedzie", "Kedzie (Brown Line)", "41180", true, TrainLine.BROWN));
        stops.add(new TrainStop(
                "30207", Direction.E, "Kedzie (63rd-bound)", -87.706155, 41.884321, "Kedzie", "Kedzie (Green Line)", "41070", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30208", Direction.W, "Kedzie (Harlem-bound)", -87.706155, 41.884321, "Kedzie", "Kedzie (Green Line)", "41070", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30049", Direction.W, "Kedzie-Homan (Forest Pk-bound)", -87.70604, 41.874341, "Kedzie-Homan", "Kedzie-Homan (Blue Line)", "40250", true, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30048", Direction.E, "Kedzie-Homan (O'Hare-bound)", -87.70604, 41.874341, "Kedzie-Homan", "Kedzie-Homan (Blue Line)", "40250", true, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30250", Direction.S, "Kimball (Loop-bound)", -87.713065, 41.967901, "Kimball", "Kimball (Brown Line)", "41290", true, TrainLine.BROWN));
        stops.add(new TrainStop(
                "30249", Direction.N, "Kimball (Terminal arrival)", -87.713065, 41.967901, "Kimball", "Kimball (Brown Line)", "41290", true, TrainLine.BROWN));
        stops.add(new TrainStop(
                "30217", Direction.E, "King Drive (Cottage Grove-bound)", -87.615546, 41.78013, "King Drive", "King Drive (Green Line)", "41140", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30218", Direction.W, "King Drive (Harlem-bound)", -87.615546, 41.78013, "King Drive", "King Drive (Green Line)", "41140", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30118", Direction.W, "Kostner (54th/Cermak-bound)", -87.733258, 41.853751, "Kostner", "Kostner (Pink Line)", "40600", true, TrainLine.PINK));
        stops.add(new TrainStop(
                "30117", Direction.E, "Kostner (Loop-bound)", -87.733258, 41.853751, "Kostner", "Kostner (Pink Line)", "40600", true, TrainLine.PINK));
        stops.add(new TrainStop(
                "30290", Direction.S, "Lake/State (95th-bound)", -87.627813, 41.884809, "Lake", "Lake (Red Line)", "41660", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30289", Direction.N, "Lake/State (Howard-bound)", -87.627813, 41.884809, "Lake", "Lake (Red Line)", "41660", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30135", Direction.E, "Laramie (63rd-bound)", -87.754986, 41.887163, "Laramie", "Laramie (Green Line)", "40700", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30136", Direction.W, "Laramie (Harlem-bound)", -87.754986, 41.887163, "Laramie", "Laramie (Green Line)", "40700", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30262", Direction.W, "LaSalle (Forest Pk-bound)", -87.631722, 41.875568, "LaSalle", "LaSalle (Blue Line)", "41340", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30261", Direction.E, "LaSalle (O'Hare-bound)", -87.631722, 41.875568, "LaSalle", "LaSalle (Blue Line)", "41340", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30031", Direction.W, "LaSalle/Van Buren (Inner Loop)", -87.631739, 41.8768, "LaSalle/Van Buren", "LaSalle/Van Buren (Brown, Orange, Purple & Pink Lines)", "40160", false, TrainLine.PURPLE, TrainLine.PINK, TrainLine.ORANGE));
        stops.add(new TrainStop(
                "30030", Direction.E, "LaSalle/Van Buren (Outer Loop)", -87.631739, 41.8768, "LaSalle/Van Buren", "LaSalle/Van Buren (Brown, Orange, Purple & Pink Lines)", "40160", false, TrainLine.BROWN));
        stops.add(new TrainStop(
                "30150", Direction.S, "Lawrence (95th-bound)", -87.658493, 41.969139, "Lawrence", "Lawrence (Red Line)", "40770", false, TrainLine.RED));
        stops.add(new TrainStop(
                "30149", Direction.N, "Lawrence (Howard-bound)", -87.658493, 41.969139, "Lawrence", "Lawrence (Red Line)", "40770", false, TrainLine.RED));
        stops.add(new TrainStop(
                "30204", Direction.S, "Linden (Howard-Loop-bound)", -87.69073, 42.073153, "Linden", "Linden (Purple Line)", "41050", true, TrainLine.PURPLE, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30203", Direction.N, "Linden (Linden-bound)", -87.69073, 42.073153, "Linden", "Linden (Purple Line)", "41050", true, TrainLine.PURPLE, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30198", Direction.S, "Logan Square (Forest Pk-bound)", -87.708541, 41.929728, "Logan Square", "Logan Square (Blue Line)", "41020", true, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30197", Direction.N, "Logan Square (O'Hare-bound)", -87.708541, 41.929728, "Logan Square", "Logan Square (Blue Line)", "41020", true, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30252", Direction.S, "Loyola (95th-bound)", -87.661061, 42.001073, "Loyola", "Loyola (Red Line)", "41300", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30251", Direction.N, "Loyola (Howard-bound)", -87.661061, 42.001073, "Loyola", "Loyola (Red Line)", "41300", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30124", Direction.S, "Madison/Wabash (Inner Loop)", -87.626098, 41.882023, "Madison/Wabash", "Madison/Wabash (Brown, Green, Orange, Pink & Purple Lines)", "40640", false, TrainLine.GREEN, TrainLine.PURPLE, TrainLine.PINK, TrainLine.ORANGE));
        stops.add(new TrainStop(
                "30123", Direction.N, "Madison/Wabash (Outer Loop)", -87.626098, 41.882023, "Madison/Wabash", "Madison/Wabash (Brown, Green, Orange, Pink & Purple Lines)", "40640", false, TrainLine.BROWN, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30053", Direction.S, "Main (Howard-Loop-bound)", -87.679538, 42.033456, "Main", "Main (Purple Line)", "40270", false, TrainLine.PURPLE, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30052", Direction.N, "Main (Linden-bound)", -87.679538, 42.033456, "Main", "Main (Purple Line)", "40270", false, TrainLine.PURPLE, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30090", Direction.N, "Merchandise Mart (Kimball-Linden-bound)", -87.633924, 41.888969, "Merchandise Mart", "Merchandise Mart (Brown & Purple Lines)", "40460", true, TrainLine.BROWN, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30091", Direction.S, "Merchandise Mart (Loop-bound)", -87.633924, 41.888969, "Merchandise Mart", "Merchandise Mart (Brown & Purple Lines)", "40460", true, TrainLine.BROWN, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30182", Direction.S, "Midway (Arrival)", -87.737875, 41.78661, "Midway", "Midway (Orange Line)", "40930", true, TrainLine.ORANGE));
        stops.add(new TrainStop(
                "30181", Direction.N, "Midway (Loop-bound)", -87.737875, 41.78661, "Midway", "Midway (Orange Line)", "40930", true, TrainLine.ORANGE));
        stops.add(new TrainStop(
                "30154", Direction.S, "Monroe/Dearborn (Forest Pk-bound)", -87.629378, 41.880703, "Monroe", "Monroe (Blue Line)", "40790", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30153", Direction.N, "Monroe/Dearborn (O'Hare-bound)", -87.629378, 41.880703, "Monroe", "Monroe (Blue Line)", "40790", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30212", Direction.S, "Monroe/State (95th-bound)", -87.627696, 41.880745, "Monroe", "Monroe (Red Line)", "41090", false, TrainLine.RED));
        stops.add(new TrainStop(
                "30211", Direction.N, "Monroe/State (Howard-bound)", -87.627696, 41.880745, "Monroe", "Monroe (Red Line)", "41090", false, TrainLine.RED));
        stops.add(new TrainStop(
                "30260", Direction.S, "Montrose (Forest Pk-bound)", -87.743574, 41.961539, "Montrose", "Montrose (Blue Line)", "41330", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30259", Direction.N, "Montrose (O'Hare-bound)", -87.743574, 41.961539, "Montrose", "Montrose (Blue Line)", "41330", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30287", Direction.N, "Montrose (Kimball-bound)", -87.675047, 41.961756, "Montrose", "Montrose (Brown Line)", "41500", true, TrainLine.BROWN));
        stops.add(new TrainStop(
                "30288", Direction.S, "Montrose (Loop-bound)", -87.675047, 41.961756, "Montrose", "Montrose (Brown Line)", "41500", true, TrainLine.BROWN));
        stops.add(new TrainStop(
                "30296", Direction.W, "Morgan (Harlem-54th/Cermak-bound)", -87.652193, 41.885586, "Morgan", "Morgan (Green & Pink Lines)", "41510", true, TrainLine.GREEN, TrainLine.PINK));
        stops.add(new TrainStop(
                "30295", Direction.E, "Morgan (Loop-63rd-bound)", -87.652193, 41.885586, "Morgan", "Morgan (Green & Pink Lines)", "41510", true, TrainLine.GREEN, TrainLine.PINK));
        stops.add(new TrainStop(
                "30021", Direction.S, "Morse (95th-bound)", -87.665909, 42.008362, "Morse", "Morse (Red Line)", "40100", false, TrainLine.RED));
        stops.add(new TrainStop(
                "30020", Direction.N, "Morse (Howard-bound)", -87.665909, 42.008362, "Morse", "Morse (Red Line)", "40100", false, TrainLine.RED));
        stops.add(new TrainStop(
                "30126", Direction.S, "North/Clybourn (95th-bound)", -87.649177, 41.910655, "North/Clybourn", "North/Clybourn (Red Line)", "40650", false, TrainLine.RED));
        stops.add(new TrainStop(
                "30125", Direction.N, "North/Clybourn (Howard-bound)", -87.649177, 41.910655, "North/Clybourn", "North/Clybourn (Red Line)", "40650", false, TrainLine.RED));
        stops.add(new TrainStop(
                "30079", Direction.S, "Noyes (Howard-Loop-bound)", -87.683337, 42.058282, "Noyes", "Noyes (Purple Line)", "40400", false, TrainLine.PURPLE, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30078", Direction.N, "Noyes (Linden-bound)", -87.683337, 42.058282, "Noyes", "Noyes (Purple Line)", "40400", false, TrainLine.PURPLE, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30035", Direction.W, "Oak Park (Forest Pk-bound)", -87.791602, 41.872108, "Oak Park", "Oak Park (Blue Line)", "40180", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30034", Direction.E, "Oak Park (O'Hare-bound)", -87.791602, 41.872108, "Oak Park", "Oak Park (Blue Line)", "40180", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30263", Direction.E, "Oak Park (63rd-bound)", -87.793783, 41.886988, "Oak Park", "Oak Park (Green Line)", "41350", false, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30264", Direction.W, "Oak Park (Harlem-bound)", -87.793783, 41.886988, "Oak Park", "Oak Park (Green Line)", "41350", false, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30297", Direction.N, "Oakton (Dempster-Skokie-bound)", -87.74722084, 42.02624348, "Oakton-Skokie", "Oakton-Skokie (Yellow Line)", "41680", true, TrainLine.YELLOW));
        stops.add(new TrainStop(
                "30298", Direction.S, "Oakton (Howard-bound)", -87.74722084, 42.02624348, "Oakton-Skokie", "Oakton-Skokie (Yellow Line)", "41680", true, TrainLine.YELLOW));
        stops.add(new TrainStop(
                "30172", Direction.S, "O'Hare Airport (Forest Pk-bound)", -87.90422307, 41.97766526, "O'Hare", "O'Hare (Blue Line)", "40890", true, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30171", Direction.N, "O'Hare Airport (Terminal Arrival)", -87.90422307, 41.97766526, "O'Hare", "O'Hare (Blue Line)", "40890", true, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30253", Direction.N, "Paulina (Kimball-bound)", -87.670907, 41.943623, "Paulina", "Paulina (Brown Line)", "41310", true, TrainLine.BROWN));
        stops.add(new TrainStop(
                "30254", Direction.S, "Paulina (Loop-bound)", -87.670907, 41.943623, "Paulina", "Paulina (Brown Line)", "41310", true, TrainLine.BROWN));
        stops.add(new TrainStop(
                "30200", Direction.W, "Polk (54th/Cermak-bound)", -87.66953, 41.871551, "Polk", "Polk (Pink Line)", "41030", true, TrainLine.PINK));
        stops.add(new TrainStop(
                "30199", Direction.E, "Polk (Loop-bound)", -87.66953, 41.871551, "Polk", "Polk (Pink Line)", "41030", true, TrainLine.PINK));
        stops.add(new TrainStop(
                "30185", Direction.N, "Pulaski (Loop-bound)", -87.724493, 41.799756, "Pulaski", "Pulaski (Orange Line)", "40960", true, TrainLine.ORANGE));
        stops.add(new TrainStop(
                "30186", Direction.S, "Pulaski (Midway-bound)", -87.724493, 41.799756, "Pulaski", "Pulaski (Orange Line)", "40960", true, TrainLine.ORANGE));
        stops.add(new TrainStop(
                "30029", Direction.W, "Pulaski (54th/Cermak-bound)", -87.724311, 41.853732, "Pulaski", "Pulaski (Pink Line)", "40150", true, TrainLine.PINK));
        stops.add(new TrainStop(
                "30028", Direction.E, "Pulaski (Loop-bound)", -87.724311, 41.853732, "Pulaski", "Pulaski (Pink Line)", "40150", true, TrainLine.PINK));
        stops.add(new TrainStop(
                "30180", Direction.W, "Pulaski (Forest Pk-bound)", -87.725663, 41.873797, "Pulaski", "Pulaski (Blue Line)", "40920", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30179", Direction.E, "Pulaski (O'Hare-bound)", -87.725663, 41.873797, "Pulaski", "Pulaski (Blue Line)", "40920", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30005", Direction.E, "Pulaski (63rd-bound)", -87.725404, 41.885412, "Pulaski", "Pulaski (Green Line)", "40030", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30006", Direction.W, "Pulaski (Harlem-bound)", -87.725404, 41.885412, "Pulaski", "Pulaski (Green Line)", "40030", true, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30007", Direction.N, "Quincy/Wells (Inner Loop)", -87.63374, 41.878723, "Quincy/Wells", "Quincy/Wells (Brown, Orange, Purple & Pink Lines)", "40040", false, TrainLine.PURPLE, TrainLine.PINK, TrainLine.ORANGE));
        stops.add(new TrainStop(
                "30008", Direction.S, "Quincy/Wells (Outer Loop)", -87.63374, 41.878723, "Quincy/Wells", "Quincy/Wells (Brown, Orange, Purple & Pink Lines)", "40040", false, TrainLine.BROWN));
        stops.add(new TrainStop(
                "30093", Direction.W, "Racine (Forest Pk-bound)", -87.659458, 41.87592, "Racine", "Racine (Blue Line)", "40470", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30092", Direction.E, "Racine (O'Hare-bound)", -87.659458, 41.87592, "Racine", "Racine (Blue Line)", "40470", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30039", Direction.S, "Randolph/Wabash (Inner Loop)", -87.626149, 41.884431, "Randolph/Wabash", "Randolph/Wabash (Brown, Green, Orange, Pink & Purple Lines)", "40200", false, TrainLine.GREEN, TrainLine.PURPLE, TrainLine.PINK, TrainLine.ORANGE));
        stops.add(new TrainStop(
                "30038", Direction.N, "Randolph/Wabash (Outer Loop)", -87.626149, 41.884431, "Randolph/Wabash", "Randolph/Wabash (Brown, Green, Orange, Pink & Purple Lines)", "40200", false, TrainLine.BROWN, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30119", Direction.E, "Ridgeland (63rd-bound)", -87.783661, 41.887159, "Ridgeland", "Ridgeland (Green Line)", "40610", false, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30120", Direction.W, "Ridgeland (Harlem-bound)", -87.783661, 41.887159, "Ridgeland", "Ridgeland (Green Line)", "40610", false, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30195", Direction.N, "Rockwell (Kimball-bound)", -87.6941, 41.966115, "Rockwell", "Rockwell (Brown Line)", "41010", true, TrainLine.BROWN));
        stops.add(new TrainStop(
                "30196", Direction.S, "Rockwell (Loop-bound)", -87.6941, 41.966115, "Rockwell", "Rockwell (Brown Line)", "41010", true, TrainLine.BROWN));
        stops.add(new TrainStop(
                "30080", Direction.N, "Roosevelt/Wabash (Loop-Harlem-bound)", -87.62659, 41.867405, "Roosevelt", "Roosevelt (Red, Orange & Green Lines)", "41400", true, TrainLine.GREEN, TrainLine.ORANGE));
        stops.add(new TrainStop(
                "30081", Direction.S, "Roosevelt/Wabash (Midway-63rd-bound)", -87.62659, 41.867405, "Roosevelt", "Roosevelt (Red, Orange & Green Lines)", "41400", true, TrainLine.GREEN, TrainLine.ORANGE));
        stops.add(new TrainStop(
                "30269", Direction.N, "Roosevelt/State (Howard-bound)", -87.627402, 41.867368, "Roosevelt", "Roosevelt (Red, Orange & Green Lines)", "41400", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30270", Direction.S, "Roosevelt/State (Howard-bound)", -87.627402, 41.867368, "Roosevelt", "Roosevelt (Red, Orange & Green Lines)", "41400", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30160", Direction.S, "Rosemont (Forest Pk-bound)", -87.859388, 41.983507, "Rosemont", "Rosemont (Blue Line)", "40820", true, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30159", Direction.N, "Rosemont (O'Hare-bound)", -87.859388, 41.983507, "Rosemont", "Rosemont (Blue Line)", "40820", true, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30155", Direction.N, "Sedgwick (Kimball-Linden-bound)", -87.639302, 41.910409, "Sedgwick", "Sedgwick (Brown & Purple Lines)", "40800", true, TrainLine.BROWN, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30156", Direction.S, "Sedgwick (Loop-bound)", -87.639302, 41.910409, "Sedgwick", "Sedgwick (Brown & Purple Lines)", "40800", true, TrainLine.BROWN, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30017", Direction.S, "Sheridan (95th-bound)", -87.654929, 41.953775, "Sheridan", "Sheridan (Red Line)", "40080", false, TrainLine.RED));
        stops.add(new TrainStop(
                "30016", Direction.N, "Sheridan (Howard-bound)", -87.654929, 41.953775, "Sheridan", "Sheridan (Red Line)", "40080", false, TrainLine.RED));
        stops.add(new TrainStop(
                "30293", Direction.N, "Sheridan (Howard-Linden-bound)", -87.654929, 41.953775, "Sheridan", "Sheridan (Red Line)", "40080", false));
        stops.add(new TrainStop(
                "30294", Direction.S, "Sheridan (Loop-bound)", -87.654929, 41.953775, "Sheridan", "Sheridan (Red Line)", "40080", false));
        stops.add(new TrainStop(
                "30026", Direction.N, "Skokie (Arrival)", -87.751919, 42.038951, "Skokie", "Dempster-Skokie  (Yellow Line)", "40140", true, TrainLine.YELLOW));
        stops.add(new TrainStop(
                "30027", Direction.S, "Skokie (Howard-bound)", -87.751919, 42.038951, "Skokie", "Dempster-Skokie  (Yellow Line)", "40140", true, TrainLine.YELLOW));
        stops.add(new TrainStop(
                "30164", Direction.S, "South Blvd (Howard-Loop-bound)", -87.678329, 42.027612, "South Boulevard", "South Boulevard (Purple Line)", "40840", false, TrainLine.PURPLE, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30163", Direction.N, "South Blvd (Linden-bound)", -87.678329, 42.027612, "South Boulevard", "South Boulevard (Purple Line)", "40840", false, TrainLine.PURPLE, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30070", Direction.N, "Southport (Kimball-bound)", -87.663619, 41.943744, "Southport", "Southport (Brown Line)", "40360", true, TrainLine.BROWN));
        stops.add(new TrainStop(
                "30071", Direction.S, "Southport (Loop-bound)", -87.663619, 41.943744, "Southport", "Southport (Brown Line)", "40360", true, TrainLine.BROWN));
        stops.add(new TrainStop(
                "30037", Direction.S, "Sox-35th (95th-bound)", -87.630636, 41.831191, "Sox-35th", "Sox-35th (Red Line)", "40190", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30036", Direction.N, "Sox-35th (Howard-bound)", -87.630636, 41.831191, "Sox-35th", "Sox-35th (Red Line)", "40190", true, TrainLine.RED));
        stops.add(new TrainStop(
                "30050", Direction.E, "State/Lake (Inner Loop)", -87.627835, 41.88574, "State/Lake", "State/Lake (Brown, Green, Orange, Pink & Purple Lines)", "40260", false, TrainLine.GREEN, TrainLine.PURPLE, TrainLine.PINK, TrainLine.ORANGE));
        stops.add(new TrainStop(
                "30051", Direction.W, "State/Lake (Outer Loop)", -87.627835, 41.88574, "State/Lake", "State/Lake (Brown, Green, Orange, Pink & Purple Lines)", "40260", false, TrainLine.BROWN, TrainLine.GREEN));
        stops.add(new TrainStop(
                "30170", Direction.S, "Thorndale (95th-bound)", -87.659076, 41.990259, "Thorndale", "Thorndale (Red Line)", "40880", false, TrainLine.RED));
        stops.add(new TrainStop(
                "30169", Direction.N, "Thorndale (Howard-bound)", -87.659076, 41.990259, "Thorndale", "Thorndale (Red Line)", "40880", false, TrainLine.RED));
        stops.add(new TrainStop(
                "30069", Direction.W, "UIC-Halsted (Forest Pk-bound)", -87.649707, 41.875474, "UIC-Halsted", "UIC-Halsted (Blue Line)", "40350", true, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30068", Direction.E, "UIC-Halsted (O'Hare-bound)", -87.649707, 41.875474, "UIC-Halsted", "UIC-Halsted (Blue Line)", "40350", true, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30073", Direction.S, "Washington/Dearborn (Forest Pk-bound)", -87.62944, 41.883164, "Washington", "Washington (Blue Line)", "40370", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30072", Direction.N, "Washington/Dearborn (O'Hare-bound)", -87.62944, 41.883164, "Washington", "Washington (Blue Line)", "40370", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30141", Direction.N, "Washington/Wells (Inner Loop)", -87.63378, 41.882695, "Washington/Wells", "Washington/Wells (Brown, Orange, Purple & Pink Lines)", "40730", true, TrainLine.PURPLE, TrainLine.PINK, TrainLine.ORANGE));
        stops.add(new TrainStop(
                "30142", Direction.S, "Washington/Wells (Outer Loop)", -87.63378, 41.882695, "Washington/Wells", "Washington/Wells (Brown, Orange, Purple & Pink Lines)", "40730", true, TrainLine.BROWN));
        stops.add(new TrainStop(
                "30231", Direction.N, "Wellington (Kimball-Linden-bound)", -87.653266, 41.936033, "Wellington", "Wellington (Brown & Purple Lines)", "41210", true, TrainLine.BROWN, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30232", Direction.S, "Wellington (Loop-bound)", -87.653266, 41.936033, "Wellington", "Wellington (Brown & Purple Lines)", "41210", true, TrainLine.BROWN, TrainLine.PURPLE));
        stops.add(new TrainStop(
                "30060", Direction.N, "Western (Loop-bound)", -87.684019, 41.804546, "Western", "Western (Orange Line)", "40310", true, TrainLine.ORANGE));
        stops.add(new TrainStop(
                "30061", Direction.S, "Western (Midway-bound)", -87.684019, 41.804546, "Western", "Western (Orange Line)", "40310", true, TrainLine.ORANGE));
        stops.add(new TrainStop(
                "30144", Direction.W, "Western (54th/Cermak-bound)", -87.685129, 41.854225, "Western", "Western (Pink Line)", "40740", true, TrainLine.PINK));
        stops.add(new TrainStop(
                "30143", Direction.E, "Western (Loop-bound)", -87.685129, 41.854225, "Western", "Western (Pink Line)", "40740", true, TrainLine.PINK));
        stops.add(new TrainStop(
                "30043", Direction.W, "Western (Forest Pk-bound)", -87.688436, 41.875478, "Western", "Western (Blue Line - Forest Park Branch)", "40220", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30042", Direction.E, "Western (O'Hare-bound)", -87.688436, 41.875478, "Western", "Western (Blue Line - Forest Park Branch)", "40220", false, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30130", Direction.S, "Western/Milwaukee (Forest Pk-bound)", -87.687364, 41.916157, "Western", "Western (Blue Line - O'Hare Branch)", "40670", true, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30129", Direction.N, "Western/Milwaukee (O'Hare-bound)", -87.687364, 41.916157, "Western", "Western (Blue Line - O'Hare Branch)", "40670", true, TrainLine.BLUE));
        stops.add(new TrainStop(
                "30283", Direction.N, "Western (Kimball-bound)", -87.688502, 41.966163, "Western", "Western (Brown Line)", "41480", true, TrainLine.BROWN));
        stops.add(new TrainStop(
                "30284", Direction.S, "Western (Loop-bound)", -87.688502, 41.966163, "Western", "Western (Brown Line)", "41480", true, TrainLine.BROWN));
        stops.add(new TrainStop(
                "30106", Direction.S, "Wilson (95th-bound)", -87.657588, 41.964273, "Wilson", "Wilson (Red Line)", "40540", false, TrainLine.RED));
        stops.add(new TrainStop(
                "30105", Direction.N, "Wilson (Howard-bound)", -87.657588, 41.964273, "Wilson", "Wilson (Red Line)", "40540", false, TrainLine.RED));
        return stops;
    }
}