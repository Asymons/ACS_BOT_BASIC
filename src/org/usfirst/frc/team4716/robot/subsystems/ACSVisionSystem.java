package org.usfirst.frc.team4716.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ACSVisionSystem {
    
	public static final double BIN_TARGET_ANGLE = 5;
	public static final double TOTE_TARGET_ANGLE = 0;
	public static boolean getBinFound(){
		return SmartDashboard.getBoolean("Bin detected");
	}
	public static double getBinAngle(){
		return SmartDashboard.getNumber("Bin angle");
	}
	public static boolean getToteFound(){
		return SmartDashboard.getBoolean("Tote detected");
	}
	public static double getToteAngle(){
		return SmartDashboard.getNumber("Tote angle");
	}
	
}

