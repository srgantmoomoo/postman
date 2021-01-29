# postman
this client was started for fun as a project to help with my java and coding in general. i started getting into coding around July of 2020 and a project like this is perfect, not only for fun, but to help me out. postman originally started as a private project with roots stemming from my previous project, Present, i decided to make postman a public project on Halloween 2020, the original set release date for Present Utility Mod. :)

postman is a client for Minecraft 1.12.2 intended for use on servers which allow client-side modification. this client is not intended for and is of no use on servers which do not allow it. postman takes inspiration from a few clients listed in the credits below, and uses Zeros Alpine for an event system and lukflugs panelstudio for the clickGui, so thank you to all mentioned! this wouldn't be possible without them. i intend for this project to be as community driven as possible, no private access, no private development, every beta release is public and free to use! and with this GitHub, the client is now officially open source.

this client is on Minecraft 1.12.2, Forge version 1.12.2-14.23.5.2768, Alpine 1.5, PanelStudio 0.1.7, and is coded in 100% java (although the website code is not java, obviously).

join the postman discord - https://discord.gg/Jd8EmEuhb. <br />
check out the postman website - https://moomooooo.github.io/postman/. <br />
*postman archive* - this basically just holds all the old versions of postman, https://github.com/moomooooo/postman-archive. <br />

thank you,

-SrgantMooMoo

# instructions
**download**
1. postman uses forge in order to run so, if you havent already, go to http://files.minecraftforge.net/maven/net/minecraftforge/forge/index_1.12.2.html and download the latest or recommended version of forge 1.12.2. <br />
2. in this repository, under tags/releases click the latest version or whatever version you want. <br />
3. click assets at the bottom of the release, then download the .jar file. <br />
4. if you havent ran forge yet, do so, if you have already ran forge at least once, type %appdata% in ur windows search. <br />
5. locate and open up your .minecraft folder, than find your mods folder (if your on any other os, than locate your .minecraft folder, you may need to look it up).<br />
6. open up your mods folder and drag the postman .jar file into it. <br />

**use**
- the inital keybind for the clickgui is rshift.
- once your clickgui is opened you will see a bunch of categorys, right click to open them up.
- each category has different modules you may enable by left clicking, or right clicking to open up their settings.
- the settings consist of booleans (enable or disable), numbers (sliders to change the value of something), and modes (switches modes). 
- you can rebind each module in the settings by clicking keybind (at the bottom of each modules settings), and clicking the button you want to bind the module to.
- esc to close the clickgui 0_0.

**building**
1. gradlew setupDecompWorkspace <br />
2. gradlew eclipse (for eclipse) or gradlew genIntellijRuns (for intellij) <br />
(./gradlew for linux and mac users) <br />

# credits
**contributions**
- lukflug, being helpful person. making panelstudio. helping me with panelstudio.
- some random guy idk named jacobtread, for giving proper credits to the MinnDevelopment java discord rpc.

**base stuff**
- mod api - *Forge* http://files.minecraftforge.net.
- event system - *ZeroMemes, Alpine* https://github.com/ZeroMemes/Alpine.
- discord rpc - *club.minnced, java discord rpc* https://github.com/MinnDevelopment/java-discord-rpc.
- clickGui library - *lukflug, PanelStudio* https://github.com/lukflug/PanelStudio/tree/main.
- mixins - *SpongeForge* https://www.spongepowered.org/downloads/spongeforge/stable/1.12.2.

**bassically everything that has been skidded, hopefully one day this is pointless :D. whenever i have the time, i try to work on unskidding**
- outline esp utils... and a lot of the fontrenderer - *superblaubeere27* https://www.youtube.com/channel/UCtRhisaTkICo72ZI8Z2yWNg.
- font renderer - pretty much *kamiblue*, some stuff was rewritten for postman tho, so it's not exactly https://github.com/kami-blue/client.
- surround & holeTp - *gamesense* https://github.com/IUDevman/gamesense-client. 0_0
- 2dEsp - mostly taken from *finz0's Osiris*, but changed a quite bit. git doesent exsist anymore, some drama or smthn, not sure lol.

**a lot of inspiration from tutorials and what not taken from these creators, so thank you so much to them**
- sebsb - https://www.youtube.com/channel/UChsMQm_9PXITsUhBkypP_1A.
- superblaubeere27 - https://www.youtube.com/channel/UCtRhisaTkICo72ZI8Z2yWNg.

... for more depth, the necessary modules are credited. if anyone has an issue with crediting please lmk. srgantmoomoo#1052
