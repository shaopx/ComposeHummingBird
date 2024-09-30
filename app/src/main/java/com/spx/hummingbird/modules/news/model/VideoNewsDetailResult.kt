package com.spx.hummingbird.modules.news.model

import kotlinx.serialization.Serializable

@Serializable
data class VideoNewsDetailResult(
    val code: String,
    val msg: String,
    val expires: Long,
    val data: VideoNewsDetailData
)

@Serializable
data class VideoNewsDetailData(
    val videoInfo: VideoInfo,
    val recommendInfo: List<RecommendInfo>,
    val adInfo: AdInfo
)

@Serializable
data class VideoInfo(
    val docId: Int,
    val title: String,
    val desc: String,
    val imageUrl: String,
    val videoUrl: String,
    val author: String,
    val views: Int,
    val countComment: String,
    val dateline: String,
    val replyCount: String,
    val isCollect: Int,
    val isNoComment: String,
    val zanCount: Int,
    val isZan: Int,
    val className: String
)

@Serializable
data class RecommendInfo(
    val docId: String,
    val title: String,
    val videoTime: String,
    val imageUrl: String
)

@Serializable
data class AdInfo(
    val imageUrl: String,
    val jumpUrl: String
)
