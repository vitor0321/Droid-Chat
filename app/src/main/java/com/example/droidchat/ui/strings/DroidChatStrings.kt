package com.example.droidchat.ui.strings

import cafe.adriel.lyricist.LyricistStrings

@LyricistStrings(languageTag = "pt")
internal val strings: DroidChatStrings = DroidChatStrings()

internal data class DroidChatStrings(
    val commonStrings: CommonStrings = CommonStrings(),
    val errorMessagesStrings: ErrorMessagesStrings = ErrorMessagesStrings(),
    val signInStrings: SignInStrings = SignInStrings(),
    val signUpStrings: SignUpStrings = SignUpStrings(),
    val splashStrings: SplashStrings = SplashStrings(),
)