package com.colinruan.ui_utils.location_utils

import com.google.android.gms.common.api.Status
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener

fun makeLocationCallback(onLocationAvailability : (locationAvailability : LocationAvailability?) -> Unit = {},
                         onLocationResult : (locationResult : LocationResult?) -> Unit = {}) : LocationCallback {
    return object : LocationCallback() {
        override fun onLocationAvailability(p0: LocationAvailability?) {
            super.onLocationAvailability(p0)
            onLocationAvailability(p0)
        }

        override fun onLocationResult(p0: LocationResult?) {
            super.onLocationResult(p0)
            onLocationResult(p0)
        }
    }
}

fun AutocompleteSupportFragment.makePlaceListener(onPlaceSelected : (place : Place) -> Unit = {}, onError : (status : Status) -> Unit = {}) : AutocompleteSupportFragment {
    this.setOnPlaceSelectedListener(object : PlaceSelectionListener {
        override fun onPlaceSelected(p0: Place) {
            onPlaceSelected(p0)
        }

        override fun onError(p0: Status) {
            onError(p0)
        }

    })
    return this
}


