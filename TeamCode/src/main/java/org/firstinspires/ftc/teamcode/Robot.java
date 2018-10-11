// *************************************************************************************************************************
// *************************************************************************************************************************
// Edit Date:   October 11, 2018 @ 14:04
// Clone Date:  October 11, 2018 @ 14:02
// Team Name:   Lightning Robotics
// Team Number: FRC862
// Code Type:   definition and initial setup of all robot hardware:
//              - DC motors
//              - Servo motors  (none present yet)
//              - Sensors       (none present yet)
// NOTE:        This code is based on AUTON_withExternalConstants_v00
// *************************************************************************************************************************
// *************************************************************************************************************************
//
// DEFINE CODE PACKAGE
package org.firstinspires.ftc.teamcode;
//
// IMPORT PROGRAMMING ELEMENTS DESCRIBED ELSEWHERE IN THE CODE PACKAGE FOR USE IN THIS FILE
//      1. Classes
//         none
//
//      2. Utilities
//         none
//
//      3. Hardware Types (ONE import per TYPE of hardware, NOT for each INSTANCE of that TYPE of hardware)
import com.qualcomm.robotcore.hardware.DcMotor;
//         import Servos, Sensors, etc. here

//
// DEFINE class
// FORMAT:  access level, class class_name, extends NameOfClass_this_new_class_extends_(if_any) {
public class Robot {
    //
    // DECLARE CLASS MEMBERS
    // 1. Utilities
    //    none
    //
    // 2. Hardware
    //    NOTE:     This section tells the code that, later, these names will be used to refer to items on the robot.
    //    FORMAT:   HardwareType variableName;
    DcMotor leftDriveMotor;
    DcMotor rightDriveMotor;
    //
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
}
//
// END OF FILE
//
