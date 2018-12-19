App for showing ShutterStock images

This project includes
 - MVP architecture
 - Dagger DI in application and testing scopes
 - RxJava, Retrofit, Fresco and basic libraries
 - BDD and TDD testing frameworks and already prepared test
 - Activity with recycler and adapter ready to go

API communications configuration
 - Headers with several types of security
 - **Implemented complexity for login (ShutterStock official page) and retrieve token api for a whole session
 
 Points to consider
  - Along all this project development, I've realised that is necessary to purchase a plan from ShutterStock, hence,
    I have to work with the web page token, which can be retrieved in "https://developers.shutterstock.com/images/apis/get/images/search"
  - I have implemented all the token access procedure, with corresponding web services, but the retrieved token value
    was not correct for the app, since it is a token which belongs to a "free user", and it doesn't give you access to any API
  - You can see all this implementation in LogicPresenter and also in LogicActivity