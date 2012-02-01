Android Hybrid Demo
===================

**WARNING:** There is a known bug in the emulator that causes apps to crash when 
a native method is called from inside the DOM. Please test on a real device.

This projects is a simple prototype to show how to communicate between Android and 
Javascript with a `android.webkit.WebView`.

The key parts are `addJavascriptInterface` which creates proxy object in the `WebView`'s `window` that binds it to the given object.

So calling this method in an Android app:

	webview.addJavascriptInterface( anObject, "hybrid_interface" );

Will provide access to the instance `anObject` via the variable `hybrid_interface` like so:

	<script type="text/javascript">
	// someMethod being a method defined in the native app
	hybrid_interface.someMethod(); 
	</script>

