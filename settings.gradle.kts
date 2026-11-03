pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version("1.0.0")
}

// 0 = core（原野之风：核心）
// 1 = wilderness（原野之风：荒野更新）
// 2 = barren（原野之风：瘠土更新）
val let = listOf("core", "wilderness", "barren")
let.forEach {
    include(it)

    var f = file("modules/$it")
    f.mkdirs()
    project(":$it").projectDir = f
}


rootProject.name = "ww"
