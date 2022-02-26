# This will help you set up your project for linux
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk version
sdk install java 17.0.2.8.1-amzn
sdk default java 17.0.2.8.1-amzn
sdk install maven
sdk install quarkus