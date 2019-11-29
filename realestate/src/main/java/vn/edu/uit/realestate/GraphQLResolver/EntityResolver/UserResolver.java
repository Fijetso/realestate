package vn.edu.uit.realestate.GraphQLResolver.EntityResolver;

import java.util.List;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

import vn.edu.uit.realestate.Relational.Model.Trade;
import vn.edu.uit.realestate.Relational.Model.User;

@Component
public class UserResolver implements GraphQLResolver<User>{
	
	public String getJob(User user) {
		if(user.getJob() == null) {
			return null;
		}
		return user.getJob().getName();
	}
	
	public List<Trade> getTrades(User user){
		return user.getTrades();
	}
}
