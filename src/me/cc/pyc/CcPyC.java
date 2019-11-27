package me.cc.pyc;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import me.cc.pynterface.PythonInterface;
import me.cc.run.Exec;
import py4j.GatewayServer;

public class CcPyC {
	static Logger logger = Logger.getLogger(CcPyC.class);

	public static GatewayServer server = null;
	public static PythonInterface py = null;
	private static final CcPyC pyc = new CcPyC();

	public   CcPyC ()  {
		try  {
			startSingleton(false);
		} catch (py4j.Py4JNetworkException e) {
	        Exec.killPort(server.getPort());
	        try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

	        startSingleton(true);
		}
	}
	
	public static void startSingleton(boolean force)  {
		if (server == null || force)  {
	        server = new GatewayServer();
	        server.start();
	    	py = (PythonInterface) server.getPythonServerEntryPoint(new Class[] { PythonInterface.class });	
			logger.info("python gateway connected");

	    	}
		else  {
			logger.error("python gateway seems to be running");
		}
	}


	public static CcPyC getInstance() {
		return pyc;
	}

	public void finalize() {
		// server.shutdown(true);
		// py.pythonShutdown();
		// server.shutdown();
		logger.info("pyc finalize, doing nothing");
	}
}
