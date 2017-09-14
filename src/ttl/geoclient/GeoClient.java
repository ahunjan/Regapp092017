package ttl.geoclient;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;

import ttl.larku.location.GeoResult;
import ttl.larku.location.LocationFetcher;

public class GeoClient {
	public static void main(String[] args) throws JsonProcessingException, IOException {
		GeoClient gc = new GeoClient();
		GeoResult result = gc.fetchLatLong("22 Fairlane Drive, Los Angeles, CA");
		System.out.println(result);
	}
	
	private final static String baseAddress = "https://maps.googleapis.com/maps/api/geocode/json";
	private LocationFetcher locationFetcher = new LocationFetcher();
	public GeoResult fetchLatLong(String address) throws JsonProcessingException, IOException {
		WebClient client = WebClient.create(baseAddress);
		client.query("address", address);
		
		Response response = client.get();
		if(response.getStatus() >= 400) {
			throw new WebApplicationException("Response Status is " + response.getStatus());
		}
		
		String jsonResult = response.readEntity(String.class);
		GeoResult geoResult = locationFetcher.readLocation(jsonResult);
		
		return geoResult;
	}
	
	

}
