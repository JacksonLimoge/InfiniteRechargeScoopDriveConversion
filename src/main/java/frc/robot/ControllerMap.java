/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Add your docs here.
 */
public class ControllerMap {

    //Drive Train
    public static Joystick DriveTrain_Drive_Joystick;
	public static final int DRIVETRAIN_THROTTLE_ID = 1;
	public static final int DRIVETRAIN_TURN_ID     = 4;
	public static final int DRIVETRAIN_QUICK_TURN  = 5;
	
	public static void setControllers(Joystick driver, Joystick operator) {
        DriveTrain_Drive_Joystick = driver;
    }
}
