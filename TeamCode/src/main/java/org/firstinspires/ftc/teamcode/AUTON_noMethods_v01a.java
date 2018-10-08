//*************************************************************************************************************************
//*************************************************************************************************************************
// Edit Date:   October 07, 2018 @ 20:31
// Team Name:   _____
// Team Number: _____
// Code Type:   OpMode for AUTONOMOUS
// NOTE:        This code is based on Template_AUTON_no_custom_methods_v00,
//              but removes the gripperServo and the armMotor.
// Description: Order of operations
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
//               9.  Wait for Teleop
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
//      1. Classes
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//      2. Utilities
import com.qualcomm.robotcore.util.ElapsedTime;
//
//      3. Hardware Types (ONE import per TYPE of hardware, NOT for each INSTANCE of that TYPE of hardware)
import com.qualcomm.robotcore.hardware.DcMotor;
//
// DEFINE OpMode
// NOTE:    OpMode is the name for a set of code that contains the instructions the robot
//            will perform. It is a CLASS inside the ftc_app code package supplied by FTC.
//          The NAME porperty:
//            - Can be any text that helps the user identify this program.
//            - Will be displayed on the DRIVER CONTROL phone when the user chooses which program to run
//            - Does NOT need to be the same as the FILENAME, but often is. The most common reason
//              to name the OpMode differently from the FILE is to allow the OpMode name as displayed on
//              the DRVIER CONTROL phone to have a shortened name that sorts in a desired order.
//          The GROUP property can be any text that helps the user group this program with related programs
// FORMAT:  @type(name="OpMode_Name", group="GroupName") 
@Autonomous(name="AUTON_noMethods_v01a", group="Derry_FTC_Templates")
//
// DEFINE class
// NOTE:    - All JAVA files must have at least one public CLASS, but can have more,
//            as long as they are private.
//          - This file will create one CLASS, and that CLASS will be an OpMode that
//            extends the pre-defined CLASS named LinearOpMode, which is supplied
//            by FTC as part of the CODE PACKAGE.
//          - Not all CLASSES are OpModes.
//          - The public CLASS name MUST match the FILENAME (EXcluding the ".java" extension)
// FORMAT:  access level, class class_name, extends NameOfClass_this_new_class_extends_(if_any) {
public class AUTON_noMethods_v01a extends LinearOpMode {
    // DECLARE OpMode MEMBERS
    // 1. Utilities
    //    FORMAT:   access level, UtilityName runtime = new UtilityName();
    private ElapsedTime runtime = new ElapsedTime();        // Use private unless you need access from other classes.
    // 
    // 2. Hardware
    //    NOTE:     This section tells the code that, later, these names will be used to refer to items on the robot.
    //    FORMAT:   HardwareType variableName;
    DcMotor leftDriveMotor;
    DcMotor rightDriveMotor;
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
    //
    @Override
    // Override is a note to the compiler stating that you expect that you are replacing a METHOD
    //    with the same name in the parent (extends ______ class) with this METHOD. This way, if you typo/change
    //    the METHOD name you will get an error, stopping you from having two METHODS when you expect only one.
    //
    // Call runOpMode() METHOD from the parent CLASS of LinearOpMode
    // FORMAT:  access_level, return_type or void, methodName(arguments), type of errors or exception {
    public void runOpMode() throws InterruptedException  {              // "void" means that this METHOD does not return
                                                                        //      data to any code that calls it. It
                                                                        //      does NOT mean "invalid."
                                                                        // "InterruptedException" keeps the program 
                                                                        //    from freezing completely if there is an error
                                                                        //    that it does not know how to handle
      // SET HARDWARE VARIABLE VALUES
      // NOTE:  - The robot's pieces are named in the hardwareMap using the software on the ROBOT CONTROLLER PHONE.
      //        - Values after 'get' MUST match EXACTLY the names used when the robot configuration was 
      //          built using the FTC Robot Controller app on the ROBOT CONTROLLER PHONE.
      //        - In this code, each hardware variable name matches the name of the corresponding item in 
      //          the hardwareMap. This is not required, but is recommended because it keeps communication
      //          clear and usage consistent.
      // FORMAT:  variableName = hardwareMap.hardwareType.get("nameInHardwareMap");
      leftDriveMotor  = hardwareMap.dcMotor.get("leftDriveMotor");
      rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");
      //
      // SET DC MOTOR DIRECTIONS
      // NOTE:    "Reverse" any motor that runs backwards (relative to desired "forward" motion of element powered
      //          by that motor, such as a drive wheel or extension arm) when powered by a positive power value
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
      leftDriveMotor.setPower(0);                                     // All DC motors STOPPED
      rightDriveMotor.setPower(0);                            
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
      telemetry.addData("Status", "Running");                         // Specific info to be sent to controller phone
      telemetry.update();                                             // Send info to controller phone
      //
      //   0.  Start at predetermined location (positioned by drivers prior to game start)
      //       with robot gripper holding a pre-loaded item
      //
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
      //   9.  Wait for Teleop
    }
    // END of METHOD runOpMode
    //
    //****************************************************************************************************************
    // END of AUTONOMOUS code 
    //****************************************************************************************************************
}
// END of CLASS AUTON_noMethods_v01
//
//*************************************************************************************************************************
// END OF FILE
//*************************************************************************************************************************
