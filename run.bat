@echo off

echo Compiling program...
javac SortingAlgorithms.java HybridSorting.java

if %errorlevel% neq 0 (
    echo Compilation failed.
    pause
    exit /b
)

echo Compilation successful.
echo.
echo Running Hybrid Sorting Algorithm...
echo.

java HybridSorting

echo.
pause