/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

     // DriveTrain constants
     public static final double sensitivity = .75;


     //Shooter PIDF
     public static final int SHOOTER_PID_PRIMARY = 0;
     public static final int SHOOTER_TIMEOUT_MS  = 30;
     public static final double SHOOTER_P        = 0;
     public static final double SHOOTER_I        = 0;
     public static final double SHOOTER_D        = 0;
     public static final double SHOOTER_F        = 0; 
     public static final double SHOOTER_RAMP     = 0;  
     
     //Hood PIDF
     public static final int HOOD_PID_PRIMARY    = 0;
     public static final double HOOD_P           = 0;
     public static final double HOOD_I           = 0;
     public static final double HOOD_IZ          = 0;
     public static final double HOOD_D           = 0;
     public static final double HOOD_F           = 0; 
     public static final double HOOD_MAX_OUTPUT  = 1;
     public static final double HOOD_MIN_OUTPUT  = -1;
     public static final double HOOD_RAMP        = 0;
}
