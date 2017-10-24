**Architecture**

This sample app adapts the MVP architecture, very used in Android development, in which we separate the app in three layers:

*Model* - Referring to the data
*View* - Referring on what the user sees
*Presenter* - Referring on how to show the data to the user through the view

On top of that, I've used **Dagger2** as dependency injection system, **RxAndroid** for asynchronous operations, on top of **Retrofit2** for the network calls. 
The image calls are managed by **Glide**, and **Butterknife** is used to bind the views from the XML to their corresponding containers saving us from a lot
of boilerplate code. For the list I've used the RecyclerView component, recommended by Google, as it makes you follow the 
ViewHolder pattern for binding the data.

**Improvement points**
- Find some API which gives you the flag matching a currency, for not saving the images in the project (structure already created)
- Create more tests as the app grows.
- Investigate the Android Architecture Components (Room, Lifecycle)
- Consider Kotlin migration
- Make it Locale-aware (e.g in Spain, we separate the decimal part with a comma instead of a dot)