/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//import frc.robot.commands.turn;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
//import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import frc.robot.commands.Vision.Seek;

public class limelight extends SubsystemBase {

  NetworkTable table;
  NetworkTableEntry tv;
  NetworkTableEntry tx;
  NetworkTableEntry ty;
  NetworkTableEntry ta;
  NetworkTableEntry ts;
  NetworkTableEntry tl;
  NetworkTableEntry getpipe;
  NetworkTableEntry camtran;

  NetworkTableEntry ledMode;
  NetworkTableEntry camMode;
  NetworkTableEntry pipeline;
  NetworkTableEntry stream;
  NetworkTableEntry snapshot;

  double txV;
  double taV;
  double d;
  String camtranV;
  double x, y, z, yaw;

  /**
   * Creates a new Limelight.
   */
  public limelight() {
    table = NetworkTableInstance.getDefault().getTable("limelight");
    tv = table.getEntry("tv");
    tx = table.getEntry("tx");
    ty = table.getEntry("ty");
    ta = table.getEntry("ta");
    ts = table.getEntry("ts");
    tl = table.getEntry("tl");

    getpipe = table.getEntry("getpipe");
    ledMode = table.getEntry("ledMode");
    camMode = table.getEntry("camMode");
    pipeline = table.getEntry("pipeline");
    stream = table.getEntry("stream");
    snapshot = table.getEntry("snapshot");
    camtran = table.getEntry("camtran");
  }

  /*
   * double horizontalOffset = tx.getDouble(0.0); double verticalOffset =
   * ty.getDouble(0.0); double targetArea = ta.getDouble(0.0); double validTargets
   * = tv.getDouble(0.0); double skew = ts.getDouble(0.0); double
   * latencyContribution = tl.getDouble(0.0);
   */

  public double getvalidTargets() {
    return (tv).getDouble(0.0);
  }

  public double gethorizontalOffset() {
    return (tx).getDouble(0);
  }

  public double getverticalOffset() {
    return (ty).getDouble(0);
  }

  public double gettargetArea() {
    if (tv.getDouble(0.0) == 1) {
      taV = ta.getDouble(taV);
    }
    return taV;
  }

  public double getskew() {
    return ts.getDouble(0.0);
  }

  public double getlatencyContribution() {
    return tl.getDouble(0.0);
  }

  public void setLedMode(double ledType) {
    ledMode.setNumber(ledType);
  }

  public void dashboard() {
    SmartDashboard.putNumber("Valid Targets", getvalidTargets());
    SmartDashboard.putNumber("Horizontal Offset", gethorizontalOffset());
    SmartDashboard.putNumber("Vertical Offset", getverticalOffset());
    SmartDashboard.putNumber("Target Area", gettargetArea());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}