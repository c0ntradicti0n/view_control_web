package me.cc.restclient;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.glassfish.jersey.client.ClientResponse;
import org.json.simple.JSONObject;
import org.primefaces.model.UploadedFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.LinkedHashTreeMap;
import com.google.gson.reflect.TypeToken;

import me.cc.beans.CcPyBean;
import me.cc.model.Tag;

import java.lang.reflect.Type;

import me.cc.model.*;

public class PythonClient {
	static Logger logger = Logger.getLogger(PythonClient.class);

	private final static String url = "http://127.0.0.1:5000";

	Gson gson;

	public PythonClient() {
		gson = new GsonBuilder().create();
	}

	public ArrayList<String> getPaths() {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(PythonClient.url);
		Response response = target.path("paths").request(MediaType.APPLICATION_JSON).get();
		String jsonString = response.readEntity(String.class);

		Type list = new TypeToken<List<String>>() {
		}.getType();
		ArrayList<String> ret = gson.fromJson(jsonString, list);
		logger.info(ret);
		return ret;
	}

	public static void main(String... args) {
		PythonClient rc = new PythonClient();
		logger.info(rc.getPaths());

	}

	public String getHTML(String path) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(PythonClient.url);
		Response response = target.queryParam("path", path).path("html").request(MediaType.APPLICATION_JSON).get();
		String jsonString = response.readEntity(String.class);
		System.out.println(jsonString);

		int bStart = jsonString.indexOf("<body>") + 6;
		int bEnd = jsonString.lastIndexOf("</body>");
		jsonString = jsonString.substring(bStart, bEnd);

		return jsonString;

	}

	public void sendTextFile(byte[] bs, String filename) {
		logger.info(bs.toString());
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(PythonClient.url);

		Response response = target.path("docload").queryParam("filename", filename).request(MediaType.APPLICATION_JSON)
				.post(Entity.json(bs));
		String jsonString = response.readEntity(String.class);
		System.out.println(jsonString);

		return;
	}

	public String recomputeAll() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(PythonClient.url);
		Response response = target.queryParam("pass", "kacke").path("compall").request(MediaType.APPLICATION_JSON)
				.get();
		String jsonString = response.readEntity(String.class);
		System.out.println(jsonString);
		return jsonString;

	}

	public String getMarkup(String text, ArrayList<HashMap<String, Tag>> annotationSets) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(PythonClient.url);
		Gson gson = new Gson();

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("text", text);
		param.put("spans", annotationSets);

		Response response = target.path("markup").request(MediaType.APPLICATION_JSON).post(Entity.json(param));
		String jsonString = response.readEntity(String.class);
		System.out.println(jsonString);
		return jsonString;
	}

	public <T> T stdCall(String command, String text, T t) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(PythonClient.url);

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("text", text);
		logger.info("calling " + command + " on text '" + text + "'");

		Response response = target.path(command).request(MediaType.APPLICATION_JSON).post(Entity.json(param));
		String jsonString = response.readEntity(String.class);
		logger.info("finally got an answer: '" + jsonString + "'");


		try { 
			T ret =  (T) (t.getClass().cast( (Object) gson.fromJson(jsonString, t.getClass())));
			System.out.println("type ");
			logger.info(ret);
			return ret;
		} catch (JsonSyntaxException e) {
			logger.error("unable to call, strange value: '" + t.toString() + "'");
			return t;
		}
	}

}
