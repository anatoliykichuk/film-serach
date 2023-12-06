package ru.geekbrains.filmserach.ui.pages.maps

import android.location.Address
import android.location.Geocoder
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import ru.geekbrains.filmserach.R
import ru.geekbrains.filmserach.databinding.FragmentMapsBinding
import ru.geekbrains.filmserach.ui.base.BaseFragment
import ru.geekbrains.filmserach.ui.pages.film.LOCATION_NAME
import java.io.IOException

class MapsFragment : BaseFragment<FragmentMapsBinding>() {

    override fun getViewBinding() = FragmentMapsBinding.inflate(layoutInflater)

    private var _locationName: String? = null
    private val locationName
        get() = _locationName!!

    private lateinit var map: GoogleMap

    override fun initView() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private val callback = OnMapReadyCallback { googleMap ->
        map = googleMap

        val defaultLocation = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(defaultLocation))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(defaultLocation))
    }

    override fun initData() {
        _locationName = arguments?.getString(LOCATION_NAME)

        Thread {
            view?.let {
                getAddress()?.let { address ->
                    val location = LatLng(address.latitude, address.longitude)
                    val zoom = 15f

                    it.post {
                        setMarker(location)

                        map.moveCamera(
                            CameraUpdateFactory.newLatLngZoom(location, zoom)
                        )
                    }
                }
            }
        }.start()
    }

    private fun getAddress(): Address? {
        val geoCoder = context?.let { Geocoder(it) }

        try {
            val addresses = geoCoder?.getFromLocationName(locationName, 1)

            if (addresses?.isNotEmpty() == true) {
                return addresses!!.first()

            }

        } catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }

    private fun setMarker(location: LatLng): Marker? {
        val mapMarkerIcon = R.drawable.map_marker_48

        return map.addMarker(
            MarkerOptions()
                .position(location)
                .title(locationName)
                .icon(BitmapDescriptorFactory.fromResource(mapMarkerIcon))
        )
    }
}