# DeployLibrary
  Deploy is a Proof of concept  Networking library using AsynkTaskLaoders.
# Download
 it wil be avalaible soon on maven
# Usage

# Init
 In the Application Class 

            new DeployBuilder().build();

# Custom Cache

             new DeployBuilder().requestCache(new BaseCache(cachLimit)).build();

 #Load Image

 First Way to Load image use  DeployImageView Widget

             DeployImageView userImage;
             userImage.loadImage(url, activity);

  # OR

  You can use normal get Request and to retrive bitmap and assgin it to image view

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


# Load Request
 The Library Have 3 template parsers JsonArray Parser, JsonObject Parser and Bitmap Parser

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

 # Make Custom Request

 you can Implement BasicParser and send it to the request

    public class PdfParser implements BaseParser<PDF> {
        @Override
        public Bitmap  parse(InputStream inputStream) {
    // Parser inputstream into pdf
        }
    }
        Request<PDF> request = new RequestBuilder().setUrl(url).setMethodType(MethodType.GET).setCustomParser(new PdfParser()).setLoaderManager(activity).Build();



 # Cancel Request

  You have the option to cancel Request by calling the cancel method in Deploy Queue

          Deploy.getInstance().getDeployQueue().cancelRequest(request);

   --------------------------------------------------------------------------------------
    # Sample App

     The repository include  asample app to demonstrate the basic functions  of Deploy Libs

     and Some Material Component
            *   FloatingActionbutton
            *   Coordinator Layout
            *   Ripple Effect
            *   SnackBar
            *   CardView
            *   RecyclerView
     ---------------------------------------------------------------------------------------

     # FutureWork

        * Add Support for Post Request
        * Add UnitTesting



        
        

   
