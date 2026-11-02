# Changelog

All notable changes to the Wild Wind Module project will be documented in this file.

---

## 拆分为三个模块（core / wilderness / barren）

原 `hfas` 单模块按内容归属拆为三个可独立安装的 mod：

| 目录 | modid | 显示名 | 内容 |
|:----:|:-----:|:------|:-----|
| `src/core` | `ww_core` | Wild Wind: Core | 杜鹃木套件、玫瑰 / 灵魂玫瑰（丛）、制箭台与矢术、幼蛛 |
| `src/wilderness` | `ww_wilderness` | Wild Wind: Wilderness Update | 泥沼蟹、恐鱼、蟹钳 / 尖牙 / 万用蟹钳、延展药水、灵魂南瓜灯 |
| `src/barren` | `ww_barren` | Wild Wind: Barren Update | 焚烬木 / 灵焰木套件、砂岩 / 沙 / 流沙 / 焦土 / 仙人球 / 可疑沙 |

要点：

- Java 包名同步为 `org.polaris2023.wildwind.core` / `.wilderness` / `.barren`，主类为 `WildWindCore` / `WildWindWilderness` / `WildWindBarren`。
- `wilderness`、`barren` 在 `neoforge.mods.toml` 中以 `type="required"`、`ordering="AFTER"` 依赖 `ww_core`，Gradle 侧用 `implementation project(":core")`。
- 共享组件留在 core：`WoodFamily` / `ModWoodFamilies`（仅杜鹃木）、`WindupAttackMob` 与 AI goal、制箭台菜单与方块实体。
- 每个模块各自拥有 `mixins.json`、访问转换器、datagen 入口与 `assets/data` 命名空间（`ww_core` / `ww_wilderness` / `ww_barren`），原 `ww_hfas` 资源已按归属重新分配。
- 创造模式物品栏不再共用一个标签页：core 提供 `ww_core:wild_wind`（图标杜鹃木原木），wilderness 提供 `ww_wilderness:wilderness`（图标灵魂南瓜灯），barren 提供 `ww_barren:barren`（图标焚烬木原木）；三者同时安装时并列显示。

---

## 项目总结 - HFAS 模块

### 模组概述
**HFAS (Wild Wind - Fletching Table and Saplings)** 是 Wild Wind Module 的子模块，专注于：
- 制箭台功能扩展
- 自定义木质方块套件
- 世界生成增强

### 核心功能

#### 1. 制箭台替换系统
- **区块加载替换** - 使用 `ChunkEvent.Load` 事件自动替换原版制箭台
- **配置控制** - 通过 `worldgen.replaceVanillaFletchingTable` 配置项控制开关
- **GUI系统** - 自定义制箭台带有交互界面（3个输入槽位）

#### 2. 木质方块套件
| 木材类型| 方块数量 | 特点|
|--------------|------|-----------|
| 焚烬木 (Blaze)| 16种| 橙色调，下界风格|
| 灵焰木 (Soul)| 16种| 青色调，灵魂主题|
| 杜鹃木 (Azalea) | 13种| 浅绿色调，繁茂洞穴 |

每种套件包含：原木、木头、去皮原木、去皮木头、木板、楼梯、台阶、栅栏、栅栏门、门、活板门、压力板、按钮、树叶、树苗

#### 3. 世界生成
- **焚烬木** - 下界废土群系 (Nether Wastes)
- **灵焰木** - 灵魂沙峡谷群系 (Soul Sand Valley)
- **杜鹃木** - 繁茂洞穴群系 (Lush Caves)，替换原版杜鹃树

#### 4. 杜鹃树增强
- **Mixin 注入** - 修改杜鹃花催熟行为，生成杜鹃木原木
- **生物群系修改** - 移除原版杜鹃树，添加自定义杜鹃树
- **保留原版树叶** - 使用原版杜鹃树叶和开花杜鹃树叶

#### 5. 木质方块功能
- **去皮功能** - 使用斧头可将原木/木头去皮
- **堆肥功能** - 树叶和树苗可用于堆肥（30%概率）
- **燃料功能** - 木质方块可作为熔炉燃料（由 NeoForge `furnace_fuels` 数据地图提供）

### 技术实现

