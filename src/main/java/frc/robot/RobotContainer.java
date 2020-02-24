/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.DriveTrain.ScoopDrive;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import frc.robot.utilities.Log;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  

  public static DriveTrain driveTrainSubsystem = new DriveTrain();
  public static Shooter shooterSubsystem = new Shooter();
  public static Log m_logger;
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */

   //Xbox buttons
   public final static int POV_0 = 0;
   public final static int POV_45 = 45;
   public final static int POV_90 = 90;
   public final static int POV_135 = 135;
   public final static int POV_180 = 180;
   public final static int POV_225 = 225;
   public final static int POV_270 = 270;
   public final static int POV_315 = 315;
 
   public final static int OPERATOR_XBOX = 1;
   public final static int DRIVER_XBOX = 0;
 
   // === Axis ids; they are the same for each joystick
   public final static int XBOX_LEFT_XAXIS = 0;
   public final static int XBOX_LEFT_YAXIS = 1;
   public final static int XBOX_RIGHT_XAXIS = 4;
   public final static int XBOX_RIGHT_YAXIS = 5;
 
   public final static int XBOX_LEFT_TRIGGER = 2;
   public final static int XBOX_RIGHT_TRIGGER = 3;
 
   public final static int buttonAid = 1;
   public final static int buttonBid = 2;
   public final static int buttonXid = 3;
   public final static int buttonYid = 4;
   public final static int buttonBumpLid = 5;
   public final static int buttonBumpRid = 6;
   public final static int buttonBackid = 7;
   public final static int buttonStartid = 8;
   public final static int buttonLClickid = 9;
   public final static int buttonRClickid = 10;
 
   public Joystick operatorXbox;
   private Button oButtonA;
   private Button oButtonB;
   private Button oButtonX;
   private Button oButtonY;
   private Button oButtonBumpL;
   private Button oButtonBumpR;
   private Button oButtonBack;
   private Button oButtonStart;
   private Button oButtonLClick;
   private Button oButtonRClick;
   private POVButton oPOV0;
   private POVButton oPOV45;
   private POVButton oPOV90;
   private POVButton oPOV135;
   private POVButton oPOV180;
   private POVButton oPOV225;
   private POVButton oPOV270;
   private POVButton oPOV315;
 
   public static Joystick driverXbox;
   private Button dButtonA;
   private Button dButtonB;
   private Button dButtonX;
   private Button dButtonY;
   private Button dButtonBumpL;
   private Button dButtonBumpR;
   private Button dButtonBack;
   private Button dButtonStart;
   private Button dButtonLClick;
   private Button dButtonRClick;
   
   private POVButton dPOV0;
   private POVButton dPOV45;
   private POVButton dPOV90;
   private POVButton dPOV135;
   private POVButton dPOV180;
   private POVButton dPOV225;
   private POVButton dPOV270;
   private POVButton dPOV315;

{

 
  //  private SharedButton expelHatch;
  //  private SharedButton autoClimb;
   //private SharedTriggers expelCargo;
  
     // Setup driver joystick
     try {
       driverXbox = new Joystick(DRIVER_XBOX);
       dButtonA = new JoystickButton(driverXbox, 1);
       dButtonB = new JoystickButton(driverXbox, buttonBid);
       dButtonX = new JoystickButton(driverXbox, buttonXid);
       dButtonY = new JoystickButton(driverXbox, buttonYid);
       dButtonBumpL = new JoystickButton(driverXbox, buttonBumpLid);
       dButtonBumpR = new JoystickButton(driverXbox, buttonBumpRid);
       dButtonBack = new JoystickButton(driverXbox, buttonBackid);
       dButtonStart = new JoystickButton(driverXbox, buttonStartid);
       dButtonLClick = new JoystickButton(driverXbox, buttonLClickid);
       dButtonRClick = new JoystickButton(driverXbox, buttonRClickid);
       dPOV0 = new POVButton(driverXbox, POV_0);
       dPOV90 = new POVButton(driverXbox, POV_90);
       dPOV135 = new POVButton(driverXbox, POV_135);
       dPOV180 = new POVButton(driverXbox, POV_180);
       dPOV225 = new POVButton(driverXbox, POV_225);
       dPOV270 = new POVButton(driverXbox, POV_270);
       dPOV315 = new POVButton(driverXbox, POV_315);
     } catch (Exception e){
       m_logger.console("OI: Unable to setup driver joystick: " + e.toString());
     }
 
     try {
       operatorXbox = new Joystick(OPERATOR_XBOX);
       oButtonA = new JoystickButton(operatorXbox, buttonAid);
       oButtonB = new JoystickButton(operatorXbox, buttonBid);
       oButtonX = new JoystickButton(operatorXbox, buttonXid);
       oButtonY = new JoystickButton(operatorXbox, buttonYid);
       oButtonBumpL = new JoystickButton(operatorXbox, buttonBumpLid);
       oButtonBumpR = new JoystickButton(operatorXbox, buttonBumpRid);
       oButtonBack = new JoystickButton(operatorXbox, buttonBackid);
       oButtonStart = new JoystickButton(operatorXbox, buttonStartid);
       oButtonLClick = new JoystickButton(operatorXbox, buttonLClickid);
       oButtonRClick = new JoystickButton(operatorXbox, buttonRClickid);
       oPOV0 = new POVButton(operatorXbox, POV_0);
       oPOV90 = new POVButton(operatorXbox, POV_90);
       oPOV135 = new POVButton(operatorXbox, POV_135);
       oPOV180 = new POVButton(operatorXbox, POV_180);
       oPOV225 = new POVButton(operatorXbox, POV_225);
       oPOV270 = new POVButton(operatorXbox, POV_270);
       oPOV315 = new POVButton(operatorXbox, POV_315);
       
     } catch (Exception e) {
      m_logger.console("OI: Unable to setup operator joystick: " + e.toString());
     }
     ControllerMap.setControllers(driverXbox,operatorXbox);
 
 
     try {
          
     // autoClimb = new SharedButton(new Button[] {dButtonStart, oButtonStart});
     //	expelCargo = new SharedTriggers(new Trigger[] {X});
       setupSharedCommands();
     } catch (Exception e) {
    //   Robot.m_logger.console("OI: Unable to setup shared commands: " + e.toString());
   }
  }

  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    driveTrainSubsystem.setDefaultCommand(new ScoopDrive());
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  //   // An ExampleCommand will run in autonomous
  //  // return m_autoCommand;
  // }

  public void setupSharedCommands() {
   // autoClimb.whenPressed(new ClimbDumb());
    }
    
}
