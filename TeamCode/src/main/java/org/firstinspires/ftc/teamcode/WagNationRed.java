package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name = "WagNationRed")
public class WagNationRed extends LinearOpMode {
    DcMotorEx front_left;
    DcMotorEx back_left;
    DcMotorEx front_right;
    DcMotorEx back_right;
    DcMotorEx launcher_back;
    DcMotorEx launcher_left;
    DcMotorEx launcher_right;
    Servo servo;
    Servo servo2;
    ElapsedTime time = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
        front_left = hardwareMap.get(DcMotorEx.class, "frontLeftMotor");
        back_left = hardwareMap.get(DcMotorEx.class, "backLeftMotor");
        front_right = hardwareMap.get(DcMotorEx.class, "frontRightMotor");
        back_right = hardwareMap.get(DcMotorEx.class, "backRightMotor");
        launcher_back = hardwareMap.get(DcMotorEx.class, "launcherBackMotor");
        launcher_left = hardwareMap.get(DcMotorEx.class, "launcherLeftMotor");
        launcher_right = hardwareMap.get(DcMotorEx.class, "launcherRightMotor");
        servo = hardwareMap.get(Servo.class, "Servo1");
        servo2 = hardwareMap.get(Servo.class, "Servo2");
        servo2.setPosition(0.26);
        servo.setPosition(0.9);
        front_left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        back_left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        front_right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        back_right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        launcher_left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        launcher_right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        time.reset();
        launcher_left.setPower(0.97);
        launcher_right.setPower(0.97);
        //distance(12);
        shoot(5);

    }
    public void shoot(int x){

        //front_right.setPower(-1);
        //back_right.setPower(1);
        //front_left.setPower(1);
        //back_left.setPower(-1);

        sleep(1000);
        //front_right.setPower(0);
        //back_right.setPower(0);
        //front_left.setPower(0);
        //back_left.setPower(0);
        launcher_back.setPower(1);

        sleep(1000);
        servo.setPosition(0.05);
        sleep(2500);
        servo.setPosition(0.9);
       // launcher_left.setPower(0);
        //launcher_right.setPower(0);
        x--;
        if(x>0){
            shoot(x);
        }

    }
    public void distance(double inches){

        front_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        int wheelDiameter = 4;
        double wheelC = wheelDiameter*Math.PI;
        double rotation = inches/wheelC;
        double tpr = 28;
        int ticks = (int)(rotation*tpr);
        front_right.setPower(1);
        back_right.setPower(-1);
        front_left.setPower(-1);
        back_left.setPower(1);
        front_right.setTargetPosition(ticks);
        back_right.setTargetPosition(ticks);
        front_left.setTargetPosition(ticks);
        back_left.setTargetPosition(ticks);

        front_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        back_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        front_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        back_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while ((front_left.isBusy()) || (front_right.isBusy()) || (back_left.isBusy()) || (back_right.isBusy())){

        }
        front_right.setPower(0);
        back_right.setPower(0);
        front_left.setPower(0);
        back_left.setPower(0);
    }

}
