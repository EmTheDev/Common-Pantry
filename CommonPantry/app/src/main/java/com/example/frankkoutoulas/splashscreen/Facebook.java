package com.example.frankkoutoulas.splashscreen;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONException;
import org.json.JSONObject;

/** This class adds a "login into Facebook" icon on the Recipe WebView.
 * From there it launches Facebook, and you can share the link for the recipe you were just viewing
 * and any comments you may have. You can also access your profile.
 *
 * @author Kristina Milkovich
 * @author Team 6 (secondary)
 * @version beta test 12/7/2015
 */
/* Resources/Citations
http://code2care.org/2015/facebook-android-sdk-v4.1.0-integration-android-studio
http://www.integratingstuff.com/2010/10/14/integrating-facebook-into-an-android-application/
http://www.androidhive.info/2012/03/android-facebook-connect-tutorial/

*/

public class Facebook extends Fragment {
    CallbackManager callbackManager;
    Button share, details;
    ShareDialog shareDialog;
    LoginButton login;
    ProfilePictureView profile;
    Dialog details_dialog;
    TextView details_txt;

    private View mView;
    Context context;
    Activity activity;

    String url = "";
    /** Creates and allows re-creation of the Facebook Activity.
     * Initializes Facebook SDK, uses the LoginManager
     *
     * @param savedInstanceState uses the bundle to pass recipe url from ingredients to share listener
     *
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getActivity());
        callbackManager = CallbackManager.Factory.create();

        mView = inflater.inflate(R.layout.activity_facebook, container, false);
        login = (LoginButton) mView.findViewById(R.id.login_button);
        login.setReadPermissions("public_profile email");
        login.setFragment(this);

        //final String url = super.getArguments().getString("url");


        shareDialog = new ShareDialog(this);
        share = (Button) mView.findViewById(R.id.share);
        details = (Button) mView.findViewById(R.id.details);
        profile = (ProfilePictureView) mView.findViewById(R.id.picture);

        share.setVisibility(View.INVISIBLE);

        details.setVisibility(View.INVISIBLE);
        details_dialog = new Dialog(getActivity());

        details_dialog.setContentView(R.layout.facebook_share);
        details_dialog.setTitle("Link to Profile");
        details_txt = (TextView) details_dialog.findViewById(R.id.details);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                details_dialog.show();
            }
        });

        if (AccessToken.getCurrentAccessToken() != null) {
            fromFacebookProfile();
            share.setVisibility(View.VISIBLE);
            details.setVisibility(View.VISIBLE);

        }
        /** Anonymous class setOnClickListener for main "login into Facebook" listener. This is
         * where login token is first requested and credentials are checked.
         * */
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AccessToken.getCurrentAccessToken() != null) {
                    share.setVisibility(View.INVISIBLE);
                    details.setVisibility(View.INVISIBLE);
                    profile.setProfileId(null);

                }}
        });

        /** Anonymous class setOnClickListener for Share button
         *
         * When the button is clicked, the recipe link is auto-added (with a picture from the webview
         * courtesy of Facebook API. There is a box next to it where you can add a comment if you want.
         * */
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle("Common Pantry")
                            .setContentDescription(
                                    "Recipes by ingredients... Get Yo' Food Here")
                            .setContentUrl(Uri.parse(url))
                            .build();

                    shareDialog.show(linkContent);
                }


            }
        });
        /** This anonymous class checks that an access token has been received. If so,
         * the share and details buttons will be displayed.
         * */
        login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                if (AccessToken.getCurrentAccessToken() != null) {
                    fromFacebookProfile();
                    share.setVisibility(View.VISIBLE);
                    details.setVisibility(View.VISIBLE);
                }

            }


            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException exception) {
            }
        });
        return mView;
    }


    /** This method uses GraphRequest to get info from the person's Facebook profile.
     * Currently, a link to the profile is also that's used - but the full JSON of info (name,
     * email, birthday, location etc is returned.)
     * */
    public void fromFacebookProfile() {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {

                JSONObject json = response.getJSONObject();
                try {
                    if (json != null) {
                        String text = "Profile link :</b> " + json.getString("link");
                        details_txt.setText(Html.fromHtml(text));
                        profile.setProfileId(json.getString("id"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }

    public void setUrl(String urlWeb)
    {
        url = urlWeb;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}


