package Controllers;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import Models.Interviews;

@RestController
public class TestRestController {
	

	@RequestMapping(method = RequestMethod.POST, value = "/data")
    public String test(@RequestBody String jsonRequest) throws Exception {
		
		
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");// populates the data of the configuration file
		//
		// //creating seession factory object
		SessionFactory factory = cfg.buildSessionFactory();
		//
		// //creating session object
		Session session = factory.openSession();
		
		boolean failed = false;
		Interviews thing=new Interviews(0,"0");
		
		try
		{
			Gson gson = new Gson();
		    thing = gson.fromJson(jsonRequest, Interviews.class);
		}
		catch (IllegalStateException | JsonSyntaxException exception)
		{
			System.out.println(exception.toString());
		  failed = true;
		}
		
		

		String sql = (String) ("insert into interviews(score,comment)values("+thing.getScore()+",'"+thing.getComment()+"');");
			
		Transaction tx = null;
	    tx = (Transaction) session.beginTransaction();
		
		SQLQuery query = session.createSQLQuery((java.lang.String) sql);


		query.executeUpdate();
		
		 tx.commit();
		
		session.close();
		cfg = null;
		
		
		cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");// populates the data of the configuration file
		//
		// //creating seession factory object
		factory = cfg.buildSessionFactory();
		//
		// //creating session object
		session = factory.openSession();

		sql = (String) ("select max(id) from interviews;");
		
		query = session.createSQLQuery((java.lang.String) sql);


		java.lang.Integer bigIndex=(java.lang.Integer)(query.list().get(0));
		
		
		session.close();
		cfg = null;
		
		return bigIndex.toString();
    }
	
	
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/datadelete")
    public void testDelete(@RequestBody String jsonRequest) throws Exception {
		
		
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");// populates the data of the configuration file
		//
		// //creating seession factory object
		SessionFactory factory = cfg.buildSessionFactory();
		//
		// //creating session object
		Session session = factory.openSession();

		int index=Integer.parseInt(jsonRequest);
		
		
//		String sql = (String) ("SET SQL_SAFE_UPDATES = 0;delete from interviews where id="+index+";SET SQL_SAFE_UPDATES = 1;");
		
		String sql = (String) ("delete from interviews where id="+index+";");
		
		Transaction tx = null;
	    tx = (Transaction) session.beginTransaction();
		
		SQLQuery query = session.createSQLQuery((java.lang.String) sql);


		query.executeUpdate();
		
		 tx.commit();
		session.close();
		cfg = null;
		
    }
	
	@RequestMapping(method = RequestMethod.POST, value = "/dataedit")
    public void testEdit(@RequestBody String jsonRequest) throws Exception {
		
		
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");// populates the data of the configuration file
		//
		// //creating seession factory object
		SessionFactory factory = cfg.buildSessionFactory();
		//
		// //creating session object
		Session session = factory.openSession();

		boolean failed = false;
		Interviews thing=new Interviews(0,"0");
		
		try
		{
			Gson gson = new Gson();
		    thing = gson.fromJson(jsonRequest, Interviews.class);
		}
		catch (IllegalStateException | JsonSyntaxException exception)
		{
			System.out.println(exception.toString());
		  failed = true;
		}
		
		
		
		String sql = (String) ("update interviews set score="+thing.getScore() +" , comment= '"+ thing.getComment()+"' WHERE id="+thing.getId()+";");
		
		Transaction tx = null;
	    tx = (Transaction) session.beginTransaction();
		
		SQLQuery query = session.createSQLQuery((java.lang.String) sql);


		query.executeUpdate();
		
		 tx.commit();
		session.close();
		cfg = null;
		
    }
	
	@RequestMapping(method = RequestMethod.POST, value = "/datasort")
    public String testSort(@RequestBody String jsonRequest) throws Exception {
		
		
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");// populates the data of the configuration file
		//
		// //creating seession factory object
		SessionFactory factory = cfg.buildSessionFactory();
		//
		// //creating session object
		Session session = factory.openSession();

		
		
		
		
		String sql = null;
		
		if(jsonRequest.equals("no"))
			{
				sql=(String) ("select * from interviews order by score;");
			}
		else
			{
			sql=(String) ("select * from interviews order by score desc;");
			}
		
		Transaction tx = null;
	    tx = (Transaction) session.beginTransaction();
		
		SQLQuery query = session.createSQLQuery((java.lang.String) sql).addEntity(Interviews.class);


		List<Interviews> result=query.list();
		
		 tx.commit();
		session.close();
		cfg = null;
		
		int resultLength = result.size();
		
		JsonArray resultObj = new JsonArray();
		
		for(int l=0;l<resultLength;l++)
		{
			Interviews temp = result.get(l);
			
			JsonObject obj = new JsonObject();

		      obj.addProperty("id", temp.getId());
		      obj.addProperty("score", temp.getScore());
		      obj.addProperty("comment", temp.getComment());
		      
		      resultObj.add(obj); 
			
		}
		
		
	return	new Gson().toJson(resultObj);
	
	
		
    }
	
	
}
