// *************************************************************************************************************************
// *************************************************************************************************************************
// Edit Date:   October 10, 2018 @ 17:16
// Team Name:   _____
// Team Number: _____
// Code Type:   OpMode for AUTONOMOUS
// NOTE:        This code is based on AUTON_withMethods_v01d, but moves custom METHODS to external location.
// Description: Order of operations
//               0.  Start at predetermined location (positioned by drivers prior to game start)
//               1.  Drive FORWARD, FAST, to CHECKPOINT_ONE
//               2.  STOP driving
//               3.  Spin LEFT, FAST, 90 degrees
//               4.  STOP spinning
//               5.  Drive FORWARD, FAST, from CHECKPOINT ONE to CHECKPOINT TWO
//               6.  STOP driving
//               7.  Spin RIGHT, FAST, 45 degrees
//               8.  STOP spinning
//               9.  Wait for Teleop
// *************************************************************************************************************************
// *************************************************************************************************************************
//
// DEFINE CODE PACKAGE
package org.firstinspires.ftc.teamcode;
//
// IMPORT PROGRAMMING ELEMENTS DESCRIBED ELSEWHERE IN THE CODE PACKAGE FOR USE IN THIS FILE
//      1. Classes
        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        //
        // TODO:  Is this where we tell this file how/where to find the custom METHODS stored in external file?
        import org.firstinspires.ftc.teamcode.MotorMethods_v00;    // <<-- is that even close???
//
//      2. Utilities
        import com.qualcomm.robotcore.util.ElapsedTime;
//
//      3. Hardware Types (ONE import per TYPE of hardware, NOT for each INSTANCE of that TYPE of hardware)
        import com.qualcomm.robotcore.hardware.DcMotor;
//
// DEFINE OpMode
// FORMAT:  @type(name="OpMode_Name", group="GroupName")
@Autonomous(name="AUTON_externalMethods_v00", group="Derry_FTC_Templates")
//
// DEFINE class
// FORMAT:  access level, class class_name, extends NameOfClass_this_new_class_extends_(if_any) {

public class AUTON_externalMethods_v00 extends LinearOpMode {
    // DECLARE OpMode MEMBERS
    // 1. Utilities
    //    FORMAT:   access level, UtilityName runtime = new UtilityName();
    private ElapsedTime runtime = new ElapsedTime();        // Use private unless you need access from other classes.
    //
    // 2. Hardware
    //    FORMAT:   HardwareType variableName;
    DcMotor leftDriveMotor;
    DcMotor rightDriveMotor;
    //
    // DEFINE CODE CONSTANTS
    // FORMAT:  access_level static final value_type VALUE_NAME = assigned_value;
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
    @Override
    // Call runOpMode() METHOD from the parent CLASS of LinearOpMode
    // FORMAT:  access_level, return_type or void, methodName(arguments), type of errors or exception {
    public void runOpMode() throws InterruptedException {
        // SET HARDWARE VARIABLE VALUES
        // FORMAT:  variableName = hardwareMap.hardwareType.get("nameInHardwareMap");
        leftDriveMotor  = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        //
        // SET DC MOTOR DIRECTIONS
        // FORMAT:  hardwareName.setDirection(DcMotor.Direction.DIRECTION)
        leftDriveMotor.setDirection(DcMotor.Direction.REVERSE);
        rightDriveMotor.setDirection(DcMotor.Direction.FORWARD);
        //
        // Display status and OpMode name on controller phone
        // FORMAT:  telemetry.desiredAction("arguments");
        telemetry.addData("Status", "Initialized");                     // Specific info to be sent to controller phone
        telemetry.update();                                             // Send info to controller phone
        //
        // SET ALL MOTORS TO DESIRED STARTING STATUS
        //      DC Motors
        MotorMethods_v00.stopDriveMotors(leftDriveMotor,rightDriveMotor,MOTOR_STOP);
        //
        // ****************************************************************************************************************
        // END OF PREPARATIONS
        // ****************************************************************************************************************
        //
        // WAIT for driver to press PLAY
        waitForStart();                                                 // The waitForStart() METHOD is part of
        //    the LinearOpMode CLASS,
        //    which is defined elsewhere in
        //    the FTC resource code
        //
        // ****************************************************************************************************************
        // AFTER driver presses PLAY, the ROBOT CONTROLLER PHONE will execute the code below this line
        // ****************************************************************************************************************
        //
        telemetry.addData("Status", "Running");                         // Specific info to be sent to controller phone
        telemetry.update();                                             // Send info to controller phone
        //
        //   0.  Start at predetermined location (positioned by drivers prior to game start)
        //       with robot gripper holding a pre-loaded item
        //
        //   1.  Drive FORWARD, FAST, to CHECKPOINT_ONE
        telemetry.addData("Status", "Drive FORWARD");
        telemetry.update();
        //
        MotorMethods_v00.driveForward(DRIVE_TIME_START_TO_CHECKPOINT_ONE,DRIVE_POWER_FAST);
        //
        //   2.  STOP driving
        MotorMethods_v00.stopDriveMotors(leftDriveMotor,rightDriveMotor,MOTOR_STOP);
        sleep(1000);
        //
        //   3.  Spin LEFT, FAST, 90 degrees
        telemetry.addData("Status", "Spin LEFT 90deg");
        telemetry.update();
        //
        MotorMethods_v00.spinLeft(DRIVE_TIME_90_DEG_TURN,DRIVE_POWER_MEDIUM);
        //
        //   4.  STOP spinning
        MotorMethods_v00.stopDriveMotors(leftDriveMotor,rightDriveMotor,MOTOR_STOP);
        sleep(1000);
        //
        //   5.  Drive FORWARD, FAST, from CHECKPOINT ONE to CHECKPOINT TWO
        telemetry.addData("Status", "Drive FORWARD");       
        telemetry.update();                                         
        //
        MotorMethods_v00.driveForward(DRIVE_TIME_CHECKPOINT_ONE_TO_TWO,DRIVE_POWER_FAST);
        //
        //   6.  STOP driving
        MotorMethods_v00.stopDriveMotors(leftDriveMotor,rightDriveMotor,MOTOR_STOP);
        sleep(1000);                               
        //
        //   7.  Spin RIGHT, FAST, 45 degrees
        telemetry.addData("Status", "Spin RIGHT 45deg");
        telemetry.update();
        //
        MotorMethods_v00.spinRight(DRIVE_TIME_45_DEG_TURN,DRIVE_POWER_FAST);
        //
        //   8.  STOP spinning
        MotorMethods_v00.stopDriveMotors(leftDriveMotor,rightDriveMotor,MOTOR_STOP);
        //
        //   9.  Wait for Teleop
    }
    // END of METHOD runOpMode
    //
    // ****************************************************************************************************************
    // END of AUTONOMOUS code
    // ****************************************************************************************************************
    //
}
// END of CLASS AUTON_externalMethods_v00
//
// *************************************************************************************************************************
// END OF FILE
// *************************************************************************************************************************
