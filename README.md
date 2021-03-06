# Lost in Peril
## LibGDX powered Android, Windows, MacOS game.
Run around the map to gather coins, but watch out for landmines! Hurry before the clock runs out!

![CI](https://github.com/timmay54/lost_in_peril/workflows/CI/badge.svg)
![Java/Gradle CI](https://github.com/timmay54/lost_in_peril/workflows/Java/Gradle%20CI/badge.svg?branch=master)

# 1. Playing the game
## To play, use one of the following methods to start:
* Download on [Google Play!](https://play.google.com/store/apps/details?id=com.freebandz.lost_in_peril)
* Download the [newest release - direct download](https://github.com/timmay54/lost_in_peril/releases/download/v0.0.1/lip_FINAL.jar) - Also can be found on the release page.
  To run, execute in Bash or Powershell `java -jar lip_FINAL.jar`
* Gradle run:
  1. Clone this repo: `git clone https://github.com/timmay54/lost_in_peril`
  2. In the folder, run `./gradlew desktop:run`
  
# 2. Contribute code
## If you want to add maps or content to the game, as well as bug fixes, here is how you can start:
1. Clone this repo: `git clone https://github.com/timmay54/lost_in_peril`
2. Create your changes, test, and commit your code. 
3. Use Gradle to build a distrbutable JAR file: `./gradlew desktop:dist`
  this will output to build/libs directory.
