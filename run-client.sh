#!/bin/bash

echo "========================================="
echo "  RIDE DISPATCH CLIENT - STARTING"
echo "========================================="
echo ""

# Compile client files
echo "Compiling client files..."
javac -d bin src/main/network/*.java src/main/model/*.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo ""
    echo "Starting client..."
    echo "You will be prompted for server IP and role."
    echo ""
    echo "========================================="
    echo ""
    
    # Run the client
    java -cp bin network.Client
else
    echo "Compilation failed! Please fix errors and try again."
    exit 1
fi
