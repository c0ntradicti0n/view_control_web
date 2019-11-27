package me.cc.run;

import java.io.*;

import org.apache.log4j.Logger;

public class Exec {
	static Logger logger = Logger.getLogger(Exec.class);

	public void run(String... args) {
		try {
			String line;
			Process p = Runtime.getRuntime().exec(String.join(" ", args));
			BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader bre = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			while ((line = bri.readLine()) != null) {
				System.out.println(line);
			}
			bri.close();
			while ((line = bre.readLine()) != null) {
				System.out.println(line);
			}
			bre.close();
			p.waitFor();
			System.out.println("Done.");
		} catch (Exception err) {
			err.printStackTrace();
		}
	}

	public static void killPort(int PID) {
		try {
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec("cmd /c netstat -ano | findstr " + PID);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String s = null;
			if ((s = stdInput.readLine()) != null) {
				int index = s.lastIndexOf(" ");
				String sc = s.substring(index, s.length());

				rt.exec("cmd /c Taskkill /PID" + sc + " /T /F");
			}
			logger.info("Old Process Stopped");
		} catch (Exception e) {
			logger.error("Something Went wrong with server");
		}
	}
}
