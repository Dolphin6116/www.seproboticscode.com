
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "OpenHouse")
public class Openhouse extends LinearOpMode {
    DcMotor frontLeft; //this
    DcMotor backLeft;
    DcMotor frontRight;
    DcMotor backRight; //this

    @Override

    public void runOpMode() throws InterruptedException {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        backLeft = hardwareMap.get(DcMotor.class, "backLeftMotor");
        frontRight = hardwareMap.get(DcMotor.class, "frontRightMotor");
        backRight = hardwareMap.get(DcMotor.class, "backRightMotor");

        // Reverse right side motors if needed
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);

        waitForStart();

        while (opModeIsActive()) {
            double y =  gamepad1.left_stick_x; // forward/backward
            double x =  -gamepad1.left_stick_y;  // strafe
            double rx = gamepad1.right_stick_x; // rotate

            // Mecanum drive formula
            double frontLeftPower = y + x + rx;
            double backLeftPower = y - x + rx;
            double frontRightPower = y - x - rx;
            double backRightPower = y + x - rx;

            // Normalize powers
            double max = Math.max(Math.abs(frontLeftPower), Math.max(Math.abs(backLeftPower),
                    Math.max(Math.abs(frontRightPower), Math.abs(backRightPower))));
            if (max > 1.0) {
                frontLeftPower /= max;
                backLeftPower /= max;
                frontRightPower /= max;
                backRightPower /= max;
            }

            // Set motor powers
            frontLeft.setPower(frontLeftPower);
            backLeft.setPower(backLeftPower);
            frontRight.setPower(frontRightPower);
            backRight.setPower(backRightPower);

            telemetry.addData("Front Left Power", frontLeftPower);
            telemetry.addData("Front Right Power", frontRightPower);
            telemetry.addData("Back Left Power", backLeftPower);
            telemetry.addData("Back Right Power", backRightPower);
            telemetry.addData("Direction", "Use left stick to move, right stick to turn");
            telemetry.update();
        }
    }
}

