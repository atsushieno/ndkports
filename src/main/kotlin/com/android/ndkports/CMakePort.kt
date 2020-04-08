/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.ndkports

import java.io.File

abstract class CMakePort : Port() {

    val cmakeAndroidCfg
        get() = System.getenv("ANDROID_SDK_ROOT") + "/cmake/3.10.2.4988404/android.toolchain.cmake"

    open fun configureEnv(
        workingDirectory: File,
        toolchain: Toolchain
    ): Map<String, String> = emptyMap()

    override fun configure(
        toolchain: Toolchain,
        sourceDirectory: File,
        buildDirectory: File,
        installDirectory: File,
        workingDirectory: File
    ): Result<Unit, String> {
        buildDirectory.mkdirs()
        return executeProcessStep(
            listOf(
                "cmake",
                "-DCMAKE_TOOLCHAIN_FILE=" + toolchain.ndk.path.getAbsolutePath() + "/build/cmake/android.toolchain.cmake",
                "-DANDROID_ABI=" + toolchain.abi.abiName,
                "-DANDROID_NATIVE_API_LEVEL=" + "android-29",
                "-DCMAKE_INSTALL_PREFIX=" + installDirectory.getAbsolutePath(),
                sourceDirectory.getAbsolutePath()
            ),
            buildDirectory,
            additionalEnvironment = mutableMapOf(
                "AR" to toolchain.ar.absolutePath,
                "CC" to toolchain.clang.absolutePath,
                "CXX" to toolchain.clangxx.absolutePath,
                "RANLIB" to toolchain.ranlib.absolutePath,
                "STRIP" to toolchain.strip.absolutePath,
                "PATH" to "${toolchain.binDir}:${System.getenv("PATH")}"
            ).apply { putAll(configureEnv(workingDirectory, toolchain)) }
        )
    }

    override fun build(
        toolchain: Toolchain,
        buildDirectory: File
    ): Result<Unit, String> =
        executeProcessStep(
            listOf("make", "-j$ncpus"), buildDirectory
        )

    override fun install(
        toolchain: Toolchain,
        buildDirectory: File,
        installDirectory: File
    ): Result<Unit, String> =
        executeProcessStep(
            listOf("make", "-j$ncpus", "install"), buildDirectory
        )
}
