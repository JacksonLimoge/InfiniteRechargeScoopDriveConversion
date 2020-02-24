/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.DriveTrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.ControllerMap;
import frc.robot.RobotContainer;

public class ScoopDrive extends CommandBase {
  /**
   * Creates a new ScoopDrive.
   */
  private double oldwheel = 0;
	private double quickStopAccumulator;
	private double throttleDeadband = .1;
	private double wheelDeadband = .1;

  public ScoopDrive() {
    // Use addRequirements() here to declare subsystem dependencies.
   addRequirements(RobotContainer.driveTrainSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double wheelNonLinearity;
		double wheel;
		double throttle;
		wheel = -handleDeadband(RobotContainer.driverXbox.getRawAxis(ControllerMap.DRIVETRAIN_TURN_ID)*.6, wheelDeadband);
		throttle = handleDeadband(RobotContainer.driverXbox.getRawAxis(ControllerMap.DRIVETRAIN_THROTTLE_ID)*.8, throttleDeadband);
		
		SmartDashboard.putNumber("Wheel", wheel);
		SmartDashboard.putNumber("throttle", throttle);

		double negInertia = wheel - oldwheel;
		oldwheel = wheel;

		wheelNonLinearity = .5;
		// Apply a sin function that's scaled to make it feel better.
		wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) /
			Math.sin(Math.PI / 2.0 * wheelNonLinearity);
		wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) /
			Math.sin(Math.PI / 2.0 * wheelNonLinearity);
		wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) /
			Math.sin(Math.PI / 2.0 * wheelNonLinearity);
	
		double left, right, overPower;
		double sensitivity = 1.7;
		double angularPower;
		double linearPower;

		//Wow Negative inertia!
	
		double negInertiaAccumulator = 0;
		double negInertiaScalar;
		if (wheel * negInertia > 0) {
			negInertiaScalar = 2.5;
		  } else {
			if (Math.abs(wheel) > 0.65) {
			  negInertiaScalar = 5.0;
			} else {
			  negInertiaScalar = 3.0;
			}
		  }
		  sensitivity = Constants.sensitivity;

		  double negInertiaPower = negInertia * negInertiaScalar;
		  negInertiaAccumulator += negInertiaPower;

		  wheel = wheel + negInertiaAccumulator;
		  if(negInertiaAccumulator > 1 ){
			  negInertiaAccumulator -= 1;
		  }else if( negInertiaAccumulator < -1){
			  negInertiaAccumulator += 1;
		  }else{
			  negInertiaAccumulator = 0;
		  }
		  linearPower = throttle;

		  
			overPower = 0;
			if(throttle != 0){
				angularPower = Math.abs(throttle) * wheel * sensitivity - quickStopAccumulator;
			} else {
				angularPower = wheel * sensitivity - quickStopAccumulator;
			}
			
			if (quickStopAccumulator > 1) {
				quickStopAccumulator -= 1;
			  } else if (quickStopAccumulator < -1) {
				quickStopAccumulator += 1;
			  } else {
				quickStopAccumulator = 0.0;
			  }

		right = left = linearPower;
		left += angularPower;
		right-= angularPower;

		if (left > 1.0) {
			right -= overPower * (left - 1.0);
			left = 1.0;
		  } else if (right > 1.0) {
			left -= overPower * (right - 1.0);
			right = 1.0;
		  } else if (left < -1.0) {
			right += overPower * (-1.0 - left);
			left = -1.0;
		  } else if (right < -1.0) {
			left += overPower * (-1.0 - right);
			right = -1.0;
		  }
		
		  // SmartDashboard.putNumber("Left", left);
		  // SmartDashboard.putNumber("key", right);
		RobotContainer.driveTrainSubsystem.tankDrive(left, right);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.driveTrainSubsystem.tankDrive(0, 0);
  }

  // Returns true when the command should end.
  
  public boolean isFinished() {
    return false;
  }

  public double handleDeadband(double val, double deadband) {
		return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
	  }
}
