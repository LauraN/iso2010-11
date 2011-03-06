package com.umbrella.worldconq;

import java.net.InetAddress;

import com.umbrella.worldconq.comm.ClientAdapter;
import com.umbrella.worldconq.comm.ServerAdapter;
import com.umbrella.worldconq.domain.GameManager;
import com.umbrella.worldconq.domain.UserManager;
import com.umbrella.worldconq.ui.MainWindow;
import com.umbrella.worldconq.ui.StartupWindow;

public class WorldConqApp {

	private UserManager usrMgr = null;
	private GameManager gameMgr = null;

	private StartupWindow startupWindow = null;
	private MainWindow mainWindow = null;

	private ServerAdapter srvAdapter = null;
	private ClientAdapter cltAdapter = null;

	public static void main(String[] args) throws Exception {
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (final Exception e) {
			e.printStackTrace();
		}

		System.setProperty("java.security.policy",
			ClassLoader.getSystemResource("data/open.policy").toString());

		final WorldConqApp app = new WorldConqApp();
		app.setStartupMode();
	}

	private WorldConqApp() throws Exception {
		startupWindow = null;
		mainWindow = null;

		cltAdapter = new ClientAdapter();

		srvAdapter = new ServerAdapter();
		srvAdapter.setRemoteInfo(
			"Server",
			InetAddress.getByName("161.67.106.47"),
			1099);
		srvAdapter.connect();

		gameMgr = new GameManager(srvAdapter, cltAdapter);

		usrMgr = new UserManager(srvAdapter, gameMgr, cltAdapter);

		gameMgr.setUserManager(usrMgr);

	}

	public void setStartupMode() {
		if (mainWindow != null) {
			mainWindow.setVisible(false);
			mainWindow.dispose();
			mainWindow = null;
		}
		if (startupWindow != null) {
			startupWindow.setVisible(false);
			startupWindow.dispose();
		}
		startupWindow = new StartupWindow(this, usrMgr);
		startupWindow.setLocationRelativeTo(null);
		startupWindow.setVisible(true);
	}

	public void setMainMode() {
		if (startupWindow != null) {
			startupWindow.setVisible(false);
			startupWindow.dispose();
			startupWindow = null;
		}
		if (mainWindow != null) {
			mainWindow.setVisible(false);
			mainWindow.dispose();
		}
		mainWindow = new MainWindow(gameMgr, this);
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setVisible(true);
	}

	public void freeResources() {
		if (startupWindow != null) {
			startupWindow.setVisible(false);
			startupWindow.dispose();
			startupWindow = null;
		}

		if (mainWindow != null) {
			mainWindow.setVisible(false);
			mainWindow.dispose();
			mainWindow = null;
		}

		if (gameMgr != null) gameMgr = null;

		if (usrMgr != null) usrMgr = null;

		if (srvAdapter != null) {
			srvAdapter.disconnect();
			srvAdapter = null;
		}
	}

}
