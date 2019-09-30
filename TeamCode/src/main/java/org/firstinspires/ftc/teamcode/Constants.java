// *************************************************************************************************************************
// *************************************************************************************************************************
// Edit Date:   September, 2019 @ 08:55
// Team Name:   Lightning Robotics
// Team Number: FRC862
// Code Type:   CONSTANTS file for use by multiple OpMode or other CLASS files
// *************************************************************************************************************************
// *************************************************************************************************************************
// DEFINE CODE PACKAGE
package org.firstinspires.ftc.teamcode;
//
// DEFINE CODE CONSTANTS
// FORMAT:  access_level static final value_type VALUE_NAME = assigned_value;
//          - public means it can be accessed from other classes
//          - static means there is only one copy no matter how many instances of the CLASS your code creates
//          - final means its value never changes (constant)
//          - long, double, etc. is the type of value held by the variable
//
public class Constants {
    //
    // Drive times: all values are in milliseconds, and all values assume motors are using DRIVE_POWER_FAST value
    public static final long DRIVE_TIME_START_TO_CHECKPOINT_ONE     = 3000;
    public static final long DRIVE_TIME_CHECKPOINT_ONE_TO_TWO       = 2000;
    public static final long DRIVE_TIME_45_DEG_TURN                 = 250;
    public static final long DRIVE_TIME_90_DEG_TURN                 = DRIVE_TIME_45_DEG_TURN * 2;
    //
    // Drive powers (speeds): all values use range of 0 to 1
    public static final double MOTOR_STOP                           = 0;
    public static final double DRIVE_POWER_FAST                     = .8;
    public static final double DRIVE_POWER_ADJUSTER                 = 2;
    public static final double DRIVE_POWER_MEDIUM                   = DRIVE_POWER_FAST / DRIVE_POWER_ADJUSTER;
    public static final double DRIVE_POWER_SLOW                     = DRIVE_POWER_FAST / (DRIVE_POWER_ADJUSTER * 2);
    public static final double DRIVE_TIME_ADJUSTER_FOR_POWER_MED    = DRIVE_POWER_ADJUSTER;
    public static final double DRIVE_TIME_ADJUSTER_FOR_POWER_SLOW   = DRIVE_POWER_ADJUSTER * 2;
    //
}
// END of CLASS Constants
// *************************************************************************************************************************
// END OF FILE
// *************************************************************************************************************************
