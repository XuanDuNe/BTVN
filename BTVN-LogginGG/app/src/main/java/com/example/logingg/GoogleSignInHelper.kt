package com.example.logingg

import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class GoogleSignInHelper(context: Context) {
    private val auth = FirebaseAuth.getInstance()
    private val googleSignInClient: GoogleSignInClient
    private val TAG = "GoogleSignInHelper"

    init {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("221670071384-hn8ua954j8c77l95hasmnvdcnhp75jgi.apps.googleusercontent.com")
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(context, gso)
    }

    fun getSignInIntent(): Intent {
        Log.d(TAG, "Getting sign-in intent")
        return googleSignInClient.signInIntent
    }

    fun handleSignInResult(
        data: Intent?,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        if (data == null) {
            onError("Sign-in data is null")
            return
        }

        try {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)

            account?.idToken?.let { token ->
                Log.d(TAG, "ID Token received: ${token.take(10)}...")
                val credential = GoogleAuthProvider.getCredential(token, null)

                auth.signInWithCredential(credential)
                    .addOnCompleteListener { authTask ->
                        if (authTask.isSuccessful) {
                            val user = auth.currentUser
                            Log.d(TAG, "Sign-in successful: ${user?.email}")
                            onSuccess(user?.email ?: "No Email")
                        } else {
                            val errorMsg = authTask.exception?.message ?: "Unknown authentication error"
                            Log.e(TAG, "Authentication failed: $errorMsg")
                            onError(errorMsg)
                        }
                    }
            } ?: run {
                val errorMsg = "Google Sign-In failed: ID token is null"
                Log.e(TAG, errorMsg)
                onError(errorMsg)
            }
        } catch (e: ApiException) {
            val errorMsg = "Google sign-in failed: ${e.statusCode} - ${e.message}"
            Log.e(TAG, errorMsg, e)
            onError(errorMsg)
        }
    }

    fun signOut() {
        auth.signOut()
        googleSignInClient.signOut().addOnCompleteListener {
            Log.d(TAG, "User signed out from Google and Firebase")
        }
    }
}