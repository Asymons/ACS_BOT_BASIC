package org.usfirst.frc.team4716.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
   
	// ========= Drive Train ============= \\
	
	//motors
	public static int leftDrivePortA = 0,
					  leftDrivePortB = 1,
					  leftDrivePortC = 2,
					  rightDrivePortA = 3,
					  rightDrivePortB = 4,
					  rightDrivePortC = 5;
	//Shifters
	public static int shifterPortHigh = 0,
					  shifterPortLow = 1;
	
	//Encoders
	public static int leftEncoderPortA = 0,
					  leftEncoderPortB = 1,
					  rightEncoderPortA = 2,
					  rightEncoderPortB = 3;
	
	//Gyro
	public static int gyroPort = 0;
	
    
}