#### 数据生成
| 提供器| 功能|
|------------------------|--------------|
| `HFASDatagen`         | 数据生成入口|
| `ModModelProvider`    | 模型生成（支持染色树叶） |
| `ModBlockTagsProvider`| 方块标签生成|
| `ModItemTagsProvider` | 物品标签生成|
| `ModLootProvider`     | 战利品表生成|
| `ModDatapackProvider` | 数据包提供器|
| `ModDataMapsProvider` | 数据映射提供器|
| `EnUsLanguageProvider`| 英文语言文件|
| `ZhCnLanguageProvider`| 中文语言文件|

#### 世界生成系统
| 类| 功能|
|---------------------------|------------|
| `ModConfiguredFeatures`   | 配置特征（树木结构） |
| `ModPlacedFeatures`       | 放置特征|
| `ModBiomeModifiers`       | 生物群系修改器|
| `ModTreeGrowers`          | 树苗生长器|

#### 事件处理
| 类| 功能|
|--------------------------------|------------|
| `FletchingTableReplaceHandler` | 区块加载时替换制箭台 |
| `BlockFeaturesHandler`         | 方块功能（堆肥等）|

#### Mixin 注入
| 类| 功能|
|--------------------|-----------|
| `AzaleaBlockMixin` | 修改杜鹃花催熟行为 |

#### 自定义类型
| 类型| 用途|
|----------------|------------------------|
| `BlockSetType` | 焚烬木、灵焰木、杜鹃木的门/活板门/按钮类型 |
| `WoodType`     | 对应木材类型|
| `TreeGrower`   | 自定义树木生长逻辑|

### 文件结构
```
src/hfas/java/org/polaris2023/wildwind/hfas/
├── block/# 方块注册
│├── ModBlocks.java# 方块定义
│├── FletchingTableBlock.java
│└── entity/# 方块实体
│└── FletchingTableBlockEntity.java
├── worldgen/# 世界生成
│├── ModConfiguredFeatures.java
│├── ModPlacedFeatures.java
│├── ModBiomeModifiers.java
│└── ModTreeGrowers.java
├── datagen/# 数据生成
│├── HFASDatagen.java
│└── provider/
│├── loot/# 实体掉落
│├── lang/# 语言系统
│├── tag/# Tag标签
│├── ModModelProvider.java
│├── ModLootProvider.java
│├── ModDatapackProvider.java
│├── ModDataMapsProvider.java
├── event/# 事件处理
│├── FletchingTableReplaceHandler.java
│└── BlockFeaturesHandler.java
├── mixin/# Mixin 注入
│└── AzaleaBlockMixin.java
├── config/# 配置文件
│└── ModCommonConfig.java
├── menu/# GUI 菜单
│└── FletchingTableMenu.java
└── groups/# 创造模式物品栏
└── ModCreativeTabs.java
```

### 生成资源统计
| 类型| 数量|
|---------|---------------------------|
| 方块模型 | 118+|
| 物品模型 | 60+|
| 方块状态 | 45+|
| 战利品表 | 45+|
| 语言条目 | 90+ (en_us) / 90+ (zh_cn) |

---

## [1.0.0] - 2026.04.15

### Added
- **区块加载替换机制** - 使用 `ChunkEvent.Load` 事件在区块加载时替换制箭台
- **制箭台GUI系统** - `FletchingTableBlock`、`FletchingTableBlockEntity`、`FletchingTableMenu`
- **数据生成系统** - 模型、战利品表、语言文件、数据映射生成
- **焚烬木套件** - 16种方块（原木、木板、楼梯、门等）
- **灵焰木套件** - 16种方块
- **杜鹃木套件** - 13种方块（不含树叶和树苗，使用原版）
- **树木自然生成** - 焚烬木（下界）、灵焰木（灵魂沙峡谷）、杜鹃木（繁茂洞穴）
- **杜鹃树增强** - Mixin 修改催熟行为，使用杜鹃木原木
- **配置文件系统** - `worldgen.replaceVanillaFletchingTable` 配置项
- **方块放置事件处理器** - `FletchingTableReplaceHandler`
- **焦灰草方块、焦土、焦灰草丛、焦灰枝条、仙人球**
- **制箭台（带GUI功能）**
- **创造模式物品栏自动排序**
- **木头去皮功能** - 使用 NeoForge DataMaps 注册去皮映射
- **树叶堆肥功能** - 树叶和树苗30%概率增加堆肥等级
- **木质方块燃料功能** - 木质方块可作为熔炉燃料（300 tick）
- **模型数据生成** - 使用 `BlockModelGenerators.family()` 和 `woodProvider()` 自动生成方块和物品模型
- **标签数据生成** - 使用 `ModBlockTagsProvider` 和 `ModItemTagsProvider` 为木质方块添加原版标签（logs、planks、wooden_stairs 等）

