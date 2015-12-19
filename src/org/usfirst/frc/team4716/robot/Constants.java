package org.usfirst.frc.team4716.robot;

public class Constants {
	
	//Gyro Constants
	public static double GYRO_SENSITIVITY = 0.03;
	
	 //Drive Constants
    public static double SENSITIVITY = 0.80;
    
    
    // Camera Stuff
    public static final String CAMERA_ADDRESS = "axis-camera";
    public static final int CAMERA_BRIGHTNESS = 15;
    public static final int CAMERA_COMPRESSION = 0;
    public static final int CAMERA_COLOR_LEVEL = 100;
    public static final int TARGETING_LIGHT_RELAY_CHANNEL = 5;
    
    
    // Targeting and Camera Thingamajigs
    public static final int Y_IMAGE_RES = 480;
    public static final double VIEW_ANGLE = 49.0;
    public static final double PI = 3.141592653;
    public static final int RECTANGULARITY_LIMIT = 40;
    public static final int ASPECT_RATIO_LIMIT = 55;
    public static final int TAPE_WIDTH_LIMIT = 50;
    public static final int VERTICAL_SCORE_LIMIT = 50;
    public static final int LR_SCORE_LIMIT = 50;
    public static final int AREA_MINIMUM = 150;
    public static final int MAX_PARTICLES = 8;
    
    //Autonomous Constants
    
    public static final double WHEEL_DIAMETER = 6;
    public static final double INCHES_PER_REV = (PI * WHEEL_DIAMETER);//18.849555918 

}
