# GPS-Info
My latest project to try out current architecture and android components.
I have only spent a few hours on it since getting back from Canada at the end of September, so is still an active project that I’m working on alongside the job search.

### Use Case
My phone sometimes does this weird thing where it stops receiving GPS updates and can't connect to any satelittes. I thought I'd create a project to help diagnose the problem and perhaps fix it by resetting... something? Up until now the only fix I have is turning my phone on/off, but maybe there's a way to solve this programatically.

### Key Features
* **FusedLocationProvider** - The Android GPS apis were foreign to me, but this looks to be the most lightweight way to integrate location data (if you don't need to do anything to specialized
* **Dagger2** - For dependecny injection
* **Lifecycle-Aware Components** - Such as ViewModel, to structure the app in a contemporary way according to the offical docs
* **LiveData & Databinding** - UI is automatically informed when data changes
