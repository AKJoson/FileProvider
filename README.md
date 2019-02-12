###Android7.0以上，拍照时需要使用FileProvider

### FileProvider
* 提供Uri使用 content://Uri 来代替传统的file:///Uri
### 如何使用 FileProvider 
**1. Defining a FileProvider,在AndroidManifest.xml中进行定义**

	<manifest>
		...
		<application>
			...
			<provider
				android:name="android.support.v4.content.FileProvider"
				android:authorities="com.yp.www.fileprovider"
				android:exported="false"
				android:grantUriPermission="true"
				<meta-data>
					android:name="android:support.FILE_PROVIDER_PATHS"
					android:resource="@xml/file_paths"/>
			</provider>
		</application>

	<manifest>

**2.在res下面，新建一个xml的文件夹，然后新建一个file_paths.xml的文件**

	<path xmlns:android="http://schemas.android.com/apk/res/android">
		// 代表内置 Context.getFilesDir()  --->  files/   下面
		<files-path name="my_images" path="imags/" />

		// 代表内置存储器下面的 cache 位置 ---->可以用getCacheDir()来获取
	 	<cache-path name="name" path="path">

		//代表root下的外置存储器 可以使用Environment.getExternalStorageDirectory()
		<external-path name = "name" path="path"/>

		//root下的外置存储器，代表Context.getExternalFilesDir(String) Context/getExternalFilesDir(null)
		<external-files-path name="name" path="path" />
	
		//root的外置存储器的cache路径 ----> Context.getExternalCacheDir()
		<external-cache-path name= "name" path="path"/>

		//Context.getExternalMediaDirs()   Note:这个调用需要API大于21才可以
		<external-media-path name="name" path= "path"/>