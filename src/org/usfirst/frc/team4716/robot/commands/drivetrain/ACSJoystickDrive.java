package org.usfirst.frc.team4716.robot.commands.drivetrain;

import org.usfirst.frc.team4716.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ACSJoystickDrive extends Command {
	
	double maxChange = 0.10;
	double speed = 0;
	double input = 0;

    public ACSJoystickDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	input = Robot.oi.getJoyY();
    	if (input > (speed + maxChange)){
    		speed = speed + maxChange;
    	}else if(input < speed - maxChange){
    		speed = speed - maxChange;
    	}else{
    		speed = input;
    	}
//    	Robot.drivetrain.driveSpeedTurn(speed, Robot.oi.getJoyX());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
