# postman
[![GitHub all releases](https://img.shields.io/github/downloads/moomooooo/postman/total?color=79C1FF&style=flat-square)](https://github.com/moomooooo/postman/releases)
[![Lines of code](https://img.shields.io/tokei/lines/github/moomooooo/postman?color=79C1FF&style=flat-square)](https://github.com/moomooooo/postman/tree/master/src/main/java/me/srgantmoomoo)
[![Discord](https://img.shields.io/discord/760964236779716648?color=79C1FF&label=discord&style=flat-square)](https://discord.gg/Jd8EmEuhb5)
[![GitHub](https://img.shields.io/github/license/moomooooo/postman?color=79C1FF&style=flat-square)](https://github.com/moomooooo/postman/blob/master/LICENSE) <br>
<hr>
postman is a client that runs off Minecraft Forge 1.12.2, it is intended for use on servers which allow client-side modification. postman takes inpiration from many previous clients like osiris, kami, gamesense, and others. this client uses ZeroMemes's Alpine for an event system, lukflugs PanelStudio for the clickGui library, and Forge's 1.12.2 mod api. i intend for this project to be community driven and as open as possible, open access, open development, and open sourced. 
<br>
<br>

this client is on Minecraft 1.12.2, Forge version 1.12.2-14.23.5.2768, Alpine 1.5, PanelStudio 0.1.8, and is coded in 100% java.

check out the postman website - https://techale.github.io/postman-website/. <br />
*postman archive* - this basically just holds all the old versions of postman, https://github.com/moomooooo/postman-archive. <br />

thank you,

-SrgantMooMoo

# instructions
**download**
1. postman uses forge in order to run so, if you havent already, go to http://files.minecraftforge.net/maven/net/minecraftforge/forge/index_1.12.2.html and download the latest or recommended version of forge 1.12.2. <br />
2. at the top of this readme, click on the downloads button and select the latest version, or your preferred version. <br />
3. click assets at the bottom of the release, then download the .jar file. <br />
4. if you havent ran forge yet, do so, if you have already ran forge at least once, type %appdata% in ur windows search, enter, and navigate to .minecraft -> mods (if there is no mods folder than you can add it yourself) -> 1.12.2, "1.12.2" may not exist either, so you can create it yourself if needed. this process may vary depending on your device, so you may need to look it up. <br />
5. drag the postman .jar file that you downloaded earlier into the 1.12.2 folder. <br />
- if you want to take advantage of postman's baritone integration, you can download the [baritone api](https://github.com/cabaletta/baritone/releases/download/v1.2.14/baritone-api-forge-1.2.14.jar) and drag it into your mods folder along with postman.

**use**
- prefix is "," (comma), this is customizable too.
- the inital keybind for the clickgui is rshift.
- once your clickgui is opened you will see a bunch of categories, you can drag these around and right click them to open them.
- each category has different modules you may enable by left clicking and right clicking to open up their settings.
- the settings consist of booleans (enable or disable), numbers (sliders to change the value of something), and modes (switches modes). 
- you can rebind each module in the settings by clicking keybind (at the bottom of each modules settings), and clicking the button you want to bind the module to.
- esc to close the clickgui 0_0.

**building**
1. gradlew setupDecompWorkspace <br />
2. gradlew eclipse (for eclipse) or gradlew genIntellijRuns (for intellij) <br />
(./gradlew for linux and mac users) <br />

# credits
**really helpful people**
- lukflug, being cool person. making panelstudio. helping me with panelstudio.
- techale, adding multithreading to the main class. making postman website. helping me a lot :).
- RECIOR, for postman bot, and being an extremly based individual. <br>
- cattyngmd, making pr's for a variety of fixes and modules. <br>

**contributors** <br>
- mwahaha9, fixing the spelling in an onDisable method in autoGap.
- jacobtread, giving proper credits to the MinnDevelopment java discord rpc.

**base stuff**
- mod api - *Forge* http://files.minecraftforge.net.
- event system - *ZeroMemes, Alpine* https://github.com/ZeroMemes/Alpine.
- discord rpc - *club.minnced, java discord rpc* https://github.com/MinnDevelopment/java-discord-rpc.
- clickGui library - *lukflug, PanelStudio* https://github.com/lukflug/PanelStudio/tree/main.
- mixins - *SpongeForge* https://www.spongepowered.org/downloads/spongeforge/stable/1.12.2.
- font renderer - *086, KAMI* some stuff was rewritten, so it's not exact, https://github.com/zeroeightysix/KAMI.

**bassically everything that has been skidded, hopefully one day this is pointless :D. whenever i have the time, i try to work on unskidding**
- outline esp utils - *superblaubeere27* https://www.youtube.com/channel/UCtRhisaTkICo72ZI8Z2yWNg.
- surround & holeTp - *gamesense* https://github.com/IUDevman/gamesense-client. 0_0
- 2dEsp - mostly taken from *finz0's Osiris*, but changed a quite bit. git doesent exsist anymore.

... for more depth, the necessary modules are credited. if anyone has an issue with crediting please lmk. srgantmoomoo#1052
