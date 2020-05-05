# sample-traveler-android-app

This is a native Android mobile app for a fictitious "Traveler" company, using content and assets from Acoustic Content. This is a companion sample to [sample-react-traveler-website](https://github.com/acoustic-content-samples/sample-react-traveler-website), and it uses the content and assets from that sample. A third sample, [sample-traveler-ios-app](https://github.com/acoustic-content-samples/sample-traveler-ios-app), shows the same content in a native iOS app.

Together these samples illustrate the value of a headless CMS: all the content and assets are managed in one place for all three channels. Whenever a business user creates and publishes new content, it's immediately available in all three channels.

Here are a few of the screens from the sample:

<img src="./docs/images/android1.png" height="400"></img>
<img src="./docs/images/android3.png" height="400"></img>
<img src="./docs/images/android4.png" height="400"></img>

## Installing the sample

### Prerequisites

In order to run this sample, the following is required:

- An Acoustic Content tenant.

- [wchtools-cli](https://github.com/ibm-wch/wchtools-cli) v2.3.4 or above.

- A recent Node 10.x LTS version of Node.

- The [Traveler Website Sample](https://github.com/acoustic-content-samples/sample-react-traveler-website) must be installed to your Acoustic Content tenant. That sample includes all the content and assets used by this sample app.

- Java SDK v13.0.1 or above must be installed. It is available from here: https://www.oracle.com/technetwork/java/javase/downloads/index.html.

- Android Studio v. 3.5.3 or above must be installed. It is available from here: https://developer.android.com/studio/index.html%23downloads. The installation guide is here: https://developer.android.com/studio/install.

- Android SDK Build - Tools version 29.0.2 and Android SDK Platform version 29 (Android Q) must be installed using ​AndroidStudio SDK manager.

### Download and run this sample project

1. Download or clone this repository.

2. Import the project into Android Studio.

3. Set the tenant information as follows:
- Open the file /com/acoustic/contenthub/sample/Constants.java
- Update the following fields for your tenant:
	- CONTENT_HUB_ID - with your tenant ID
	- BASE_URL - with your hostname

The hostname can be extracted from the delivery URL of your tenant, (e.g. https:/hostname/content_hub_id/).

All hub information containing domain, IDs and URLs for the hub can be found inside Acoustic Content (_Sidebar > About > Hub Information_):

- Log in to Acoustic Content and open the 'About' tab.
- Click the 'Hub information' option.
- Find a delivery URL and hub ID on 'Hub Information' pop-up window.

### Resources

Acoustic Content developer documentation: https://developer.goacoustic.com/acoustic-content/docs

Acoustic Content API reference documentation: https://developer.goacoustic.com/acoustic-content/reference

Acoustic Content Samples Gallery: https://content-samples.goacoustic.com/




