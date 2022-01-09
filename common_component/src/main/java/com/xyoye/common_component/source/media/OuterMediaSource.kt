package com.xyoye.common_component.source.media

import com.xyoye.common_component.source.inter.ExtraSource
import com.xyoye.common_component.source.inter.VideoSource
import com.xyoye.common_component.utils.PlayHistoryUtils
import com.xyoye.common_component.utils.getFileName
import com.xyoye.data_component.enums.MediaType

/**
 * Created by xyoye on 2021/11/21.
 */

class OuterMediaSource private constructor(
    private val videoUrl: String,
    private val currentPosition: Long,
    private var danmuPath: String?,
    private var episodeId: Int,
    private var subtitlePath: String?,
) : VideoSource, ExtraSource {

    companion object {
        suspend fun build(videoUrl: String): OuterMediaSource {
            val history = PlayHistoryUtils.getPlayHistory(videoUrl, MediaType.OTHER_STORAGE)

            return OuterMediaSource(
                videoUrl,
                history?.videoPosition ?: 0L,
                history?.danmuPath,
                history?.episodeId ?: 0,
                history?.subtitlePath
            )
        }
    }

    override fun getDanmuPath(): String? {
        return danmuPath
    }

    override fun setDanmuPath(path: String) {
        danmuPath = path
    }

    override fun getEpisodeId(): Int {
        return episodeId
    }

    override fun setEpisodeId(id: Int) {
        episodeId = id
    }

    override fun getSubtitlePath(): String? {
        return subtitlePath
    }

    override fun setSubtitlePath(path: String) {
        subtitlePath = path
    }

    override fun getVideoUrl(): String {
        return videoUrl
    }

    override fun getVideoTitle(): String {
        return getFileName(videoUrl)
    }

    override fun getCurrentPosition(): Long {
        return currentPosition
    }

    override fun getMediaType(): MediaType {
        return MediaType.OTHER_STORAGE
    }

    override fun getHttpHeader(): Map<String, String>? {
        return null
    }

    override fun getUniqueKey(): String {
        return getVideoUrl()
    }

}