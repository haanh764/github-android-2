package com.github.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import br.pelom.android.utils.LogManager;

import com.github.model.Feed;


public class Utils {
	/** Nome do log **/
	public static final String NOME_LOG = "GithubAndorid";

	private static final String FORMAT_DATE = "yyyy-MM-dd'T'HH:mm:ssZ";

	public static final String URL_COMMITS = "http://github.com/api/v2/json/commits/list/";
	
	public static final String URL_USER = "http://github.com/api/v2/json/user/show/";
	
	public static final String URL_WATCHED = "http://github.com/api/v2/json/repos/watched/";
		
	public static final String URL_GRAVATAR = "http://www.gravatar.com/avatar/";
	
	/**
	 * 
	 * @param data
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDate(String data) {
		try {
			return new SimpleDateFormat(FORMAT_DATE).parse(data);

		} catch (ParseException e) {
			LogManager.e(Utils.NOME_LOG, e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 
	 * @param listaFeeds
	 */
	public static void ordenarPorDateCommit(List<Feed> listaFeeds) {
		Collections.sort(listaFeeds, new Comparator<Feed>() {
			public int compare(Feed feed, Feed otherFeed) {
				return feed.getCommit().getData().compareTo(otherFeed.getCommit().getData()) * -1;
				
			}
		});
	}
	
	/**
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static byte[] readBytes(InputStream inputStream) throws IOException {
		// this dynamically extends to take the bytes you read
		final ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

		// this is storage overwritten on each iteration with bytes
		final int bufferSize = 1024;
		final byte[] buffer = new byte[bufferSize];

		// we need to know how may bytes were read to write them to the byteBuffer
		int len = 0;
		while ((len = inputStream.read(buffer)) != -1) {
			byteBuffer.write(buffer, 0, len);
		}

		// and then we can return your byte array.
		return byteBuffer.toByteArray();
	}
}
