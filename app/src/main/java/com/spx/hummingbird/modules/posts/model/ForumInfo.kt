package com.spx.hummingbird.modules.posts.model

import kotlinx.serialization.Serializable

@Serializable
data class ForumInfoResult(
    val code: String,
    val msg: String,
    val expires: Long,
    val data: ForumInfoData
)

@Serializable
data class ForumInfoData(
    val channelInfo: List<ChannelInfo>,
    val index: List<Index>,
    val forums: List<ForumGroup>,
    val forumName: String?
)

@Serializable
data class ChannelInfo(
    val channelId: String,
    val channelName: String,
    val channelType: String
)

@Serializable
data class Index(
    val forumid: String,
    val name: String
)

@Serializable
data class ForumGroup(
    val title: String,
    val forum: List<Forum>
)

@Serializable
data class Forum(
    val forumid: Int,
    val name: String
)
