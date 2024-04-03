# Farmer's Delight Refabricated

<a href="https://www.curseforge.com/minecraft/mc-mods/farmers-delight-refabricated">
  <img src="http://cf.way2muchnoise.eu/full_993166_downloads.svg" alt="Curseforge Downloads">
</a>
<a href="https://discord.gg/eFsz5SK">
  <img alt="Discord" src="https://img.shields.io/discord/790151253144895508?color=brightgreen&label=Discord">
</a>
<br>
<img src="https://cdn.modrinth.com/data/cached_images/55f4eef09b087d3b08a792e1c7c224e5796cbb71.png" width="50%">

## For the official Forge version of the mod's GitHub, please go [here](https://github.com/vectorwing/FarmersDelight/).

### Overview

**Farmer's Delight** is a mod that gently expands upon farming and cooking in Minecraft.

Using a simple cooking system and a few familiar ingredients, you'll be able to prepare a wide variety of **hearty meals**: from sandwiches to salads and stews, from beautiful desserts to mouth-watering feasts, no ingredient will be left behind in your kitchen!

It also introduces a rich set of utilities: a way to **improve the very soil** your crops grow in, a brand new kind of tool to **scavenge resources** with, cute **decorations** for your builds, and many blocks and items to help you on your adventure!

It's time to farm a little bit of everything!

### Contributing

Thank you for visiting the repository! If you'd like to contribute with the mod, feel free to check the wiki for more details, or take a look at the issues page!

I am open to constructive feedback about the mod's code: if you spot any glaring mistakes in my code, and/or you know a better way to accomplish something, feel free to open an issue/PR about it. Any help is appreciated!

### Depending on Farmer's Delight Refabricated
Starting from 2.0.7, Farmer's Delight Refabricated can be depended on within development environments through the Greenhouse Maven (https://maven.greenhouseteam.dev/).

To do so, assuming you have a field in your gradle.properties named `fdrf_version`.
```groovy
repositories {
    maven {
        name = "Greenhouse Maven"
        url = 'https://maven.greenhouseteam.dev/releases/'
    }
    maven { url = "https://mvn.devos.one/releases/" } // Porting Lib
    maven {
        url "https://maven.jamieswhiteshirt.com/libs-release" // Reach Entity Attributes (Required by Porting Lib)
        content {
            includeGroup "com.jamieswhiteshirt"
        }
    }
}
dependencies {
    modImplementation("vectorwing:FarmersDelight:${fdrf_version}") {
        exclude(group: "net.fabricmc")
    }
}
```

Replace the `x`s with the current version number.
```properties
fdfr_version=1.20.1-x.x.x+refabricated
```
