package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;




@Autonomous(name = "WagNationRed")
public class WagNationRed extends OpMode {

    private final ElapsedTime timer = new ElapsedTime();
    DcMotor leftf;
    DcMotor leftb;
    DcMotor rightf;
    DcMotor rightb;
    DcMotor liftarm1;
    DcMotor liftarm2;
    DcMotor Cool2;
    DcMotor Intake;
    Servo Feeder;
    Servo rampA;
    @Override
    public void init() {
        timer.reset();
        leftf = hardwareMap.get(DcMotor.class, "Left_front");
        leftb = hardwareMap.get(DcMotor.class, "Left_back");
        rightf = hardwareMap.get(DcMotor.class, "Right_front");
        rightb = hardwareMap.get(DcMotor.class, "Right_back");
        liftarm1 = hardwareMap.get(DcMotor.class, "Actuator");
        liftarm2 = hardwareMap.get(DcMotor.class, "Actuator2");
        Cool2 = hardwareMap.get(DcMotor.class, "skib2");
        Intake = hardwareMap.get(DcMotor.class,"Intake");
        Feeder = hardwareMap.get(Servo.class, "Servo1");
        rampA = hardwareMap.get(Servo.class, "RampA");

        leftf.setDirection(DcMotorSimple.Direction.REVERSE);
        leftb.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
        if(timer.seconds()<3){
            leftf.setPower(1);
            leftb.setPower(1);
            rightf.setPower(1);
            rightb.setPower(1);
        }else if(timer.seconds()<5 && timer.seconds()>3.5){
            leftf.setPower(-1);
            leftb.setPower(-1);
            rightf.setPower(1);
            rightb.setPower(1);
        }

    }
}
