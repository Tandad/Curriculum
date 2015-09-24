package com.ss.webutil.net;

import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by liymm on 2015-04-02.
 */
public class Http {
    public static String get(String url) {

        byte[] buffer = new byte[1024];
        int ofs = 0;

        try {
            URL console = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) console
                    .openConnection();

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
        } catch (ConnectException e) {
            e.printStackTrace();
            System.out.println("ConnectException");
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            return new String(buffer, 0, ofs, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return new String(buffer, 0, ofs);
        }
    }

    public static boolean get(String url, File file) {

        byte[] buffer = new byte[1024];

        try {
            OutputStream fileStream = new FileOutputStream(file);

            URL console = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) console
                    .openConnection();

            conn.connect();

            InputStream is = conn.getInputStream();
            int len;
            while ((len = is.read(buffer)) != -1) {
                fileStream.write(buffer, 0, len);
            }

            is.close();
            fileStream.close();

            conn.disconnect();
            return true;
        } catch (ConnectException e) {
            e.printStackTrace();
            System.out.println("ConnectException");
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static String post(String url, String content) {

        byte[] buffer = new byte[1024];
        int ofs = 0;

        try {
            URL console = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) console
                    .openConnection();
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
        } catch (ConnectException e) {
            e.printStackTrace();
            System.out.println("ConnectException");
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            return new String(buffer, 0, ofs, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return new String(buffer, 0, ofs);
        }
    }

}
