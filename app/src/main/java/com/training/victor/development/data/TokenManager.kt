package com.training.victor.development.data

import com.training.victor.development.data.models.TokenViewModel

class TokenManager {
    // Values retrieved from https://developers.shutterstock.com/images/apis/get/images/search
    companion object {
        const val TOKEN_TYPE = "Bearer"
        const val TOKEN_VALUE = "1/eyJjbGllbnRfaWQiOiIyMGFhMWMwYjM2ZGM4NmEyZjliMyIsInJlYWxtIjoiY3VzdG9tZXIiLCJzY29wZSI6InVzZXIudmlldyBjb2xsZWN0aW9ucy52aWV3IiwidXR2IjoiUVBkMyIsInVzZXJuYW1lIjoidnBhbG1hY2FycmFzY285OCIsInVzZXJfaWQiOjIyMTYxNTc4MSwib3JnYW5pemF0aW9uX2lkIjpudWxsLCJvcmdhbml6YXRpb25fdXNlcl9pZCI6bnVsbCwicGFyZW50X29yZ2FuaXphdGlvbl9pZHMiOltdLCJjdXN0b21lcl9pZCI6MjIxNjE1NzgxLCJleHAiOjE1NDUwODgzNTN9.WHHxP1U7vfexwTst4g6nU8olIfDgEWk05FBTDXpkhfKM4DF48f-bT1ZUHrzRaLg0Ee7_kLM9g3qhA13OdD32Yw"
    }

    val sessionToken = TokenViewModel(TOKEN_TYPE, TOKEN_VALUE)
}