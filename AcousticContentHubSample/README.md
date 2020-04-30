# Acoustic Content Sample App

## How to start

1. Please make sure Content Hub contains provided test data. Test data can be found in `Source Test Data/source_v3.zip`.

    > WCHtools (with instructions) required for pushing data model to the Content Hub can be found here: [wchtool docs](https://github.com/acoustic-content-samples/wchtools-cli)

1. Make sure you have access to repo `<path-to-repo>`

1. `git clone <link to repo>`

1. Checkout **master** branch to get the latest features.

1. Import project to **Android Studio**

1. Open `/com/acoustic/contenthub/sample/Constants.java` file and update next fields with your Content Hub information:
    - `CONTENT_HUB_ID` - with your `tenantId`;
    - `BASE_URL` - with your domain name.

1. Build and run the app.
