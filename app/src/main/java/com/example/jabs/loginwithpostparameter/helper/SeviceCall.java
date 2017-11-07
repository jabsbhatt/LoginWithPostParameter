package com.example.jabs.loginwithpostparameter.helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SeviceCall extends AsyncTask<String, String, String> {
	Context c;
	String url;
	boolean isShowDialog;
	String msg;
	ProgressDialog pd;
	String method;
	int ser=0;
	ArrayList<String> key,value;
	public AsyncResponse delegate = null;
	public SeviceCall(Context c1, String url1, boolean isShowDialog1, String msg1, String method1, AsyncResponse delegate1, int ser1, ArrayList<String> key1, ArrayList<String> value1) {
		// TODO Auto-generated constructor stub
		c=c1;
		url=url1;
		isShowDialog=isShowDialog1;
		msg=msg1;
		method=method1;
		delegate=delegate1;
		key=key1;
		value=value1;
		pd=new ProgressDialog(c);
		pd.setMessage(msg);
		ser=ser1;
	}
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		if(isShowDialog){
			pd.show();
		}
	}
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub4
		return null;
	}
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		callservice();
	}
	private void callservice(){
		/*if(isShowDialog){
			pd.show();
		}*/

		StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						if(isShowDialog){
							if(pd.isShowing()){
								pd.dismiss();
							}
						}
						delegate.processFinish(response,ser);
					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						if(isShowDialog){
							if(pd.isShowing()){
								pd.dismiss();
							}
						}
						if (error instanceof NetworkError) {
							delegate.processFinish("Cannot connect to internet. Please check your connection.",10);
						} else if (error instanceof ServerError) {
							delegate.processFinish("The server could not be found. Please try again after some time!!",10);
						} else if (error instanceof AuthFailureError) {
							delegate.processFinish("Cannot connect to internet. Please check your connection.",10);
						} else if (error instanceof ParseError) {
							delegate.processFinish("Parsing error! Please try again after some time!!",10);
						} else if (error instanceof NoConnectionError) {
							delegate.processFinish("Cannot connect to internet. Please check your connection.",10);
						} else if (error instanceof TimeoutError) {
							delegate.processFinish("Connection TimeOut! Please check your internet connection.",10);
						}else {
							delegate.processFinish(error.toString(), 10);
						}
					}
				}){
			@Override
			protected Map<String,String> getParams(){
				Map<String,String> params = new HashMap<String, String>();
				for(int i=0;key.size()>i;i++)
				{
					params.put(key.get(i),value.get(i));
				}

				return params;
			}

		};

		RequestQueue requestQueue = Volley.newRequestQueue(c);
		int socketTimeout = 30000;
		RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
		stringRequest.setRetryPolicy(policy);
		requestQueue.add(stringRequest);
	}
}