### Changed
- 移除 `StructureTemplateMixin`，改用事件监听方式
- 使用 `BiomeModifiers.RemoveFeaturesBiomeModifier` 移除原版杜鹃树
- 使用自定义 `BlockSetType` 和 `WoodType`
- 优化 `ModModelProvider` 使用 `family()` 方法批量生成木质方块模型

---

## 开发计划

### 已完成功能
- [x] 模组制箭台GUI功能
- [x] 世界生成时替换结构内制箭台
- [x] 创造模式物品栏排序
- [x] 焚烬木/灵焰木/杜鹃木套件
- [x] 树木自然生成
- [x] 杜鹃树催熟增强
- [x] 木头去皮功能
- [x] 树叶堆肥功能
- [x] 木质方块燃料功能
- [x] 模型数据生成
- [x] 标签数据生成

### 待开发功能
- [ ] 更多下界植物
- [ ] 制箭台配方系统
- [ ] 成就系统

---

## [Unreleased] - 2026.10.23

### Added
- **木材家族聚合** - 新增 `WoodFamily` 与 `ModWoodFamilies`，模型、战利品、标签、数据地图、创造物品栏统一遍历，不再各写一份 13~15 参数列表
- **方块战利品表** - 注册 `BlockLoot`，`ModLootProvider` 此前只挂了实体战利品，方块战利品表完全缺失
- **燃料数据地图** - 使用 NeoForge `furnace_fuels` 数据地图注册燃料（原木/木头/去皮/木板/楼梯/栅栏/栅栏门/压力板 300 tick，台阶 150，按钮与树苗 100）
- **补全方块标签** - `logs_that_burn`、`c:stripped_logs`、`c:stripped_woods`、`fences`、`fence_gates`、`wooden_fences`，原先只写在手写 JSON 里

### Changed
- 模型、战利品、标签、创造物品栏提供器改为遍历 `ModWoodFamilies.ALL`
- `ModItemTagsProvider` 改继承 `net.neoforged.neoforge.common.data.ItemTagsProvider`
- 创造物品栏的 62 行 `insertAfter` 链收敛为 `appendWoodSet()`
- 燃料由 `FurnaceFuelBurnTimeEvent` 事件改为数据地图驱动，移除 `FuelHandler`

### Fixed
- **活板门不掉落** - 原先误用 `createDoorTable`（带 `half=lower` 条件），天花板上的活板门破坏后无掉落，改为 `dropSelf`，与 `VanillaBlockLoot` 一致
- **木质方块被误加入 `needs_stone_tool`** - 导致木板、门、按钮等必须石级工具才能挖掘掉落，已移除该标签注册
- **Gradle 9 下资源重复项报错** - 原 `.cache` 排除模式相对 srcDir 根无效，已修正并显式设置 `duplicatesStrategy`

### Removed
- **回滚 PR #29 的资源污染** - 删除 438 个与 datagen 产物同名的手写 `blockstates` / `models` / `items` / `data` 文件，以及 130 个未注册方块（sign / boat / potted sapling）的孤儿资源，仅保留 80 个 datagen 无法重建的文件
- **被提交进 `src` 的 datagen HashCache** - 删除 `src/hfas/resources/.cache/`（9 个文件），它会令 datagen 误判"已生成"而跳过重写
- **重复的标签与语言文件** - 删除 15 个与 datagen 输出同名的 JSON（datagen 每次重新生成，无信息丢失）
- **改名前的重复贴图** - 删除 26 张与 `blaze_*` / `soul_*` 逐字节相同的 `cinder_*` / `ember_*`（含 `.mcmeta`），无信息丢失；另有 28 张没有对应新命名版本的贴图（船 / 箱船 / 告示牌 / 悬挂告示牌的实体贴图与物品图标、`gui/hanging_signs`、`screens`）全部保留，作为后续船与告示牌开发的素材
- **`ModBlockTagsProvider.INSTANCE`** - 无人使用的静态单例

