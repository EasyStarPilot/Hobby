rd /s /q out
java -jar c:\Users\mail\Downloads\packr-all-4.0.0.jar --executable Taschenrechner --platform windows64 --jdk "C:\Program Files\Eclipse Adoptium\jdk-21.0.4.7-hotspot" --mainclass GUIRechner --output out --classpath .\Hobby.jar
xcopy /y out\Taschenrechner.exe .
pause