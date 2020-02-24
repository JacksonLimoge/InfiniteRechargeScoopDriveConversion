/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */

  private WPI_TalonFX right1;
  private WPI_TalonFX right2;
  private WPI_TalonFX left1;
  private WPI_TalonFX left2;
  private double rightEncoder;
  private double leftEncoder;
  private DifferentialDrive drive;
  public DriveTrain() {

    right1 = new WPI_TalonFX(RobotMap.DRIVETRAIN_CANID_RIGHT1);
    right1.configFactoryDefault();
    right1.setInverted(true);
    right1.setSensorPhase(true);
    

    right2 = new WPI_TalonFX(RobotMap.DRIVETRAIN_CANID_RIGHT2);
    right2.configFactoryDefault();
    right2.follow(right1);
    right2.setInverted(InvertType.FollowMaster);
    right2.setSensorPhase(true);

    left1 = new WPI_TalonFX(RobotMap.DRIVETRAIN_CANID_LEFT3);
    left1.configFactoryDefault();

    left2 = new WPI_TalonFX(RobotMap.DRIVETRAIN_CANID_LEFT4);
    left2.configFactoryDefault();
    left2.follow(left1);
    left2.setInverted(InvertType.FollowMaster);
    
    drive = new DifferentialDrive(left1, right1);

    rightEncoder = (((right1.getSelectedSensorPosition() + right2.getSelectedSensorPosition())/2)*-1);
    leftEncoder  = ((left1.getSelectedSensorPosition() + left2.getSelectedSensorPosition())/2);
  }
  

  /**
   * Tank Drive
   * @param leftSpeed
   *  from -1 to 1
   * @param rightSpeed
   *  from -1 to 1
   */
  public void tankDrive(double leftSpeed, double rightSpeed){
    drive.tankDrive(leftSpeed, rightSpeed);
  }

  /**
   * 
   * Arcade Drive
   * @param forward
   *  The value to translate forawd or backwards
   * @param turn
   *  Valuee to translate left or right
   */
  public void arcadeDrive(double forward, double turn){
    drive.arcadeDrive(forward, turn, false);
  }

  /**
	 * Set the speed of the right motors
	 * 
	 * @param speed
	 *            from -1 to 1
	 */
	public void setRightSpeed(double speed) {
		right1.set(speed);
	}

	/**
	 * Set the speed of the left motors
	 * 
	 * @param speed
	 *            from -1 to 1
	 */
	public void setLeftSpeed(double speed) {
		left1.set(speed);
		
  }
  
  /**
   * Limits the given input to the given magnitude.
	 * @return COD
   */
  public static double limit(double v, double limit) {
    return (Math.abs(v) < limit) ? v : limit * (v < 0 ? -1 : 1);
  }
 /**
   * @return the rightEncoder
   */
  public double getRightEncoder(){
    return rightEncoder;
  }

  /**
   * 
   * @return the leftEncoder
   */
  public double leftEncoder(){
    return leftEncoder;
  }

  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //CommandScheduler.getInstance().setDefaultCommand(subsystem, defaultCommand);
  }
}
