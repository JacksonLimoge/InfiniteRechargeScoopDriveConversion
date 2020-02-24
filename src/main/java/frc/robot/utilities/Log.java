/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utilities;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class Log {
    public final static String LOG_SETTING = "Enable Logging?";
	public final static boolean LOG_ENABLE = true;
	public final static boolean LOG_DISABLE = false;
	public final static boolean LOG_DEFAULT = LOG_DISABLE;

	private boolean bConsoleLog = true;

	public Log(boolean enable) {
		bConsoleLog = enable;
	}

	public void setLogToConsole(boolean value) {
		bConsoleLog = value;
	}

	public void console(String message) {
		bConsoleLog = SmartDashboard.getBoolean(LOG_SETTING, LOG_DEFAULT);
		if (bConsoleLog == true)
			System.out.println(message);
	}
}
