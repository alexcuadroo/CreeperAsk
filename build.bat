@echo off
echo [INFO] Compilando CreeperAsk contra Paper API 26.1...
mvn --batch-mode clean package
if errorlevel 1 exit /b %errorlevel%

echo [OK] JAR generado en target\CreeperAsk-1.0.0-mc26.1.jar
