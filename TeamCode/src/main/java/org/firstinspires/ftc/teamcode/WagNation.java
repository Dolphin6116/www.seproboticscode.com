package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.AprilTagWebcam;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;


@Autonomous(name = "WagNation")
public class WagNation extends OpMode {
    AprilTagWebcam aprilTagWebcam = new AprilTagWebcam();
    //Robot and Field Specifications
    public static final double field_width = 144;
    public static final double field_length= 144;
    public static final double tile_size = 24;
    public static final double robot_width = 18;
    public static final double robot_length = 18;
    public static final double wheel_diameter = 4;
    public static final double TICKS_PER_REV = 28;
    public static double TRACK_WIDTH = 14;
    public static final double TICKS_PER_INCH= TICKS_PER_REV/(wheel_diameter*Math.PI);
    //Change these
    public static final double Max_Velocity = 2000;
    public static final double Drive_Velocity = 800;
    public static final double Slow_Velocity = 400;
    public static final double Turn_Velocity = 300;
    public static final double Shooter_Velocity = 1500;
    //
    double currentX = 0,currentY = 0,currentHeading = 0;
    double lastLeftTicks = 0,lastRightTicks = 0;
    double velocitySetpoint = 0;
    private boolean[] colors = new boolean[3];
    //Origin is bottom left corner
    public static final Pose2d Origin = new Pose2d(0,0,0);
    //Please change these

    enum Pattern {GPP,PPG,PGP,UNKNOWN};
    enum State{
        DriveRed, //Driving
        DriveBlue,
        Scan, //Finding April Tags
        Shoot,//Shooting the ball
        End,
    }
    DcMotorEx front_left;
    DcMotorEx back_left;
    DcMotorEx front_right;
    DcMotorEx back_right;
    DcMotorEx launcher_back;
    DcMotorEx launcher_left;
    DcMotorEx launcher_right;
    Servo servo;
    IMU imu;
    //By declaring the type the same as the enum, it can only hold values from the enum
    State state = State.DriveRed;

    @Override
    public void init() {
        state = State.DriveRed;
        aprilTagWebcam.init(hardwareMap,telemetry);
        front_left = hardwareMap.get(DcMotorEx.class, "frontLeftMotor");
        back_left = hardwareMap.get(DcMotorEx.class, "backLeftMotor");
        front_right = hardwareMap.get(DcMotorEx.class, "frontRightMotor");
        back_right = hardwareMap.get(DcMotorEx.class, "backRightMotor");
        launcher_back = hardwareMap.get(DcMotorEx.class, "launcherBackMotor");
        launcher_left = hardwareMap.get(DcMotorEx.class, "launcherLeftMotor");
        launcher_right = hardwareMap.get(DcMotorEx.class, "launcherRightMotor");
        servo = hardwareMap.get(Servo.class, "Servo1");
        //Reset Odometry
        for(DcMotorEx motor : new DcMotorEx[]{front_left,front_right,back_left,back_right,launcher_back,launcher_left,launcher_right}){
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        front_left.setDirection(DcMotorSimple.Direction.REVERSE);
        back_left.setDirection(DcMotorSimple.Direction.REVERSE);
        imu = hardwareMap.get(IMU.class,"")
    }
    @Override
    public void loop(){

        aprilTagWebcam.update();
        //Change these to new april tag ids
        AprilTagDetection gpp21 = aprilTagWebcam.getTagBySpecificId(21);
        AprilTagDetection pgp22 = aprilTagWebcam.getTagBySpecificId(22);
        AprilTagDetection ppg23 = aprilTagWebcam.getTagBySpecificId(23);
        aprilTagWebcam.displayDetectionTelemetry(gpp21);
        aprilTagWebcam.displayDetectionTelemetry(pgp22);
        aprilTagWebcam.displayDetectionTelemetry(ppg23);
        telemetry.addData("State",state);
        switch(state){
            case DriveRed:
                telemetry.addLine("To exit state press A");

                if(gamepad1.a) state = State.DriveBlue;
                break;
            case DriveBlue:
                telemetry.addLine("To exit state press A");
                if(gamepad1.a) state = State.Scan;
                break;
            case Scan:
                telemetry.addLine("To exit state press B");
                if (gpp21 != null){
                    telemetry.addLine("Detected Tag 21");
                }
                if (pgp22 != null){
                    telemetry.addLine("Detected Tag 22");
                }
                if (ppg23 != null){
                    telemetry.addLine("Detected Tag 23");
                }
                if(gamepad1.b) state = State.Shoot;
                break;
            case Shoot:
                telemetry.addLine("To exit state press X");
                if(gamepad1.x) state = State.End;
                break;
            default:
                telemetry.addLine("Auto State machine finished");

        }




    }


}
