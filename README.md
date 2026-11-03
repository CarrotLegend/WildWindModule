# Wild Wind Modules 原野之风模块包

### Module List 模块列表

| name |         note         | ch name |  note   |
|:----:|:--------------------:|:-------:|:-------:|
| core |   Wild Wind: Core    |  核心   | 原野之风：核心 |
| wilderness | Wild Wind: Wilderness Update | 荒野更新 | 原野之风：荒野更新 |
| barren | Wild Wind: Barren Update | 瘠土更新 | 原野之风：瘠土更新 |

模块 `modid` 由 `ww_<模块名>` 生成：`ww_core` / `ww_wilderness` / `ww_barren`。
`wilderness` 与 `barren` 在 `neoforge.mods.toml` 中声明依赖 `ww_core`，构建上通过 `implementation project(":core")` 关联。

### 内容归属

| 模块 | 内容 |
|:----:|:-----|
| core（0） | 杜鹃木套件、玫瑰 / 灵魂玫瑰（丛）、制箭台与矢术、幼蛛 |
| wilderness（1） | 泥沼蟹、恐鱼、蟹钳 / 尖牙 / 万用蟹钳、延展药水与状态效果、灵魂南瓜灯 |
| barren（2） | 焚烬木 / 灵焰木套件、石英砂岩 / 灵魂砂岩全套、石英沙与流沙、焦土、仙人球、可疑沙 |

[CHANGES](./CHANGE_LOG.MD)