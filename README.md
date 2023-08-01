<img src="https://user-images.githubusercontent.com/69589624/132962368-25885f65-740e-4955-9b31-4a1cb899b660.png" alt="postman" width="175"/>

# postman

[![GitHub all releases](https://img.shields.io/github/downloads/moomooooo/postman/total?color=79C1FF&style=flat-square)](https://github.com/moomooooo/postman/releases)
[![Lines of code](https://img.shields.io/tokei/lines/github/moomooooo/postman?color=79C1FF&style=flat-square)](https://github.com/moomooooo/postman/tree/master/src/main/java/me/srgantmoomoo)
[![Discord](https://img.shields.io/discord/760964236779716648?color=79C1FF&label=discord&style=flat-square)](https://discord.gg/Jd8EmEuhb5)
[![GitHub](https://img.shields.io/github/license/moomooooo/postman?color=79C1FF&style=flat-square)](https://github.com/moomooooo/postman/blob/master/LICENSE)

postman is a [**Minecraft**](https://minecraft.net) client intended for use on servers which allow client-side modification. postman is written in 100% java (very based programming language) for the latest version of [**Fabric**](https://fabricmc.net/). the current version of the client is heavily based off it's [***legacy***](https://github.com/srgantmoomoo/postman/tree/legacy) version which was written for [**Forge**](https://files.minecraftforge.net/net/minecraftforge/forge/) 1.12.2. i intend for this project to be heavily community driven, open source, open access, open development, and available to all for free!
<br>

join the [**postman discord**](https://discord.gg/Jd8EmEuhb5) for frequent updates, support, and a strong community :) <br>
check out the [**postman website**](https://techale.github.io/postman-website/) by techale! <br>
check out the [**postman archive**](https://github.com/moomooooo/postman-archive) for pre-github releases of postman.

thank you :),

-SrgantMooMoo

![image](https://user-images.githubusercontent.com/69589624/129431288-d6a1c2db-7a68-488d-b885-901b86ca02f7.png)

# instructions
***the following instructions are for the latest version of postman and have no real application as of yet, please disregard :). for instructions on downloading postman for 1.12.2, head to the [***legacy***](https://github.com/srgantmoomoo/postman/tree/legacy) branch of this repository.***

**download**
1. postman uses the Fabric mod loader to run. if you have not already, please head to [**FabricMC**](https://fabricmc.net/) and follow their directions on downloading the Fabric mod loader. you will also need to download the [**Fabric API**](https://www.curseforge.com/minecraft/mc-mods/fabric-api) and add it to your mods folder (i'll go over the mods folder in a second).
2. head to the [**releases**](https://github.com/srgantmoomoo/postman/releases) part of this repository; select the latest version (or preferred version), find the assets section at the bottom, then download the .jar file.
3. navigate to your .minecraft directory (for windows users, type %appdata% into your windows search and hit enter, it should be there). open the mods directory (if there is no mods directory then you can create it yourself).
4. drag the postman .jar file you downloaded earlier into the mods folder, as well as that fabric api from earlier if you have not done so already.

*please, if you have used the forge vairiant of postman before, delete that config file before using this.*

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
- jdk 17 required.
- make sure to run the genSources gradle command.

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
- [**modding api**](https://fabricmc.net/) - *Fabric*.
- [**clickGui library**](https://github.com/lukflug/PanelStudio/tree/main) - *Lukflug, PanelStudio*.

... if anyone has an issue with crediting please let me know. srgantmoomoo#1052
