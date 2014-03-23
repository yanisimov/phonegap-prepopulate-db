package com.yanisimov.prepopulatedb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * This class echoes a string called from JavaScript.
 */
public class PrePopulateDb extends CordovaPlugin {

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if (action.equals("copy")) 
		{
			//Use this code in your bootstrapping steps like in onCreate()
			try
			{
				String pName = this.getClass().getPackage().getName();
				this.copy("eve.db","/data/data/"+pName+"/app_database/");
				this.copy("0000000000000001.db","/data/data/"+pName+"/app_database/file__0/");
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	private void copy(String file, String folder) throws IOException 
	{
		
		File CheckDirectory;
		CheckDirectory = new File(folder);
		if (!CheckDirectory.exists())
		{ 
			CheckDirectory.mkdir();
		}

		InputStream in = this.cordova.getActivity().getApplicationContext().getAssets().open(file);
		OutputStream out = new FileOutputStream(folder+file);

		// Transfer bytes from in to out
		byte[] buf = new byte[1024];
		int len; while ((len = in.read(buf)) > 0) out.write(buf, 0, len);
		in.close(); out.close();

	}
}