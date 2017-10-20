# test-task

Check out API documentation here on google developer site following the step on this link for sign-in
Step-1 go to this link https://developers.google.com/oauthplayground/ click Task API from the list. select https//:wwww.googleapis.com/auth/tasks
then Click on authorised API `blue` button, it will prompt you to login with your google account select the account you want access
token with.
click `Allow` button

Then you should be seing a code in against this box `Authorization code` start with something like `y.....`

Click on Exchange token button. Then you should be able to see 200 response on the `Request / Response` section 
you will see the values against these 

{
  "access_token": "y.............", 
  "token_type": "Bearer", 
  "expires_in": 3600, 
  "refresh_token": ""
}


copy the access_token and put it in `stepDef` file in the test package 

in order to run test use RunSuite class.
