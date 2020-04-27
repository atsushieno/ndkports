package com.android.ndkports

import java.io.File

object : AutoconfPort() {
    override val name = "libogg"
    override val version = "1.3.4"
    override val mavenVersion = "$version-aap-1"
    override val prefabVersion = CMakeCompatibleVersion(1, 1, 1, 1)

    override val licensePath: String = "COPYING"

    override val license = License(
        "BSD-like License",
        "https://svn.xiph.org/trunk/ogg/COPYING"
    )

    override val modules = listOf(
        Module("ogg")
    )
}
