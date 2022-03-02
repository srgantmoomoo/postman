<img src="https://user-images.githubusercontent.com/69589624/132962368-25885f65-740e-4955-9b31-4a1cb899b660.png" alt="postman" width="175"/>

# postman

[![GitHub all releases](https://img.shields.io/github/downloads/moomooooo/postman/total?color=79C1FF&style=flat-square)](https://github.com/moomooooo/postman/releases)
[![Lines of code](https://img.shields.io/tokei/lines/github/moomooooo/postman?color=79C1FF&style=flat-square)](https://github.com/moomooooo/postman/tree/master/src/main/java/me/srgantmoomoo)
[![Discord](https://img.shields.io/discord/760964236779716648?color=79C1FF&label=discord&style=flat-square)](https://discord.gg/Jd8EmEuhb5)
[![GitHub](https://img.shields.io/github/license/moomooooo/postman?color=79C1FF&style=flat-square)](https://github.com/moomooooo/postman/blob/master/LICENSE)

postman is a [**Forge**](https://files.minecraftforge.net/net/minecraftforge/forge/) client that runs on [**Minecraft**](https://minecraft.net) 1.12.2, it is intended for use on servers which allow client-side modification. postman is coded in 100% java (very based programming language) and takes inpiration from many previous clients like osiris, kami, gamesense, and others. i intend for this project to be community driven and as open as possible, open access, open development, and open sourced.
<br>

check out the [**postman website**](https://techale.github.io/postman-website/) by techale! <br>
check out the [**postman archive**](https://github.com/moomooooo/postman-archive) for pre-github releases of postman.

thank you,

-SrgantMooMoo

![image](https://user-images.githubusercontent.com/69589624/129431288-d6a1c2db-7a68-488d-b885-901b86ca02f7.png)

# instructions
**download**
1. postman uses forge in order to run so, if you havent already, go [**here**](http://files.minecraftforge.net/maven/net/minecraftforge/forge/index_1.12.2.html) and download the latest or recommended version of forge 1.12.2 (make sure you run this download of forge in minecraft at least once before continuing). <br />
2. head to the [**releases**](https://github.com/srgantmoomoo/postman/releases) section of this repositroy; select the latest version (or your preferred version), find assets at the bottom of the release, then download the .jar file. <br />
3. find the location of your .minecraft directory. for windows users, type %appdata% in your windows search and hit enter. open the .minecraft directory and open the mods directory (if there is no mods folder than you can create one yourself). open the 1.12.2 directory (once again you can create your own if needed).
5. drag the postman .jar file that you downloaded earlier into the 1.12.2 folder. <br />
- if you want to take advantage of postman's baritone integration, you can download the [**baritone api**](https://github.com/cabaletta/baritone/releases/download/v1.2.14/baritone-api-forge-1.2.14.jar) and drag it into your mods folder along with postman.

**use**
- prefix is "," (comma) by default.
- clickgui is bound to rshift by default.
- once your clickgui is opened you will see a bunch of categories, you can drag these around and right click them to open them.
- each category has different modules you may enable by left clicking.
- you can open the settings for a module by right clicking the module. 
- the settings consist of booleans (enable or disable), numbers (sliders to change the value of something), modes (switches modes), and colors (right click to configure). 
- you can rebind each module in the settings by clicking keybind (at the bottom of each modules settings), and clicking the button you want to bind the module to.
- esc to close the clickgui 0_0.

**development**
- jdk 8 required.
- gradlew setupDecompWorkspace should be ran before anything (if your using intellij, it should take care of it for you).
- gradlew eclipse (for eclipse users) or gradlew genIntellijRuns (for intellij users).

# credits
**really helpful people**
- lukflug, being cool person. making panelstudio. helping me with panelstudio.
- techale, adding multithreading to the main class. making postman website. helping me a lot :).
- RECIOR, for postman bot, and being an extremly based individual. <br>
- cattyngmd, making pr's for a variety of fixes and modules. <br>

<!--
**contributors**
- cattyngmd, 
- mwahaha9, fixing the spelling in an onDisable method in autoGap.
- jacobtread, giving proper credits to the MinnDevelopment java discord rpc.
-->
**base stuff**
- [**modding api**](http://files.minecraftforge.net) - *Forge*.
- [**event system**](https://github.com/ZeroMemes/Alpine) - *ZeroMemes, Alpine*.
- [**clickGui library**](https://github.com/lukflug/PanelStudio/tree/main) - *Lukflug, PanelStudio*.
- [**discord rpc**](https://github.com/MinnDevelopment/java-discord-rpc) - *MinnDevelopment, java discord rpc*.
- [**mixins**](https://www.spongepowered.org/downloads/spongeforge/stable/1.12.2) - *SpongePowered, SpongeForge*.
- [**font renderer**](https://github.com/zeroeightysix/KAMI) - *086, KAMI*.

**everything that is skidded as of now**
- outline esp utils - [**superblaubeere27**](https://www.youtube.com/channel/UCtRhisaTkICo72ZI8Z2yWNg).
- surround & holeTp - [**Hoosier's Gamesense**](https://github.com/IUDevman/gamesense-client). 0_0
- 2dEsp - [**FINZ0's Osiris**](https://github.com/epicfacethe3rd/Osiris).

... for more depth, the necessary modules are credited. if anyone has an issue with crediting please lmk. srgantmoomoo#1052
