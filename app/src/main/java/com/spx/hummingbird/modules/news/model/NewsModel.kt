package com.spx.hummingbird.modules.news.model

import kotlinx.serialization.Serializable

@Serializable
data class NewsResult(
    val code: String,
    val msg: String,
    val expires: Int,
    val data: ResultData
)

@Serializable
data class ResultData(
    val channelInfo: List<ChannelInfo>? = null,
    val focusInfo: List<FocusInfo>? = null,
    val topInfo: List<TopInfo>? = null,
    val docLists: List<DocInfo>? = null
)

interface News

@Serializable
data class ChannelInfo(
    val channelId: String,
    val channelName: String,
    val channelType: String
):News

@Serializable
data class FocusInfo(
    val docType: Int,
    val docId: String? = null,
    val picUrl: String? = null,
    val jumpUrl: String? = null
):News

@Serializable
data class TopInfo(
    val docType: String? = null,
    val docId: String? = null,
    val docTitle: String? = null,
    val jumpUrl: String? = null
):News

@Serializable
data class DocInfo(
    val docId: String? = null,
    val docTitle: String,
    val docDate: String? = null,
    val docImageUrl: List<String>? = null,
    val videoHour: String? = null,
    val videoMin: String? = null,
    val videoSecond: String? = null,
    val videoUrl: String? = null,
    val docCommentNum: String? = null,
    val docPVNum: String? = null,
    val className: String? = null,
    val author: String? = null,
    val docType: Int,
    val username: String? = null,
    val authorHeadPic: String? = null,
    val jumpUrl: String? = null
):News

