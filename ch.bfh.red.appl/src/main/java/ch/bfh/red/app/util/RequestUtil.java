package ch.bfh.red.app.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ch.bfh.red.app.view.RedAppUI;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;

/**
 * Util Class to handle Requests to and from Skybiometry API<br>
 * <br>
 * <b>TODO This class needs refactoring, if the customer says yes to this prove of concept!<b/>
 * 
 * @author reubd1
 */
public class RequestUtil {

	private static String API_KEY = "e5fc6bd4cd5a4772b707d382dde6d793";
	private static String API_SECRET = "f39385a6a9ea4f0d8392cacb78766870";
	private static String API_AUTH = "&api_key=" + API_KEY + "&api_secret=" + API_SECRET;
	private static String UID = "Dominik";
	private static String TIDS = "TEMP_F@06fc6960792e562813f93fca008500ac_9add5f77765af_53.20_49.14_0_1";

	public interface Callback {
		void callback(String response);
	}

	/*
	 * This method creates a HTTP Session by passing a defined URL
	 */
	private static void doRequest(String url) {

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		try {

			HttpResponse response = client.execute(post);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				System.out.println(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * Saves a specified face tag to permanent storage
	 */
	public static void save() {
		doRequest("http://api.skybiometry.com/fc/tags/save.json?uid=" + UID + "@" + RedAppUI.FACELOGIN + "&tids=" + TIDS
				+ API_AUTH);
	}

	/*
	 * train a face of a given user
	 */
	public static void train(String userId) {
		doRequest("http://api.skybiometry.com/fc/faces/train.json?uids=" + UID + "@" + RedAppUI.FACELOGIN + API_AUTH);

	}

	/*
	 * Method is used for recognizing trained user ids in one or more photos. For each detected
	 * face, method will return user ids that match specified face or empty result set if no matches
	 * found
	 */
	public static void recognize(File file) {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://api.skybiometry.com/fc/faces/recognize.json");
		try {

			MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
			// For File parameters
			Charset chars = Charset.forName("UTF-8");
			entity.addPart("api_key", new StringBody(API_KEY, chars));
			entity.addPart("api_secret", new StringBody(API_SECRET, chars));
			entity.addPart("attributes", new StringBody("age_est,gender,glasses,smiling,mood", chars));
			entity.addPart("uids", new StringBody("all@" + RedAppUI.FACELOGIN, chars));
			entity.addPart("filename", new StringBody("temp.jpg", chars));
			entity.addPart("file", new FileBody((file), "application/jpg"));
			post.setEntity(entity);

			HttpResponse response = client.execute(post);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			// String line = "";
			JSONObject json;
			JSONArray photos;
			JSONArray tags;
			JSONArray uids;
			String user = "";
			try {
				json = readJsonFromBufferedReader(rd);
				photos = json.getJSONArray("photos");
				// looping through All Photos
				for (int i = 0; i < photos.length(); i++) {
					JSONObject photo = photos.getJSONObject(i);
					tags = photo.getJSONArray("tags");
					for (int j = 0; j < tags.length(); j++) {
						JSONObject tag = tags.getJSONObject(j);
						uids = tag.getJSONArray("uids");

						for (int k = 0; k < uids.length(); k++) {
							JSONObject uid = uids.getJSONObject(k);
							user = uid.getString("uid");

						}

					}

				}
				System.out.println(user);
				if (user != null && !"".equals(user)) {
					Notification notif = new Notification("Identifiziert als: " + user);
					notif.show(Page.getCurrent());
				} else {
					Notification notif2 = new Notification("Gesicht wurde nicht erkannt");
					notif2.show(Page.getCurrent());
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				Notification notif2 = new Notification("Gesicht wurde nicht erkannt");
				notif2.show(Page.getCurrent());

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromBufferedReader(BufferedReader rd) throws IOException, JSONException {
		String jsonText = readAll(rd);
		JSONObject json = new JSONObject(jsonText);
		return json;

	}

	/*
	 * Returns tags for detected faces in one or more photos, with geometric information of the tag,
	 * eyes, nose and mouth, as well as additional attributes such as gender.
	 */
	public static void sendDetectRequest(File file) {

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://api.skybiometry.com/fc/faces/detect.json");
		try {

			MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
			// For File parameters
			Charset chars = Charset.forName("UTF-8");
			entity.addPart("api_key", new StringBody(API_KEY, chars));
			entity.addPart("api_secret", new StringBody(API_SECRET, chars));
			entity.addPart("attributes", new StringBody("age_est,gender,glasses,smiling,mood", chars));
			entity.addPart("filename", new StringBody("temp.jpg", chars));
			entity.addPart("file", new FileBody((file), "application/jpg"));
			post.setEntity(entity);

			HttpResponse response = client.execute(post);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				System.out.println(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
