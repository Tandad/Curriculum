package com.ss.webutil.net;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class HttpSSL {

	protected static class TrustAnyTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}

	}

	protected static class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	public static String get(String url) {
		for (int retry = 3; retry>0; retry--) {
			String ret = atomGet(url);
			if (ret != null)
				return ret;
		}

		return null;
	}

	static String atomGet(String url) {

		byte[] buffer = new byte[1024];
		int ofs = 0;
		
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());
			URL console = new URL(url);
			HttpsURLConnection conn = (HttpsURLConnection) console
					.openConnection();
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.connect();
			
			InputStream is = conn.getInputStream();

			while (true) {
				int ret = is.read();
				if (ret < 0)
					break;

				byte b = (byte) ret;

				if (ofs >= buffer.length) {
					byte[] temp = buffer;
					buffer = new byte[buffer.length + 1024];
					System.arraycopy(temp, 0, buffer, 0, temp.length);
				}

				buffer[ofs++] = b;
			}
			
			is.close();
			
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		try {
			return new String(buffer, 0, ofs, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return new String(buffer, 0, ofs);
		}
	}

	public static String post(String url, String content) {
		for (int retry = 3; retry>0; retry--) {
			String ret = atomPost(url, content);
			if (ret != null)
				return ret;
		}

		return null;
	}
	
	static String atomPost(String url, String content) {
		
		byte[] buffer = new byte[1024];
		int ofs = 0;
		
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());
			URL console = new URL(url);
			HttpsURLConnection conn = (HttpsURLConnection) console
					.openConnection();
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			
			if (content != null) {
				OutputStream os = conn.getOutputStream();
				os.write(content.getBytes("UTF-8"));
				os.close();
			}
			
			conn.connect();
			
			int code = conn.getResponseCode();
			
			System.out.println("response code: " + code);
			
			InputStream is = conn.getInputStream();

            while (true) {
                int ret = is.read();
                if (ret < 0)
                    break;

                byte b = (byte) ret;

                if (ofs >= buffer.length) {
                    byte[] temp = buffer;
                    buffer = new byte[buffer.length + 1024];
                    System.arraycopy(temp, 0, buffer, 0, temp.length);
                }

                buffer[ofs++] = b;
            }

			is.close();
			
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		try {
			return new String(buffer, 0, ofs, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return new String(buffer, 0, ofs);
		}
	}


}
