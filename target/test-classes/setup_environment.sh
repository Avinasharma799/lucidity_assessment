#!/bin/bash

# Add Docker's binary directory to PATH
export PATH="/Applications/Docker.app/Contents/Resources/bin:$PATH"

# Navigate to the mockserver directory and start the mock server
cd ~/Downloads/sample-cart-offer-main/mockserver || { echo "Failed to change directory to mockserver"; exit 1; }

echo "$PWD"

docker compose up -d || { echo "Failed to start mock server"; exit 1; }

# Wait for the mock server to be fully up and running
echo "Waiting for MockServer to start on port 1080..."
until nc -z -v -w30 localhost 1080
do
  echo "Waiting for MockServer..."
  sleep 5
done
echo "MockServer is up and running on port 1080."

cd .. || { echo "Failed to change directory to sample-cart-offer-main"; exit 1; }
# Build and start the service
#!/bin/bash

# Function to set JAVA_HOME to JDK 11 if the current version is not 11
set_java_home() {
  # Retrieve the current Java version
  current_version=$(java --version 2>&1 | head -n 1 | awk '{print $1 $2}')
  
  # Check if the current version is not 11
  if [[ "$current_version" != "java 11."* ]]; then
    echo "Current Java version is $current_version. Setting JAVA_HOME to JDK 11."
    unset JAVA_HOME
    export JAVA_HOME=$(/usr/libexec/java_home -v 11)
    export PATH=$JAVA_HOME/bin:$PATH
  else
    echo "Java version 11 is already set. Skipping JAVA_HOME modification."
  fi
}

# Set JAVA_HOME if necessary
set_java_home

# Verify the Java version
echo "Java Version:"
java --version

echo "Maven Version"
mvn --version

./mvnw clean install -DskipTests || { echo "Maven build failed"; exit 1; }
java -jar target/simple-springboot-app-0.0.1-SNAPSHOT.jar &
echo "Service is up and running on port 9001."
