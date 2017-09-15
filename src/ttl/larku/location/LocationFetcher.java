package ttl.larku.location;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public final class LocationFetcher {

	public static void main(String[] args) throws IOException {
		byte [] bytes = Files.readAllBytes(Paths.get("goog.json"));
		String jsonString = new String(bytes);
		
		LocationFetcher lf = new LocationFetcher();
		GeoResult result = lf.readLocation(jsonString);
		
		System.out.println("result is " + result);
	}
	
	public GeoResult readLocation(String jsonString) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(jsonString);
		
		String status = root.path("status").asText();

		ArrayNode studentsNode = (ArrayNode) root.path("results");
		
		JsonNode result = studentsNode.get(0);
		
		double lat = result.path("geometry").path("location").path("lat").asDouble();
		double lon = result.path("geometry").path("location").path("lng").asDouble();
		
		
		GeoResult gr = new GeoResult(lat, lon, status);
		return gr;
		
	}
} 
