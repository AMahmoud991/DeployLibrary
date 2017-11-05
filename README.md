# DeployLibrary
  Deploy is a Proof of concept  Networking library using AsynkTaskLaoders.
# Download
 it wil be avalaible soon on maven
# Usage
 Step 1 requried

 In the Application Class 

            new DeployBuilder().build();

 #Load Image

 First Way to Load image use  DeployImageView Widget

             DeployImageView userImage;
         userImage.loadImage(url, activity);
        #Or
ImageView userImage;
Request<Bitmap> request = new RequestBuilder()
                .setUrl(url)
                .setMethodType(MethodType.GET)
                .setDefaultImageParser()
                .setLoaderManager(activity)
                .Build();
        request.registerObserver(new RequestStateObserver<Bitmap>() {
            @Override
            public void onSuccess(Bitmap data) {

                userImage.setImageBitmap(data);
            }

            @Override
            public void onError(DeployError error) {


            }
        });
        Deploy.getInstance().getDeployQueue().addRequest(request);
        
#Load Request
 Request<JSONArray> request = new RequestBuilder().setUrl(url).setMethodType(MethodType.GET).setDefaultJsonArrayParser().setLoaderManager(activity).Build();
        request.registerObserver(new RequestStateObserver<JSONArray>() {
            @Override
            public void onSuccess(JSONArray data) {
                presenterCallback.onFeedDataReterived(parse(data));
            }

            @Override
            public void onError(DeployError error) {

            }
        });
        Deploy.getInstance().getDeployQueue().addRequest(request);       
 #Make Custom Request
 you can Implment BasicPArser and send it to the request 
 
        
        

   
