/**
/**
 * Created by ls.hernandez10 on 14/11/2016.
 */
(function (ng) {

    var mod = ng.module("loginModule");

// the list controller
    mod.controller("loginCtrl", ["$scope", "$resource", function($scope)
    {
         console.log("Entro login controller");

        // var ref = new Firebase("https://oilcol-17ece.firebaseio.com");


        $scope.signIn = function() {
            console.log("Entra al método singIn");
            var email = $scope.username;
            var password = $scope.password;
            if (!email || !password) {
                return console.log('username and password required');
            }
            // Sign in user
            firebase.auth().signInWithEmailAndPassword(email, password)
                .catch(function(error) {
                    // Handle Errors here.
                    console.log("Hay error con el login");
                    var errorCode = error.code;
                    var errorMessage = error.message;
                    console.log('signIn error', error);
                    // ...
                });
        };

        $scope.register = function() {
            var email = $scope.username;
            var password = $scope.password;
            if (!email || !password) {
                return console.log('email and password required');
            }
            // Register user
            firebase.auth().createUserWithEmailAndPassword(email, password)
                .catch(function(error) {
                    console.log('register error', error);
                    if (error.code === 'auth/email-already-in-use') {
                        var credential = firebase.auth.EmailAuthProvider.credential(email, password);
                        app.signInWithGoogle()
                            .then(function() {
                                firebase.auth().currentUser.link(credential)
                                    .then(function(user) {
                                        console.log("Account linking success", user);
                                    }, function(error) {
                                        console.log("Account linking error", error);
                                    });
                            });
                    }
                });
        };

        $scope.signInWithGoogle = function() {
            // Sign in with Google
            var provider = new firebase.auth.GoogleAuthProvider();
            provider.addScope('profile');
            provider.addScope('email');
            return firebase.auth().signInWithPopup(provider)
                .catch(function(error) {
                    console.log('Google sign in error', error);
                });
        };

        $scope.signOut = function() {
        // Sign out
        firebase.auth().signOut();
                };

        // Listen to auth state changes
        firebase.auth().onAuthStateChanged(function(user) {
            console.log("Entra a cambiar el estado")
            $scope.user = user;
            console.log('user', user);
        });

        /*
        * Copyright 2016 Google Inc. All Rights Reserved.
    *
    * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
    * in compliance with the License. You may obtain a copy of the License at
    *
    * http://www.apache.org/licenses/LICENSE-2.0
        *
        * Unless required by applicable law or agreed to in writing, software distributed under the
    * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
    * express or implied. See the License for the specific language governing permissions and
    * limitations under the License.
    */

        /**
         * FirebaseUI initialization to be used in a Single Page application context.
         */
// FirebaseUI config.
        var uiConfig = {
            'callbacks': {
                // Called when the user has been successfully signed in.
                'signInSuccess': function(user, credential, redirectUrl) {
                    handleSignedInUser(user);
                    // Do not redirect.
                    return false;
                }
            },
            // Opens IDP Providers sign-in flow in a popup.
            'signInFlow': 'popup',
            'signInOptions': [
                // TODO(developer): Remove the providers you don't need for your app.
                {
                    provider: firebase.auth.GoogleAuthProvider.PROVIDER_ID,
                    scopes: ['https://www.googleapis.com/auth/plus.login']
                },
                firebase.auth.EmailAuthProvider.PROVIDER_ID
            ],
            // Terms of service url.
            'tosUrl': 'https://www.google.com'
        };

// Initialize the FirebaseUI Widget using Firebase.
        var ui = new firebaseui.auth.AuthUI(firebase.auth());
// Keep track of the currently signed in user.
        var currentUid = null;

        /**
         * Redirects to the FirebaseUI widget.
         */
        var signInWithRedirect = function() {
            window.location.assign('/widget');
        };


        /**
         * Open a popup with the FirebaseUI widget.
         */
        var signInWithPopup = function() {
            window.open('/widget', 'Sign In', 'width=985,height=735');
        };


        /**
         * Displays the UI for a signed in user.
         * @param {!firebase.User} user
         */
        var handleSignedInUser = function(user) {
            currentUid = user.uid;
            document.getElementById('user-signed-in').style.display = 'block';
            document.getElementById('user-signed-out').style.display = 'none';
            document.getElementById('name').textContent = user.displayName;
            document.getElementById('email').textContent = user.email;
            if (user.photoURL){
                document.getElementById('photo').src = user.photoURL;
                document.getElementById('photo').style.display = 'block';
            } else {
                document.getElementById('photo').style.display = 'none';
            }
        };


        /**
         * Displays the UI for a signed out user.
         */
        var handleSignedOutUser = function() {
            document.getElementById('user-signed-in').style.display = 'none';
            document.getElementById('user-signed-out').style.display = 'block';
            ui.start('#firebaseui-container', uiConfig);
        };

// Listen to change in auth state so it displays the correct UI for when
// the user is signed in or not.
        //firebase.auth().onAuthStateChanged(function(user) {
            // The observer is also triggered when the user's token has expired and is
            // automatically refreshed. In that case, the user hasn't changed so we should
            // not update the UI.
        //    if (user && user.uid == currentUid) {
           //     return;
         //   }
        //    document.getElementById('loading').style.display = 'none';
        //    document.getElementById('loaded').style.display = 'block';
       //     user ? handleSignedInUser(user) : handleSignedOutUser();
       // });

        /**
         * Deletes the user's account.
         */
        var deleteAccount = function() {
            firebase.auth().currentUser.delete().catch(function(error) {
                if (error.code == 'auth/requires-recent-login') {
                    // The user's credential is too old. She needs to sign in again.
                    firebase.auth().signOut().then(function() {
                        // The timeout allows the message to be displayed after the UI has
                        // changed to the signed out state.
                        setTimeout(function() {
                            alert('Please sign in again to delete your account.');
                        }, 1);
                    });
                }
            });
        };


        /**
         * Initializes the app.
         */
        var initApp = function() {
            document.getElementById('sign-in-with-redirect').addEventListener(
                'click', signInWithRedirect);
            document.getElementById('sign-in-with-popup').addEventListener(
                'click', signInWithPopup);
            document.getElementById('sign-out').addEventListener('click', function() {
                firebase.auth().signOut();
            });
            document.getElementById('delete-account').addEventListener(
                'click', function() {
                    deleteAccount();
                });
        };

        window.addEventListener('load', initApp);


    }]);
})(window.angular)