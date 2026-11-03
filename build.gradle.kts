import org.gradle.api.file.DuplicatesStrategy
import org.slf4j.event.Level.INFO
import java.util.Locale

plugins {
    `java-library`
    `maven-publish`
    id("net.neoforged.moddev") version ("2.0.141")
    idea
    base
}


//tasks.named<Wrapper>("wrapper").configure {
//    distributionType = Wrapper.DistributionType.BIN
//}

val minecraftVersion: String by rootProject
val minecraftVersionRange: String by rootProject
val modName: String by rootProject
val modLicense: String by rootProject

val geocklibVersion: String by rootProject

allprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    apply(plugin = "idea")
    apply(plugin = "net.neoforged.moddev")
    apply(plugin = "base")
    val modGroupId: String by project
    val modId: String by project
    var projectModId = rootProject.name + "_" + project.name.lowercase(Locale.ROOT)
    val modVersion: String by project
    val neoVersion: String by project
    if (project == rootProject) return@allprojects
    // 原野之风三份内容的显示名
    val moduleDisplayName: String = mapOf(
        "core" to "Wild Wind: Core",
        "wilderness" to "Wild Wind: Wilderness Update",
        "barren" to "Wild Wind: Barren Update",
    )[project.name] ?: modName
    // 非核心模块必须依赖核心模块
    val coreDependency: String = if (project.name == "core") "" else """
[[dependencies.${projectModId}]]
    modId="${rootProject.name}_core"
    type="required"
    versionRange="[${modVersion},)"
    ordering="AFTER"
    side="BOTH"
""".trimIndent()
    group = modGroupId
    version = modVersion
    configure<BasePluginExtension> {
        archivesName = projectModId

    }

    var generateModMetadata = tasks.register<ProcessResources>("generatedMetadata") {
        val replaceProperties = mapOf(
            "minecraft_version" to minecraftVersion,
            "minecraft_version_range" to minecraftVersionRange,
            "neo_version" to neoVersion,
            "mod_id" to projectModId,
            "mod_name" to moduleDisplayName,
            "mod_license" to modLicense,
            "mod_version" to modVersion,
            "geocklib_version" to geocklibVersion,
            "core_dependency" to coreDependency
        )
        inputs.properties(replaceProperties)
        expand(replaceProperties)
        from(rootProject.file("src/templates"))
        into("build/generated/sources/modMetadata")
    }


    sourceSets {
        rootProject.file("src/${project.name}/java").resolve(modGroupId.replace('.', '/')).mkdirs()
        main {
            java {
                srcDir(rootProject.file("src/${project.name}/java"))
            }
            resources {
                srcDir(generateModMetadata)
                srcDir(rootProject.file("build/generated/${project.name}/server"))
                srcDir(rootProject.file("build/generated/${project.name}/client"))
                srcDir(rootProject.file("src/${project.name}/resources"))

                exclude("**/*.bbmodel")
                // datagen 的 hash 缓存目录，位于 client/server 两个 srcDir 根下，
                // 同名 entry 会在 processResources 里触发 duplicate 错误，必须排除
                exclude("**/.cache/**")
                exclude(".cache/**")
            }
        }
    }

    repositories {
        exclusiveContent {
            forRepository {
                maven {
                    name = "GeckoLib"
                    url = uri("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/")
                }
            }

            filter {
                includeGroupAndSubgroups("com.geckolib")
            }
        }
    }

    java.toolchain.languageVersion = JavaLanguageVersion.of(25)

    // Gradle 9 对资源重复项默认报错；保持既有语义：先生成的 srcDir（build/generated）优先
    tasks.withType<ProcessResources>().configureEach {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    neoForge {
        version = neoVersion

        accessTransformers.from(rootProject.file("src/${project.name}/resources/META-INF/accesstransformer.cfg"))

        runs {
            register("client") {
                client()
                systemProperty("neoforge.enabledGameTestNamespaces", projectModId)
                gameDirectory = layout.buildDirectory.dir("runs/${project.name}/client").get().asFile
            }
            register("server") {
                server()
                programArgument("--nogui")
                systemProperty("neoforge.enabledGameTestNamespaces", projectModId)
                gameDirectory = layout.buildDirectory.dir("runs/${project.name}/server").get().asFile
            }
            register("clientData") {
                clientData()
                gameDirectory = layout.buildDirectory.dir("runs/${project.name}/datagen").get().asFile
                programArguments.addAll(
                    listOf(
                        "--mod", projectModId,
                        "--all",
                        "--output", rootProject.file("build/generated/${project.name}/client").absolutePath,
                        "--existing", rootProject.file("src/${project.name}/resources").absolutePath,
                    )
                )
            }
            register("serverData") {
                serverData()
                gameDirectory = layout.buildDirectory.dir("runs/${project.name}/datagen").get().asFile
                programArguments.addAll(
                    listOf(
                        "--mod", projectModId,
                        "--all",
                        "--output", rootProject.file("build/generated/${project.name}/server").absolutePath,
                        "--existing", rootProject.file("src/${project.name}/resources").absolutePath,
                    )
                )
            }
            configureEach {
                systemProperty("forge.logging.markers", "REGISTRIES")
                logLevel.set(INFO)
            }
        }

        mods {
            register(projectModId) {
                sourceSet(sourceSets["main"])
            }
        }


    }

    neoForge.ideSyncTask(generateModMetadata)

    val localRuntime by configurations.registering

    configurations {
        runtimeClasspath {
            extendsFrom(localRuntime.get())
        }
    }

    dependencies {
        compileOnly("org.projectlombok:lombok:1.18.44")
        annotationProcessor("org.projectlombok:lombok:1.18.44")
        implementation("com.geckolib:geckolib-neoforge-26.2:5.5.4")
        // 荒野 / 瘠土更新都建立在核心之上
        if (project.name != "core") {
            implementation(project(":core"))
        }
        // 开发期联调：瘠土更新的 run 额外加载荒野更新；只在本地运行时生效，不进入发布 jar
        if (project.name == "barren") {
            add("localRuntime", project(":wilderness"))
        }
    }

    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
    }

    idea {
        module {
            isDownloadJavadoc = true
            isDownloadSources = true
        }
    }
}
