package com.android.ndkports

import java.io.File

object : MesonPort() {
    override val name = "glib"
    override val version = "2.60.6"
    override val mavenVersion = "$version-alpha-1"
    override val prefabVersion = CMakeCompatibleVersion(2, 60, 6, 0)

    override val licensePath: String = "COPYING"

    override val license = License(
        "LGPL v2.1",
        "https://gitlab.gnome.org/GNOME/glib/-/blob/master/COPYING"
    )

    override val dependencies = listOf("libffi")

    override val otherOptions = listOf("-Dinternal_pcre=true", "-Dlibmount=false", "-Ddtrace=false", "-Diconv=auto", "-Dc_link_args=-lm -lz")

    override val modules = listOf(
        Module("gobject-2.0"),
        Module("gmodule-2.0"),
        Module("glib-2.0"),
        Module("gio-2.0"),
        Module("gthread-2.0")
    )
}
