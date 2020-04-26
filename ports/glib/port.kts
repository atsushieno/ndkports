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

// The port is based on Cerbero libffi recipe + patch.
// https://gitlab.freedesktop.org/gstreamer/cerbero/-/tree/master/recipes
// Note that the latest libffi is 3.3 and 3.3-rc0 is > 1 year behind.
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