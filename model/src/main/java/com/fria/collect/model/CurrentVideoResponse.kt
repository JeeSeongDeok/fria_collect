package com.fria.collect.model


data class CurrentVideoResponse(
    val kind: String,
    val etag: String,
    val nextPageToken: String?,
    val prevPageToken: String?,
    val regionCode: String?,
    val pageInfo: PageInfo,
    val items: List<SearchResult>?
)

data class PageInfo(
    val totalResults: Int,
    val resultsPerPage: Int
)

data class SearchResult(
    val kind: String,
    val etag: String,
    val id: VideoId,
    val snippet: VideoSnippet
)

data class VideoId(
    val kind: String,
    val videoId: String
)

data class VideoSnippet(
    val publishedAt: String,
    val channelId: String,
    val title: String,
    val description: String,
    val thumbnails: ThumbnailDetails,
    val channelTitle: String,
    val liveBroadcastContent: String
)

data class ThumbnailDetails(
    val default: Thumbnail,
    val medium: Thumbnail,
    val high: Thumbnail
)

data class Thumbnail(
    val url: String,
    val width: Int,
    val height: Int
)