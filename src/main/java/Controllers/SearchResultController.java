package Controllers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import Models.*;
import Models.Developers;
import Models.Languages;


@RestController
public class SearchResultController {

	HashMap<String, HashSet<String>> languageMap;
	HashMap<String, HashSet<String>> programmingLanguageMap;
	HashMap<String, HashSet<String>> developerProgrammingLanguageMap;
	HashMap<String, HashSet<String>> developerLanguageMap;
	HashSet<String> unusedProgrammingLanguages;

	List<Developers> result = new ArrayList();

	public void initLanguageAndProgrammingLanguageMap() {

		languageMap = new HashMap();
		programmingLanguageMap = new HashMap();
		developerProgrammingLanguageMap = new HashMap();
		developerLanguageMap = new HashMap();
		unusedProgrammingLanguages = new HashSet<String>();
		unusedProgrammingLanguages.add("php");
		unusedProgrammingLanguages.add("ruby");
		unusedProgrammingLanguages.add("javascript");
		unusedProgrammingLanguages.add("python");
		unusedProgrammingLanguages.add("scala");
		unusedProgrammingLanguages.add("kotlin");
		unusedProgrammingLanguages.add("swift");

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");// populates the data of the configuration file
		//
		// //creating seession factory object
		SessionFactory factory = cfg.buildSessionFactory();
		//
		// //creating session object
		Session session = factory.openSession();

		String sql = (String) ("select * from languages ;");
		// +" where `id`=6)";

		SQLQuery query = session.createSQLQuery((java.lang.String) sql).addEntity(Languages.class);

		List<Languages> languageFullList = query.list();
		int length = languageFullList.size();

		for (int i = 0; i < length; i++) {
			Languages l = languageFullList.get(i);
			String tempCode = (String) l.getCode();
			String tempEmail = (String) l.getDevelopers().getEmail();
			HashSet<String> tempSet;
			if (!languageMap.containsKey(tempCode)) {
				tempSet = new HashSet();
			} else
				tempSet = languageMap.get(tempCode);
			tempSet.add(tempEmail);
			languageMap.put(tempCode, tempSet);

			HashSet<String> tempSetDeveloper;
			if (!developerLanguageMap.containsKey(tempEmail)) {
				tempSetDeveloper = new HashSet();
			} else
				tempSetDeveloper = developerLanguageMap.get(tempEmail);
			tempSetDeveloper.add(tempCode);
			developerLanguageMap.put(tempEmail, tempSetDeveloper);
			
		}

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

		sql = (String) ("select * from programminglanguages ;");
		// +" where `id`=6)";

		query = session.createSQLQuery((java.lang.String) sql).addEntity(Programminglanguages.class);

		List<Programminglanguages> programmingLanguageFullList = query.list();
		length = programmingLanguageFullList.size();

		for (int i = 0; i < length; i++) {
			Programminglanguages p = programmingLanguageFullList.get(i);
			String tempName = (String) p.getName();
			String tempEmail = (String) p.getDevelopers().getEmail();
			HashSet<String> tempSet;
			if (!programmingLanguageMap.containsKey(tempName)) {
				tempSet = new HashSet();
			} else
				tempSet = programmingLanguageMap.get(tempName);
			tempSet.add(tempEmail);
			programmingLanguageMap.put(tempName, tempSet);

			HashSet<String> tempSetDeveloper;
			if (!developerProgrammingLanguageMap.containsKey(tempEmail)) {
				tempSetDeveloper = new HashSet();
			} else
				tempSetDeveloper = developerProgrammingLanguageMap.get(tempEmail);
			tempSetDeveloper.add(tempName);
			developerProgrammingLanguageMap.put(tempEmail, tempSetDeveloper);
			if(unusedProgrammingLanguages.contains(tempName))unusedProgrammingLanguages.remove(tempName);
		}

		session.close();
		cfg = null;

	}

	@RequestMapping("/")
	public ModelAndView mymethodindex() {

		if (languageMap == null)
			initLanguageAndProgrammingLanguageMap();
		return new ModelAndView("homepage", "msg", "Welcome First Spring");
	}

