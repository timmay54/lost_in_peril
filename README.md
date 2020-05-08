# lost_in_peril
LibGDX powered Android, Windows, MacOS game.

![CI](https://github.com/timmay54/lost_in_peril/workflows/CI/badge.svg)


## What needs done:
* Pipelines
    * GitHub Actions
        * explore capabilities 
    * TeamCity 
        * Build dev branch on each commit
        * if commit builds, then allow for merging to master
        * compile master to create jar, apk, all platforms
        * automatically deploy apk to Google Play (gradle task)
        * seperate builds, desktop builds first, then trigger android, then HTML (seperate builds so that desktop can be tested successfully, HTML will always fail)

    * Other:
        * badges for build info
        * auto create releases for Desktop & Android?
        * wrap jar file for windows, linux, mac
* Cleaning up branches
    * gitignore file
    * clean assets out
* determine what project files to publish
    * video
    * powerpoint?
* add how to contribute section
* Programming:
    * iOS platform 
        * need roboVM to test with
    * HTML 
        * breaks with L_STICK
        * cannot have static classes (world contact listener)
    * better lighting
        * move lights to center on flame icons
    * better randy movement graphics
        * he doesnt always face the direction he is going
    * finish dragon implementation
        * he is out in far right room (coded in kotlin)
    * GodMode:
        * better music
        * no timer
        * beta graphics