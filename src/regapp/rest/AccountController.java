package regapp.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/account")
public class AccountController {
	
	@GET
	@Path("/{id}")
	public String getAccount(@PathParam("id") int id)  {
		return "got here";
	}
}
