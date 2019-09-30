package com.aqm.botM;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UAT {
	private String outputFolderName;
	private String package_name;
	private String launch_activity;

	public void extract_info(String PATH_OF_APK) {
		split_APK(PATH_OF_APK);
	}

	private void split_APK(String path_of_apk) {
		String[] after_split = path_of_apk.split("\\\\");

		String APP_NAME = after_split[after_split.length - 1];

		try {
			Path src = Paths.get(path_of_apk);
			Path dest = Paths.get(System.getProperty("user.dir") + "\\" + APP_NAME);
			Files.copy(src, dest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		outputFolderName = outputfolderName(APP_NAME);
		String command = "apktool d " + APP_NAME + " -f -o " + outputFolderName;
		ProcessBuilder builder_ = new ProcessBuilder("cmd.exe", "/c", command);

		try {
			Process pr_ = builder_.start();
			pr_.waitFor();
			pr_.destroy();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getPackageName();
		}
	}

	private void getPackageName() {
		String file_name = "AndroidManifest.xml";
		try {
			FileInputStream fstream = new FileInputStream(outputFolderName + "\\" + file_name);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			while ((strLine = br.readLine()) != null) {
				if (strLine.contains("package")) {
					String[] splitted = strLine.split("package");
					String[] temp = splitted[1].split("=\"");
					String[] temp_ = temp[1].split("\"\\s+");

					package_name = temp_[0];
				}
			}
			br.close();
			in.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		} finally {
			getLaunchActivity();
		}
	}

	private void getLaunchActivity() {
		String file_name = "AndroidManifest.xml";
		try {
			String text = new String(Files.readAllBytes(Paths.get(outputFolderName + "\\" + file_name)),
					StandardCharsets.UTF_8);
			String[] temp = text.split("android.intent.action.MAIN");
			String[] temp_ = temp[0].split("android:name=\"");
			String[] temp__ = temp_[temp_.length - 1].split("\"\\s+");

			launch_activity = temp__[0];
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		} finally {
			clean_up_cache();
		}
	}

	private void clean_up_cache() {
		if (new File(System.getProperty("user.dir") + "/" + outputFolderName).exists()) {
			File dir_loc = new File(outputFolderName);
			rmdir(dir_loc);
			System.out.println("deleted");
		}
	}

	public String getPackage_name() {
		return package_name;
	}

	public String getLaunch_activity() {
		return launch_activity;
	}

	private static String outputfolderName(String APK_name) {
		String[] temp = APK_name.split("\\.");
		return temp[0];
	}
	
	private void rmdir(final File folder) {     
	      if (folder.isDirectory()) {           //Check if folder file is a real folder
	          File[] list = folder.listFiles(); //Storing all file name within array
	          if (list != null) {               //Checking list value is null or not to check folder containts atlest one file
	              for (int i = 0; i < list.length; i++) {    
	                  File tmpF = list[i];
	                  if (tmpF.isDirectory()) {   //if folder  found within folder remove that folder using recursive method
	                      rmdir(tmpF);
	                  }
	                  tmpF.delete();             //else delete file
	              }
	          }
	          if (!folder.delete()) {            //if not able to delete folder print message
	            System.out.println("can't delete folder : " + folder);
	          }
	      }
	  }
}