---

## [Unreleased] - 2026.10.25

### Added
- **树苗花盆方块** - 注册 `potted_blaze_sapling` / `potted_soul_sapling`，使用原版 `FlowerPotBlock(树苗, props)` 构造器，构造时自动把树苗登记进原版空花盆的可放入映射表，无需额外的手工 `addPlant`
- **花盆模型与战利品表** - `ModModelProvider` 用 `createPlant()` 一次生成树苗交叉模型 + 花盆模型（含 blockstate），`BlockLoot` 用 `dropPottedContents()` 生成「花盆 + 树苗」两池掉落，与原版 `potted_oak_sapling` 一致
- **花盆方块本地化** - `block.ww_hfas.potted_blaze_sapling` / `potted_soul_sapling`，en_us 为 `Potted Blaze Sapling` / `Potted Soul Sapling`，zh_cn 为 `焚烬木树苗盆栽` / `灵焰木树苗盆栽`
- **食人鱼拍打音效** - 新增 `entity.piranha.flop` SoundEvent（`createVariableRangeEvent`）与 `subtitles.entity.piranha.flop` 字幕（en_us `Piranha flops` / zh_cn `食人鱼拍打`）

### Changed
- **食人鱼 `getFlopSound()`** - 由原版 `SoundEvents.SALMON_FLOP` 改为自有 `ModSoundEvents.PIRANHA_FLOP`，移除遗留的 `// WIP SOUND` 标记
- **`sounds.json` 暂借原版音源** - `entity.piranha.flop` 以 `sounds: [{ "type": "event", "name": "minecraft:entity.salmon.flop" }]` 引用原版鲑鱼拍打声；待有独立 `.ogg` 后把该条目替换为文件列表即可，事件 ID 与字幕键均无需变动

### Fixed
- **`minecraft:flower_pots` 标签加载失败** - 标签内引用的 `ww_hfas:potted_blaze_sapling` / `ww_hfas:potted_soul_sapling` 此前没有对应注册，`TagLoader` 报 `Couldn't load tag minecraft:flower_pots as it is missing following references`；现两个方块均已注册（BUG-TG-001）
- **杜鹃木 / 焚烬木 / 灵焰木木板无法合成** - `azalea_planks` / `blaze_planks` / `soul_planks` 的 `ingredients` 误写为单个方块 id（`ww_hfas:blaze_log` 等），改为物品标签 `#ww_hfas:azalea_logs` / `#ww_hfas:blaze_logs` / `#ww_hfas:soul_logs`，原木、木头与两种去皮变体都能参与合成（BUG-TG-002）
- **食人鱼 flop 无声音且字幕不显示** - `sounds.json` 曾把 `type` / `name` 写在事件层级并缺少 `sounds` 数组，`SoundEventRegistrationSerializer.getSounds()` 解析出空音源，事件播放被丢弃；已改为把 `type: "event"` 内嵌在 sound 条目中

---

## [Unreleased] - 2026.10.29

