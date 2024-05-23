package com.example.travelai.ui.fragments.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.travelai.R
import com.example.travelai.databinding.FragmentMapBinding
import com.google.android.gms.common.api.Status
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener


class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentMapBinding
    private lateinit var autocompleteFragment: AutocompleteSupportFragment
    private var googleMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Places.initialize(
            requireContext().applicationContext,
            getString(R.string.google_map_api_key)
        )
        val place = Places.createClient(requireContext())


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(inflater, container, false)

        val mapFragment =
            childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        autocompleteFragment =
            childFragmentManager.findFragmentById(R.id.autoComplete) as AutocompleteSupportFragment

        autocompleteFragment.setPlaceFields(
            listOf(
                Place.Field.ID,
                Place.Field.ADDRESS,
                Place.Field.LAT_LNG
            )
        )
        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onError(p0: Status) {
                Toast.makeText(requireContext(), "Not found location", Toast.LENGTH_SHORT).show()
                Log.e("TAG", "Error fetching places: $p0")
            }

            override fun onPlaceSelected(p0: Place) {
                val add = p0.address
                val id = p0.id
                val latLng = p0.latLng

                if (latLng != null) {

                    val marker = addMarker(latLng,"$add")
                    marker?.let { mark ->
                        mark.title = "$add"
                        mark.snippet="$id"
                    }
                    setZoom(latLng)
                }
            }
        })
        return binding.root
    }

    private fun setZoom(latLng: LatLng) {
        val newLatLngZoom = CameraUpdateFactory.newLatLngZoom(latLng, 12f)
        googleMap?.animateCamera(newLatLngZoom)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        googleMap?.setOnMapClickListener {
            googleMap?.clear()
            addMarker(it,"Marker")
        }

        googleMap?.setOnMapLongClickListener {
            googleMap?.clear()
            addCustomMarker(it,"Marker")
        }


    }

    private fun addCustomMarker(position: LatLng, title:String): Marker? {
        val marker = googleMap?.addMarker(
            MarkerOptions()
                .position(position)
                .title(title)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.flag))
        )
        return marker
    }

    private fun addMarker(position: LatLng,title:String): Marker?{
        val marker = googleMap?.addMarker(
            MarkerOptions()
                .position(position)
                .title(title)
        )
        return marker
    }

}