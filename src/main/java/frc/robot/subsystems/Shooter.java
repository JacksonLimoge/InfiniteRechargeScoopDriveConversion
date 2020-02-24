/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */

  private static WPI_TalonFX top;
  private static WPI_TalonFX bottom;
  private static CANSparkMax feeder;
  private static CANSparkMax hood;
  private static CANEncoder hoodEncoder;
  private static CANPIDController hoodPIDController;
  private static double flyWheelEncoder;
  private static double flyWheelCurrent;
  public Shooter() {

    top = new WPI_TalonFX(RobotMap.SHOOTER_CANID_TOP5);
    top.configFactoryDefault();
    top.setSensorPhase(true);// Not sure yet
    top.setNeutralMode(NeutralMode.Brake);// brake or coast?
    top.configNominalOutputForward(0, Constants.SHOOTER_TIMEOUT_MS);
    top.configNominalOutputReverse(0, Constants.SHOOTER_TIMEOUT_MS);
    top.configPeakOutputForward(1, Constants.SHOOTER_TIMEOUT_MS);
    top.configPeakOutputReverse(-1, Constants.SHOOTER_TIMEOUT_MS);
    top.configClosedloopRamp(Constants.SHOOTER_RAMP, Constants.SHOOTER_TIMEOUT_MS);
    
    // Shooter pidf
    top.config_kP(Constants.SHOOTER_PID_PRIMARY, Constants.SHOOTER_P, Constants.SHOOTER_TIMEOUT_MS);
    top.config_kI(Constants.SHOOTER_PID_PRIMARY, Constants.SHOOTER_I, Constants.SHOOTER_TIMEOUT_MS);
    top.config_kD(Constants.SHOOTER_PID_PRIMARY, Constants.SHOOTER_D, Constants.SHOOTER_TIMEOUT_MS);
    top.config_kF(Constants.SHOOTER_PID_PRIMARY, Constants.SHOOTER_F, Constants.SHOOTER_TIMEOUT_MS);//Unsure
   


    bottom = new WPI_TalonFX(RobotMap.SHOOTER_CANID_BOTTOM6);
    bottom.configFactoryDefault();
    bottom.setSensorPhase(true);// Not sure
    bottom.follow(top);
    bottom.setInverted(InvertType.FollowMaster);

    flyWheelEncoder = ((top.getSelectedSensorPosition() + bottom.getSelectedSensorPosition()) / 2);
    flyWheelCurrent = ((top.getSupplyCurrent() + bottom.getSupplyCurrent())/2);

    feeder = new CANSparkMax(RobotMap.SHOOTER_CANID_FEEDER, MotorType.kBrushless);
    feeder.restoreFactoryDefaults();
    feeder.setIdleMode(CANSparkMax.IdleMode.kBrake);

    hood = new CANSparkMax(RobotMap.SHOOTER_CANID_HOOD, MotorType.kBrushless);
    hood.restoreFactoryDefaults();
    hood.setIdleMode(CANSparkMax.IdleMode.kBrake);
    hoodEncoder = hood.getEncoder();

    // Hood pidf
    hoodPIDController = hood.getPIDController();
    hoodPIDController.setP(Constants.HOOD_P, Constants.HOOD_PID_PRIMARY);
    hoodPIDController.setI(Constants.HOOD_I, Constants.HOOD_PID_PRIMARY);
    hoodPIDController.setIZone(Constants.HOOD_IZ, Constants.HOOD_PID_PRIMARY);// Unsure of what it does
    hoodPIDController.setD(Constants.HOOD_D, Constants.HOOD_PID_PRIMARY);
    hoodPIDController.setFF(Constants.HOOD_F, Constants.HOOD_PID_PRIMARY);//Unsure
    hoodPIDController.setOutputRange(Constants.HOOD_MIN_OUTPUT, Constants.HOOD_MAX_OUTPUT, Constants.HOOD_PID_PRIMARY);

    
  }

  public static double FlyWheelCurrentOutput(){
    return flyWheelCurrent;
  }
  /**
   * Goes to the desired velocity
   * @param targetVelocity
   */
  public static void GoToTargetVelocity(double targetVelocity){
    top.set(ControlMode.Velocity, targetVelocity);
  }

  /**
   * Input a desired encoder value to get to 
   * @param setPoint 
   */
  public static void HoodPosition(double setPoint) {
    hoodPIDController.setReference(setPoint, ControlType.kPosition, Constants.HOOD_PID_PRIMARY);
  }





  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
