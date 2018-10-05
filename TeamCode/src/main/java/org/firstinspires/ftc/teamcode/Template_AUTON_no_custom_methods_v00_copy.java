/* ALL CODE COMMENTED OUT

//*************************************************************************************************************************
//***************************************** THIS FILE IS A TEACHING TEMPLATE **********************************************
// Edit Date:   October 03, 2018 @ 19:37
// Team Name:   _____
// Team Number: _____
// Code Type:   OpMode for AUTONOMOUS
// Description: Brief description of what this code does:
//               0.  Start at predetermined location (positioned by drivers prior to game start)
//                   with robot gripper holding a pre-loaded item
//               1.  Drive FORWARD, FAST, for 3 seconds
//               2.  STOP driving
//               3.  Spin LEFT, FAST, for 1 second
//               4.  STOP spinning
//               5.  Drive FORWARD, FAST, for 2 seconds
//               6.  STOP driving
//               7.  Spin RIGHT, SLOWLY, for 2 seconds
//               8.  STOP spinning
//               9.  Extend arm OUTWARD, for 1 second
//              10.  STOP arm
//              11.  Set gripper to OPEN position to release item
//              12.  Retract arm INWARD, for 1 second
//              13.  STOP arm
//              14.  Wait for Teleop
//*************************************************************************************************************************
//*************************************************************************************************************************
//
// DEFINE CODE PACKAGE      // All code used by your robot will reference this package. That code can be:
                            //      A)  Supplied by FTC
                            //      B)  Written by someone other than FTC (you, another team, etc.) and then
                            //          placed inside this code package
package org.firstinspires.ftc.teamcode;
//
// IMPORT PROGRAMMING ELEMENTS DESCRIBED ELSEWHERE IN THE CODE PACKAGE FOR USE IN THIS FILE
//      1. Classes (specific)

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

//
//      2. Utilities (specific)
//
//      3. Hardware Types (ONE import per TYPE of hardware, NOT for each INSTANCE of that TYPE of hardware)

//
// DEFINE OpMode
// NOTE:    OpMode is the name for a set of code that contains the instructions the robot will perform. 
//          It is a class inside the ftc_app code package supplied by FTC.
// FORMAT:  @type(name="OpMode_Name", group="GroupName") 
@Autonomous(name="Template_AUTON_no_custom_methods_v00_copy", group="Derry_FTC_Templates")
//
// DEFINE class
// NOTE:    - All JAVA files must have at least one CLASS, but can have more.
//          - This file will create one CLASS, and that CLASS will be an OpMode that
//            extends the pre-defined CLASS named LinearOpMode, which is suplied by FTC as part of the CODE PACKAGE.
//          - Not all classes are OpModes.
//          - The CLASS that extends the LinearOpMode MUST be named to match
//            the FILENAME (EXcluding the ".java" extension)
// FORMAT:  access level, class class_name, extends NameOfClass this new class extends (if any) {
public class Template_AUTON_no_custom_methods_v00_copy extends LinearOpMode {
    //
    // DECLARE OpMode MEMBERS
    // 1. Utilities
    //    FORMAT:   access level, UtilityName runtime = new UtilityName();
    private ElapsedTime runtime = new ElapsedTime();        // Use private unless you need access from other classes.
    //
    // 2. Hardware (DECLARE and INITIALIZE variables at the same time)
    //    NOTE:     - This section tells the code that, later, these names will be used to refer to items on the robot.
    //              - The robot's pieces are named in the HardwareMap using the software on the ROBOT CONTROLLER PHONE.
    //              - Values after 'get' MUST match EXACTLY the names used when the robot configuration was 
    //                built using the FTC Robot Controller app on the ROBOT CONTROLLER PHONE.
    //              - In this code, each hardware variable name matches the name of the corresponding item in 
    //                the HardwareMap. This is not required, but is recommended because it keeps communication
    //                clear and usage consistent.
    //    FORMAT:   HardwareType hardwareVariableName = hardwareMap.hardwareType.get("nameAsAssignedInHardwareMap");             
    DcMotor leftDriveMotor  = hardwareMap.dcMotor.get("leftDriveMotor");
    DcMotor rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");
    DcMotor armMotor        = hardwareMap.dcMotor.get("armMotor");                        
    Servo   gripperServo    = hardwareMap.servo.get("gripperServo");                    
    //
    // DEFINE CODE CONSTANTS
    // NOTE:    CONSTANTS should generally be defined outside of METHOD bodies,
    //          instead of inside runOpMode() or any other METHOD,
    //          especially if you ever want to access them from outside of this CLASS.
    //
    //          CONSTANTS can also be defined in separate JAVA file(s) as long as:
    //          1.  Those files are part of the same project as the file (like this one) that
    //              needs to use the CONSTANTS
    //          2.  The CONSTANTS are declared as public so that they can be accessed from 
    //              outside the JAVA file(s) in which they are located
    //
    // FORMAT:  access_level static final value_type VALUE_NAME = assigned_value;
    //          - public means it can be accessed from other classes
    //          - static means there is only one copy no matter how many instances of the CLASS you create
    //          - final means its value never changes (constant)
    //          - long, double, etc. is the type of value held by the variable
    //
    // Drive powers (speeds): all values use range of 0 to 1
    public static final double MOTOR_STOP               = 0;
    public static final double DRIVE_POWER_FAST         = 0.8;
    public static final double DRIVE_POWER_SLOW         = DRIVE_POWER_FAST / 2;
    public static final double ARM_MOTOR_MOVE           = .25;
    //
    // Position address values for servos
    public static final double GRIPPER_SERVO_OPEN       = 0;            // Open to receive game elements
    public static final double GRIPPER_SERVO_CLOSED     = 200;          // Closed to grip game elements
    //
    @Override
    // Override is a note to the compiler stating that you expect that you are replacing a METHOD
    //      with the same name in the parent (extends ______ class) with this METHOD. This way, if you typo/change
    //      the METHOD name you will get an error, stopping you from having two METHODS when you expect only one.
    //
    // Call runOpMode() METHOD from the parent CLASS of LinearOpMode
    // FORMAT:  access_level, return_type or void, methodName(arguments), type of errors or exception {
    public void runOpMode() throws InterruptedException  {              // "void" means that this METHOD does not return
                                                                        //      data to any code that calls it. It
                                                                        //      does NOT mean "invalid."
                                                                        // "InterruptedException" keeps the program 
                                                                        //    from freezing completely if there is an error
                                                                        //    that it does not know how to handle
        //
        // SET DC MOTOR DIRECTIONS
        // NOTE:    "Reverse" any motor that runs backwards (relative to desired "forward" motion of element powered
        //          by that motor, such as a drive wheel or extension arm) when powered by a positive power value
        // FORMAT:  hardwareName.setDirection(DcMotor.Direction.DIRECTION);
        leftDriveMotor.setDirection(DcMotor.Direction.REVERSE);
        rightDriveMotor.setDirection(DcMotor.Direction.FORWARD);
        armMotor.setDirection(DcMotor.Direction.FORWARD);
        //
        // Display status and OpMode name on controller phone
        // FORMAT:  telemetry.desiredAction("arguments");
        telemetry.addData("Status", "Initialized", "name");             // Specific info to be sent to controller phone
        telemetry.update();                                             // Send info to controller phone
        //
        // SET ALL MOTORS TO DESIRED STARTING STATUS
        //      DC Motors
        leftDriveMotor.setPower(0);                                     // All DC motors STOPPED
        rightDriveMotor.setPower(0);                            
        armMotor.setPower(0);      
        //
        //      Servo Motors
        gripperServo.setPosition(GRIPPER_SERVO_CLOSED);                 // gripperServo motor gripping item, using a
                                                                        //      variable defined above
        //
        //****************************************************************************************************************
        // END OF PREPARATIONS
        //****************************************************************************************************************
        //
        // WAIT for driver to press PLAY
        waitForStart();                                                 // The waitForStart() METHOD is part of
                                                                        //    the LinearOpMode CLASS,
                                                                        //    which is defined elsewhere in
                                                                        //    the FTC resource code
        //
        //****************************************************************************************************************
        // AFTER driver presses PLAY, the ROBOT CONTROLLER PHONE will execute the code below this line
        //****************************************************************************************************************
        //
        //   0.  Start at predetermined location (positioned by drivers prior to game start)
        //       with robot gripper holding a pre-loaded item
        //   1.  Drive FORWARD, FAST, for 3 seconds
        leftDriveMotor.setPower(DRIVE_POWER_FAST);
        rightDriveMotor.setPower(DRIVE_POWER_FAST);
        sleep(3000);                                            //  Drive for 3000 miliseconds (3 seconds)
                                                                //  Sleep(X) pauses code execution so that motors
                                                                //      can turn for the time entered as X                                                                
        //
        //   2.  STOP driving
        leftDriveMotor.setPower(MOTOR_STOP);                    //  You could enter 0 here instead of the variable MOTOR_STOP,
        rightDriveMotor.setPower(MOTOR_STOP);                   //      but this keeps the code readable    
        //
        //   3.  Spin LEFT, FAST, for 1 second
        leftDriveMotor.setPower(-DRIVE_POWER_FAST);             //  Negative value to make motor spin wheel backward
        rightDriveMotor.setPower(DRIVE_POWER_FAST);             //  When left motor spins backward, and
                                                                //      right motor spins forward, then the
                                                                //      robot will spin to its left
        sleep(1000);                                            //  Spin for 1 second
        //
        //   4.  STOP spinning
        leftDriveMotor.setPower(MOTOR_STOP); 
        rightDriveMotor.setPower(MOTOR_STOP);
        //
        //   5.  Drive FORWARD, FAST, for 2 seconds
        leftDriveMotor.setPower(DRIVE_POWER_FAST);
        rightDriveMotor.setPower(DRIVE_POWER_FAST);
        sleep(2000);                                            //  Drive motors for 2 seconds
        //
        //   6.  STOP driving
        leftDriveMotor.setPower(MOTOR_STOP); 
        rightDriveMotor.setPower(MOTOR_STOP);
        //
        //   7.  Spin RIGHT, SLOWLY, for 2 seconds
        leftDriveMotor.setPower(DRIVE_POWER_SLOW);  
        rightDriveMotor.setPower(-DRIVE_POWER_SLOW);            //  Negative value to make motor spin wheel backward
                                                                //  When right motor spins backward, and
                                                                //      left motor spins forward, then the
                                                                //      robot will spin to its right
        sleep(2000);                                            //  Drive motors for 2 seconds
        //
        //   8.  STOP spinning
        leftDriveMotor.setPower(MOTOR_STOP); 
        rightDriveMotor.setPower(MOTOR_STOP);
        //
        //   9.  Extend arm OUTWARD for 1 second
        armMotor.setPower(ARM_MOTOR_MOVE);                      //  Positive value = extend arm
        sleep(1000);                                            //  Extend arm for 1 second
        //
        //  10.  STOP arm
        armMotor.setPower(MOTOR_STOP); 
        //
        //  11.  Set gripper to OPEN position to release item
        gripperServo.setPosition(GRIPPER_SERVO_OPEN);           
        //
        //  12.  Retract arm INWARD, for 1 second
        armMotor.setPower(-ARM_MOTOR_MOVE);                     //  Negative value = retract arm
        sleep(1000);                                            //  Retract arm for 1 second
        //  12.  STOP arm
        armMotor.setPower(MOTOR_STOP); 
        //
        //  13.  Wait for Teleop
    }
    // END of METHOD runOpMode
    //
    //****************************************************************************************************************
    // END of AUTONOMOUS code 
    //****************************************************************************************************************
}
// END of CLASS Template_AUTON_no_custom_methods_v00
//
//*************************************************************************************************************************
// END OF FILE
//*************************************************************************************************************************

*/
