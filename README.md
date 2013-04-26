logdroid
========

Simplified logging component for Android developers

No longer you have to specify the "TAG" argument of android.util.Log, potentially saving thousands of keystrokes

The name of the class that has the log command is retrieved using introspection

If performance is your priority (for some reason you need logging on in a tight loop), you can always resort to original API.

All you need to do is to import the jar file from the library (or include the library project) and define the call the LogPlug class init at the start of the application lifecycle (sample implementation is provided in the Application class that is part of the sample usage project)