### Added
- **幼蛛实体** - 回溯移植 `Spiderling`：继承原版 `Spider`（保留攀爬与目标选择），实现 `GeoEntity` + `WindupAttackMob`，`registerGoals()` 中移除原版 `MeleeAttackGoal` 并改挂 `ChargingMeleeAttackGoal`，蓄力 13 tick 后扑击并把自身弹向目标
- **实体注册与生成** - `ModEntities.SPIDERLING`（`MobCategory.MONSTER`，0.55×0.35、`eyeHeight 0.15`、`notInPeaceful`）、`ModItems.SPIDERLING_SPAWN_EGG`、`EntitiesHandler` 中的属性（8 血 / 1 攻 / 0.25 速）与 `SpawnPlacementTypes.ON_GROUND` 生成规则、创造栏条目
- **GeckoLib 渲染** - `SpiderlingModel` / `SpiderlingRenderer` / `SpiderlingRenderState`，外加 `SpiderlingEyesLayer`（`AutoGlowingGeoLayer`，贴图 `textures/entity/spiderling_eyes.png`）
- **模型与贴图资源** - `geckolib/models/entity/spiderling.geo.json`、`geckolib/animations/entity/spiderling.animation.json`、`textures/entity/spiderling.png`、`textures/item/spiderling_spawn_egg.png`
- **战利品表** - `ModLootProvider` 生成两池：线 0~2 与蜘蛛眼 -1~1，均叠 `enchanted_count_increase`（抢夺），蜘蛛眼镜条件 `killed_by_player`，结构对齐原版 `entities/spider.json`
- **实体标签** - `ModEntityTypeTagsProvider` 追加 `arthropod` / `dismounts_underwater` / `no_anger_from_wind_charge`，与原版蜘蛛一致
- **音效字幕** - `ModSoundEvents` 新增 `entity.spiderling.ambient` / `.hurt` / `.death`，`sounds.json` 以 `type: "event"` 引用原版蜘蛛音源（与食人鱼 `flop` 同一做法），`Spiderling` 覆写 `getAmbientSound` / `getHurtSound` / `getDeathSound`
- **本地化** - `ZhEnCombineLangProvider` 追加实体名（`Spiderling` / `幼蛛`）与刷怪蛋名（`Spiderling Spawn Egg` / `幼蛛刷怪蛋`）；字幕键 `subtitles.entity.spiderling.*` 按原版文案格式补齐

### Changed
- **数据生成资源不手写** - 上游 diff 提交到 `src` 的 `assets/ww_hfas/items`、`models/item`、`lang`、`data/**` 均未带入，统一由 `runClientData` / `runServerData` 生成（与 PR #29 的资源去重约定一致）
- **字幕中文统一为原版格式** - 副标题由 `实体+动作`（`食人鱼袭击`）改为原版中文的 `实体：动作`（`食人鱼：袭击`、`幼蛛：嘶嘶`）；英文侧保持原版无冒号写法（`Piranha attacks`、`Spiderling hisses`），原版中英文本就不同构

### Fixed
- **`SpiderlingEyesLayer` 泛型编译失败** - `GeoRenderLayer` / `AutoGlowingGeoLayer` 要求 `R extends GeoRenderState`，而 `EntityRenderState` 上的该接口由 GeckoLib 运行时 mixin 注入，编译期 `LivingEntityRenderState` 不满足边界；图层改用 `GeoRenderState` 作为 `R`，注册处做一次等价强转（`@SuppressWarnings({"unchecked", "rawtypes"})`）并注明原因
- **属性翻译键前缀错误** - `RangedAttribute` 的 `descriptionId` 是构造参数原样保存，不按注册 id 派生；原值为 `attributes.ww_hfas.extra_item_pickup_range`，与 `Attributes` 中的 `attribute.name.armor` 规范不符，属性名会直接显示原始键。现已改为 `attribute.name.ww_hfas.extra_item_pickup_range`，`ModAttributes` 与 en_us / zh_cn 三处同步
- **字幕格式与原版不一致** - 原版 `entity.spider.step` 没有 subtitle（脚步声不产生字幕），此前为改字幕额外覆写 `playStepSound` 属多余，已连同 `entity.spiderling.step` 字幕一并移除

### Removed
- **脚步声字幕及其载体** - `ModSoundEvents.SPIDERLING_STEP`、`sounds.json` 的 `entity.spiderling.step` 条目、`Spiderling.playStepSound` 覆写；脚步声回落原版 `SoundEvents.SPIDER_STEP`，与原版蜘蛛行为一致
- **旧属性键** - `attributes.ww_hfas.extra_item_pickup_range`（en_us / zh_cn 各一条），随属性键修正移除

---

## [Unreleased] - 2026.10.30

