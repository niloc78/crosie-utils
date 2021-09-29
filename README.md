### crosie-utils is a collection of Kotlin functions and extension functions for common Android UI listeners to make implementation easier by removing boilerplate code. 

## Why? 

We all know how annoying it is to add original Java UI listeners by overriding and implementing all required methods even if we don't need them. The resulting code takes up space and is an eyesore to look at. Take this code for example: 

```kotlin
set.addListener(object : Transition.TransitionListener {
            override fun onTransitionStart(transition: Transition) {
            }

            override fun onTransitionEnd(transition: Transition) {
                doStuff()
            }

            override fun onTransitionCancel(transition: Transition) {
            }

            override fun onTransitionPause(transition: Transition) {
            }

            override fun onTransitionResume(transition: Transition) {
            }

        })
```
In cases where we don't need to use all the overridden functions, it would be much easier to not include them in the first place. By making shortcut functions and extension functions we end up with code that looks much cleaner: 

```kotlin
set.makeListener(onEnd = { doStuff() })
```

# Try it out:

In your project level gradle file, add this into your repositories:
```gradle
maven {url 'https://jitpack.io'}
```
In your app level gradle file, add this into your dependencies (latest version: 1.0.0):
```gradle
implementation 'com.github.niloc78:crosie-utils:$version'
```
# Features

### Animation Utils 

| Functions                                                      | Description                                                                                                     | Params                                                                                                                                                     |
| --------------------------------                               | ------------------------------------------------------------                                                    | ----------------                                                                                                                                           |
| `ViewPropertyAnimator.makeListener`                            | An extension function for ViewPropertyAnimator that replaces setListener() with default parameters              | `onSlide` `onOpened` `onClosed` `onStateChanged`                                                                                                           |
| `View.hide/View.show`                                          | A simple extension function for View that allows you hide or show it with a simple fade in/out animation        | `animationDuration` `hiddenOpacity` `visibleOpacity`                                                                                                       |
| `BottomSheetBehavior<ConstraintLayout>.makeBottomSheetCallback`| An extension function for BottomSheetBehavior that replaces addBottomSheetCallback() with default parameters    | `onStateChanged` `onSlide`                                                                                                                                 |
| `makeItemTouchCallback`                                        | A function that replaces ItemTouchCallback.SimpleCallback with added features and default functionality         | `dragDirs` `swipeDirs` `longPressedEnabled` `onMove` `getMovementFlags` `doNotAllowSwipeIf` `doNotAllowDragIf` `onSwiped` `onClearView`                    |
| `TextView.setSimpleValueAnimator`                              | An extension function for TextView that allows you to animate value changes specifically for Int and Float      | `valueType` `toValue` `animationDuration` `onAnimating`                                                                                                    |
| `View.makeSimpleConstraintAnimation`                           | An extension function that sets up TransitionManager and ConstraintSet for animations within a ConstraintLayout | `transitionSet` `invokeAfterApply` `setUpConstraintSet`                                                                                                    |
| `DrawerLayout.makeListener`                                    | An extension function for DrawerLayout that replaces addDrawerListener() with default functionalities           | `onSlide` `onOpened` `onClosed` `onStateChanged`                                                                                                           |          

### Location Utils

| Functions                                                      | Description                                                                                                                  | Params                                                                                                                                        |
| --------------------------------                               | ------------------------------------------------------------                                                                 | ----------------                                                                                                                              |
| `makeLocationCallback`                                         | An  function that returns a LocationCallback with default functionalities                                                    | `onLocationAvailability` `onLocationResult`                                                                                                   |
| `AutocompleteSupportFragment.makePlaceListener`                | An extension function for AutocompleteSupportFragment that replaces setPlaceListener() with default functionalities          | `onPlaceSelected` `onError`                                                                                                                   |

### General Utils

| Functions                                                      | Description                                                                                                                                                      | Params                                                                                                    |
| --------------------------------                               | ------------------------------------------------------------                                                                                                     | ----------------                                                                                          |
| `makeDiffUtilListener`                                         | An simple function that replaces DiffUtil.calculateDiff with default functionalities                                                                             | `getOldListSize` `getNewListSize` `areItemsTheSame` `areContentsTheSame`                                  |
| `RecyclerView.setUp`                                           | A simple extension function for RecyclerView that allows you to set up your adapter and layout manager in a single method                                        | `adapter` `layoutManager`                                                                                 |
| `View.setOnLongClickListener`                                  | An extension function that replaces the original View.setOnLongClickListener with added features by allowing you to set a custom time for the long click         | `overLapClickAndLongClick` `longClickDuration` `onLongClick` `onPressing` `onPressCanceled`               |


