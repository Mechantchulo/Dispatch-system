#!/bin/bash

echo "========================================="
echo "  RIDE DISPATCH SERVER - STARTING"
echo "========================================="
echo ""

# Compile all Java files
echo "Compiling Java files..."
javac -d bin src/main/**/*.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo ""
    
    # Get IP address
    echo "Your IP address:"
    if [[ "$OSTYPE" == "linux-gnu"* ]]; then
        hostname -I | awk '{print $1}'
    elif [[ "$OSTYPE" == "darwin"* ]]; then
        ipconfig getifaddr en0 2>/dev/null || ipconfig getifaddr en1 2>/dev/null || echo "localhost"
    else
        echo "localhost"
    fi
    
    echo ""
    echo "Starting server on port 8080..."
    echo "Share your IP address with clients to connect!"
    echo ""
    echo "========================================="
    echo ""
    
    # Run the server
    java -cp bin network.Server
else
    echo "Compilation failed! Please fix errors and try again."
    exit 1
fi
