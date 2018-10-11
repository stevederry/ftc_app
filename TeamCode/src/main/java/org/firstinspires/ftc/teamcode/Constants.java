   public static final Constants(){
   
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
    // Drive times: all values are in milliseconds, and all values assume motors are using DRIVE_POWER_FAST value
    public static final long DRIVE_TIME_START_TO_CHECKPOINT_ONE     = 3000;
    public static final long DRIVE_TIME_CHECKPOINT_ONE_TO_TWO       = 2000;
    public static final long DRIVE_TIME_45_DEG_TURN                 = 250;
    public static final long DRIVE_TIME_90_DEG_TURN                 = DRIVE_TIME_45_DEG_TURN * 2;
    //
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
