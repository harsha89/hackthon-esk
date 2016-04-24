package server.store;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import server.obj.Rating;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataStore {
    private String fileName = "DOCUMENT_DATA.json";

    public void addRating(Rating rating) {
        try {
            List<Rating> ratings = getAllRatings();
            if (ratings == null) {
                ratings = new ArrayList<Rating>();
            }
            ratings.add(rating);
            JSONArray list = new JSONArray();
            JSONObject obj;
            JSONObject root = new JSONObject();
            for (Rating rating1 : ratings) {
                obj = new JSONObject();
                obj.put("documentId", rating1.getDocumentId());
                obj.put("rating", rating1.getRating());
                obj.put("comment", rating1.getComment());
                obj.put("user", rating1.getUser());
                list.add(obj);
            }
            root.put("ratings", list);

            try {

                FileWriter file = new FileWriter(fileName);
                file.write(root.toJSONString());
                file.flush();
                file.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    public List<Rating> getAllRatings() {
        JSONParser parser = new JSONParser();
        List<Rating> ratings = new ArrayList<Rating>();
        try {
            Object obj = parser.parse(new FileReader(fileName));
            if (obj != null) {
                JSONObject jsonObject = (JSONObject) obj;
                JSONObject element;
                Rating rating;
                // loop array
                JSONArray msg = (JSONArray) jsonObject.get("ratings");
                if (msg != null) {
                    Iterator i = msg.iterator();
                    while (i.hasNext()) {
                        element = (JSONObject) i.next();
                        rating = new Rating();
                        rating.setComment((String) element.get("comment"));
                        Object object = element.get("rating");
                        if(object instanceof  Integer) {
                            rating.setRating((Integer) object);
                        } else if (object instanceof String) {
                            rating.setRating(Double.parseDouble((String) object));
                        } else {
                            rating.setRating((Double) object);
                        }
                        rating.setDocumentId((String) element.get("documentId"));
                        rating.setUser((String) element.get("user"));
                        ratings.add(rating);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return ratings;
    }
}
