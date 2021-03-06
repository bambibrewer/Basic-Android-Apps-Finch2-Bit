This is a basic Bluetooth application using the Finch Robot 2.0. You can use it as a basis for writing your own apps.

All of the classes specific to the Finch are in the Finch folder. For most basic apps, you will not need to change anything in this folder.

The app opens with a screen that enables you to choose your Finch and connect to it. You will need to do this in your app, so you probably want to leave this alone (though you may want to make it more beautiful). The files for this screen are in the Scan folder. Once you choose a device to connect to, you to move to the MainActivity. 

In the MainActivity, it is VERY IMPORTANT that you declare the Finch object and set the activity to be a FinchListener. You will need to do this any time you move to a new activity that uses the Finch.

The MainActivity contains the variables finch. The finch variable has public functions that you can use to control the lights, motors, and buzzers of the Finch. Those public functions are listed below. In addition, the finch variable includes sensorState, which you can use to access all of the values of the Finch sensors. For example, finch?.sensorState?.distance will give you the value of the distance sensor. SensorState is a class that contains the sensor information for the Finch. The variables inside that class that contain the Finch data are described below.

The MainActivity screen demonstrates how you can use buttons and sliders to control the Finch outputs. It also displays the values of the Finch sensors on the screen.

Public Finch Functions:

NOTE: If you issue Bluetooth commands too close together, the last command may overwrite earlier ones. For example, if you set the color of the Finch beak and then immediately set the wheels, you may not see the effect of the beak command.

Method Signature: setMove(direction: String, distance: Double, speed: Int)

Description: Moves the Finch forward or backward for a specified distance at a specified speed. The method requires a direction ("F" for forward or "B’" for backward), a distance in centimeters, and a speed from 0-100.

Example: finch.setMove("F", 10, 50)

Method Signature: setTurn(direction: String, angle: Double, speed: Int)

Description: Turns the Finch right or left for a specified angle at a specified speed. The method requires a direction ("R" for right or "L" for left), an angle in degrees, and a speed from 0-100.

Example: finch.setTurn("R", 90, 50)

Method Signature: setMotors(leftSpeed: Int, rightSpeed: Int)

Description: Sets the Finch wheels to spin at the given speeds. The method requires two speeds between -100 and 100 for the left and right wheels. Setting the speed to 0 turns the motor off.

Example: finch.setMotors(-50, 50)

Method Signature: stop()

Description: Stops the Finch wheels.

Example: finch.stop()

Method Signature: setBeak(red: Int, green: Int, blue: Int)

Description: Sets a tri-color LED in the Finch beak to a given color by setting the intensities of the red, green, and blue elements inside it. The method requires three intensity values from 0-100. Setting all three intensity values to 0 turns the beak off.

Example: finch.setBeak(0, 100, 0)

Method Signature: setTail(port: Int, red: Int, green: Int, blue: Int)

Description: Sets a tri-color LED in the Finch tail to a given color by setting the intensities of the red, green, and blue elements inside it. The method requires the port number of the LED (1-4) and three intensity values from 0-100. Setting all three intensity values to 0 turns the LED off.

Example: finch.setTail(1, 0, 100, 0)

Method Signature: setTail(port: String, red: Int, green: Int, blue: Int)

Description: Sets all the tri-color LEDs in the Finch tail to a given color by setting the intensities of the red, green, and blue elements inside it. The method requires a String equal to “all” and three intensity values from 0-100. Setting all three intensity values to 0 turns the LED off.

Example: finch.setTail("all", 0, 100, 0)

Method Signature: playNote(note: Int, beats: Double)

Description: Plays a note using the buzzer on the Finch. The method requires an integer representing the note (32-135) and a number giving the number of beats (0-16). The number of beats can be a decimal number.

Example: finch.playNote(60, 0.5)

Method Signature: setDisplay(pattern: Array)

Description: Sets the LED array of the micro:bit to display a pattern defined by an array of length 25. Each value in the list must be 0 (off) or 1 (on). The first five values in the array correspond to the five LEDs in the first row, the next five values to the second row, etc.

Example: finch.setDisplay(arrayOf(1,1,1,1,1,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,1,1,1,1,1))

Method Signature: printString(_ stringToPrint: String)

Description: Print a string on the micro:bit LED array. The string must have 15 or fewer characters and should contain only digits and English letters (upper or lower case).

Example: finch.printString("hello")

Method Signature: stopAll()

Description: Stops all outputs. This includes the LED display for the micro:bit and all lights and motors for the Finch.

Example: finch.stopAll()

Method Signature: correctLightSensorValues() -> Array<Int?>

Description: The Finch light sensors are slightly affected by the value of the beak. It is a fairly small effect, but if you want, you can use this function to correct them

Example: let correctedLightSensors = finch.correctLightSensorValues()

Method Signature: calibrateCompass()

Description: This function send a Bluetooth command to calibrate the compass. When the Finch receives this command, it will place dots on the micro:bit screen as it waits for you to tilt the Finch in different directions. If the calibration is successful, you will then see a check on the micro:bit screen. Otherwise, you will see an X.

Example: finch.calibrateCompass()

Method Signature: resetEncoders()

Description: This function sets the right and left encoder values to 0.

Example: finch.resetEncoders()

Variables that you can access within the SensorState class (the finch?.sensorState variable):

timestamp: Date - time of the sensor reading

batteryVoltage: Float - voltage of Finch battery

isStale: Bool - is the data old?

distance: Int - value of the distance sensor in centimeters

leftLight: Int - value of left light sensor (0-100)

rightLight: Int - value of right light sensor (0-100)

leftLine: Int - value of left line sensor (0-100)

rightLine: Int - value of left line sensor (0-100)

leftEncoder: Double - number of rotations the left wheel has turned since the encoders were reset

rightEncoder: Double - number of rotations the right wheel has turned since the encoders were reset

acceleration: Array - acceleration in x, y, z directions in m/s^2

magnetometer: Array - magnetometer readings in x, y, and z directions in microTesla

compass: Int? - degrees from North; undefined in the case of 0 z direction acceleration

buttonA: Bool - true when button A is pressed

buttonB: Bool - true when button B is pressed

shake: Bool - true when Finch is being shaken

movementFlag: Bool - true when the Finch is moving. You need to watch this flag if you don't want to start another Finch movement until the first one is finished.
