In this project I've used: 
- Native Header (Toolbar)
	- JS Injection in left button event
- Native Tabbar (BottomNavigationView)
- Single Activity with viewbinding
- Webview with progress dialog that will be closed when page is loaded

- Since this is a small project with a single activity, I'm not using ViewModels with MVVM, as I normally do, just to keep it simple.

- My approach was to open the website in a browser and use "Inspect" tool and get the html of the hamburger and close buttons:
   - <button class="navbar-toggler svg-svg-20-hamburger svg-image d-lg-none" type="button" aria-controls="sg-navbar-collapse" aria-expanded="false" aria-label="Toggle navigation">
   </button>

   - <button role="button" class="close-button" aria-label="Close Menu">
   <span aria-hidden="true"></span>
   </button>

After that I've created a method ("menuHandler") that executes a javascript function on a button according the state, open or closed, and then load the event on my webview when the Hamburger menu is pressed on the app toolbar. In order to get the buttons on the website, the javascript function will search the DOM by class name (navbar-toggler to open, and close-button to close).


Thoughts
- I've used webviews in other projects, but this kind of task, where I need to inject code into the webview is something that I've done only a couple of times (Once I've done the oposite of this task, i.e., click a button on webview and trigger a method in my Activity through a JavascriptInterface). Nevertheless, this was a great challenge :) and it took me less than 4 hours to do it.