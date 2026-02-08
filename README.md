# ApkSizeExplorer

ApkSizeExplorer is a tool allowing to view the size of the APK file and for each application 
installed on a device. 

The app was created based on an assignment that can be found [here](./CHALLENGE.md).

## Improvements
- Write unit tests for the ViewModel and the repository
- Add instrumentation tests for the UI

## Development
### Main dependencies
- Kotlin
- Kotlin Coroutines
- Jetpack Compose
- AndroidX Paging
- Hilt
- Coil
- Timber

### Architecture
The app uses the MVI architecture pattern for the presentation layer and the Google recommended 
architecture for the overall app structure.

### Versioning
The app uses semantic versioning. 

### Signing a release APK
The app needs the pipeline to set the following environment variables:

```
KEYSTORE_FILE - the path to the keystore file
KEYSTORE_PASSWORD - the password for the keystore
KEY_ALIAS - the alias of the key to use for signing
KEY_PASSWORD - the password for the key
SIGNING_ENABLED - set to "true" to enable signing
```


