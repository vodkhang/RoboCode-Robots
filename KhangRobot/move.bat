cd ..\..\KhangRobot
del /Q Strategy KhangRobot.class StrategyFactory.class Helper.class
rd /Q Strategy
xcopy ..\RoboCode-Robots\KhangRobot\MyFirstRobot\build\classes\KhangRobot\* . /s /i
pause