	@RequestMapping("/search")
	public ModelAndView mymethod() {
		if (languageMap == null)
			initLanguageAndProgrammingLanguageMap();
		return new ModelAndView("searchpage", "msg", "Welcome First Spring");
	}

	@RequestMapping("/api")
	public ModelAndView mymethodapi() {
		return new ModelAndView("api", "msg", "Welcome First Spring");
	}
	
	@RequestMapping("/searchresult/{pageid}")
	public ModelAndView mymethod(@PathVariable int pageid, HttpServletRequest request,
			@RequestParam(value = "total", required = false) String total,
			@RequestParam(value = "totalRow", required = false) String resultSizeString) {

		int resultSize;
		int totalRowsToShow = 10;

		int start = pageid;

		if (pageid == 1) {
		} else {
			start = (pageid - 1) * totalRowsToShow + 1;
		}

		int totalPages = 1;

		if (total == null) {

			result = new ArrayList();

			resultSize = 0;

			String bd, vn, en, ja;
			String php, ruby, javascript, python, scala, kotlin, swift;

			bd = (String) request.getParameter("bd");
			vn = (String) request.getParameter("vn");
			en = (String) request.getParameter("en");
			ja = (String) request.getParameter("ja");

			php = (String) request.getParameter("php");
			ruby = (String) request.getParameter("ruby");
			javascript = (String) request.getParameter("javascript");
			python = (String) request.getParameter("python");
			scala = (String) request.getParameter("scala");
			kotlin = (String) request.getParameter("kotlin");
			swift = (String) request.getParameter("swift");

			List<String> languageList = new ArrayList();
			List<String> programmingLanguageList = new ArrayList();

			if (bd != null)
				languageList.add((String) "bd");
			if (vn != null)
				languageList.add((String) "vn");
			if (en != null)
				languageList.add((String) "en");
			if (ja != null)
				languageList.add((String) "ja");

			if (php != null)
				programmingLanguageList.add((String) "php");
			if (ruby != null)
				programmingLanguageList.add((String) "ruby");
			if (javascript != null)
				programmingLanguageList.add((String) "javascript");
			if (python != null)
				programmingLanguageList.add((String) "python");
			if (scala != null)
				programmingLanguageList.add((String) "scala");
			if (kotlin != null)
				programmingLanguageList.add((String) "kotlin");
			if (swift != null)
				programmingLanguageList.add((String) "swift");

			HashSet<String> intersectionLanguage = new HashSet<String>();
			HashSet<String> intersectionProgrammingLanguage = new HashSet<String>();
			HashSet<String> resultMap = new HashSet<String>();

			if (!languageList.isEmpty()) {

				String tempLanguageString = languageList.get(0);

				if (languageMap.containsKey(tempLanguageString)) {
					intersectionLanguage = new HashSet<String>(languageMap.get(tempLanguageString)); // use the copy
					// constructor
					int length = languageList.size();
					for (int i = 1; i < length; i++) {

						tempLanguageString = languageList.get(i);

						if (!languageMap.containsKey(tempLanguageString)) {
							intersectionLanguage = new HashSet<String>();
							break;
						}
						intersectionLanguage.retainAll(new HashSet<String>(languageMap.get(languageList.get(i))));

					}
				}

			}

			if (!programmingLanguageList.isEmpty()) {

				String tempLanguageString = programmingLanguageList.get(0);

				if (programmingLanguageMap.containsKey(tempLanguageString)) {
					intersectionProgrammingLanguage = new HashSet<String>(
							programmingLanguageMap.get(tempLanguageString)); // use the copy
					// constructor
					int length = programmingLanguageList.size();
					for (int i = 1; i < length; i++) {

						tempLanguageString = programmingLanguageList.get(i);

						if (!programmingLanguageMap.containsKey(tempLanguageString)) {
							intersectionProgrammingLanguage = new HashSet<String>();
							break;
						}

						intersectionProgrammingLanguage.retainAll(
								new HashSet<String>(programmingLanguageMap.get(programmingLanguageList.get(i))));

					}
				}

			}

			if (!languageList.isEmpty()) {
				if (!intersectionLanguage.isEmpty()) {
					resultMap = new HashSet<String>(intersectionLanguage);
					if (!programmingLanguageList.isEmpty()) {
						if (!intersectionProgrammingLanguage.isEmpty())
							resultMap.retainAll(intersectionProgrammingLanguage);
						else
							resultMap = new HashSet<String>();
					}

				} else
					resultMap = new HashSet<String>();
			}

			else {
				if (!programmingLanguageList.isEmpty()) {
					if (!intersectionProgrammingLanguage.isEmpty())
						resultMap = new HashSet<String>(intersectionProgrammingLanguage);
					else
						resultMap = new HashSet<String>();
				}
			}

			Iterator iterator = resultMap.iterator();

			if (!resultMap.isEmpty()) {
				// check values
				while (iterator.hasNext()) {
					String email = (String) iterator.next();

					result.add(new Developers((java.lang.String) email));
					resultSize++;
				}
			}

			totalPages = resultSize / totalRowsToShow;
			if (resultSize % totalRowsToShow != 0)
				totalPages++;
		}

		else {

			resultSize = Integer.parseInt((java.lang.String) resultSizeString);
			totalPages = Integer.parseInt((java.lang.String) total);
		}

		List<Developers> resultInOnePage = new ArrayList();

		int end = Math.min(start + totalRowsToShow, resultSize);

		start--;
		end--;

		for (int i = start; i <= end; i++) {

			resultInOnePage.add(new Developers((java.lang.String) (result.get(i).getEmail())));
		}

		int startid, endid;
		if (pageid % 4 != 0) {
			startid = (pageid / 4) * 4 + 1;
			endid = Math.min(startid + 3, totalPages);
		}

		else {
			startid = pageid - 3;
			endid = pageid;
		}

		ModelAndView mav = new ModelAndView("searchresult");

		mav.addObject("msg", resultInOnePage);
		mav.addObject("total", totalPages);
		mav.addObject("pageid", pageid);
		mav.addObject("startpageid", startid);
		mav.addObject("endpageid", endid);
		mav.addObject("totalRow", resultSize);

		// mav.addObject("languageList", languageList);
		// mav.addObject("programmingLanguageList", programmingLanguageList);

		return mav;
	}

