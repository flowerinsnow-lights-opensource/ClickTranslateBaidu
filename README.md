# Click Translate Baidu
[简体中文](README/zh_cn.md)

Click chat message to translate with baidu translate API. From old FlowerinsnowPlus feature.

Translate with [百度翻译](https://fanyi.baidu.com/) API

# Disclaimer
This project is for reference and trial only, all responsibilities are borne by the user.

Please abide by the relevant terms of [百度翻译](https://fanyi.baidu.com/) when using it.

This project does not involve the author's server. The client communicates directly with the API server.

# Open-Source License
This project uses the GPL 3.0 open source license but adds additional terms. This software requires you to mark "This software is not the original version" in the software when modifying and redistributing it, and attach the original version's repository link (i.e. this repository); details See [LICENSE](LICENSE) file

# Usage
1. This project requires you to apply for the Baidu Translation API yourself. Generally, you only need a Baidu account. Please go to [百度翻译开放平台] (https://fanyi-api.baidu.com/) to apply. You can upgrade to a higher version if necessary.
2. Check your `APP ID` and `Key`, they are in [开发者中心](https://fanyi-api.baidu.com/manage/developer)
3. Download the mod file to the `mods` folder, then launch the game
4. In the game directory, find the directory `config`, and then find `clicktranslate.cfg` or `clicktranslate.yml`
5. Open and edit the file using text mode. You must modify `app-id` as your APP ID, modify `app-secret` as your key, and modify the rest as needed.
6. Restart the client or enter the command `/clicktranslate reload` anywhere. If it's unknown command, the current version is likely to have access to GUI reload - if you are using the Fabric version, please install [Mod Menu](https:/ /modrinth.com/mod/modmenu); if you are using the Forge version, please operate in the Mod list

# Using libraries
- [EasyConfiguration](https://github.com/CarmJos/EasyConfiguration) by [CarmJos](https://github.com/CarmJos)