### Added
- **砂岩 / 沙 / 花全家桶** - 从 `master/NeoForge-26.2`（`a40d1a4` 添加流沙 → `f5e4c7d` 注册WIP物品 → `ebd3762` 灵魂与石英全家桶）移植 21 种方块：`quicksand`、`suspicious_red_sand`、`soul_jack_o_lantern`、`chiseled` / `cut` / `nitrified` 砂岩八种、`quartz_sandstone`、`soul_sandstone`、`quartz_sand`、`quartz_quicksand`、`soul_quicksand`、`rose`、`soul_rose`、`soul_rose_bush`、`suspicious_quartz_sand`、`suspicious_soul_sand`
- **平滑砂岩** - `smooth_quartz_sandstone` / `smooth_soul_sandstone`，六面均为对应砂岩的顶面贴图（`quartz_sandstone_top` / `soul_sandstone_top`）、底面旋转 180 度；复用已有 `rotated_top_sandstone` 模板，不新增贴图。硝化系列不做平滑
- **`BlockEntityTypeMixin`** - 在 `BlockEntityType.isValid` 的 HEAD 放行自定义可疑沙，使它们能绑定原版 `BrushableBlockEntity`（可用刷子刷出物品）；`ww_hfas.mixins.json` 同步登记
- **砂岩模型生成辅助** - `ModModelProvider` 新增 `ROTATED_TOP_SANDSTONE` 模板与 `createSandstone` / `createRotatedTopSandstone` / `createVanillaTopBottomSandstone` / `createSmoothSandstone`，覆盖「侧面上顶底」「侧面自身上顶底」「顶底借用原版」「六面同图」四种贴图映射
- **战利品 / 标签 / 本地化** - `BlockLoot` 为 23 种新方块补掉落（三种可疑沙用 `createSilkTouchOnlyTable`）；方块标签补 `mineable_with_pickaxe` / `mineable_with_shovel` / `mineable_with_axe` / `piglin_repellents` / `small_flowers` / `flowers`；`en_us` / `zh_cn` 全量条目（WIP 物品保留上游的 `§4` 红色标记）
- **创造物品栏分类** - 模组 tab 内新方块改为按材质分组：石英岩类 / 灵魂岩类 / 硝化岩类 / 沙类 / 可疑沙类 / 花类 / 灯具类，类内按「基础 → 雕纹/切制 → 硝化 → 平滑」排列
- **模型与贴图资源** - 35 张方块贴图、`assets/ww_hfas/models/block/rotated_top_sandstone.json` 与 `BlockEntityTypeMixin.java` 自 `ebd3762` 原样检出，datagen 无法重建这三类文件

### Changed
- **方块标签注释格式** - `ModBlockTagsProvider` 的说明由「用锹挖掘」等形式改为类型式：制箭台 → 工作站类、可疑红沙 → 沙子类、灵魂南瓜灯 → 灯具类、石英沙/流沙/可疑沙 → 沙子类
- **数据生成资源不手写** - 模型、blockstate、战利品表、标签、语言文件均由 `runClientData` / `runServerData` 生成，未把上游提交到 `src` 的 `blockstates` / `models` / `data/**` 带入（与 PR #29 的资源去重约定一致）
- **移植取舍** - 上游 `f5e4c7d` 补挂 `BlockLoot` 的改动未搬，本分支 `ModLootProvider` 早已挂上；上游 `ModModelProvider` 仍为传 13~15 个参数的木材写法，本分支保持 `WoodFamily` / `ModWoodFamilies` 版

### Fixed
- **中文名用词** - `nitrified_red_sandstone` 由「硝化红色砂岩」改为「硝化红砂岩」；`suspicious_quartz_sand` / `suspicious_soul_sand` 由「可疑的…沙子」改为「可疑的…沙」

---

## [Unreleased] - 2026.11.02

