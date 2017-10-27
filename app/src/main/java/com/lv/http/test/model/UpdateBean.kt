package com.lv.http.test.model

/**
 * Date: 2017-10-27
 * Time: 16:56
 * Description:
 */
data class UpdateBean(val version_name: String,
                      val new_version: String,
                      val content: String,
                      val gmt_create: String,
                      val ver_version: String,
                      val least_version: String,
                      val is_force_update: String,
                      val url: String)