package org.usfirst.frc.team4716.robot.subsystems;

import org.usfirst.frc.team4716.robot.RobotMap;
import org.usfirst.frc.team4716.robot.commands.drivetrain.ACSJoystickDrive;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ACSDriveTrain extends Subsystem {



	public double iGainHG = .05; // a lower iGain for the
	public double iGainLG = .03; // low gear prevents jerky movements
	private double leftPower = 0, rightPower = 0;
	private boolean isHighGear;

	public final double RIGHT_ENCOCDER_TO_DISTANCE_RATIO = (6 * Math.PI) / (12.0 * 255.0);
	public final double LEFT_ENCOCDER_TO_DISTANCE_RATIO = (6 * Math.PI) / (12.0 * 255.0);
	// Speed controllers
	private Talon leftDriveA = new Talon(RobotMap.leftDrivePortA);
	private Talon leftDriveB = new Talon(RobotMap.leftDrivePortB);
	private Talon leftDriveC = new Talon(RobotMap.leftDrivePortC);
	private Talon rightDriveA = new Talon(RobotMap.rightDrivePortA);
	private Talon rightDriveB = new Talon(RobotMap.rightDrivePortB);
	private Talon rightDriveC = new Talon(RobotMap.rightDrivePortC);
	// Encoders
	private Encoder leftEncoder = new Encoder(RobotMap.leftEncoderPortA, RobotMap.leftEncoderPortB, false);
	private Encoder rightEncoder = new Encoder(RobotMap.rightEncoderPortA, RobotMap.rightEncoderPortB, true);

	// Solenoids
	private DoubleSolenoid shifter = new DoubleSolenoid(RobotMap.shifterPortHigh, RobotMap.shifterPortLow);
	// Gyro
	public Gyro gyro;

	public ACSDriveTrain() {
		super("Drivebase");
		gyro = new Gyro(RobotMap.gyroPort);

	}
	
	// Default Command
	public void initDefaultCommand() {
		setDefaultCommand(new ACSJoystickDrive());
	}
	//Set Speed of Drive Train
	public void setLeftRightPower(double leftPower, double rightPower) {
		leftDriveA.set(leftPower);
		leftDriveB.set(leftPower);
		leftDriveC.set(leftPower);
		rightDriveA.set(-rightPower);
		rightDriveB.set(-rightPower);
		rightDriveC.set(-rightPower);
	}
	//Set Gear of Drive Train regardless of prior position
	public void setGear(boolean isHigh) {
		if (isHigh == true) {
			shifter.set(DoubleSolenoid.Value.kForward);
			isHighGear = false;
		} else if (isHigh == false) {
			shifter.set(DoubleSolenoid.Value.kReverse);
			isHighGear = true;
		} else {
			if (getGearState() == DoubleSolenoid.Value.kForward) {
				isHigh = true;
				setGear(isHigh);
			} else {
				isHigh = false;
				setGear(isHigh);
			}
		}
	}
	//Set Speed for near Perfect Turns (Test before to find desired angle)
	public void setSpeedAngleTurn(double _speed, double _angle, boolean _isRight) {
		double leftpow, rightpow;
		if (_isRight == true && Math.abs(gyro.getAngle()) < _angle) {
			rightpow = _speed;
			setLeftRightPower(0, rightpow);
		} else if (_isRight == false && Math.abs(gyro.getAngle()) < _angle) {
			leftpow = _speed;
			setLeftRightPower(leftpow, 0);
		}
	}
	//Set Speed to Drive Straight, adjust kP for less jerky movements.
	public void setSpeedDriveStraight(double _speed) {
		double rightpow, leftpow;
		double angle = gyro.getAngle();
		double kP = 0.02;
		if (2 < angle) {
			rightpow = _speed - kP * angle;
			setLeftRightPower(rightpow, _speed);
		} else if (-2 > angle) {
			leftpow = _speed + kP * angle;
			setLeftRightPower(_speed, leftpow);
		} else {
			setLeftRightPower(_speed, _speed);
		}

	}

	public void setRightSP(double rightPower) {
		this.rightPower = rightPower;
	}

	public void setLeftSP(double leftPower) {
		this.leftPower = leftPower;
	}

	// value is defined by either kReverse, kForward or kOff
	public Value getGearState() {
		return shifter.get();
	}
	
	//The rest of the getters, straight forward// 
	public Encoder getLeftEncoder() {
		return leftEncoder;
	}

	public double getLeftEncoderDistance() { // in feet
		return leftEncoder.get() * LEFT_ENCOCDER_TO_DISTANCE_RATIO;
	}

	public double getLeftEncoderDistanceInMeters() {
		return getLeftEncoderDistance() * 0.3048;
	}

	public Encoder getRightEncoder() {
		return rightEncoder;
	}

	public double getRightEncoderDistance() {
		return rightEncoder.get() * RIGHT_ENCOCDER_TO_DISTANCE_RATIO;
	}

	public double getRightEncoderDistanceInMeters() {
		return getRightEncoderDistance() * 0.3048;
	}

	public double getGyroAngle() {
		return -gyro.getAngle();
	}

	public double getGyroAngleInRadians() {
		return (getGyroAngle() * Math.PI) / 180.0;
	}

	public double getAverageDistance() {
		return (getRightEncoderDistance() + getLeftEncoderDistance()) / 2.0;
	}

	public double getLeftSP() {
		return leftPower;
	}

	public double getRightSP() {
		return rightPower;
	}
	//Sensor Resets// 
	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}

	public void resetGyro() {
		gyro.reset();
	}
	//Smart Dashboard Update//
	public void update() {
		SmartDashboard.putNumber("driveLeftEncoder", getLeftEncoderDistance());
		SmartDashboard.putNumber("driveRightEncoder", getRightEncoderDistance());
		SmartDashboard.putNumber("gyro", getGyroAngle());
		// super.update();
	}
}
