package com.rishabh.githubquery;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

/**
 * Created by rishabh on 24-09-2017.
 */

public class MainActivity extends AppCompatActivity {

    private TextView mSearchResultsTextView;

//    private static int LIST_ITEMS = 100;
//    private RecyclerView.Adapter mAdapter;
//    private RecyclerView mSearchResultsTextView;

    private EditText mSearchBoxEditText;

    // Creates a variable to store a reference to the error message TextView
    private TextView mErrorMessageDisplay;

    // Creates a ProgressBar variable to store a reference to the ProgressBar
    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchBoxEditText = (EditText) findViewById(R.id.et_search_box);

        mSearchResultsTextView = (TextView) findViewById(R.id.tv_github_search_results_json);

//        mSearchResultsTextView = (RecyclerView) findViewById(R.id.rv_github_search_results_json);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        mSearchResultsTextView.setLayoutManager(layoutManager);

        //Get a reference to the error TextView using findViewById
        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);

        //Get a reference to the ProgressBar using findViewById
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
    }

    private void makeGithubSearchQuery() {
        String githubQuery = mSearchBoxEditText.getText().toString();
        URL githubSearchUrl = NetworkUtils.buildUrl(githubQuery);
        new GithubQueryTask().execute(githubSearchUrl);
    }

    private void showJsonDataView() {
        // First, make sure the error is invisible
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        // Then, make sure the JSON data is visible
        mSearchResultsTextView.setVisibility(View.VISIBLE);
    }

    //Error Checking
    private void showErrorMessage() {
        // First, hide the currently visible data
        mSearchResultsTextView.setVisibility(View.INVISIBLE);
        // Then, show the error
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    public class GithubQueryTask extends AsyncTask<URL, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String githubSearchResults = null;
            try {
                githubSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return githubSearchResults;
        }

        @Override
        protected void onPostExecute(String githubSearchResults) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (githubSearchResults != null && !githubSearchResults.equals("")) {
                showJsonDataView();
                //Optimization on UI
                mSearchResultsTextView.setText(githubSearchResults);
//                mSearchResultsTextView.setHasFixedSize(true);
//                mAdapter = new RecycleAdapter(LIST_ITEMS);
//                mSearchResultsTextView.setAdapter(mAdapter);

            } else {
                showErrorMessage();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_search) {
            makeGithubSearchQuery();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}