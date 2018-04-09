package com.example.graha.studentlogin;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class Stats extends Activity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            WebView webview = (WebView) findViewById(R.id.webView1);
            String content = "<html>"
                    + "  <head>"
                    + "    <script type=\"text/javascript\" src=\"jsapi.js\"></script>"
                    + "    <script type=\"text/javascript\">"
                    + "      google.load(\"visualization\", \"1\", {packages:[\"corechart\"]});"
                    + "      google.setOnLoadCallback(drawChart);"
                    + "      function drawChart() {"
                    + "        var data = google.visualization.arrayToDataTable(["
                    + "          ['Year', 'Sales', 'Expenses'],"
                    + "          ['2010',  1000,      400],"
                    + "          ['2011',  1170,      460],"
                    + "          ['2012',  660,       1120],"
                    + "          ['2013',  1030,      540]"
                    + "        ]);"
                    + "        var options = {"
                    + "          title: 'Truiton Performance',"
                    + "          hAxis: {title: 'Year', titleTextStyle: {color: 'red'}}"
                    + "        };"
                    + "        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));"
                    + "        chart.draw(data, options);"
                    + "      }"
                    + "    </script>"
                    + "  </head>"
                    + "  <body>"
                    + "    <div id=\"chart_div\" style=\"width: 1000px; height: 500px;\"></div>"
                    + "	   <img style=\"padding: 0; margin: 0 0 0 330px; display: block;\" src=\"truiton.png\"/>"
                    + "  </body>" + "</html>";

            WebSettings webSettings = webview.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webview.requestFocusFromTouch();
            webview.loadDataWithBaseURL( "file:///android_asset/", content, "text/html", "utf-8", null );
            //webview.loadUrl("file:///android_asset/Code.html"); // Can be used in this way too.
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            switch (item.getItemId()) {
                case R.id.action_to_image:
                    Intent intent = new Intent(Stats.this, GoogleImageGraphActivity.class);
                    startActivity(intent);
                    return true;

                default:
                    return super.onOptionsItemSelected(item);
            }
        }
    }