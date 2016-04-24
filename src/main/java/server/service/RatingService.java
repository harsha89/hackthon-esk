package server.service;

import server.obj.Rating;
import server.store.DataStore;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/ratingservice/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RatingService {

	@POST
	@Path("/rating/add/")
	public void addRating(Rating rating) {
		DataStore dataStore = new DataStore();
		dataStore.addRating(rating);
	}

	@GET
	@Path("/rating/getall")
	public List<Rating> getAllAccounts() {
		DataStore dataStore = new DataStore();
		return dataStore.getAllRatings();
	}

	@GET
	@Path("/rating/document/{id}")
	public List<Rating> getRatingsByDocument(@PathParam("id") String id) {
		DataStore dataStore = new DataStore();
		List<Rating> results = new ArrayList<Rating>();
		for(Rating rating : dataStore.getAllRatings()) {
			if(id.equalsIgnoreCase(rating.getDocumentId())) {
				results.add(rating);
			}
		}
		return results;
	}

}
