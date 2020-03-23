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

object : AutoconfPort() {
    override val name = "libiconv"
    override val version = "1.16"
    override val mavenVersion = "$version-alpha-1"
    override val prefabVersion = CMakeCompatibleVersion(1, 1, 1, 4)
    override val licensePath = "COPYING.LIB"

    override val license = License(
        "LGPL v2.0",
        //"https://git.savannah.gnu.org/gitweb/?p=libiconv.git;a=blob;f=COPYING.LIB;h=778d0bb5b2a459f6f28ccb2e8aad409c2b497e50;hb=HEAD"
	"https://www.gnu.org/licenses/old-licenses/lgpl-2.0.html"
    )

    override val modules = listOf(
        Module("iconv")
    )
}
