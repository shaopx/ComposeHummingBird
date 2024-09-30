package com.spx.hummingbird.modules.posts.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoPostsResult(
    val code: String,
    val msg: String,
    val expires: Int,
    val data: PhotoPostsResultData
)

@Serializable
data class PhotoPostsResultData(
    val forumName: String?,
    val shareUrl: String,
    val list: List<PhotoPostsItem>
)

@Serializable
data class PhotoPostsItem(
    val threadId: String,
    val userId: String?,
    val username: String?,
    val userTitle: String?,
    val usergroupid: String?,
    val headPic: String,
    val title: String,
    val picTotal: String?,
    val replycount: String,
    val time: String?,
    val vipType: Int?,
    val picInfo: List<PicInfo>?,
    val tag: List<TagInfo>?,
    val isFollow: Int?,
    val isZan: Boolean?,
    val execpostGrade: String?,
    val zanCount: Int?
)

@Serializable
data class PicInfo(
    val w: Int,
    val h: Int,
    val url: String,
    @SerialName("url_enlargement")
    val urlEnlargement: String
)

@Serializable
data class TagInfo(
    val labelid: String,
    val labelName: String
)