	public List<Developers> select(int pageid, int total) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");// populates the data of the configuration file
		//
		// //creating seession factory object
		SessionFactory factory = cfg.buildSessionFactory();
		//
		// //creating session object
		Session session = factory.openSession();

		pageid--;

		String sql = (String) ("Select email from developers limit " + pageid + " , " + total);
		// +" where `id`=6)";

		SQLQuery query = session.createSQLQuery((java.lang.String) sql).addEntity(Developers.class);

		List<Developers> result1 = query.list();

		session.close();
		cfg = null;

		return result1;
	}

	public int totalPages(int total) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");// populates the data of the configuration file
		//
		// //creating seession factory object
		SessionFactory factory = cfg.buildSessionFactory();
		//
		// //creating session object
		Session session = factory.openSession();

		String sql = (String) ("select count(*) from developers ;");
		// +" where `id`=6)";

		SQLQuery query = session.createSQLQuery((java.lang.String) sql);

		BigInteger totalRows = (BigInteger) query.list().get(0);

		session.close();
		cfg = null;

		int ret = ((totalRows.intValue()) / total);

		if ((totalRows.intValue()) % total != 0)
			ret++;

		return ret;
	}

	@RequestMapping("/show/{pageid}")
	public ModelAndView mymethod(@PathVariable int pageid, HttpServletRequest request,
			@RequestParam(value = "total", required = false) String total) {
		int totalRowsToShow = 10;
		int start = pageid;
		if (pageid == 1) {
		} else {
			start = (pageid - 1) * totalRowsToShow + 1;
		}

		List<Developers> showTempresult = select(start, totalRowsToShow);
		int totalDevelopers = showTempresult.size();

		List<String> languageList = new ArrayList();
		List<String> programingLanguageList = new ArrayList();

		if (developerLanguageMap == null)
			initLanguageAndProgrammingLanguageMap();

		for (int d = 0; d < totalDevelopers; d++) {
			String tempEmail = (String) showTempresult.get(d).getEmail();

			StringBuffer languageSingleList = new StringBuffer("");
			int firstIndex;
			Iterator iterator;

			if (developerLanguageMap.containsKey(tempEmail)) {
				HashSet<String> languageSet = developerLanguageMap.get(tempEmail);
				firstIndex = 0;
				iterator = languageSet.iterator();

				// check values
				while (iterator.hasNext()) {
					String l = (String) iterator.next();
					if (firstIndex != 0) {
						languageSingleList.append("," + l);
					} else {
						languageSingleList.append(l);
						firstIndex++;
					}
				}

			}
			languageList.add((String) languageSingleList.toString());

			StringBuffer programmingLanguageSingleList = new StringBuffer("");
			if (developerProgrammingLanguageMap.containsKey(tempEmail)) {
				HashSet<String> ProgrammingLanguageSet = developerProgrammingLanguageMap.get(tempEmail);

				firstIndex = 0;
				iterator = ProgrammingLanguageSet.iterator();
				// check values
				while (iterator.hasNext()) {
					String p = (String) iterator.next();
					if (firstIndex != 0) {
						programmingLanguageSingleList.append("," + p);
					} else {
						programmingLanguageSingleList.append(p);
						firstIndex++;
					}

				}

			}
			programingLanguageList.add((String) programmingLanguageSingleList.toString());

		}

		int totalPages;
		if (total == null)
			totalPages = totalPages(totalRowsToShow);

		else
			totalPages = Integer.parseInt((java.lang.String) total);

		int startid, endid;
		if (pageid % 4 != 0) {
			startid = (pageid / 4) * 4 + 1;
			endid = Math.min(startid + 3, totalPages);
		}

		else {
			startid = pageid - 3;
			endid = pageid;
		}

		ModelAndView mav = new ModelAndView("showpage");

		mav.addObject("msg", showTempresult);

		mav.addObject("languageList", languageList);
		mav.addObject("programingLanguageList", programingLanguageList);

		mav.addObject("total", totalPages);
		mav.addObject("pageid", pageid);
		mav.addObject("startpageid", startid);
		mav.addObject("endpageid", endid);

		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/apiget")
	public String testGet() throws Exception {

		JsonArray jsonArray = new JsonArray();

		HashMap<String, HashSet<String>> map = developerLanguageMap;
		for (Entry<String, HashSet<String>> entry : map.entrySet()) {

			String email = entry.getKey();

			HashSet<String> programmingLanguageFordeletedEmail = new HashSet();
			HashSet<String> languageFordeletedEmail = new HashSet();

			if (developerProgrammingLanguageMap.containsKey(email))
				programmingLanguageFordeletedEmail = developerProgrammingLanguageMap.get(email);
			if (developerLanguageMap.containsKey(email))
				languageFordeletedEmail = developerLanguageMap.get(email);

			JsonObject obj = new JsonObject();
			obj.addProperty("email", new Gson().toJson(email));
			obj.addProperty("programmingLanguage", new Gson().toJson(programmingLanguageFordeletedEmail));
			obj.addProperty("language", new Gson().toJson(languageFordeletedEmail));

			jsonArray.add(obj);
		}

		return (String) new Gson().toJson(jsonArray);

	}

	@RequestMapping(method = RequestMethod.POST, value = "/apidel")
	public String testDelete(@RequestBody String jsonRequest) throws Exception {

		boolean failed = false;
		TempProgramminglanguages thing = new TempProgramminglanguages("0", "0");

			Gson gson = new Gson();
			thing = gson.fromJson((java.lang.String) jsonRequest, TempProgramminglanguages.class);
		
		boolean successfull = false;
		String emailForDelete = (String) thing.getDevelopers();
		String programmingLanguageForDelete = (String) thing.getName();

		HashSet<String> programmingLanguageSetFordeletedEmail = new HashSet();
		HashSet<String> languageSetFordeletedEmail = new HashSet();
		HashSet<String> programmingLanguageSetTodeletedEmail = new HashSet();

		if (developerProgrammingLanguageMap.containsKey(emailForDelete))
			programmingLanguageSetFordeletedEmail = developerProgrammingLanguageMap.get(emailForDelete);
		if (developerLanguageMap.containsKey(emailForDelete))
			languageSetFordeletedEmail = developerLanguageMap.get(emailForDelete);
		if (programmingLanguageMap.containsKey(programmingLanguageForDelete))
			programmingLanguageSetTodeletedEmail = programmingLanguageMap.get(programmingLanguageForDelete);

		if (programmingLanguageSetFordeletedEmail != null
				&& programmingLanguageSetFordeletedEmail.contains(programmingLanguageForDelete)) {
			successfull = true;
			programmingLanguageSetFordeletedEmail.remove(programmingLanguageForDelete);
		}

		if (successfull) {
			if (programmingLanguageSetTodeletedEmail != null)
				programmingLanguageSetTodeletedEmail.remove(emailForDelete);

			if (programmingLanguageMap.containsKey(programmingLanguageForDelete)) {
				if (programmingLanguageMap.get(programmingLanguageForDelete).isEmpty())
					unusedProgrammingLanguages.add(programmingLanguageForDelete);
			}

		}
		JsonObject obj = new JsonObject();

		obj.addProperty("email", new Gson().toJson(emailForDelete));
		obj.addProperty("programmingLanguageFordeletedEmail", new Gson().toJson(programmingLanguageSetFordeletedEmail));
		obj.addProperty("languageFordeletedEmail", new Gson().toJson(languageSetFordeletedEmail));

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");// populates the data of the configuration file
		//
		// //creating seession factory object
		SessionFactory factory = cfg.buildSessionFactory();
		//
		// //creating session object
		Session session = factory.openSession();

		// String sql = (String) ("SET SQL_SAFE_UPDATES = 0;delete from interviews where
		// id="+index+";SET SQL_SAFE_UPDATES = 1;");

		String sql = (String) ("delete from programminglanguages where developer='" + emailForDelete + "' and name= '"
				+ programmingLanguageForDelete + "';");

		Transaction tx = null;
		tx = (Transaction) session.beginTransaction();

		SQLQuery query = session.createSQLQuery((java.lang.String) sql);

		query.executeUpdate();

		tx.commit();
		session.close();
		cfg = null;

		// initLanguageAndProgrammingLanguageMap();

		return (String) new Gson().toJson(obj);

	}

	@RequestMapping(method = RequestMethod.POST, value = "/apipost")
	public String testPost(@RequestBody String jsonRequest) throws Exception {

		boolean failed = false;
		TempProgramminglanguages thing = new TempProgramminglanguages("0", "0");

			Gson gson = new Gson();
			thing = gson.fromJson((java.lang.String) jsonRequest, TempProgramminglanguages.class);
		
		boolean successfull = false;
		String emailForPost = (String) thing.getDevelopers();
		String programmingLanguageForPost = (String) thing.getName();

		HashSet<String> programmingLanguageSetForpostEmail = new HashSet();
		HashSet<String> languageSetForpostEmail = new HashSet();
		HashSet<String> programmingLanguageSetTopostEmail = new HashSet();

		if (developerProgrammingLanguageMap.containsKey(emailForPost))
			programmingLanguageSetForpostEmail = developerProgrammingLanguageMap.get(emailForPost);
		if (developerLanguageMap.containsKey(emailForPost))
			languageSetForpostEmail = developerLanguageMap.get(emailForPost);
		if (programmingLanguageMap.containsKey(programmingLanguageForPost))
			programmingLanguageSetTopostEmail = programmingLanguageMap.get(programmingLanguageForPost);

		if (programmingLanguageSetForpostEmail != null) {
			if (!programmingLanguageSetForpostEmail.contains(programmingLanguageForPost)) {
				programmingLanguageSetForpostEmail.add(programmingLanguageForPost);
				successfull = true;
			}

		}
		if (successfull) {
			if (programmingLanguageSetTopostEmail != null) {
				if (!programmingLanguageSetTopostEmail.contains(emailForPost))
					programmingLanguageSetTopostEmail.add(emailForPost);
			}
			if (!developerProgrammingLanguageMap.containsKey(emailForPost))
				developerProgrammingLanguageMap.put(emailForPost, programmingLanguageSetForpostEmail);
			if (!programmingLanguageMap.containsKey(programmingLanguageForPost))
				programmingLanguageMap.put(programmingLanguageForPost, programmingLanguageSetTopostEmail);

			if (unusedProgrammingLanguages.contains(programmingLanguageForPost))
				unusedProgrammingLanguages.remove(programmingLanguageForPost);

		}
		JsonObject obj = new JsonObject();

		obj.addProperty("email", new Gson().toJson(emailForPost));
		obj.addProperty("programmingLanguageForpostEmail", new Gson().toJson(programmingLanguageSetForpostEmail));
		obj.addProperty("languageForpostEmail", new Gson().toJson(languageSetForpostEmail));

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");// populates the data of the configuration file
		//
		// //creating seession factory object
		SessionFactory factory = cfg.buildSessionFactory();
		//
		// //creating session object
		Session session = factory.openSession();

		String sql = (String) ("insert into programminglanguages (name, developer) VALUES ('"
				+ programmingLanguageForPost + "' ,'" + emailForPost + "');");

		Transaction tx = null;
		tx = (Transaction) session.beginTransaction();

		SQLQuery query = session.createSQLQuery((java.lang.String) sql);

		query.executeUpdate();

		tx.commit();
		session.close();
		cfg = null;

		// initLanguageAndProgrammingLanguageMap();

		return (String) new Gson().toJson(obj);

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/reload")
	public void reload() {
		initLanguageAndProgrammingLanguageMap();
		
	}

	@RequestMapping(method = RequestMethod.POST, value = "/apiput")
	public String testPut(@RequestBody String jsonRequest) throws Exception {

		boolean failed = false;
		TempUpdateProgramminglanguages thing = new TempUpdateProgramminglanguages("0", "0","0");

			Gson gson = new Gson();
			thing = gson.fromJson((java.lang.String) jsonRequest, TempUpdateProgramminglanguages.class);
		
		
		
		boolean succesfull = false;
		String emailForPut = (String) thing.getDevelopers();
		String inprogrammingLanguageForPut = (String) thing.getInname();
		String outprogrammingLanguageForPut = (String) thing.getOutname();

		HashSet<String> programmingLanguageSetForputEmail = new HashSet();
		HashSet<String> languageSetForputEmail = new HashSet();
		HashSet<String> inprogrammingLanguageSetToputEmail = new HashSet();
		HashSet<String> outprogrammingLanguageSetToputEmail = new HashSet();

		if (developerProgrammingLanguageMap.containsKey(emailForPut))
			programmingLanguageSetForputEmail = developerProgrammingLanguageMap.get(emailForPut);
		if (developerLanguageMap.containsKey(emailForPut))
			languageSetForputEmail = developerLanguageMap.get(emailForPut);
		if (programmingLanguageMap.containsKey(inprogrammingLanguageForPut))
			inprogrammingLanguageSetToputEmail = programmingLanguageMap.get(inprogrammingLanguageForPut);
		if (programmingLanguageMap.containsKey(outprogrammingLanguageForPut))
			outprogrammingLanguageSetToputEmail = programmingLanguageMap.get(outprogrammingLanguageForPut);

		
		
		if (programmingLanguageSetForputEmail != null) {
			if (!programmingLanguageSetForputEmail.contains(inprogrammingLanguageForPut) && programmingLanguageSetForputEmail.contains(outprogrammingLanguageForPut))
			{
				programmingLanguageSetForputEmail.add(inprogrammingLanguageForPut);
				programmingLanguageSetForputEmail.remove(outprogrammingLanguageForPut);
				succesfull=true;
				
			}
		}
		
		if (succesfull) {
		if (inprogrammingLanguageSetToputEmail != null) {
			if (!inprogrammingLanguageSetToputEmail.contains(emailForPut))
				inprogrammingLanguageSetToputEmail.add(emailForPut);
		}
			
			if (outprogrammingLanguageSetToputEmail != null) {
				if (outprogrammingLanguageSetToputEmail.contains(emailForPut))
					outprogrammingLanguageSetToputEmail.remove(emailForPut);
			} 
			
			if (!developerProgrammingLanguageMap.containsKey(emailForPut))
				developerProgrammingLanguageMap.put(emailForPut, programmingLanguageSetForputEmail);
			if (!programmingLanguageMap.containsKey(inprogrammingLanguageForPut))
				programmingLanguageMap.put(inprogrammingLanguageForPut, inprogrammingLanguageSetToputEmail);
			if (programmingLanguageMap.containsKey(outprogrammingLanguageForPut))
				programmingLanguageMap.put(outprogrammingLanguageForPut, outprogrammingLanguageSetToputEmail);
			
			if (programmingLanguageMap.containsKey(outprogrammingLanguageForPut))
			{
				
					if(programmingLanguageMap.get(outprogrammingLanguageForPut).isEmpty())
						unusedProgrammingLanguages.add(outprogrammingLanguageForPut);
						
				
			}
				
			if (unusedProgrammingLanguages.contains(inprogrammingLanguageForPut))
				unusedProgrammingLanguages.remove(inprogrammingLanguageForPut);
			
		}
		JsonObject obj = new JsonObject();

		obj.addProperty("email", new Gson().toJson(emailForPut));
		obj.addProperty("programmingLanguageForputEmail", new Gson().toJson(programmingLanguageSetForputEmail));
		obj.addProperty("languageForputEmail", new Gson().toJson(languageSetForputEmail));

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");// populates the data of the configuration file
		//
		// //creating seession factory object
		SessionFactory factory = cfg.buildSessionFactory();
		//
		// //creating session object
		Session session = factory.openSession();

		
		String sql = (String) ("UPDATE programminglanguages SET name='" + inprogrammingLanguageForPut + "' WHERE name='"
				+  outprogrammingLanguageForPut+ "' and developer='"+emailForPut +"';");

		Transaction tx = null;
		tx = (Transaction) session.beginTransaction();

		SQLQuery query = session.createSQLQuery((java.lang.String) sql);

		query.executeUpdate();

		tx.commit();
		session.close();
		cfg = null;

		//initLanguageAndProgrammingLanguageMap();

		return (String) new Gson().toJson(obj);

	}
	
	
	@RequestMapping("/unused/{pageid}")
	public ModelAndView mymethodunused(@PathVariable int pageid, HttpServletRequest request,
			@RequestParam(value = "total", required = false) String total) {
		int totalRowsToShow = 10;
		int start = pageid;
		if (pageid == 1) {
		} else {
			start = (pageid - 1) * totalRowsToShow + 1;
		}
		
		if (developerLanguageMap == null)
			initLanguageAndProgrammingLanguageMap();
		
		
		int totalunused = unusedProgrammingLanguages.size();
		
		start--;
		int end = Math.min(start+totalRowsToShow,totalunused );
		List<String> resultunused = new ArrayList(unusedProgrammingLanguages).subList(start, end);

		int totalPages;
		if (total == null)
			{
			totalPages = totalunused / totalRowsToShow;
			if (totalunused % totalRowsToShow != 0)
				totalPages++;
			}

		else
			totalPages = Integer.parseInt((java.lang.String) total);

		int startid, endid;
		if (pageid % 4 != 0) {
			startid = (pageid / 4) * 4 + 1;
			endid = Math.min(startid + 3, totalPages);
		}

		else {
			startid = pageid - 3;
			endid = pageid;
		}

		ModelAndView mav = new ModelAndView("unused");

		mav.addObject("msg", resultunused);

		mav.addObject("total", totalPages);
		mav.addObject("pageid", pageid);
		mav.addObject("startpageid", startid);
		mav.addObject("endpageid", endid);

		return mav;
	}
	

}
