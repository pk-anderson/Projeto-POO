import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;

public class RouteCalculator {

    private static final String URL_BASE = "http://dev.virtualearth.net/REST/v1/Routes/Driving";
    private static final String API_KEY = "At81zsX3nYYi9Zy9E2hFfI_t7PIuLqhhzMDiWlzxZIRmrYUZNPfIw-Kw5Kcit5EB";

    private String origin;
    private String destination;
    private String url;

    private Route resultadoRequisicao;

    private String unit = "km";
    private int responseCode;

    public RouteCalculator(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
        this.buildUrl();
    }

    private void buildUrl() {
        try {
            this.url = URL_BASE.concat("?wp.0=").
                            concat(URLEncoder.encode(origin, "UTF-8")).
                            concat("&wp.1=").
                            concat(URLEncoder.encode(destination, "UTF-8")).
                            concat("&distanceUnit=").
                            concat(unit).
                            concat("&key=").
                            concat(API_KEY);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public double getDistance() throws IOException {
        if (resultadoRequisicao == null) {
            doRequest();
        }
        return this.resultadoRequisicao.getTravelDistance();
    }


    private void doRequest() throws IOException {
        URL request = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) request.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        this.responseCode = conn.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(response.toString());
        double travelDistance = element.getAsJsonObject().get("resourceSets").getAsJsonArray().get(0).getAsJsonObject().get("resources").getAsJsonArray().get(0).getAsJsonObject().get("travelDistance").getAsDouble();

        this.resultadoRequisicao = new Route(travelDistance);
    }

}