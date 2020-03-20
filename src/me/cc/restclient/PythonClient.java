package me.cc.restclient;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import me.cc.model.AnyAnswer;
import me.cc.model.Spot;
import me.cc.model.Tag;

import java.io.IOException;

public class PythonClient {
	static Logger logger = Logger.getLogger(PythonClient.class);

	public final static TypeReference String_Type =  new TypeReference<String>() { };
	public final static TypeReference AnnotationSet_Type = new TypeReference<ArrayList<ArrayList<Tag>>>() { };
	public final static TypeReference ListString_Type = new TypeReference<ArrayList<String>>() { };

	public final static TypeReference Int_Type = new TypeReference<Integer>()  { };
	public final static TypeReference AnyAnswerList_Type = new TypeReference<ArrayList<AnyAnswer>>() { };
	public final static TypeReference Topic2PathMap_Type = new TypeReference<HashMap<String, List<String>>>() { };
	HashMap<String, List<String>> topic2PathMap_obj = new HashMap<String, List<String>>();

	private String url = "";
	public ObjectMapper objectMapper = new ObjectMapper();
	private Client client;
	private WebTarget target;

	public PythonClient(String _url) {
		url = _url;
		client = ClientBuilder.newClient();
		target = client.target(url);
	}

	public HashMap<String, List<String>> getDocsPaths() {
		HashMap<String, List<String>> paths = stdCall(
				"docs_paths",
				null,
				topic2PathMap_obj,
				null ,
				Topic2PathMap_Type );
		return paths;
	}

	public HashMap<String, List<String>> getDiffPaths() {
		HashMap<String, List<String>> paths = stdCall(
				"diff_paths",
				null,
				topic2PathMap_obj,
				null ,
				Topic2PathMap_Type );
		return paths;
	}


	public String getLogs(String which) {
		Response response = target.queryParam("which", which).path("get_logs").request(MediaType.APPLICATION_JSON)
				.get();
		String jsonString = response.readEntity(String.class);
		logger.info("got log for " + which);
		return jsonString;
	}

	public String getHTML(String path) {
		Response response = target.queryParam("path", path).path("html").request(MediaType.APPLICATION_JSON).get();
		String jsonString = response.readEntity(String.class);
		logger.info(jsonString);
		int bStart = jsonString.indexOf("<body>") + 6;
		int bEnd = jsonString.lastIndexOf("</body>");
		jsonString = jsonString.substring(bStart, bEnd);
		return jsonString;
	}

	public String getDoc(String path, String restPath) {
		Response response = target.queryParam("path", path).path(restPath).request(MediaType.APPLICATION_JSON).get();
		String result = response.readEntity(String.class);
		logger.info(result);
		int bStart = result.indexOf("<body>") + 6;
		int bEnd = result.lastIndexOf("</body>");
		try {
			result = result.substring(bStart, bEnd);
		}
		catch (StringIndexOutOfBoundsException e) {
			logger.info("could not retrieve " + path);
		}
		return result;
	}

	public void sendTextFile(byte[] bs, String filename) {
		logger.info(bs.toString());
		Response response = target.path("docload").queryParam("filename", filename).request(MediaType.APPLICATION_JSON)
				.post(Entity.json(bs));
		String jsonString = response.readEntity(String.class);
		logger.info(jsonString);
		return;
	}

	public String recomputeAll() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url);
		Response response = target.queryParam("pass", "kacke").path("recompute_all").request(MediaType.APPLICATION_JSON)
				.get();
		String jsonString = response.readEntity(String.class);
		logger.info(jsonString);
		return jsonString;
	}

	public String getMarkup(String text, ArrayList<ArrayList<Tag>> arrayList) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url);

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("text", text);
		param.put("spans", arrayList);

		Response response = target.path("markup").request(MediaType.APPLICATION_JSON).post(Entity.json(param));
		String jsonString = response.readEntity(String.class);
		logger.info(jsonString);
		return jsonString;
	}

	@SuppressWarnings("unchecked")
	public <_T> _T stdCall(String command, Spot spot, _T SampleTargetTypeObject, Object data, TypeReference type) {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url);

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("spot", spot);
		param.put("data", data);
		logger.info("calling " + command + " on text '" + spot + "'");
		ObjectMapper mapper = new ObjectMapper();

		String paramJson = null;
		try {
			paramJson = mapper.writeValueAsString(param);
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}

		Entity<String> ent = Entity.entity(paramJson, MediaType.TEXT_PLAIN_TYPE);
		Response response = target.path(command).request(MediaType.APPLICATION_JSON).post(ent);
		String jsonData = response.readEntity(String.class);
		logger.info("finally got an answer: '" + jsonData + "'");
		logger.info("type " + type);
		try {
			return (_T) objectMapper.readValue(jsonData, type);
		} catch (JsonParseException e) {
			logger.info("JPE, so this is a String: '" + jsonData + "'");
			return (_T) jsonData;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			logger.error("Null Pointer? Maybe something is null, maybe the TypeSampleObject, that the type is taken of "
					+ command + " " + " text=" + spot + " type example obj=" + SampleTargetTypeObject + " data="
					+ data);
			e.printStackTrace();
		}
		return null;
	}

	public boolean ping() {
		/*
		try {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url);
		Response response = target.path("ping/").request(MediaType.APPLICATION_JSON).get();
		String jsonString = response.readEntity(String.class);
		ObjectMapper mapper = new ObjectMapper();
		AnyAnswer ans = new AnyAnswer();
		try {
			ans = mapper.readValue(jsonString, AnyAnswer.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (ans != null && ans.getDone().equalsIgnoreCase("yes")) {
			return true;
		} else {
			return false;
		}}
		catch (NullPointerException e) {
			return false;
		}

		 */
		return true;
	}

	public String sglCall(String what, String which) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url);
		Response response = target.queryParam("which", which).path(what).request(MediaType.APPLICATION_JSON)
				.get();
		String jsonString = response.readEntity(String.class);
		logger.info("Called Rest Client with command '" + what + "' for '" + which + "'");
		return jsonString;
	}

}
