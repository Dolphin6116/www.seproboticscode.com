package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.util.Encoder;

@TeleOp(name = "test")
public class test extends LinearOpMode {
    DcMotorEx testMotor1;
    DcMotorEx testMotor2;
    DcMotorEx testMotor3;
    DcMotorEx testMotor4;
    @Override
    public void runOpMode() throws InterruptedException {
        testMotor1 = hardwareMap.get(DcMotorEx.class,"frontLeftMotor");
        testMotor2 = hardwareMap.get(DcMotorEx.class,"frontRightMotor");
        testMotor3 = hardwareMap.get(DcMotorEx.class,"backLeftMotor");
        testMotor4 = hardwareMap.get(DcMotorEx.class,"backRightMotor");
        testMotor1.setDirection(DcMotorSimple.Direction.REVERSE);
        testMotor3.setDirection(DcMotorSimple.Direction.REVERSE);

        testMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        testMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        testMotor3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        testMotor4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        waitForStart();
        while (opModeIsActive()) {
            testMotor1.setPower(0);
            testMotor2.setPower(0);
            testMotor3.setPower(0);
            testMotor4.setPower(0);
            if(gamepad1.a) {
                testMotor1.setPower(1);

            }else if(gamepad1.b) {
                testMotor2.setPower(1);
            }else if(gamepad1.y) {
                testMotor3.setPower(1);
            } else if (gamepad1.x) {
                testMotor4.setPower(1);
            }
            telemetry.addData("Front Left Encoder Value: ",testMotor1.getCurrentPosition());
            telemetry.addData("Front Right Encoder Value: ",testMotor2.getCurrentPosition());
            telemetry.addData("Back Left Encoder Value: ",testMotor3.getCurrentPosition());
            telemetry.addData("Back Right Encoder Value: ",testMotor4.getCurrentPosition());
            telemetry.update();
        }
    }
}