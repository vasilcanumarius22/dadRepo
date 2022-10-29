@echo off
cls
title DAD2022_A01_VASILCANU_Marius_Daniel
cd assignment_1_2022\src\eu\assignment\ie
set choise=
set /p choise="Press 'y' and enter to start the program or 'n' to close this cmd: "
if '%choise%'=='n' (
	exit
) else if '%choise%'=='y' (
	start cmd /k "java .\UDPMulticastServer.java"
	TIMEOUT /T 1
	start cmd /k "javac .\UDPMulticastClient.java" & java .\UDPMulticastClient.java 5 & java .\UDPMulticastClient.java 4 & java .\UDPMulticastClient.java 6 & java .\UDPMulticastClient.java 3
)