### Added
- **三个独立创造标签页** - 拆分后每个模块各注册一页，不再挤在 core 的标签页里：`ww_core:wild_wind`（`tab.ww_core.name`，图标杜鹃木原木）、`ww_wilderness:wilderness`（`tab.ww_wilderness.name`，图标灵魂南瓜灯）、`ww_barren:barren`（`tab.ww_barren.name`，图标焚烬木原木）；三 jar 共存时三页并列，只装某一个时也只出现它自己那页
- **各模块向原版栏追加条目** - core（`groups/ModCreativeTabs`）：建筑方块（杜鹃木套件、玫瑰系列）与自然方块；wilderness（`groups/WildernessCreativeTabs`）：自然方块（灵魂南瓜灯）与工具与实用（蟹类桶、万用蟹钳）、食物与饮品、材料；barren（`groups/BarrenCreativeTabs`）：建筑方块与自然方块（焚烬木 / 灵焰木套件、流沙、石英沙、小型仙人球）
- **制箭台物品形态** - `ModBlocks` 用 `ITEMS.registerSimpleBlockItem(FLETCHING_TABLE)` 注册，方块进入创造栏并可放置
- **制箭台交互** - `FletchingTableBlock` 实现 `EntityBlock`，`useWithoutItem` 打开菜单；`getMenuProvider` 传入方块实体自身作为 `Container` 并带 `ContainerLevelAccess.create(level, pos)`
- **方块实体与结算** - `FletchingTableBlockEntity` 实现 `Container`（4 槽：箭羽 / 箭杆 / 箭头 / 产物，枚举 `Slots`），三格齐备且材料合法时 `updateResult` 生成 `ARROWS_PER_CRAFT = 4` 支箭，`consumeInputs` 在取走产物后各扣 1 并重算；数据用 `ValueOutput` / `ValueInput` 存档
- **制箭台菜单** - `FletchingTableMenu`：三个输入槽用 `FilteredSlot` 按 `ArrowFletching` / `ArrowShaft` / `ArrowHead.fromItem` 过滤，产物槽 `ResultSlot` 只取不放；槽位坐标沿用锻造台布局（(8,48)/(26,48)/(44,48)、产物 (98,48)、背包 (8,84) 起），`quickMoveStack` 以 `INV_START = CONTAINER_SIZE` 为界
- **客户端界面** - `CoreClientScreens` 监听 `RegisterMenuScreensEvent` 注册 `FletchingTableScreen`；界面直接引用原版 `minecraft:textures/gui/container/smithing.png`（不复制进 jar，避免再分发 Mojang 资源）
- **枚举名反序列化** - `ArrowFletching` / `ArrowShaft` / `ArrowHead` 新增 `fromName(String)`，`ArrowComponent` 的 `CODEC` / `STREAM_CODEC` 改用它

### Changed
- **原版制箭台放置替换** - 26.2 的 `BlockItem#place` 不再触发 `BlockEvent.EntityPlaceEvent`，改为 `BlockItemMixin` 在 `getPlacementState` 中用 `@ModifyExpressionValue` 把 `Blocks.FLETCHING_TABLE` 换成模组方块：放置校验、音效、消耗物品、方块实体创建仍走原版流程；`FletchingTableReplaceHandler` 只保留区块加载时替换旧世界方块
- **菜单连接真实容器** - 此前 `getMenuProvider` 用 `SimpleContainer`，界面与方块实体数据不同步（放进去的材料会没、产物不结算），改为传方块实体本身
- **制箭台界面标题** - `container.fletching_table` 由「制箭台 / Fletching Table」改为「制作箭 / Arrow Crafting」，与方块名区分；方块名仍为「制箭台 / Fletching Table」，不受影响
- **界面图标尺寸** - 左上角光灵箭图标由 16×16 放大到 24×24，居中于被遮盖的原版锤子区域（`ICON_SIZE` 常量控制），锤子仍被完全遮挡

### Fixed
- **制箭台无法获取与放置** - 方块缺少物品形态，创造栏里没有条目、也无法放下，已补 `registerSimpleBlockItem`
- **放下后变回原版制箭台 / 放下即消失** - 原先靠 `RightClickBlock` 事件延迟替换，存在回滚与进度统计问题，改为 mixin 直换 `getBlock()` 后一次性生成正确方块
- **槽位索引错乱** - `FletchingTableMenu` 构造时 `addSlot(this.addSlot(...))` 把玩家背包槽位重复登记，且容器尺寸与槽位布局不匹配；已按 4 槽容器 + 36 格背包重排
- **网络包解码失败导致打开界面崩溃** - `Failed to decode packet 'clientbound/minecraft:container_set_slot'`：`ArrowComponent` 的 `STREAM_CODEC` 用 `valueOf("feather")` 反序列化，而枚举常量为大写，抛 `IllegalArgumentException`；改走 `fromName` 后正常
- **打开界面无内容（白屏/原版界面）** - 未注册 `MenuScreen`，`MenuScreens` 拿不到构造器；补 `CoreClientScreens` 注册


