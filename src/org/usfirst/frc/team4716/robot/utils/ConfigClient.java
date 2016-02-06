package org.usfirst.frc.team4716.robot.utils;

import org.json.*;
import org.usfirst.frc.team4716.robot.RobotMap;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;;

public class ConfigClient {
	
	private String serverAddr;
	private int    port;
	private String configJsonText;
	
	public ConfigClient(String serverAddr, int port){
		this.serverAddr = serverAddr;
		this.port       = port;
	}
	
	public void UpdateRobotConfig() throws IOException{
		if(queryServer()){
			parseResult();
		}else{
			throw new IOException();
		}
	}
	
	private void parseResult(){
		JSONObject root = new JSONObject(this.configJsonText);
		// just trying to mess with gyro port for experiment 
		RobotMap.gyroPort = root.getJSONObject("sensor_configs")
								.getJSONObject("gyro")
								.getInt("port");
	}
	
	private boolean queryServer() {
		try {
			Socket server;
			
			server = new Socket(serverAddr, port);
			PrintWriter out = new PrintWriter(server.getOutputStream());
			Scanner in = new Scanner(server.getInputStream());
			out.println("");
			while(in.hasNext()){
				configJsonText += in.nextLine();
			}
			configJsonText = configJsonText.replaceAll("null", "");
			
			// cleaning up
			server.close();
			in.close();
			// successfully established connection
			return true; 
		} catch (IOException e) {
			// failed to connect to the server
			//e.printStackTrace();
			return false;
		}
		
	}
	
}
