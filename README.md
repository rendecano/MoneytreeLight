# Moneytree Light
An app to demonstrate MVVM + Android Architecture components

# Screenshot
![Alt text](/screenshots/account_list.png?raw=true "Screenshot")
![Alt text](/screenshots/transaction_list.png?raw=true "Screenshot")

# Architecture 
MVVM 

The app has been divided into two features:

`accounts` - contains the classes needed to display the list of accounts
`transactions` - contains the classes needed to display the list of transactions

For each of the feature, I have created 3 layers to separate the `presentation`, `domain / business logic` and lastly the `data`.

- Presentation layer consists of the `UI/View` and its corresponding `ViewModel`. It uses the `Navigation Architecture component` to switch between the accounts list and transactions list.
- Domain layer consists of the logic to handle the data fetching, composition of data before it is displayed to the user. Mostly contains `Kotlin `codes and doesn't have any Android framework classes.
- Data layer consists of the repository implementation which fetches the information from both local and remote data sources.

# Language
Kotlin

# Tech/Tools/Libs Used
- Kotlin Coroutines
- DataBinding
- ViewModel
- LiveData
- Gson
- Lottie
- Material Components 
 
# Android versions
- Android 5.1 above

# Stories implemented
- Data story
- User story 1
- User story 2

# Further Improvements
- A lot of the stories were not implemented due to time constraints, and also for User 1 and 2, I believe I was not able to complete the stories 100% :(
- Local caching of data fetched could have been done using Room
- UI tests. I was only able to write unit tests for the domain and data layer.
- Fix the accessibility to help the visually impaired individuals.
- UI/UX design improvements, such formatting of dates and currencies.
- A bit hacky to just save the provided `json` files into the `assets` folder and reading/parsing it everytime each screen is loaded.

# Challenges encountered
- Lack of time to do all the stories
- I was not able to prepare as much as I want to due to external factors such as existing work and personal errands.

