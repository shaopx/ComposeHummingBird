package com.spx.hummingbird.modules.news.model

import kotlinx.serialization.Serializable

@Serializable
data class DocDetailInfoResult(
    val code: String,
    val msg: String,
    val expires: Int,
    val data: RootData
)

@Serializable
data class DocDetailInfo(
    val documentId: String,
    val classId: String,
    val subclassId: String,
    val grandSubclassId: String,
    val nextGrandclassId: String,
    val producer: String,
    val producerLink: String,
    val isOriginal: String,
    val docTypeId: String,
    val zDeeply: String,
    val checkFlag: String,
    val docLevel: String,
    val isFee: String,
    val isNoComment: String,
    val author: String,
    val pictureId: String,
    val title: String,
    val shortTitle: String,
    val subTitle: String,
    val secondTitle: String,
    val digest: String?,
    val keywords: String,
    val producerId: String,
    val status: String,
    val published: String,
    val pageNum: String,
    val isIdp: String,
    val idpUrl: String,
    val username: String,
    val dutyEditor: String,
    val lastUsername: String,
    val charNum: String,
    val picNum: String,
    val fatherId: String,
    val rootId: String,
    val property: String,
    val cityId: String,
    val mediaType: String,
    val isNoPub: String,
    val date: String,
    val pubDate: String,
    val firstPubDate: String,
    val dateline: String,
    val lastDate: String,
    val subcateClassId: String
)

@Serializable
data class DocZanCount(
    val goodHits: Int,
    val badHits: Int
)

@Serializable
data class RootData(
    val docInfo: DocDetailInfo,
    val c: String,
    val newReply: List<String>,
    val hotReply: List<String>,
    val commentCount: String,
    val isCollect: Int,
    val docZanCount: DocZanCount,
    val userId: Int,
    val docId: Int,
    val limitC: Int,
    val pageCountC: Int,
    val adInfo: List<String>
)

@Serializable
data class ApiResponse(
    val code: String,
    val msg: String,
    val expires: Long,
    val data: RootData
)