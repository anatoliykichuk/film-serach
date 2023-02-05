package ru.geekbrains.filmserach.ui.pages.maps

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import ru.geekbrains.filmserach.R
import ru.geekbrains.filmserach.data.LOCATION_NAME
import ru.geekbrains.filmserach.databinding.FragmentMapsBinding
import java.io.IOException

class MapsFragment : Fragment() {

    private var _binding: FragmentMapsBinding? = null
    private val binding
        get() = _binding!!

    private var _locationName: String? = null
    private val locationName
        get() = _locationName!!

    private lateinit var map: GoogleMap

    private val callback = OnMapReadyCallback { googleMap ->
        map = googleMap

        val defaultLocation = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(defaultLocation))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(defaultLocation))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        _locationName = arguments?.getString(LOCATION_NAME)

        Thread {
            searchByLocationName(view)
        }.start()
    }

    private fun searchByLocationName(view: View) {
        val address = getAddress()

        if (address == null) {
            return
        }

        val location = LatLng(address.latitude, address.longitude)
        val zoom = 15f

        view.post {
            setMarker(location)

            map.moveCamera(
                CameraUpdateFactory.newLatLngZoom(location, zoom)
            )
        }
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