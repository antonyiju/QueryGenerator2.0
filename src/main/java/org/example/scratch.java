package org.example;

import org.json.JSONObject;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class scratch {
    public static void main(String[] args) {
        String s = "{\"results\":[{\"l_tax\":\"0.00\"},{\"l_tax\":\"0.01\"},{\"l_tax\":\"0.02\"},{\"l_tax\":\"0.03\"},{\"l_tax\":\"0.04\"},{\"l_tax\":\"0.05\"},{\"l_tax\":\"0.06\"},{\"l_tax\":\"0.07\"},{\"l_tax\":\"0.08\"}],\"headers\":[\"l_tax\"]}";
        JSONObject o = new JSONObject(s);
        String result = o.getJSONArray("results").get(0).toString();
        System.out.println(result);
    }
    public Table getTables(String filter_column,Table[] array){ // Return what table the filter_column belongs to
        String q = "";
        JSONObject result = getOutput(q);
        if (result == null) return array[1];
        else return array[0];


    }
    public JSONObject getTableSize(Table[] array){ // return size of the output without filtering

    }
    public JSONObject getValues(String filter_column, Table t, Table foreign_table){ // return unique values of filter_column
        String
    }
    public JSONObject getOutput(String q){
        // both the tables and joın columns and fılter column ı get form antony
        String query = "SELECT DISTINCT a.l_tax as c from pg1_sf0001_lineitem as a, pg2_sf0001_orders as b where a.l_orderkey = b.o_orderkey GROUP BY a.l_tax";
        //"SELECT MAX(nation.n_name) AS max_value, MIN(nation.n_name) AS min_value FROM pg3_sf0001_nation as nation, pg1_sf0001_region as region WHERE nation.n_regionkey = region.r_regionkey";
        try {

            URL url = new URL("http://localhost:1337/runquery");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "text/plain");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = query.getBytes("UTF-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    return response;
                    //System.out.println(response.toString());
                    // THE RESULT IN JSON FORMAT
                }
            } else {
                System.err.println("HTTP PUT request failed with status code: " + responseCode);
                //return null;
            }
            connection.disconnect();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
