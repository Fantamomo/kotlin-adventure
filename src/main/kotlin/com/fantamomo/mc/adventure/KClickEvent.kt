@file:Suppress("NOTHING_TO_INLINE")

package com.fantamomo.mc.adventure

import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.event.ClickCallback
import net.kyori.adventure.text.event.ClickEvent
import java.net.URL
import java.time.temporal.TemporalAmount
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

sealed interface KClickEventType<T : KClickEventType<T, B>, B : KClickEventBuilder<T, B>> {
    data object OpenUrl : KClickEventType<OpenUrl, KClickEventBuilder.OpenUrl> {
        override fun builder() = KClickEventBuilder.OpenUrl()
    }

    data object OpenFile : KClickEventType<OpenFile, KClickEventBuilder.OpenFile> {
        override fun builder() = KClickEventBuilder.OpenFile()
    }
    data object RunCommand : KClickEventType<RunCommand, KClickEventBuilder.RunCommand> {
        override fun builder() = KClickEventBuilder.RunCommand()
    }
    data object SuggestCommand : KClickEventType<SuggestCommand, KClickEventBuilder.SuggestCommand> {
        override fun builder() = KClickEventBuilder.SuggestCommand()
    }
    data object ChangePage : KClickEventType<ChangePage, KClickEventBuilder.ChangePage> {
        override fun builder() = KClickEventBuilder.ChangePage()
    }
    data object CopyToClipboard : KClickEventType<CopyToClipboard, KClickEventBuilder.CopyToClipboard> {
        override fun builder() = KClickEventBuilder.CopyToClipboard()
    }
    data object Callback : KClickEventType<Callback, KClickEventBuilder.Callback> {
        override fun builder() = KClickEventBuilder.Callback()
    }

    fun builder(): B
}

@ComponentDsl
sealed interface KClickEventBuilder<T : KClickEventType<T, B>, B : KClickEventBuilder<T, B>> {
    class OpenUrl : KClickEventBuilder<KClickEventType.OpenUrl, OpenUrl> {
        private lateinit var url: String

        fun url(url: String) {
            this.url = url
        }

        fun url(url: URL) {
            this.url = url.toExternalForm()
        }

        override fun buildClickEvent() = ClickEvent.openUrl(url)
    }

    class OpenFile : KClickEventBuilder<KClickEventType.OpenFile, OpenFile> {
        private lateinit var path: String

        fun path(path: String) {
            this.path = path
        }

        override fun buildClickEvent() = ClickEvent.openFile(path)
    }

    class RunCommand : KClickEventBuilder<KClickEventType.RunCommand, RunCommand> {
        private lateinit var command: String

        fun command(command: String) {
            this.command = command
        }

        override fun buildClickEvent() = ClickEvent.runCommand(command)
    }

    class SuggestCommand : KClickEventBuilder<KClickEventType.SuggestCommand, SuggestCommand> {
        private lateinit var command: String

        fun command(command: String) {
            this.command = command
        }

        override fun buildClickEvent() = ClickEvent.suggestCommand(command)
    }

    class ChangePage : KClickEventBuilder<KClickEventType.ChangePage, ChangePage> {
        private lateinit var page: String

        fun page(page: String) {
            this.page = page
        }

        fun page(page: Int) {
            this.page = page.toString()
        }

        override fun buildClickEvent() = ClickEvent.changePage(page)
    }

    class CopyToClipboard : KClickEventBuilder<KClickEventType.CopyToClipboard, CopyToClipboard> {
        private lateinit var text: String

        fun text(text: String) {
            this.text = text
        }

        override fun buildClickEvent() = ClickEvent.copyToClipboard(text)
    }

    class Callback : KClickEventBuilder<KClickEventType.Callback, Callback> {
        private lateinit var callback: (Audience) -> Unit
        private var options = ClickCallback.Options.builder()

        fun callback(callback: (Audience) -> Unit) {
            this.callback = callback
        }

        fun uses(count: Int) {
            options.uses(count)
        }

        fun lifetime(duration: TemporalAmount) {
            options.lifetime(duration)
        }

        override fun buildClickEvent() = ClickEvent.callback(callback, options.build())
    }


    fun buildClickEvent(): ClickEvent
}

inline fun KStyleable<*>.clickEvent(clickEvent: ClickEvent) {
    builder.clickEvent(clickEvent)
}

@OptIn(ExperimentalContracts::class)
inline fun <T : KClickEventType<T, B>, B : KClickEventBuilder<T, B>> KStyleable<*>.clickEvent(type: T, block: B.() -> Unit) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    val clickEvent = type.builder()
    clickEvent.block()
    this.builder.clickEvent(clickEvent.buildClickEvent())
}