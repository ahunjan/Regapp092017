package regapp.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import regapp.domain.Account;

@Path("/account")
public class AccountController {
	
	private static List<Account> accounts = new ArrayList<>();
	
	public AccountController() {
		Account a = new Account(1, "Joe", 224.40);
		accounts.add(a);

		a = new Account(2, "Mridhula", 2240.40);
		accounts.add(a);

		a = new Account(1, "Manoj", 333354.40);
		accounts.add(a);
	}
	
	@GET
	@Path("/{aid}")
	public Account getAccount(@PathParam("aid") int accountId) {
		for(Account act : accounts) {
			if(act.getId() == accountId) {
				return act;
			}
		}
		
		return null;
	}

}
