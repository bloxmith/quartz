package com.bloxmith.siwgb;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @author p@bloxmith.com
 */
public class LineNotifyUtils {

	public static void notify(final String message) {
		try ( CloseableHttpClient httpClient = HttpClients.createDefault()) {
			HttpUriRequest httpPost = new HttpPost(
				new URIBuilder(URI.create("https://notify-api.line.me/api/notify")).
					addParameter("message", message).
					build()
			);
			httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
			httpPost.addHeader("Authorization", "Bearer U1Ws3rEs3Y7HEtJRtDyMF60JM4AQFtROQTMKgd4gqyi");

			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			httpResponse.close();

			httpClient.close();
		} catch (URISyntaxException | IOException ignore) {
		}
	}
}
