package com.android.ndkports

import java.io.File

// The port is based on Cerbero libffi recipe + patch.
// https://gitlab.freedesktop.org/gstreamer/cerbero/-/tree/master/recipes
// Note that the latest libffi is 3.3 and 3.3-rc0 is > 1 year behind.
object : MesonPort() {
    override val name = "libffi"
    override val version = "3.3-rc0"
    override val mavenVersion = "$version-alpha-1"
    override val prefabVersion = CMakeCompatibleVersion(1, 1, 1, 4)

    override val license = License(
        "BSD-like License",
        "https://github.com/libffi/libffi/blob/master/LICENSE"
    )

    override val modules = listOf(
        Module("ffi")
    )
}
