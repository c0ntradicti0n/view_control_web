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
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import me.cc.model.AnyAnswer;
import me.cc.model.Spot;
import me.cc.model.Tag;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

public class PythonClient {
	static Logger logger = Logger.getLogger(PythonClient.class);

	private final static String url = "http://127.0.0.1:5000";

	public ObjectMapper objectMapper = new ObjectMapper();

	public PythonClient() {

	}

	public ArrayList<String> getPaths() {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(PythonClient.url);
		Response response = target.path("paths").request(MediaType.APPLICATION_JSON).get();
		String jsonData = response.readEntity(String.class);
		logger.info("got paths=" + jsonData);

		ArrayList<String> ret = null;
		try {
			ret = objectMapper.readValue(jsonData,
					TypeFactory.defaultInstance().constructCollectionType(List.class, String.class));
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info(ret);
		return ret;
	}

	public String getLogs(String which) {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(PythonClient.url);
		Response response = target.queryParam("which", which).path("get_logs").request(MediaType.APPLICATION_JSON)
				.get();
		String jsonString = response.readEntity(String.class);
		logger.info("got log for " + which);
		return jsonString;

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
		logger.info(jsonString);

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
		logger.info(jsonString);

		return;
	}

	public String recomputeAll() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(PythonClient.url);
		Response response = target.queryParam("pass", "kacke").path("compall").request(MediaType.APPLICATION_JSON)
				.get();
		String jsonString = response.readEntity(String.class);
		logger.info(jsonString);
		return jsonString;

	}

	public String getMarkup(String text, ArrayList<ArrayList<Tag>> arrayList) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(PythonClient.url);

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
		WebTarget target = client.target(PythonClient.url);

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
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(PythonClient.url);
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
		}
	}

	public String sglCall(String what, String which) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(PythonClient.url);
		Response response = target.queryParam("which", which).path(what).request(MediaType.APPLICATION_JSON)
				.get();
		String jsonString = response.readEntity(String.class);
		logger.info("Called Rest Client with command '" + what + "' for '" + which + "'");
		return jsonString;
	}

}
