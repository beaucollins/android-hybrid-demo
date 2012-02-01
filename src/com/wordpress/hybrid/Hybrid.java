package com.wordpress.hybrid;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.widget.Toast;
import android.util.Log;
import android.content.Context;

public class Hybrid extends Activity
{
	
	private static final String TAG = "Hybrid";
	private static final String INTERFACE_NAME = "native_interface";
	WebView mWebView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.main);
		
		mWebView = new WebView( this );
		mWebView.getSettings().setJavaScriptEnabled(true);
		
		setContentView(mWebView);
		
		refreshWebView();
		
    }
	
	private void refreshWebView(){
		mWebView.loadUrl("http://redimastudio.com/hybrid-demo.html");
		HybridInterface bridge = new HybridInterface( this );
		mWebView.addJavascriptInterface( bridge, INTERFACE_NAME );
	}
	
	private void updateButtonText(){
		Log.v(TAG, "Calling javascript");
		mWebView.loadUrl("javascript:setButtonLabel(\"Test\")");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.webmenu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.refresh:
	        refreshWebView();
	        return true;
		case R.id.demo:
			updateButtonText();
			return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	
	public class HybridInterface {
	    Context mContext;

	    HybridInterface(Context c) {
	        mContext = c;
	    }

	    public void showToast(String toast) {
	        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
	    }
	}
	
	
}
