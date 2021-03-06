package com.rishabh.githubquery;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by rishabh on 24-09-2017.
 */

public class NetworkUtils {

        final static String GITHUB_BASE_URL =
                "https://api.github.com/search/users";
        final static String PARAM_QUERY = "q";
        final static String PARAM_SORT = "sort";
        final static String sortBy = "followers";

        //Builds the URL used to query GitHub.
        public static URL buildUrl(String githubSearchQuery) {
            Uri builtUri = Uri.parse(GITHUB_BASE_URL).buildUpon()
                    .appendQueryParameter(PARAM_QUERY, githubSearchQuery)
                    .appendQueryParameter(PARAM_SORT, sortBy)
                    .build();

            URL url = null;
            try {
                url = new URL(builtUri.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            return url;
        }

        //Returns the entire result from the HTTP response due to /A hack.
        public static String getResponseFromHttpUrl(URL url) throws IOException {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = urlConnection.getInputStream();

                Scanner scanner = new Scanner(in);
                scanner.useDelimiter("\\A");

                boolean hasInput = scanner.hasNext();
                if (hasInput) {
                    return scanner.next();
                } else {
                    return null;
                }
            } finally {
                urlConnection.disconnect();
            }
        }
    }