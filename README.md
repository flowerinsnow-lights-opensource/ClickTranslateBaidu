# ClickTranslate
点击翻译Mod，原FlowerinsnowPlus功能

使用[百度翻译](https://fanyi.baidu.com/)API

# 免责声明
本项目仅供参考和试用，一切责任由使用者承担

使用时请遵守[百度翻译](https://fanyi.baidu.com/)相关条款

本项目不涉及原作者的服务器，由客户端直接与API服务器通信

# 开源协议
本项目使用[GPL 3.0](https://www.gnu.org/licenses/gpl-3.0.html)开源协议

> GPL 3.0 是一种开源协议，它规定了使用该协议的软件在使用和分发时的一些限制和规定，以下是 GPL 3.0 协议的主要限制和规定：  
>
> 可以做的事情：  
>
>    任何人都可以自由地使用和修改使用 GPL 3.0 协议的软件。  
>    可以将使用 GPL 3.0 协议的软件分发给其他人，但是必须以相同的协议分发，包括源代码。  
>    可以将使用 GPL 3.0 协议的软件用于商业目的。  
>
> 不可以做的事情：  
>
>    不能将使用 GPL 3.0 协议的软件与封闭源代码软件捆绑在一起分发，这意味着如果使用 GPL 3.0 协议的软件的一部分被修改，则整个软件必须使用 GPL 3.0 协议开源。  
>    如果使用 GPL 3.0 协议的软件的一部分被修改，则必须在修改的代码中包含相应的 GPL 3.0 协议条款，以保持开源。  
>    不能对使用 GPL 3.0 协议的软件进行专利授权或专利许可，以使专利和专利许可成为该软件的限制因素。  
>
> 总之，GPL 3.0 协议旨在确保使用和分发该软件的自由，以及确保修改和分发该软件的任何人都必须保持该软件的开源。

以上内容来自ChatGPT

本项目使用GPL 3.0开源协议但添加了附加条款，本软件要求您在修改或二次分发时必须在软件内标注“该软件并非原版”，且附上原版的仓库链接（即本仓库）；详见[LICENSE](LICENSE)文件

# 使用方式
1. 本项目需要您自己申请百度翻译API，一般只需要一个百度账号即可，请前往[百度翻译开放平台](https://fanyi-api.baidu.com/)申请，如有需要可以升级到更高版本
2. 查看你的`APP ID`和`密钥`，它们在[开发者中心](https://fanyi-api.baidu.com/manage/developer)中
3. 下载Mod文件到`mods`文件夹中，然后启动游戏
4. 在游戏目录下，找到目录`config`，再找到里面的`clicktranslate.cfg`(1.0/2.0 Forge配置格式)~~或`clicktranslate.yml`(3.0 YAML配置格式)~~或`clicktranslate.conf`(4.0以上 HOCON配置格式)
5. 使用文本方式打开并编辑该文件，您必须修改`app-id`为您的 APP ID ，修改`app-secret`为您的密钥，其余按需修改
6. 重启客户端或者在任意地方输入命令`/clicktranslate reload`；如果提示未知指令，当前版本很有可能已经接入 GUI 重载——若您在使用 Fabric 版本，请安装 [Mod Menu](https://modrinth.com/mod/modmenu)；若您正在使用 Forge 版本，请在 Mod 列表中操作

# 适用版本
| Mod版本 |   游戏版本    | Mod加载器  |
| :-----: | :-----------: | :--------: |
|   1.x   |     1.8.9     |   Forge    |
|   2.x   |    1.12.2     |   Forge    |
| ~~3.x~~ |  ~~1.19.4~~   | ~~Fabric~~ |
|   4.x   | 1.19.3~1.20.1 |   Fabric   |
|   5.x   | 1.16.2~1.16.5 |   Fabric   |

# 使用的类库
- [EasyConfiguration](https://github.com/CarmJos/EasyConfiguration) by [CarmJos](https://github.com/CarmJos)