package ttl.geoclient;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.glassfish.jersey.client.ClientConfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import ttl.larku.location.GeoResult;
import ttl.larku.location.LocationFetcher;

public class GeoClientJersey {
	public static void main(String[] args) throws JsonProcessingException, IOException {
		GeoClientJersey gc = new GeoClientJersey();
		GeoResult result = gc.fetchLatLong("22 Fairlane Drive, Los Angeles, CA");
		System.out.println(result);
	}
	
	private ClientConfig clientConfig;
	private Client client;
	private WebTarget googRoot;
	private final static String baseAddress = "https://maps.googleapis.com/maps/api/geocode/json";
	private LocationFetcher locationFetcher = new LocationFetcher();

	public GeoClientJersey() {

		clientConfig = new ClientConfig();
		clientConfig.register(JacksonJsonProvider.class);

		client = ClientBuilder.newClient(clientConfig);

		googRoot = client.target(baseAddress);
	}

	public GeoResult fetchLatLong(String address) throws JsonProcessingException, IOException {
		Response response = googRoot.queryParam("address", address)
			.request().get();
		
		if(response.getStatus() >= 400) {
			throw new WebApplicationException("Response Status is " + response.getStatus());
		}
		
		String jsonResult = response.readEntity(String.class);
		GeoResult geoResult = locationFetcher.readLocation(jsonResult);
		
		return geoResult;
	}
	
	

}
